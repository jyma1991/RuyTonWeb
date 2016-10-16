package com.ryt.web.service.user;


import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.user.UserDao;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.util.PasswordUtil;

@Service
public class UserService extends CrudService<User, UserDao> {

	/**
	 * 重置用户密码
	 * @param user
	 * @return 返回明文密码
	 */
	public String resetUserPassword(User user){
		
		String password = createNewPswd();
		
		String hash = PasswordUtil.createStorePswd(password);
		
		user.setUserPwd(hash);
		
		this.update(user);
		
		return password;
	}
	
	public void updateUserPassword(User user,String md5Pswd){    	
		user.setUserPwd(PasswordUtil.createHash(md5Pswd));    	
    	update(user);
	}
	
	/**
	 * 生成随机密码,由三个小写字母+三个数字组成
	 * @return
	 */
	public static String createNewPswd(){
		StringBuilder pswd = new StringBuilder();
		
		// 随机三个小写英文字母
		for (int i = 0; i < 3; i++) {
			// ascii码 97~122
			char ascii = (char) ((int)(Math.random() * 26) + 97);
			pswd.append(ascii);
		}
		// 随机三个1~9的数
		for (int i = 0; i < 3; i++) {
			int num = (int)(Math.random() * 9) + 1;
			pswd.append(num);
		}
		
		return pswd.toString();
	}
	  /**
     * 获取用户系统功能
     * @param username
     * @return
     */
    public User getUserByUserName(String username) {
        return this.getDao().getUserByUserName(username);
    }
	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public List<User> listAllUserByUserName(String userName){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("userName", userName));
		return this.find(query);
	}
	
	/**
	 * 根据班级编号查询家长用户
	 * @param classId
	 * @return
	 */
	public List<User> getParentsUserByClassId(int classId){
		return this.getDao().getParentsUserByClassId(classId);
	}
	
	public boolean checkUserExist(UserSch userSch){
			if(this.findTotalCount(ExpressionQuery.buildQueryAll().addAnnotionExpression(userSch))>0)
				return true;
			else {
				return false;
			}
	}
	
	/**
	 * 通过角色类型来查询角色用户
	 * @param roleProperty
	 * @return
	 */
	public List<User> getUsersByRoleProperty(int roleProperty){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("roleProperty", roleProperty));
		query.add(new ValueExpression("isDeleted", 0));
		return this.find(query);
	}
	
	/**
	 * 通过班级id查询学生用户
	 * @param classId
	 * @return
	 */
	public List<User> getStudentsByClassId(Integer classId){
		return this.getDao().getStudentsByClassId(classId);
	}
}