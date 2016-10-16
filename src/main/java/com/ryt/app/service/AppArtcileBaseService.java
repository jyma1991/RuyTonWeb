package com.ryt.app.service;

import java.util.List;

import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.app.dao.AppArticleBaseDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sys.SysUploadService;

@Service
public class AppArtcileBaseService extends CrudService<ArticleBase, AppArticleBaseDao> {
	@Autowired
	SysUploadService sysUploadService;

	/**
	 * 获取相关图片信息集合对象
	 * 
	 * @param picId
	 *            图片id，以字符串,进行分割
	 * @return
	 */
	public List<SysUpload> getPicsUrlByIds(String[] picIds) {
		return getDao().getPicsUrlByIds(picIds);
	}

	/**
	 * 通过小孩的id获取小孩的所有信息
	 * 
	 * @param studentId
	 * @return
	 */
	public User getUserById(Integer studentId) {
		return getDao().getUserById(studentId);
	}

	/**
	 * 获取所有该用户所能看到的文章信息
	 * 
	 * @return
	 */
	public Integer getAllCircleListCount(ArticleBase articleBase) {
		return getDao().getAllCircleListCount(articleBase);
	}

	/**
	 * 分页查询
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public List<ArticleBase> findByPage(ArticleBase oldArticleBase) {
		Integer firstResult = (oldArticleBase.getPage() - 1) * oldArticleBase.getPageCount();
		oldArticleBase.setFirstResult(firstResult);
		return getDao().findByPage(oldArticleBase);
	}
	
	

	/**
	 * 根据lastId 获取最新的家长圈信息
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public List<ArticleBase> getNewCircleInfoList(ArticleBase articleBase) {

		return getDao().getNewCircleInfoList(articleBase);
	}
	
	/**
	 * 根据lastId 获取最新的论坛信息
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public List<ArticleBase> getNewForumInfoList(ArticleBase articleBase) {
		return getDao().getNewForumInfoList(articleBase);
	}
	
	/**
	 * 根据lastId 获取最新的论坛回复
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public List<ArticleBase> getNewForumReplyList(ArticleBase articleBase) {
		return getDao().getNewForumReplyList(articleBase);
	}
	/**
	 * 根据firstLastId 获取之前的评论
	 * @param articleBase
	 * @return
	 */
	public List<ArticleBase> getOldForumReplyList(ArticleBase articleBase){
		return getDao().getOldForumReplyList(articleBase);
	}
	/**
	 * 获取文章管理系统分类
	 * 
	 * @param sysClass
	 * @return
	 */
	public List<ArticleBase> getScAbout(SysClass sysClass) {
		return getDao().getScAbout(sysClass);
	}

	/**
	 * 获取系统分类的通用方法
	 * 
	 * @param sysClass
	 * @return
	 */
	public List<ArticleBase> getSysClassByTypeId(SysClass sysClass) {
		return getDao().getSysClassByTypeId(sysClass);
	}

	/**
	 * 获取发的家长圈信息
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public Integer getAllMyCircleListCount(ArticleBase oldArticleBase) {
		return getDao().getAllMyCircleListCount(oldArticleBase);

	}

	/**
	 * 查找用户自己发的朋友圈信息
	 * 
	 * @param oldArticleBase
	 * @return
	 */
	public List<ArticleBase> findMyCircleInfoByPage(ArticleBase oldArticleBase) {
		Integer firstResult = (oldArticleBase.getPage() - 1) * oldArticleBase.getPageCount();
		oldArticleBase.setFirstResult(firstResult);
		return getDao().findMyCircleInfoByPage(oldArticleBase);
	}

	/**
	 * 获取用户所有校园信箱中的内容
	 * 
	 * @param articleBase
	 * @return
	 */
	public List<ArticleBase> findSuggestByPage(ArticleBase articleBase) {
		Integer firstResult = (articleBase.getPage() - 1) * articleBase.getPageCount();
		articleBase.setFirstResult(firstResult);
		return getDao().findSuggestByPage(articleBase);
	}

