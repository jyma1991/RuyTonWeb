package com.ryt.web.controller.finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.WebContext;
import org.durcframework.core.controller.ExportController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceChargeOrderBak;
import com.ryt.web.entity.finance.FinanceChargeProduct;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.finance.FinanceChargeProductService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.user.UserService;

@Controller
public class FinanceChargeOrderExportOrImportController extends ExportController<FinanceChargeOrder, FinanceChargeOrderService>{
	private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=UTF-8";
	private static final String F_HEADER_ARGU1 = "Content-Disposition";
	private static final String F_HEADER_ARGU2 = "attachment;filename=";

	private static final String templatePath = "template/";
	private static final String exportTemplateName = "student_export.xls";
	private static final String importTemplateName = "importOrderTemplate.xlsx";
	@Autowired
	UserService userService;
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	ScClassService scClassService;
	@Autowired
	ScSchoolService scSchoolService;
	@Autowired
	FinanceChargeProductService financeChargeProductService;

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
	
	/**
	@RequestMapping("/exportOrder.do")
	public void exportStudent(ScStudentSch entity, HttpServletResponse response) {
		DefaultGridResult resultGrid = (DefaultGridResult) this.query(entity);
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			int studentId = ((ScStudent) resultGrid.getRows().get(i)).getStudentId();
			User user = userService.get(studentId);
			((ScStudent) resultGrid.getRows().get(i)).setUser(user);
		}
		doExport(resultGrid.getRows(), response);
	}**/

	@RequestMapping("/exportOrderTemplate.do")
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

	@RequestMapping("/importOrder.do")
	public @ResponseBody MessageResult importStudent(SysUpload excelFile) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		String msg = "";
		try {
			msg = saveOrdersFromExcel(path + excelFile.getFileFullPath());
		} catch (Exception e) {
			return error(e.getMessage());
		}
		return success(msg);
	}

	private String saveOrdersFromExcel(String filePath) throws Exception {
		File excelFile = new File(filePath);
		if (!excelFile.exists())
			throw new Exception("未能读取文件，请联系管理员！");
		else {
			//记录Id
			int agentId = 0;
			int schoolId = 0;
			int classId = 0;
			int studentId = 0;
			//记录出错数据
			String errorMsg ="充值账号为";
			//设置是否有错误信息
			boolean errorFlag = false;
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
			Sheet mySheet = wb.getSheetAt(0);
			if (mySheet == null) {
				throw new Exception("未找到文件有效数据！");
			}
			ExpressionQuery query = new ExpressionQuery();
			query.setQueryAll(true);
			List<FinanceChargeProduct> products = financeChargeProductService.find(query);
			
			Iterator<Row> rowIter = mySheet.rowIterator();
			rowIter.next();// 从第二行开始
			while (rowIter.hasNext()) {
				Row row = rowIter.next();
				if (row.getCell(1) == null) {
					continue;
				}
				
				ScStudent scStudent = new ScStudent();
				FinanceChargeOrder order = new FinanceChargeOrder();
				User user = new User();
				UserSch userSch = new UserSch();
				ScStudentParents scStudentParents = new ScStudentParents();

				// 校验用户是否存在
				userSch.setUserNameSch(num2String(row.getCell(1)));
				if (!userService.checkUserExist(userSch)) {
					errorMsg += (userSch.getUserNameSch()+"、");
					errorFlag = true;
					continue;
				}
				
				if(schoolId == 0){
					user = userService.getUserByUserName(userSch.getUserNameSch());
					scStudentParents = scStudentParentsService.getStudentParentsByPId(user.getId()).get(0);
					scStudent = scStudentService.getSctudentByUserId(scStudentParents.getStudentId());
					schoolId = scStudent.getSchoolId();
					classId = scStudent.getClassId();
					studentId = scStudent.getStudentId();
					agentId = scSchoolService.getSchoolBySchoolId(schoolId).getAgentId();
				}
				order.setAgentId(agentId);
				order.setSchoolId(schoolId);
				order.setClassId(classId);
				order.setStudentId(studentId);
				order.setChargeAccountId(userSch.getUserNameSch());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				order.setPayTime(format.format(calendar.getTime()));
				order.setQuantity(1);
				order.setTotalFee(new BigDecimal(num2String(row.getCell(2))));
				for(int i = 0; i<products.size(); i++){
					if(order.getTotalFee().intValue() == products.get(i).getPresentPrice().intValue()){
						order.setProductId(products.get(i).getId());
						break;
					}
				}
				order.setPayStatus(1);//0表示未完成，1表示已完成，2表示取消
				order.setPayType(186);//186表示线下支付
				order.setConfirmUserId(UserContext.getInstance().getUser().getId());//审核人表示当前用户
				this.getService().save(order);

			}
			if(errorFlag){
				return errorMsg.substring(0, errorMsg.length()-1)+"的用户不存在，请确认用户存在后重新上传这些错误的充值账号相关的数据！！！切记正确的数据不需要重新上传,否则会视为重复充值操作。请在1分钟内记录好这些错误数据！";
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
