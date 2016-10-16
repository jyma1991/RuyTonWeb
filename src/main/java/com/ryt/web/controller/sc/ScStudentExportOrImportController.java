package com.ryt.web.controller.sc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.WebContext;
import org.durcframework.core.controller.ExportController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentSch;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScStudentExportOrImportController extends ExportController<ScStudent, ScStudentService> {

	private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";
	private static final String F_HEADER_ARGU1 = "Content-Disposition";
	private static final String F_HEADER_ARGU2 = "attachment;filename=";

	private static final String templatePath = "template/";
	private static final String exportTemplateName = "student_export.xls";
	private static final String importTemplateName = "importStudentTemplate.xlsx";
	@Autowired
	UserService userService;
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	ScClassService scClassService;

	@Override
	public String getTemplateFilePath() {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		// 获取模板位置
		return path + templatePath + exportTemplateName;
	}

	private String getImportTemplateFilePath() {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		// 获取模板位置
		return path + templatePath + importTemplateName;
	}

	@RequestMapping("/exportStudent.do")
	public void exportStudent(ScStudentSch entity, HttpServletResponse response) {
		DefaultGridResult resultGrid = (DefaultGridResult) this.query(entity);
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			int studentId = ((ScStudent) resultGrid.getRows().get(i)).getStudentId();
			User user = userService.get(studentId);
			((ScStudent) resultGrid.getRows().get(i)).setUser(user);
		}
		doExport(resultGrid.getRows(), response);
	}

	@RequestMapping("/exportStudentTemplate.do")
	public void exportStudentTemplate(HttpServletResponse response) throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setHeader(F_HEADER_ARGU1, F_HEADER_ARGU2 + importTemplateName);
		OutputStream outputStream = response.getOutputStream();
		InputStream data = new FileInputStream(new File(getImportTemplateFilePath()));
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = data.read(buffer, 0, 8192)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.close();
		data.close();
	}

	@RequestMapping("/importStudent.do")
	public @ResponseBody MessageResult importStudent(SysUpload excelFile) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		try {
			saveStudentsFromExcel(path + excelFile.getFileFullPath(), excelFile.getClassId());
		} catch (Exception e) {
			return error(e.getMessage());
		}
		return success();
	}

	private void saveStudentsFromExcel(String filePath, Integer classId) throws Exception {
		File excelFile = new File(filePath);
		if (!excelFile.exists())
			throw new Exception("未能读取文件，请联系管理员！");
		else {
			// 获取班级设置班主任
			ScClass scClass = scClassService.get(classId);
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
			Sheet mySheet = wb.getSheetAt(0);
			if (mySheet == null) {
				throw new Exception("未找到文件有效数据！");
			}

			Iterator<Row> rowIter = mySheet.rowIterator();
			rowIter.next();// 从第二行开始
			while (rowIter.hasNext()) {
				Row row = rowIter.next();
				if (row.getCell(1) == null) {
					continue;
				}

				boolean noParentsInfo = false;//是否有家长信息
				ScStudent scStudent = new ScStudent();
				User userStudent = new User();
				UserSch userSch = new UserSch();
				User userParent = new User();
				ScStudentParents scStudentParents = new ScStudentParents();

				// 校验学生是否存在
				userSch.setTrueNameSch(num2String(row.getCell(1)));
				userSch.setFixedPhoneSch(num2String(row.getCell(6)));
				if (userService.checkUserExist(userSch)) {
					continue;
				}

				// 学生信息到user
				userStudent.setTrueName(num2String(row.getCell(1)));
				userStudent.setSex("男".equals(num2String(row.getCell(2))) ? 1 : 0);
				userStudent.setBirthday(row.getCell(3).getDateCellValue());
				userStudent.setFixedPhone(num2String(row.getCell(6)));
				userStudent.setAddressDetail(num2String(row.getCell(7)));
				//userStudent.setRemark(num2String(row.getCell(8)));
				userService.save(userStudent);

				if(StringUtils.isEmpty(num2String(row.getCell(4))) && StringUtils.isEmpty(num2String(row.getCell(6)))){
					noParentsInfo = true;
				}
				if(!noParentsInfo){
					// 校验家长是否存在
					userSch = new UserSch();
					userSch.setTrueNameSch(num2String(row.getCell(4)));
					userSch.setMobilePhoneSch(num2String(row.getCell(6)));
					if (userService.checkUserExist(userSch)) {
						userParent = userService.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(userSch)).get(0);
					} else {
						// 家长信息到user
						userParent.setTrueName(num2String(row.getCell(4)));
						userParent.setNickName(num2String(row.getCell(4)));
						userParent.setMobilePhone(num2String(row.getCell(6)));
						userParent.setUserName(num2String(row.getCell(6)));
						userParent.setRemark(num2String(row.getCell(4)) + ":" + num2String(row.getCell(5)) + ";"
								+ num2String(row.getCell(6)) + ":" + num2String(row.getCell(7)));
						userParent.setAddressDetail(num2String(row.getCell(7)));
						userParent.setUserPwd(
								"1000:a74d48c86b6f3ed426993bc615f90960554f79a355df9145:1c98e04dff5d044b1e561eb1be36fec79225259ea27a7f8b");
						userParent.setRoleProperty(1);
						userService.save(userParent);
					}
				}
				// 学生信息到student
				scStudent.setStudentId(userStudent.getId());
				scStudent.setStudentCode(num2String(row.getCell(0)));
				scStudent.setSchoolId(UserContext.getInstance().getUser().getId());
				scStudent.setClassId(classId);
				if (scClass != null) // 设置班主任
					scStudent.setTeacherId(scClass.getManagerId());
				scStudentService.save(scStudent);

				if(!noParentsInfo){
					scStudentParents.setParentId(userParent.getId());
					scStudentParents.setStudentId(userStudent.getId());
					scStudentParents.setSchoolId(scClass.getSchoolId());
					scStudentParents.setRelatedTypeId(72);
					scStudentParentsService.save(scStudentParents);
				}
			}
		}
	}

	// 字符转换
	private String num2String(Cell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BLANK || hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
		}
		return hssfCell.getStringCellValue();
	}
}