	/**
	 * 获取园长信箱中总个数
	 * 
	 * @param articleBase
	 * @return
	 */
	public Integer getSuggestTotalPage(ArticleBase articleBase) {
		return getDao().getSuggestTotalPage(articleBase);
	}

	/**
	 * 获得一个学校的所有园长信箱内容数量
	 * 
	 * @param schoolId
	 *            学校id
	 * @return
	 */
	public Integer getSuggestCountBySchoolId(ArticleBase articleBase) {
		return getDao().getSuggestCountBySchoolId(articleBase);
	}

	/**
	 * 获得一个学校的所有园长信箱内容
	 * 
	 * @param schoolId
	 *            学校id
	 * @return
	 */
	public List<ArticleBase> findSuggestBySchoolId(ArticleBase articleBase) {
		Integer firstResult = (articleBase.getPage() - 1) * articleBase.getPageCount();
		articleBase.setFirstResult(firstResult);
		return getDao().findSuggestBySchoolId(articleBase);
	}

	/**
	 * 查询该用户的建议信息数量
	 * 
	 * @param articleBase
	 * @return
	 */
	public Integer findFeedBackCount(ArticleBase articleBase) {
		return getDao().findFeedBackCount(articleBase);
	}

	public List<ArticleBase> findFeedBackByPage(ArticleBase articleBase) {
		Integer firstResult = (articleBase.getPage() - 1) * articleBase.getPageCount();
		articleBase.setFirstResult(firstResult);
		return getDao().findFeedBackByPage(articleBase);
	}

	/**
	 * 获取关于政教通的内容信息
	 * 
	 * @param sysClassType
	 *            文章类型
	 * @return
	 */
	public List<ArticleBase> aboutMe(Integer sysClassType) {
		return getDao().aboutMe(sysClassType);
	}

	/**
	 * 通过sysClassId 查找所有的文章信息
	 * 
	 * @param SysClass
	 *            类别对象
	 * @return
	 */
	public List<ArticleBase> getArticleBySysClassId(SysClass sysClass) {
		return getDao().getArticleBySysClassId(sysClass);
	}

	public List<NotifyArticle> getNotifyArticleList(NotifyArticle notifyArticle) {
		return getDao().getNotifyArticleList(notifyArticle);
	}

	public Integer getAllCircleListCountBySchool(ArticleBase oldArticleBase) {
		return getDao().getAllCircleListCountBySchool(oldArticleBase);
	}

	public List<ArticleBase> findByPageBySchool(ArticleBase articleBase) {
		Integer firstResult = (articleBase.getPage() - 1) * articleBase.getPageCount();
		articleBase.setFirstResult(firstResult);
		return getDao().findByPageBySchool(articleBase);
	}

	public Integer getAllCircleListCountByTeacher(ArticleBase oldArticleBase) {
		return getDao().getAllCircleListCountByTeacher(oldArticleBase);
	}

	public List<ArticleBase> findByPageByTeacher(ArticleBase articleBase) {
		Integer firstResult = (articleBase.getPage() - 1) * articleBase.getPageCount();
		articleBase.setFirstResult(firstResult);
		return getDao().findByPageByTeacher(articleBase);
	}

	public List<ArticleBase> getNewCircleInfoListBySchool(
			ArticleBase oldArticleBase) {
		return getDao().getNewCircleInfoListBySchool(oldArticleBase);
	}

	public List<ArticleBase> getNewCircleInfoListByTeacher(
			ArticleBase oldArticleBase) {
		return getDao().getNewCircleInfoListByTeacher(oldArticleBase);
	}

	public void deleteArticleByParentId(int articleId) {
			getDao().deleteArticleByParentId(articleId);
	}
	/**
	 * 点击文章+1
	 */
	public void updateHitsPlusOne(Integer articleId){
		ArticleBase articleBase = this.get(articleId);
		Integer hit = articleBase.getHits() + 1;
		articleBase.setHits(hit);
		this.update(articleBase);
	}
	
	/**
	 * 获取论坛横幅
	 * @param articleBase
	 * @return
	 */
	public List<ArticleBase> getScrollForumBanner(ArticleBase articleBase){
		return this.getDao().getScrollForumBanner(articleBase);
	}
}
