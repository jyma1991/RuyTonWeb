package com.ryt.web.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;

public interface ArticleCommentDao extends BaseDao<ArticleComment> {

	List<ArticleComment> finAllComments(@Param("articleId")Integer articleId);

	ArticleComment findCommentByParentId(@Param("parentId")Integer parentId);
	
	List<ArticleComment> getLastCommentForumArticle(@Param("sysClassTypeId")Integer sysClassTypeId);
}