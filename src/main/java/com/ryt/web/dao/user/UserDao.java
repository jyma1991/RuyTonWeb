package com.ryt.web.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.user.User;

public interface UserDao extends BaseDao<User> {
	
	User getUserByUserName(@Param("userName") String userName);
	
	List<User> getParentsUserByClassId(@Param("classId") int classId);

	List<User> getStudentsByClassId(@Param("classId")Integer classId);
}