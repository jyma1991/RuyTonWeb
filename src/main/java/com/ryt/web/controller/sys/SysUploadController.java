package com.ryt.web.controller.sys;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ListExpression;

import com.ryt.web.common.FileTypeTest;
import com.ryt.web.common.PlUploadUtil;
import com.ryt.web.common.QiniuManager;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.sys.SysUploadSch;
import com.ryt.web.service.sys.SysUploadService;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysUploadController extends
		CrudController<SysUpload, SysUploadService> {

	private Map<String, String> uploadParams;
	
	private static final String RESP_SUCCESS = "{\"jsonrpc\" : \"2.0\", \"result\" : \"success\"}";
	private static final String RESP_ERROR = "{\"jsonrpc\" : \"2.0\", \"error\" : {\"code\": 101, \"message\": \"Failed to open input stream.\"}}";
	public static final String JSON = "application/json";
	public static final int BUF_SIZE = 2 * 1024;
	private int chunk;
	private int chunks;
	private String name;
	private String moduleId;
	private String funcId;
	private String dataId;
	private String clientName;
	private File f;
	private String dstPath;
	private String fileName;
		//鏂板璁板綍
	@RequestMapping("/addSysUpload.do")
	public @ResponseBody
	MessageResult addSysUpload(SysUpload entity) {
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delSysUpload.do")
	public @ResponseBody
	MessageResult delSysUpload(SysUpload entity) {
		return this.delete(entity);
	}
	
	/**
	 * 批量删除上传记录
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delSysUploadByBatch.do")
	public @ResponseBody
	MessageResult delSysUploadByBatch(Integer[] ids) {
		this.getService().deleteSysUploadByBatch(ids);
		return success();
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateSysUpload.do")
	public @ResponseBody
	MessageResult updateSysUpload(SysUpload entity) {
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listSysUpload.do")
	public @ResponseBody
	GridResult listSysUpload(SysUploadSch searchEntitySch) {
		return this.query(searchEntitySch);
	}
	
	@RequestMapping("/findSysUploadByIds.do")
	public @ResponseBody Object findSysUploadByIds(SysUpload entity,
			HttpServletRequest request) {
		ExpressionQuery query = new ExpressionQuery();
		String[] ids = request.getParameter("ids").split(",");
		query.add(new ListExpression("id", Arrays.asList(ids)));
		DefaultGridResult resultGrid = (DefaultGridResult) this.query(query);
		@SuppressWarnings("unchecked")
		List<SysUpload> rows = (List<SysUpload>) resultGrid.getRows();
		return rows;
	}
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllSysUpload.do")
    public @ResponseBody Object listAllSysUpload(SysUploadSch searchEntitySch) {
		DefaultGridResult result = (DefaultGridResult)this.queryAll(searchEntitySch);
		List<SysUpload> list = (List<SysUpload>)result.getRows();
		SysUpload sysUpload  = new SysUpload();
		sysUpload.setId(0);
		sysUpload.setClientName("空");
		list.add(sysUpload);
        return new DefaultGridResult(list);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getSysUploadById.do")
    public @ResponseBody SysUpload getSysUploadById(SysUpload entity) {
        return this.getService().get(entity.getId());
    }

	@RequestMapping("/fileUpload.do")
	public @ResponseBody void fileUpload(SysUpload entity,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 读取assets/plupload/upload.xml配置参数
		uploadParams = PlUploadUtil.getInstance(
				req.getSession().getServletContext().getRealPath(""))
				.getUploadParam();

		String responseString = RESP_SUCCESS;
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			upload.setHeaderEncoding("UTF-8");//解决http报头乱码，即中文文件名乱码
			try {
				FileItemIterator iter = upload.getItemIterator(req);
				// 遍历循环多个文件内容
				while (iter.hasNext()) {

					FileItemStream item = iter.next();
					InputStream input = item.openStream();
					// Handle a form field.
					if (item.isFormField()) {
						// 设置参数值
						String fileName = item.getFieldName();
						String value = Streams.asString(input,"UTF-8");
						if ("name".equals(fileName)) {
							this.name = value;
						} else if ("chunks".equals(fileName)) {
							this.chunks = Integer.parseInt(value);
						} else if ("chunk".equals(fileName)) {
							this.chunk = Integer.parseInt(value);
						} else if ("moduleId".equals(fileName)) {
							this.moduleId = value;
						} else if ("funcId".equals(fileName)) {
							this.funcId = value;
						} else if ("dataId".equals(fileName)) {
							this.dataId = value;
						} else if ("filename".equals(fileName)) {
							this.fileName = value;
						}

					}
					// Handle a multi-part MIME encoded file.
					else {
						String fileDir = checkSaveDir(req, this.moduleId,
								this.funcId)[0];
//						System.out.println("fileDir:"+fileDir);
//						fileDir ="D:\\fileUpload";
						File dstFile = new File(fileDir);
						File dst = new File(dstFile.getPath() + "/" + this.name);
						System.out.println(dst);
						saveUploadFile(input, dst);

						/*
						 * 处理文件属性读取操作
						 */
						File f = new File(dstFile.getPath() + "/" + this.name);
						this.f = f;
						this.clientName = item.getName();
						this.dstPath = dstFile.getPath();
					}
				}
			} catch (Exception e) {
				responseString = RESP_ERROR;
				e.printStackTrace();
			}
		}
		// Not a multi-part MIME request.
		else {
			responseString = RESP_ERROR;
		}
		if (this.chunk == this.chunks - 1) {

			if (f.exists()) {

				int filesize = Math.round(f.length() / 1024);
				int filewidth = 0;
				int fileheight = 0;

				String servername = this.name;
				String filetypes = FileTypeTest.getFileByFile(f);
				String fileext = this.clientName.substring(this.clientName
						.lastIndexOf(".") + 1);
				// xlsx、docx等新版本文档形式的文件头与zip的文件头一样，所以当遇到这些文件的时候，需要用后缀来当文件类型
				if (filetypes.equals("zip")) {
					if (!fileext.equals("zip")) {
						filetypes = fileext;
					}
				}

				// 判定为图片类型
				if (FileTypeTest.getImageFileType(f) != null) {

					// 确认是否生成缩略图及获取相应的尺寸大小
					Integer thumb_small_width = Integer.valueOf(uploadParams
							.get("thumbSmallWidth"));
					Integer thumb_small_height = Integer.valueOf(uploadParams
							.get("thumbSmallHeight"));

					Integer thumb_middle_width = Integer.valueOf(uploadParams
							.get("thumbMiddleWidth"));
					Integer thumb_middle_height = Integer.valueOf(uploadParams
							.get("thumbMiddleHeight"));

					Integer thumb_big_width = Integer.valueOf(uploadParams
							.get("thumbBigWidth"));
					Integer thumb_big_height = Integer.valueOf(uploadParams
							.get("thumbBigHeight"));

					Boolean thumbSmall = Boolean.valueOf(uploadParams
							.get("thumbSmall"));
					Boolean thumbMiddle = Boolean.valueOf(uploadParams
							.get("thumbMiddle"));
					Boolean thumbBig = Boolean.valueOf(uploadParams
							.get("thumbBig"));

					// 生成相应的缩略图文件
					if (thumbSmall) {
						Thumbnails
								.of(new File(this.dstPath + "/" + this.name))
								.size(thumb_small_width, thumb_small_height)
								.toFile(new File(this.dstPath + "/" + "small_"
										+ this.name));
					}

					if (thumbMiddle) {
						Thumbnails
								.of(new File(this.dstPath + "/" + this.name))
								.size(thumb_middle_width, thumb_middle_height)
								.toFile(new File(this.dstPath + "/" + "middle_"
										+ this.name));
					}

					if (thumbBig) {
						Thumbnails
								.of(new File(this.dstPath + "/" + this.name))
								.size(thumb_big_width, thumb_big_height)
								.toFile(new File(this.dstPath + "/" + "big_"
										+ this.name));
					}

					// 读取图片文件，并获取其宽高变量
					BufferedImage sourceImg = ImageIO.read(new FileInputStream(
							f));
					filewidth = Math.round(sourceImg.getWidth());
					fileheight = Math.round(sourceImg.getHeight());
				}
				String filefullpath = checkSaveDir(req, this.moduleId,
						this.funcId)[1] + this.name;
				String createdby = UserContext.getInstance().getUser()
						.getUsername();

				// 存入数据库
				entity.setFileSize(filesize);
				entity.setFileWidth(filewidth);
				entity.setFileHeight(fileheight);
				entity.setClientName(this.fileName);
				entity.setServerName(servername);
				entity.setFileTypes(filetypes);
				entity.setFileExt(fileext);
				entity.setFileFullPath(filefullpath);
				entity.setModuleId(this.moduleId);
				entity.setFuncId(this.funcId);
				entity.setDataId(this.dataId);
				entity.setCreatedBy(createdby);
				this.save(entity);
			}
		}

		resp.setContentType(JSON);
		byte[] responseBytes = responseString.getBytes();
		resp.setContentLength(responseBytes.length);
		ServletOutputStream output = resp.getOutputStream();
		output.write(responseBytes);
		output.flush();
	}

	
	/**
	 * @param request
	 *            请求对象
	 * @return 目录路径
	 *         <p>
	 *         用来检查文件存放路径是否存在，不存在则创立
	 *         </p>
	 */
	protected String[] checkSaveDir(HttpServletRequest request,
			String moduleId, String funcId) {
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DATE));
		String filePath = request.getSession().getServletContext()
				.getRealPath(uploadParams.get("filePath"));
		String relativeFilePath = uploadParams.get("relativeFilePath");

		String dirPath = filePath;
		String dirRelativeFilePath = relativeFilePath;
		// 目录结构
		if (Boolean.valueOf(uploadParams.get("isModule"))
				&& moduleId.trim().length() > 0) {
			dirPath += "/" + moduleId;
			dirRelativeFilePath += "/" + moduleId;
		}
		if (Boolean.valueOf(uploadParams.get("isFunction"))
				&& funcId.trim().length() > 0) {
			dirPath += "/" + funcId;
			dirRelativeFilePath += "/" + funcId;
		}
		if (Boolean.valueOf(uploadParams.get("isYear"))) {
			dirPath += "/" + year;
			dirRelativeFilePath += "/" + year;
		}
		if (Boolean.valueOf(uploadParams.get("isMonth"))) {
			dirPath += "/" + month;
			dirRelativeFilePath += "/" + month;
		}
		if (Boolean.valueOf(uploadParams.get("isDay"))) {
			dirPath += "/" + day;
			dirRelativeFilePath += "/" + day;
		}
		checkDirExist(dirPath);
		String[] dirString = new String[2];
		dirString[0] = dirPath;
		dirString[1] = dirRelativeFilePath + "/";
		return dirString;
	}

	/**
	 * @param dirPath
	 *            文件目录
	 *            <p>
	 *            用于检查目录是否存在，没有时创建
	 *            </p>
	 */
	protected void checkDirExist(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/*
	 * 保存文件
	 */
	private void saveUploadFile(InputStream input, File dst) throws IOException {
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),
						BUF_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUF_SIZE);
			}
			byte[] buffer = new byte[BUF_SIZE];
			int len = 0;
			while ((len = input.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping("/getQiniuPublicToken.do")
	public @ResponseBody Map<String, String> getQiniuPublicToken() {
		QiniuManager qManager = new QiniuManager();
		Map<String,String> token = new HashMap<String, String>();
		token.put("uptoken", qManager.getQiniuPublicToken());
        return token;
    }
}