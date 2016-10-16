package com.ryt.web.dao.notify;

import java.util.List;

import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.notify.NotifyArticeRead;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.notify.NotifyArticleSch;

public interface NotifyArticleDao extends BaseDao<NotifyArticle> {

	List<NotifyArticle> getNotifyArticleList(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListCount(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListByTeacher(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListCountByTeacher(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListByParent(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListCountByParent(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getClassNotifyListByParent(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getClassNotifyListCountByParent(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getClassNotifyListByTeacher(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getClassNotifyListCountByTeacher(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getAllNotifyListByParent(NotifyArticle notifyArticle);

	List<NotifyArticeRead> getAlreadyReadNotifyListByParent(NotifyArticle notifyArticle);

	List<NotifyArticle> getNotifyArticleListBySchool(NotifyArticleSch searchEntitySch);

	List<NotifyArticle> getNotifyArticleListCountBySchool(NotifyArticleSch searchEntitySch);

	Integer getAllClassNotifyUnreadCountByParent(NotifyArticle notifyArticle);

	Integer getAllClassNotifyReadCountByParent(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyUnreadCountByParent(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyReadCountByParent(NotifyArticle notifyArticle);

	Integer getAllClassNotifyUnreadCountBySchool(NotifyArticle notifyArticle);

	Integer getAllClassNotifyReadCountBySchool(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyUnreadCountBySchool(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyReadCountBySchool(NotifyArticle notifyArticle);

	Integer getAllClassNotifyUnreadCountByTeacher(NotifyArticle notifyArticle);

	Integer getAllClassNotifyReadCountByTeacher(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyUnreadCountByTeacher(NotifyArticle notifyArticle);

	Integer getAllSchoolNotifyReadCountByTeacher(NotifyArticle notifyArticle);

	Integer getUserReadCount(NotifyArticle notifyArticle);

	Integer getSenderUserReadCount(NotifyArticle notifyArticle);

}