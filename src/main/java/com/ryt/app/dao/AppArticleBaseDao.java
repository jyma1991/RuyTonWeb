package com.ryt.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;

public interface AppArticleBaseDao extends BaseDao<ArticleBase> {
	
//	List<ArticleBase> getCircleInfoList(@Param("firstResult") Integer firstResult,@Param("pageCount") Integer pageCount
//			, @Param("childId")Integer childId,@Param("userId")Integer userId);

	List<SysUpload> getPicsUrlByIds(@Param("picIds") String[] picIds);

	User getUserById(@Param("studentId") Integer studentId);

	Integer getAllCircleListCount(ArticleBase articleBase);

	List<ArticleBase> findByPage(ArticleBase oldArticleBase);

	List<ArticleBase> getNewCircleInfoList(ArticleBase articleBase);
	
	List<ArticleBase> getNewForumInfoList(ArticleBase articleBase);
	
	List<ArticleBase> getNewForumReplyList(ArticleBase articleBase);
	
	List<ArticleBase> getOldForumReplyList(ArticleBase articleBase);

	List<ArticleBase> getScAbout(SysClass sysClass);
	
	List<ArticleBase> getSysClassByTypeId(SysClass sysClass);

	Integer getAllMyCircleListCount(ArticleBase articleBase);

	List<ArticleBase> findMyCircleInfoByPage(ArticleBase oldArticleBase);

	List<ArticleBase> findSuggestByPage(ArticleBase articleBase);

	Integer getSuggestTotalPage(ArticleBase articleBase);

	Integer getSuggestCountBySchoolId(ArticleBase articleBase);

	List<ArticleBase> findSuggestBySchoolId(ArticleBase articleBase);

	Integer findFeedBackCount(ArticleBase articleBase);

	List<ArticleBase> findFeedBackByPage(ArticleBase articleBase);

	List<ArticleBase> aboutMe(@Param("sysClassType")Integer sysClassType);

	List<ArticleBase> getArticleBySysClassId(SysClass sysClass);

	List<NotifyArticle> getNotifyArticleList(NotifyArticle notifyArticle);

	Integer getAllCircleListCountBySchool(ArticleBase oldArticleBase);

	List<ArticleBase> findByPageBySchool(ArticleBase oldArticleBase);

	Integer getAllCircleListCountByTeacher(ArticleBase oldArticleBase);

	List<ArticleBase> findByPageByTeacher(ArticleBase articleBase);

	List<ArticleBase> getNewCircleInfoListBySchool(ArticleBase oldArticleBase);

	List<ArticleBase> getNewCircleInfoListByTeacher(ArticleBase oldArticleBase);

	void deleteArticleByParentId(@Param("articleId")Integer articleId);	
	
	public List<ArticleBase> getScrollForumBanner(ArticleBase articleBase);
}