package com.ryt.web.service.article;

import java.util.List;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.article.ArticleLikeDao;
import com.ryt.web.entity.article.ArticleLike;
import com.ryt.web.entity.user.User;

import org.springframework.stereotype.Service;

@Service
public class ArticleLikeService extends CrudService<ArticleLike, ArticleLikeDao> {

	/**
	 * 通过用户id和文章id查找该用户是否点赞
	 * 
	 * @param id
	 *            文章id
	 * @param userId
	 *            用户id
	 * @return
	 */
	public ArticleLike getArticle(Integer articleId, Integer userId) {
		return this.getDao().getArticle(articleId, userId);
	}

	/**
	 * 获取文章点赞次数
	 * 
	 * @param id
	 * @return
	 */
	public Integer getArticleLikeCount(Integer articleId) {
		return getDao().getArticleLikeCount(articleId);
	}

	// 获取点赞人员集合
	public List<User> getArticleLikeUsers(Integer articleId) {
		return getDao().getArticleLikeUsers(articleId);
	}

}