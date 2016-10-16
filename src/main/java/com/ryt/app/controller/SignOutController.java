package com.ryt.app.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.SpringContext;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.DateUtil;
import com.ryt.app.util.JpushUtil;
import com.ryt.web.common.QiniuManager;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.notify.NotifyArticleService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScSignInOutService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.util.PasswordUtil;

@Controller
@RequestMapping(value = "/app/sign")
public class SignOutController extends CrudController<User, AppUserService> {
	@Autowired
	ScSignInOutService signInOutservice;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	ScSchoolService scSchoolService;
	@Autowired
	AppUserService appUserService;

	/**
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "signInOutLogin.do")
	@ResponseBody
	public Map<String, Object> signInOutLogin(User user, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 登录先判断用户名是否存在
		User userEntity = this.getService().getUserByUserName(user.getUserName());
		if (userEntity == null) {
			resultMap.put("success", false);
			resultMap.put("info", "用户名或密码错误");
		} else {
			if (userEntity.getRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
				boolean isPswdCorrect = PasswordUtil.validatePassword(user.getMd5Pwd(), userEntity.getPassword());
				if (isPswdCorrect) {
					ScSchool school = scSchoolService.getSchoolBySchoolId(userEntity.getId());
					resultMap.put("success", true);
					resultMap.put("info", userEntity);
					resultMap.put("top_image", school.getSign_pic());
					resultMap.put("erweima_image", school.getErweima_pic());
					resultMap.put("pic_one", school.getAdvertisement_one());
					resultMap.put("pic_two", school.getAdvertisement_two());
					resultMap.put("pic_three", school.getAdvertisement_three());
				} else {
					resultMap.put("success", false);
					resultMap.put("info", "用户名或密码错误");
				}

			} else {
				resultMap.put("success", false);
				resultMap.put("info", "抱歉,目前只支持园长登录");
			}
		}

		return resultMap;

	}

	/**
	 * 获取签到机通知信息(默认显示最新的5条)
	 * 
	 * @param sysClassId
	 *            文章类别
	 * @param schoolId
	 *            园长id
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "getSignNotifyList.do")
	@ResponseBody
	public Map<String, Object> getSignNotifyList(Integer sysClassId, Integer schoolId, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<ArticleBase> list = signInOutservice.getSignNotifyList(sysClassId, schoolId);
			resultMap.put("success", true);
			resultMap.put("info", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", "获取通知信息失败");
		}
		return resultMap;

	}

	/**
	 * 保存签到信息
	 * 
	 * @param sysClassId
	 *            文章类别
	 * @param schoolId
	 *            园长id
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "saveSignOutInfo.do")
	@ResponseBody
	public Map<String, Object> saveSignOutInfo(ScSignInOut scSignInOut, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// sysUpload = sysUploadService.save(sysUpload);
			String cardNo = scSignInOut.getCardNo();
			List<ScStudentParents> scStudentParentList = scStudentParentsService.getByCardNo(scSignInOut);
			if (scStudentParentList != null && scStudentParentList.size() > 0) {
				ScStudentParents scStudentParents = scStudentParentList.get(0);
				User user = this.getService().get(scStudentParents.getParentId());
				String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				Date signDate = new SimpleDateFormat("yyyy-MM-dd").parse(str);
				Date signDateTime = new Date();

				List<ScSignInOut> list = signInOutservice.findBySignDate(signDate, scStudentParents.getStudentId());
				boolean isMoring = true;
				if (DateUtil.isMoring()) {
					isMoring = true;
				} else {
					isMoring = false;
				}
				// 该学生以前到过
				if (list != null && list.size() > 0) {
					if (list.size() == 1) {
						scSignInOut = list.get(0);
						scSignInOut.setCardNo(cardNo);
						scSignInOut.setUserId(user.getId());
						scSignInOut.setStudentId(scStudentParents.getStudentId());
						scSignInOut.setSignDate(signDateTime);
						scSignInOut.setUserName(user.getTrueName());
						if (isMoring) {
							if (scSignInOut.getSignInTime() == null) {
								scSignInOut.setSignInTime(signDateTime);
								scSignInOut.setId(null);
								scSignInOut.setSignOutTime(null);
								scSignInOut.setPicId(null);
								scSignInOut = signInOutservice.save(scSignInOut);
								resultMap.put("success", true);
								resultMap.put("info", scSignInOut.getId());

							} else {
								if("0003250038".equals(scSignInOut.getCardNo())||"0003242659".equals(scSignInOut.getCardNo())|| "0003017246".equals(scSignInOut.getCardNo())){
									resultMap.put("success", true);
									resultMap.put("info", scSignInOut.getId());
								}else{
									resultMap.put("success", false);
									resultMap.put("info", "您早上已经签到过了,不能重复签到");
								}
							
							}
						} else {
							if (scSignInOut.getSignOutTime() == null) {
								scSignInOut.setSignOutTime(signDateTime);
								scSignInOut.setPicId(null);
								scSignInOut.setId(null);
								scSignInOut.setSignInTime(null);
								scSignInOut = signInOutservice.save(scSignInOut);
								resultMap.put("success", true);
								resultMap.put("info", scSignInOut.getId());

							} else {
								if("0003250038".equals(scSignInOut.getCardNo())||"0003242659".equals(scSignInOut.getCardNo())|| "0003017246".equals(scSignInOut.getCardNo())){
									resultMap.put("success", true);
									resultMap.put("info", scSignInOut.getId());
								}else{
									resultMap.put("success", false);
									resultMap.put("info", "您下午已经签到过了,不能重复签到");
								}
							
							}
						}

					}
					else if(list.size() == 2){
						if("0003250038".equals(scSignInOut.getCardNo())||"0003242659".equals(scSignInOut.getCardNo())|| "0003017246".equals(scSignInOut.getCardNo())){
							resultMap.put("success", true);
							resultMap.put("info", scSignInOut.getId());
						}else{
							resultMap.put("success", false);
							resultMap.put("info", "您今天已经签到过了,不能重复签到");
						}
					}

				} else {
					// 根据时间设定不同的值
					if (DateUtil.isMoring()) {
						scSignInOut.setSignInTime(signDateTime);
					} else {
						scSignInOut.setSignOutTime(signDateTime);
					}
					// 保存签到信息
					if (user != null) {
						scSignInOut.setUserId(user.getId());
						// scSignInOut.setPicId(sysUpload.getId());
						scSignInOut.setStudentId(scStudentParents.getStudentId());
						scSignInOut.setSignDate(signDateTime);
						scSignInOut.setUserName(user.getTrueName());

						scSignInOut = signInOutservice.save(scSignInOut);
						resultMap.put("success", true);
						resultMap.put("info", scSignInOut.getId());
					} else {
						resultMap.put("success", false);
						resultMap.put("info", "家长信息未找到,请联系管理员进行绑定");
					}

				}

			} else {
				resultMap.put("success", false);
				resultMap.put("info", "卡号未绑定学生,请进行绑定");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", "签到失败");
		}
		return resultMap;

	}

	@RequestMapping("/saveSignPicture.do")
	public @ResponseBody Map<String, Object> saveSignPicture(SysUpload sysUpload, Integer signOutId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ScSignInOut scSignInOut = signInOutservice.get(signOutId);
			// 没有保存过则进行保存
			if (scSignInOut.getPicId() == null || scSignInOut.getPicId() == 0) {
				sysUpload = sysUploadService.save(sysUpload);
				scSignInOut.setPicId(sysUpload.getId());
				scSignInOut = signInOutservice.update(scSignInOut);
				resultMap.put("success", true);
				resultMap.put("info", scSignInOut);
				Integer studentId = scSignInOut.getStudentId();
				// 查询所有与孩子相关联的家长,发送通知
				List<User> parentsList = appUserService.getChildParents(studentId);
				List<String> temp = new ArrayList<String>();

				String[] parentIds = new String[] {};
				for (int i = 0; parentsList != null && i < parentsList.size(); i++) {
					temp.add(parentsList.get(i).getUserName());
				}
				if (temp.size() > 0) {
					parentIds = (String[]) temp.toArray(new String[temp.size()]);
					NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
					NotifyArticle notifyArticle = new NotifyArticle();
					
					JpushUtil.sendByAudienceList(parentIds, RuyTonAppContants.JPUSH_TYPE_SIGN, "您的孩子签到啦,点击查看详情",0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", "保存图片信息失败");
		}

		return resultMap;

	}

	@RequestMapping("/getQiniuPublicToken.do")
	public @ResponseBody Map<String, String> getQiniuPublicToken(String fileName) {
		QiniuManager qManager = new QiniuManager();
		Map<String, String> token = new HashMap<String, String>();
		token.put("info", qManager.getQiniuPublicUpdateToken(fileName));
		return token;
	}

}
