package com.ryt.web.controller.sc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Var;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.WebContext;
import org.durcframework.core.controller.ExportController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentParentsSch;
import com.ryt.web.entity.sc.ScStudentSch;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScStudentParentsExportController extends ExportController<ScStudentParents, ScStudentParentsService>{
	
	private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";
	private static final String F_HEADER_ARGU1 = "Content-Disposition";
	private static final String F_HEADER_ARGU2 = "attachment;filename=";

	private static final String templatePath = "template/";
	private static final String exportTemplateName = "card_export.xls";
	private static final String importTemplateName = "importCardTemplate.xlsx";
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

	@RequestMapping("/exportCard.do")
	public void exportStudent(ScStudentParentsSch entity, HttpServletResponse response) {
		DefaultGridResult resultGrid = (DefaultGridResult) this.query(entity);
		/**for (int i = 0; i < resultGrid.getRows().size(); i++) {
			int studentId = ((ScStudent) resultGrid.getRows().get(i)).getStudentId();
			User user = userService.get(studentId);
			((ScStudent) resultGrid.getRows().get(i)).setUser(user);
		}**/
		doExport(resultGrid.getRows(), response);
	}
	
	@RequestMapping("/exportCardTemplate.do")
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
	
	@RequestMapping("/importCard.do")
	public @ResponseBody MessageResult importStudent(SysUpload excelFile) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		String msg = "";
		try {
			msg = saveCardsFromExcel(path + excelFile.getFileFullPath());
		} catch (Exception e) {
			return error(e.getMessage());
		}
		return success(msg);
	}

	private String saveCardsFromExcel(String filePath) throws Exception {
		File excelFile = new File(filePath);
		if (!excelFile.exists())
			throw new Exception("未能读取文件，请联系管理员！");
		else {
			ExpressionQuery query = new ExpressionQuery();
			query.add(new ValueExpression("userName", ""));
			query.add(new ValueExpression("isDeleted", 0));
			//用来记录家长绑定卡的对象
			ScStudentParents scStudentParent = null;
			//记录出错数据
			String errorMsg ="家长手机号为";
			//设置是否有错误信息
			boolean errorFlag = false;
			//记录学生不存在的信息
			String  errorStudentMsg = "学生姓名为：";
			//记录学生信息与家长信息不匹配的信息
			String errorMatchMsg = "学生与家长信息不匹配的学生有：";
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
			Sheet mySheet = wb.getSheetAt(0);
			if (mySheet == null) {
				throw new Exception("未找到文件有效数据！");
			}

			Iterator<Row> rowIter = mySheet.rowIterator();
			rowIter.next();// 从第二行开始
			while (rowIter.hasNext()) {
				Row row = rowIter.next();
				if (row.getCell(1) == null || num2String(row.getCell(1)).equals("")) {
					continue;
				}
				
				UserSch userSch = new UserSch();
					
				String studentName = num2String(row.getCell(1));
				String mobilePhone = num2String(row.getCell(2));
				// 校验学生是否存在
				userSch.setTrueNameSch(studentName);
				if (!userService.checkUserExist(userSch)) {
					errorStudentMsg += (studentName+"、");
					errorFlag = true;
					continue;
				}
				//查询学生姓名对应的数据是否存在
				query = new ExpressionQuery();
				query.add(new ValueExpression("TrueName", studentName));
				query.add(new ValueExpression("isDeleted", 0));
				List<User> users = userService.find(query);
				User parent = userService.getUserByUserName(mobilePhone);
				//如果家长用户不存在
				if(parent == null){
					errorMsg += (mobilePhone+"、");
					errorFlag = true;
					continue;
				}
				//如果学生姓名存在重复
				if(users.size() > 1){
					for(int i = 0; i<users.size(); i++){
						List<ScStudentParents> parents = scStudentParentsService.getStudentParentsBySId(users.get(i).getId());
						for(int j = 0; j<parents.size(); j++){
							if(parents.get(j).getParentId().equals(parent.getId())){
								scStudentParent = parents.get(j);
								break;
							}
						}
					}
					//说明该学生应该绑定的家长对象信息不匹配
					if(scStudentParent == null){
						errorMatchMsg += (studentName+"、");
						errorFlag = true;
						continue;
					}
				}
				

				// 学生信息到user
				List<ScStudentParents> parents = scStudentParentsService.getStudentParentsBySId(users.get(0).getId());
				for(int i= 0; i<parents.size(); i++){
					if(parents.get(i).getParentId().equals(parent.getId())){
						scStudentParent = parents.get(i);
						break;
					}
				}
				//说明该学生应该绑定的家长对象信息不匹配
				if(scStudentParent == null){
					errorMatchMsg += (studentName+"、");
					errorFlag = true;
					continue;
				}else{
					scStudentParent.setCardNo(num2String(row.getCell(3)));
				}
				scStudentParentsService.update(scStudentParent);
			}
			if(errorFlag){
				String mString = "";
				if(errorMsg.length()>7){
					mString += errorMsg.substring(0, errorMsg.length()-1)+"的用户不存在，";
				}
				if(errorStudentMsg.length()>7){
					mString += errorStudentMsg.substring(0, errorStudentMsg.length()-1)+"的用户不存在，";
				}
				if(errorMatchMsg.length()>15){
					mString += errorMatchMsg.substring(0, errorMatchMsg.length()-1)+"。";
				}
				return mString+"请确认数据正确后重新上传这些错误的账号绑卡的数据！！！切记正确的数据不需要重新上传,否则会视为重复绑卡操作。请在1分钟内记录好这些错误数据！";
			}
			return "";
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
