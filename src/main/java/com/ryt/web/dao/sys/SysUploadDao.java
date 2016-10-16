package com.ryt.web.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.sys.SysUpload;

public interface SysUploadDao extends BaseDao<SysUpload> {
	
	List<ArticleBase> getCircleInfoList(Integer childId);

	SysUpload getByUserId(@Param("uploadId")Integer userId);
}