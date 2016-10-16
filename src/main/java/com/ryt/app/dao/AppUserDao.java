package com.ryt.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.user.User;

public interface AppUserDao extends BaseDao<User> {

	User getUserByUserName(@Param("userName") String userName);
	
    User appLogin(@Param("userName") String userName, @Param("userPwd") String userPwd);

	List<User> getChildrens(@Param("id")Integer userId);
	
	String getUserRelativeName(@Param("userId")Integer userId,@Param("studentId") Integer studentId);

	List<User> getTeachersContactsByParents(User user);
	
	List<User> getTeachersContactsByTeacher(User user);

	List<User> getParentsContactsByParents(User user);

	List<User> getParentsContactsByTeacher(User user);

	List<ScClass> getClassByTeacher(User user);

	List<User> getClassParentsContacts(@Param("classId")Integer classId,@Param("userId") Integer userId);

	List<ScClass> getClassByParents(User user);

	List<User> getTeacherChargeParentsList(ScClass scClass);

	List<ScClass> getClassBySchool(User user);

	List<User> getTeachersContactsBySchool(User user);

	List<User> getParentsContactsBySchool(User user);

	List<User> getClassParentsContactsBySchool(@Param("classId")Integer classId,@Param("userId") Integer userId);

	List<User> getChildParents(@Param("studentId")Integer studentId);

	Integer getClassParentsContactsCount(ScClass scClass);

	Integer getSenderUserCount(NotifyArticle notifyArticle);

	List<User> getSenderUserList(NotifyArticle notifyArticle);
	
	List<User> getUserbyUserNames(@Param("userNamesArray")String[] userNamesArray);

	List<User> getUserbyIds(@Param("userIdsArray")String[] userIdsArray);

	




}