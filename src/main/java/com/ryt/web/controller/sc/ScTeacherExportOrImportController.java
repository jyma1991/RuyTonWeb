package com.ryt.web.controller.sc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.durcframework.core.MessageResult;
import org.durcframework.core.WebContext;
import org.durcframework.core.controller.ExportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.user.UserService;



@Controller
public class ScTeacherExportOrImportController extends ExportController<ScTeacher, ScTeacherService> {

	private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";
	private static final String F_HEADER_ARGU1 = "Content-Disposition";
	private static final String F_HEADER_ARGU2 = "attachment;filename=";
	
	private static final String templatePath = "template/";
	private static final String importTemplateName = "importTeacherTemplate.xlsx";

	@Autowired
	UserService userService;
	@Autowired
	ScTeacherService scTeacherService;
	@Autowired
	ScSchoolService scSchoolService;
	

	@Override
	public String getTemplateFilePath() {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		// 获取模板位置
		return path + templatePath+importTemplateName;
	}

	@RequestMapping("/exportTeacherTemplate.do")
	public void exportTeacherTemplate(HttpServletResponse response) throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setHeader(F_HEADER_ARGU1, F_HEADER_ARGU2 + importTemplateName);
		OutputStream outputStream =response.getOutputStream();
		InputStream data = new FileInputStream(new File(getTemplateFilePath()));
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = data.read(buffer, 0, 8192)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.close();
		data.close();
	}
	
	@RequestMapping("/importTeacher.do")
	public @ResponseBody MessageResult importTeacher(SysUpload excelFile) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		try {
			saveTeacherFromExcel(path + excelFile.getFileFullPath());
		} catch (Exception e) {
			return error(e.getMessage());
		}
		return success();
	}

	private void saveTeacherFromExcel(String filePath) throws Exception {
		File excelFile = new File(filePath);
		if (!excelFile.exists())
			throw new Exception("未能读取文件，请联系管理员！");
		else {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
				Sheet mySheet = wb.getSheetAt(0);
				if (mySheet == null) {
					throw new Exception("未找到文件有效数据！");
				}
				
				Iterator<Row> rowIter = mySheet.rowIterator();
				rowIter.next();//从第二行开始
				while(rowIter.hasNext()) {
					Row row = rowIter.next();
					if(row.getCell(0)==null||row.getCell(3)==null){
						continue;
					}
					
					User user = new User();
					UserSch userSch = new UserSch();
					ScTeacher scTeacher = new ScTeacher();
					
					//校验是否已存在
					userSch.setTrueNameSch(num2String(row.getCell(0)));
					userSch.setMobilePhoneSch(num2String(row.getCell(3)));
					if(userService.checkUserExist(userSch)){
						continue;
					}
					
					// 教师信息到user
					user.setTrueName(num2String(row.getCell(0)));
					user.setMobilePhone(num2String(row.getCell(3)));
					user.setRemark(num2String(row.getCell(1)));
					user.setAddressDetail(num2String(row.getCell(4)));
					user.setUserPwd("1000:a74d48c86b6f3ed426993bc615f90960554f79a355df9145:1c98e04dff5d044b1e561eb1be36fec79225259ea27a7f8b");
					user.setRoleProperty(2);
					userService.save(user);
					
					
					scTeacher.setTeacherId(user.getId());
					
					ScSchool scSchool=scSchoolService.getSchoolUserBySchoolId();
					scTeacher.setSchoolId(scSchool.getSchoolId());					
					scTeacher.setAgentId(scSchool.getAgentId());
					scTeacherService.save(scTeacher);
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
