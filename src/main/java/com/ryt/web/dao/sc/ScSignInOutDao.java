package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.sc.ScSignInOut;

public interface ScSignInOutDao extends BaseDao<ScSignInOut> {
	List<ArticleBase> getSignNotifyList(@Param("sysClassId")Integer sysClassId, @Param("schoolId")Integer schoolId);	
}