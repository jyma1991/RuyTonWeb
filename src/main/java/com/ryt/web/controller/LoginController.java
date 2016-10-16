package com.ryt.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.ValidateHolder;
import org.durcframework.core.controller.BaseController;
import org.durcframework.core.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.common.RMSContext;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;
import com.ryt.web.util.PasswordUtil;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private UserService UserService;

	/*****************************************************************************
	 * @author :xiaoren
	 * @version : V1.0
	 * @param :
	 *            User
	 * @Description : check in username and password
	 * @Date : 2015年10月28日 下午12:43:47
	 ******************************************************************************/
	@RequestMapping("login.do")
	public @ResponseBody MessageResult login(User backUser, HttpServletRequest request) {
	
		ValidateHolder validateHolder = ValidateUtil.validate(backUser);
		if (validateHolder.isSuccess()) {
			if (StringUtils.hasText(backUser.getUserName())) {

				User user = UserService.getUserByUserName(backUser.getUserName());
				if (user == null) {
					return error("用户名或密码错误！");
				}
				
				if (user.getRoleProperty()!=null) {
					if(user.getRoleProperty()<=3 )
					{
						return error("抱歉，您不是代理商或者园长用户，无权登录！");
					}
				}
				
				//微信端登录
				if(request.getHeader("referer").contains("/chat") && user.getRoleProperty()!=8){
					return error("非代理商用户不能登陆！");
				}
				
				String password = backUser.getUserPwd();
				String correctHash = user.getUserPwd();

				boolean isPswdCorrect = PasswordUtil.validatePassword(password, correctHash);

				if (isPswdCorrect) {
					doLogin(user);
					return success();
				}
			}
		}

		return error("用户名或密码错误！");
	}

	/*****************************************************************************
	 * @author :xiaoren
	 * @version : V1.0
	 * @param :
	 *            User
	 * @Description : add user and updata user
	 * @Date : 2015年10月28日 下午12:43:15
	 ******************************************************************************/

	private void doLogin(User user) {
		RMSContext.getInstance().refreshUserRightData(user.getUserName());
		user.setLastLoginDate(new Date());
		UserContext.getInstance().setUser(user);
		UserService.update(user);
	}

	/*****************************************************************************
	 * @author :xiaoren
	 * @version : V1.0
	 * @param :
	 *            null
	 * @Description : login out action
	 * @Date : 2015年10月28日 下午12:42:09
	 ******************************************************************************/
	@RequestMapping("logout.do")
	public @ResponseBody MessageResult logout() {
		RMSContext.getInstance().clearCurrentUserRightData();
		UserContext.getInstance().setUser(null);
		return success();
	}

	/*****************************************************************************
	 * @author :xiaoren
	 * @version : V1.0
	 * @param :
	 *            null
	 * @Description : updatePswd
	 * @Date : 2015年10月28日 下午12:42:09
	 ******************************************************************************/

	@RequestMapping("updatePswd.do")
	public @ResponseBody String updatePswd(User backUser, String newpassword) {

		User user = UserService.getUserByUserName(backUser.getUserName());
		if (user == null) {
			return "无此用户";
		}

		String password = backUser.getUserPwd();
		String correctHash = user.getUserPwd();

		boolean isPswdCorrect = PasswordUtil.validatePassword(password, correctHash);
		if (isPswdCorrect) {
			UserService.updateUserPassword(backUser, newpassword);
			return "密码修改成功";
		} else {
			return "密码错误";
		}

	}

}
