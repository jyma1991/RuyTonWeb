package com.ryt.web.service.notify;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.notify.NotifyArticleDao;
import com.ryt.web.entity.notify.NotifyArticeRead;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.notify.NotifyArticleSch;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.user.User;


@Service
public class NotifyArticleService extends CrudService<NotifyArticle, NotifyArticleDao> {

	public List<NotifyArticle> getNotifyArticleList(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getNotifyArticleList(searchEntitySch);
	}

	public Integer getNotifyArticleListCount(NotifyArticleSch searchEntitySch) {

		List<NotifyArticle> tempList = getDao().getNotifyArticleListCount(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	public List<NotifyArticle> getNotifyArticleListByTeacher(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getNotifyArticleListByTeacher(searchEntitySch);
	}

	public Integer getNotifyArticleListCountByTeacher(NotifyArticleSch searchEntitySch) {
		List<NotifyArticle> tempList = getDao().getNotifyArticleListCountByTeacher(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	public List<NotifyArticle> getNotifyArticleListByParent(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getNotifyArticleListByParent(searchEntitySch);
	}

	public Integer getNotifyArticleListCountByParent(NotifyArticleSch searchEntitySch) {
		List<NotifyArticle> tempList = getDao().getNotifyArticleListCountByParent(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	public List<NotifyArticle> getClassNotifyListByParent(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getClassNotifyListByParent(searchEntitySch);
	}

	public Integer getClassNotifyListCountByParent(NotifyArticleSch searchEntitySch) {
		List<NotifyArticle> tempList = getDao().getClassNotifyListCountByParent(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	public List<NotifyArticle> getClassNotifyListByTeacher(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getClassNotifyListByTeacher(searchEntitySch);
	}

	public Integer getClassNotifyListCountByTeacher(NotifyArticleSch searchEntitySch) {
		List<NotifyArticle> tempList = getDao().getClassNotifyListCountByTeacher(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	/**
	 * 获取通知未读数量
	 * 
	 * @param notifyArticle
	 * @return
	 */
	public int getNotifyUnreadCount(NotifyArticle notifyArticle) {

		List<NotifyArticle> totalNotifyList = getDao().getAllNotifyListByParent(notifyArticle);
		int totalNotifyCount = 0;
		if (totalNotifyList != null && totalNotifyList.size() > 0) {
			totalNotifyCount = totalNotifyList.size();
		}
		// 获取用户已读文章数量
		List<NotifyArticeRead> alreadyList = getDao().getAlreadyReadNotifyListByParent(notifyArticle);
		int alreadyCount = 0;
		if (alreadyList != null && alreadyList.size() > 0) {
			alreadyCount = alreadyList.size();
		}

		return totalNotifyCount - alreadyCount;
	}

	public List<NotifyArticle> getNotifyArticleListBySchool(NotifyArticleSch searchEntitySch) {
		Integer firstResult = (searchEntitySch.getPageIndex() - 1) * searchEntitySch.getPageSize();
		searchEntitySch.setFirstResult(firstResult);
		return getDao().getNotifyArticleListBySchool(searchEntitySch);
	}

	public Integer getNotifyArticleListCountBySchool(NotifyArticleSch searchEntitySch) {
		List<NotifyArticle> tempList = getDao().getNotifyArticleListCountBySchool(searchEntitySch);
		if (tempList != null && tempList.size() > 0) {
			return tempList.size();
		}
		return 0;
	}

	public Integer getClassNotifyUnreadCount(NotifyArticle notifyArticle, Integer roleProperty) {
		// 登录者为家长
		Integer totalClassNotifyCount = 0;
		Integer AlreadyClassReadCount = 0;
		if (roleProperty == RuyTonAppContants.USER_TYPE_PARENT) {
			// 获取所有的该家长能看到的班级通知
			totalClassNotifyCount = getDao().getAllClassNotifyUnreadCountByParent(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllClassNotifyReadCountByParent(notifyArticle);
		} else if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {
			// 获取所有的园长能看到的班级通知
			totalClassNotifyCount = getDao().getAllClassNotifyUnreadCountBySchool(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllClassNotifyReadCountBySchool(notifyArticle);
		} else if (roleProperty == RuyTonAppContants.USER_TYPE_TEACHER) {
			// 获取所有的老師能看到的班级通知
			totalClassNotifyCount = getDao().getAllClassNotifyUnreadCountByTeacher(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllClassNotifyReadCountByTeacher(notifyArticle);
		}
		return totalClassNotifyCount - AlreadyClassReadCount;
	}

	public Integer getSchoolNotifyUnreadCount(NotifyArticle notifyArticle, Integer roleProperty) {
		// 登录者为家长
		Integer totalClassNotifyCount = 0;
		Integer AlreadyClassReadCount = 0;
		if (roleProperty == RuyTonAppContants.USER_TYPE_PARENT) {
			// 获取所有的该家长能看到的班级通知
			totalClassNotifyCount = getDao().getAllSchoolNotifyUnreadCountByParent(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllSchoolNotifyReadCountByParent(notifyArticle);
		} else if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {
			// 获取所有的园长能看到的班级通知
			totalClassNotifyCount = getDao().getAllSchoolNotifyUnreadCountBySchool(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllSchoolNotifyReadCountBySchool(notifyArticle);
		} else if (roleProperty == RuyTonAppContants.USER_TYPE_TEACHER) {
			// 获取所有的园长能看到的班级通知
			totalClassNotifyCount = getDao().getAllSchoolNotifyUnreadCountByTeacher(notifyArticle);
			// 用户已读数量
			AlreadyClassReadCount = getDao().getAllSchoolNotifyReadCountByTeacher(notifyArticle);
		}
		return totalClassNotifyCount - AlreadyClassReadCount;
	}


	public NotifyArticle findByArticleIdAndClassId(NotifyArticle notifyArticle) {
		NotifyArticleSch notifyArticleSch = new NotifyArticleSch();
		notifyArticleSch.setArticleIdSch(notifyArticle.getArticleId());
		notifyArticleSch.setIsDeletedSch(RuyTonAppContants.ARTICLE_DELETE_FALSE);
		notifyArticleSch.setClassIdSch(notifyArticle.getClassId());
		ExpressionQuery query = new ExpressionQuery();
		query.addAnnotionExpression(notifyArticleSch);
		List<NotifyArticle> list = getDao().find(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<NotifyArticle> findByArticleId(NotifyArticle notifyArticle) {
		ExpressionQuery query = new ExpressionQuery();
		query.addParam("articleId", notifyArticle.getArticleId());
		query.addParam("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE);
		query.addParam("notifyType", notifyArticle.getNotifyType());
		return getDao().find(query);
	}

	public List<NotifyArticle> findByArticleIdAndSchoolId(NotifyArticle notifyArticle) {
		NotifyArticleSch notifyArticleSch = new NotifyArticleSch();
		notifyArticleSch.setArticleIdSch(notifyArticle.getArticleId());
		notifyArticleSch.setIsDeletedSch(RuyTonAppContants.ARTICLE_DELETE_FALSE);
		notifyArticleSch.setSchoolIdSch(notifyArticle.getSchoolId());
		ExpressionQuery query = new ExpressionQuery();
		query.addAnnotionExpression(notifyArticleSch);
		return getDao().find(query);
	}

	public Integer getUserReadCount(NotifyArticle notifyArticle) {
		return getDao().getUserReadCount(notifyArticle);
	}

	public Integer getSenderUserReadCount(NotifyArticle notifyArticle) {
		return getDao().getSenderUserReadCount(notifyArticle);
	}

	public List<User> getSendUserList(NotifyArticle notifyArticle) {
		
		return null;
	}

}