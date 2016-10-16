package com.ryt.web.service.sys;

import java.util.List;

import org.durcframework.core.service.CrudService;

import com.qiniu.common.QiniuException;
import com.ryt.web.common.QiniuManager;
import com.ryt.web.dao.sys.SysUploadDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.sys.SysUpload;
import org.springframework.stereotype.Service;

@Service
public class SysUploadService extends CrudService<SysUpload, SysUploadDao> {
	
	/**
	 * 通过小孩的id获取其班级，并获取相关家长圈信息内容
	 * @param childId 
	 * @return
	 */
	public List<ArticleBase> getCircleInfoList(Integer childId) {
		return getDao().getCircleInfoList(childId);
	}
	
	
	/**
	 * 通过头像id查找文件对象
	 * @param uploadId
	 * @return
	 */
	public SysUpload getByUserId(Integer uploadId) {
		return getDao().getByUserId(uploadId);
	}
	
	/**
	 * 删除七牛云资源与数据库记录
	 * @param id
	 */
	public void deleteResourceFromQiniuAndDbById(Integer id){
		SysUpload sysUpload = this.get(id);
		this.del(sysUpload);
		QiniuManager qiniuManager = new QiniuManager();
		try {
			qiniuManager.delete(sysUpload.getServerName());
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		this.del(sysUpload);
	}
	
	public void deleteSysUploadByBatch(Integer[] ids){
		for (Integer id : ids) {
			this.del(this.get(id));
		}
	}

}