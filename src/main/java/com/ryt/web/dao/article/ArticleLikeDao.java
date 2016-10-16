package com.ryt.web.dao.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.article.ArticleLike;
import com.ryt.web.entity.user.User;

public interface ArticleLikeDao extends BaseDao<ArticleLike> {
	
	ArticleLike getArticle(@Param("articleId")Integer articleId,@Param("userId") Integer userId);

	Integer getArticleLikeCount(@Param("articleId") Integer articleId);

	List<User> getArticleLikeUsers(@Param("articleId")Integer articleId);
}