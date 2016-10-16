package com.ryt.web.service.article;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.article.ArticleCommentDao;
import com.ryt.web.entity.article.ArticleComment;

@Service
public class ArticleCommentService extends CrudService<ArticleComment, ArticleCommentDao> {
	
	/**
	 * 通过文章id查找所有相关评论
	 * @param articleId
	 */
	public List<ArticleComment> finAllComments(Integer articleId) {
		return getDao().finAllComments(articleId);
		
	}
	
	
	/**
	 * 查找上级评论
	 * @param parentId
	 */
	public ArticleComment findCommentByParentId(Integer parentId) {
			return getDao().findCommentByParentId(parentId);
		
	}
	
	public List<ArticleComment> getLastCommentForumArticle(Integer sysClassTypeId){
		return this.getDao().getLastCommentForumArticle(sysClassTypeId);
	}
	
	/**
	 * 查询某个文章中某个上级评论下的所有评论
	 * @param parentId
	 * @return
	 */
	public List<ArticleComment> findAllCommentByParentId(Integer parentId,Integer articleBaseId){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("parentId", parentId));
		query.add(new ValueExpression("articleBaseId", articleBaseId));
		query.addSort("id", "desc");
		query.setQueryAll(true);
		return this.find(query);
	}
}