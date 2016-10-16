package com.ryt.web.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;

public interface ArticleBaseDao extends BaseDao<ArticleBase> {
	public List<ArticleBase> getArticlesByParents(@Param("schoolId")Integer schoolId,@Param("classId")Integer classId,@Param("sysClassId")Integer sysClassId,@Param("roleId")Integer roleId,@Param("firstResult")Integer firstResult);
	public List<ArticleBase> getLastForumArticle(@Param("sysClassTypeId")Integer sysClassTypeId);
	public List<ArticleBase> findCommentsByPages(@Param("parentId")Integer parentId,@Param("firstResult")Integer firstResult,@Param("pageSize")Integer pageSize);
	public int getAllForumCommentNums(@Param("parentId")Integer parentId);
	public List<ArticleBase> getLastCommentForumArticle();
}