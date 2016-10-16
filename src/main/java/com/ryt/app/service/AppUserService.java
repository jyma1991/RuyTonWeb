package com.ryt.app.service;

import java.util.List;

import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.dao.AppUserDao;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.user.User;
import com.ryt.web.util.PasswordUtil;

@Service
public class AppUserService extends CrudService<User, AppUserDao> {

	/**
	 * 重置用户密码
	 * 
	 * @param user
	 * @return 返回明文密码
	 */
	public String resetUserPassword(User user) {

		String password = createNewPswd();

		String hash = PasswordUtil.createStorePswd(password);

		user.setUserPwd(hash);

		this.update(user);

		return password;
	}

	public void updateUserPassword(User user, String md5Pswd) {
		user.setUserPwd(PasswordUtil.createHash(md5Pswd));
		update(user);
	}

	/**
	 * 生成随机密码,由三个小写字母+三个数字组成
	 * 
	 * @return
	 */
	public static String createNewPswd() {
		StringBuilder pswd = new StringBuilder();

		// 随机三个小写英文字母
		for (int i = 0; i < 3; i++) {
			// ascii码 97~122
			char ascii = (char) ((int) (Math.random() * 26) + 97);
			pswd.append(ascii);
		}
		// 随机三个1~9的数
		for (int i = 0; i < 3; i++) {
			int num = (int) (Math.random() * 9) + 1;
			pswd.append(num);
		}

		return pswd.toString();
	}

	/**
	 * 登录验证
	 * 
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            密码
	 * @return
	 */
	public User appLogin(String userName, String userPwd) {
		return this.getDao().appLogin(userName, userPwd);
	}

	/**
	 * 获取用户系统功能
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUserName(String username) {
		return this.getDao().getUserByUserName(username);
	}

	/**
	 * 获取该用户的孩子信息
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<User> getChildrens(Integer userId) {

		return this.getDao().getChildrens(userId);
	}

	public static void main(String[] args) {
		System.out.println(createNewPswd());
	}

	public String getUserRelativeName(Integer userId, Integer studentId) {
		return getDao().getUserRelativeName(userId, studentId);
	}

	/**
	 * 获得该用户能看到的老师通讯录
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getTeacherContacts(User user) {
		List<User> userList = null;
		if (user.getRoleProperty() == 1) {
			userList = getDao().getTeachersContactsByParents(user);
		}
		// 老师和学长
		else if (user.getRoleProperty() == 2) {
			// 获取该老师所在学校的所有老师信息
			userList = getDao().getTeachersContactsByTeacher(user);
		} else if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
			userList = getDao().getTeachersContactsBySchool(user);
		}
		return userList;
	}

	/**
	 * 获得该用户能看到的家长通讯录
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getParentsContacts(User user) {
		List<User> userList = null;
		// 家长,获取其孩子班级中所有老师信息
		if (user.getRoleProperty() == 1) {
			userList = getDao().getParentsContactsByParents(user);
		}
		// 老师和学长
		else if (user.getRoleProperty() == 2) {
			// 获取该老师所在学校的所有老师信息
			userList = getDao().getParentsContactsByTeacher(user);
		} else if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
			userList = getDao().getParentsContactsBySchool(user);
		}
		return userList;

	}

	/**
	 * 获取该老师所关联的班级对象
	 * 
	 * @param user
	 * @return
	 */
	public List<ScClass> getClassByTeacher(User user) {
		return this.getDao().getClassByTeacher(user);
	}

	/**
	 * 获取一个班级下所有学生家长的信息
	 * 
	 * @return
	 */
	public List<User> getClassParentsContacts(Integer classId, Integer userId) {
		return this.getDao().getClassParentsContacts(classId, userId);
	}

	/**
	 * 获取该用户孩子所在的班级
	 * 
	 * @return
	 */
	public List<ScClass> getClassByParents(User user) {
		return this.getDao().getClassByParents(user);
	}

	public List<User> getTeacherChargeParentsList(ScClass scClass) {
		return getDao().getTeacherChargeParentsList(scClass);
	}

	public List<ScClass> getClassBySchool(User user) {
		return getDao().getClassBySchool(user);

	}

	public List<User> getClassParentsContactsBySchool(Integer classId, Integer userId) {
		return getDao().getClassParentsContactsBySchool(classId, userId);
	}

	public List<User> getChildParents(Integer studentId) {
		return getDao().getChildParents(studentId);
	}

	public Integer getClassParentsContactsCount(ScClass scClass) {
		return getDao().getClassParentsContactsCount(scClass);
	}

	/**
	 * 获取老师发送班级通知给某些人数
	 * 
	 * @param scClass
	 * @return
	 */
	public Integer getSenderUserCount(NotifyArticle notifyArticle) {
		return getDao().getSenderUserCount(notifyArticle);
	}

	public List<User> getSenderUserList(NotifyArticle notifyArticle) {
		return getDao().getSenderUserList(notifyArticle);
	}

	public List<User> getUserbyUserNames(String[] userNamesArray) {
		return getDao().getUserbyUserNames(userNamesArray);
	}

	public List<User> getUserbyIds(String[] userIdsArray) {
		 return getDao().getUserbyIds(userIdsArray);
	}

}