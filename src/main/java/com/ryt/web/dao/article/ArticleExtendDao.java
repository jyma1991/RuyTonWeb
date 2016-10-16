package com.ryt.web.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleExtend;

public interface ArticleExtendDao extends BaseDao<ArticleExtend> {
	void delABExtByabId(@Param("articleBaseId") Integer articleBaseId);
	List<?> findABExtByabId(@Param("articleBaseId") Integer abId);
	
	ArticleExtend findABExtCheckboxByabIdAndCellname(@Param("articleBaseId") Integer abId, @Param("cellName") String cellname);
}