package com.ryt.web.service.sys;

import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.sys.SysClassDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleBaseSch;
import com.ryt.web.entity.article.ArticleCommentSch;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysClassSch;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.article.ArticleCommentService;

@Service
public class SysClassService extends CrudService<SysClass, SysClassDao> {
	@Autowired
	ArticleBaseService articleBaseService;
	@Autowired
	ArticleCommentService articleCommentService;
	/**
	 * 通过系统类型字段id查找该系统字段下所属内容
	 * @param sysClassTypeId
	 * @return
	 */
	public List<SysClass> getSysClassByTypeId(SysClass sysClass) {
		return getDao().getSysClassByTypeId(sysClass);
	}
	
	/**
	 * 获得一个分类下的所有分类信息
	 * @param sysClass 存放查询信息
	 * @return
	 */
	public List<SysClass> getChildSysClassesListByParentId(SysClass sysClass) {
		return getDao().getChildSysClassesListByParentId(sysClass);
	}
	
	
	/**
	 * 获取用户和孩子间关系字段名称集合
	 * @param sysClassTypeId
	 * @return
	 */
	public List<SysClass> getAllRelativeName(Integer sysClassTypeId) {
		return getDao().getAllRelativeName(sysClassTypeId);
	}
	
	//获取分类下文章数及评论数
	public List<SysClass> getListSysClassAndArticleCount(SysClassSch searchEntitySch){
		List<SysClass> listSysClass =this.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch));
		for (SysClass sysClass : listSysClass) {
			ArticleBaseSch articleBaseSch = new ArticleBaseSch();
			articleBaseSch.setSysClassIdSch(sysClass.getId());
			List<ArticleBase> articleBases = articleBaseService.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(articleBaseSch));
			sysClass.setArticleCount(articleBases.size());
			Integer count = 0;
			for(ArticleBase articleBase : articleBases){
				articleBaseSch = new ArticleBaseSch();
				articleBaseSch.setParentIdSch(articleBase.getId());
				count += articleBaseService.findTotalCount(ExpressionQuery.buildQueryAll().addAnnotionExpression(articleBaseSch));
			}
			sysClass.setArticleCommentCount(count);
		}
		return listSysClass;
	}

}