package com.ryt.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.SpringContext;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ryt.app.ailpay.MD5;
import com.ryt.app.commom.OpenfireConstants;
import com.ryt.app.commom.PayConstants;
import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.service.AppArtcileBaseService;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.DateUtil;
import com.ryt.app.util.OpenfireUtil;
import com.ryt.app.util.SendSmsUtil;
import com.ryt.web.common.Md5Encrypt;
import com.ryt.web.common.MemcacheManager;
import com.ryt.web.common.PlUploadUtil;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.article.ArticleLike;
import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceChargeOrderSch;
import com.ryt.web.entity.finance.FinanceChargeProduct;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.version.SysVersion;
import com.ryt.web.service.article.ArticleCommentService;
import com.ryt.web.service.article.ArticleLikeService;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.finance.FinanceChargeProductService;
import com.ryt.web.service.notify.NotifyArticleService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.version.SysVersionService;
import com.ryt.web.util.PasswordUtil;

@Controller
@RequestMapping(value = "/app/api/account")
public class AppAccountController extends CrudController<User, AppUserService> {
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	AppArtcileBaseService appArtcileBaseService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	SysClassService sysClassService;
	@Autowired
	FinanceChargeOrderService financeChargeOrderService;
	@Autowired
	FinanceChargeProductService financeChargeProductService;

	/**
	 * 修改用户昵称
	 * 
	 * @param userId
	 *            用户id
	 * @param nickName
	 *            昵称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "updateNickName.do")
	@ResponseBody
	public Map<String, Object> updateNickName(Integer userId, String nickName, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			User user = this.get(userId);
			user.setNickName(nickName);
			User newUser = this.getService().update(user);
			resultMap.put("success", true);
			resultMap.put("info", newUser);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 *            用户id
	 * @param nickName
	 *            昵称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "updatePwd.do")
	@ResponseBody
	public Map<String, Object> updatePwd(User user, String newMd5Pwd, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			User oldUser = getService().getUserByUserName(user.getUserName());
			if (oldUser != null) {
				boolean isPswdCorrect = PasswordUtil.validatePassword(user.getMd5Pwd(), oldUser.getUserPwd());
				if (isPswdCorrect) {
					oldUser.setUserPwd(PasswordUtil.createHash(newMd5Pwd));
					// 清空token
					oldUser.setToken("");
					User newUser = this.getService().update(oldUser);
					resultMap.put("success", true);
					resultMap.put("info", newUser);
				} else {
					resultMap.put("success", false);
					resultMap.put("info", "原密码不正确");
					resultMap.put("token", request.getAttribute("token"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		return resultMap;

	}

	/**
	 * 获取用户和孩子间关系字段名称集合
	 * 
	 * @param userId
	 *            用户id
	 * @param nickName
	 *            昵称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "getAllRelativeName.do")
	@ResponseBody
	public Map<String, Object> getAllRelativeName(Integer roleProperty, NotifyArticle notifyArticle,
			Integer sysClassTypeId, HttpServletRequest request, HttpServletResponse response)
					throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysClassService sysClassService = SpringContext.getBean(SysClassService.class);
			NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
			List<SysClass> list = new ArrayList<SysClass>();
			List<SysClass> tempList = sysClassService.getAllRelativeName(sysClassTypeId);

			for (SysClass sysClass : tempList) {
				notifyArticle.setNotifyType(sysClass.getId());
				if (sysClass.getId() == RuyTonAppContants.NOTIFY_TYPE_CLASS) {
					sysClass.setUnReadCount(
							notifyArticleService.getClassNotifyUnreadCount(notifyArticle, roleProperty));
				} else if (sysClass.getId() == RuyTonAppContants.NOTIFY_TYPE_SCHOOL) {
					sysClass.setUnReadCount(
							notifyArticleService.getSchoolNotifyUnreadCount(notifyArticle, roleProperty));
				}
				list.add(sysClass);
			}
			resultMap.put("success", true);
			resultMap.put("info", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 邀请家人
	 * 
	 * @param currentUserName
	 *            当前邀请人的用户名
	 * @param user
	 *            被邀请人的一些信息
	 * @param relativeId
	 *            关系id
	 * @param 邀请人的手机号码name
	 * @param childName
	 *            小孩名字
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "inviteFamily.do")
	@ResponseBody
	public Map<String, Object> inviteFamily(String currentUserName, User user, Integer relativeId, String name,
			String childName, HttpServletRequest request, HttpServletResponse response)
					throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 邀请的家人未注册
			User family = this.getService().getUserByUserName(user.getUserName());
			if (family == null) {
				// 72 、74、76为女人
				int sex = 0;
				if (relativeId == 72 || relativeId == 74 || relativeId == 76) {
					sex = 0;
				} else {
					sex = 1;
				}
				// 随机生成6位数密码
				Random random = new Random();
				int randomPwd = random.nextInt(899999);
				randomPwd = randomPwd + 100000;
				System.out.println(randomPwd);
				String hashCodePwd = PasswordUtil.createHash(Md5Encrypt.encrypt(String.valueOf(randomPwd)));
				user.setUserPwd(hashCodePwd);
				user.setMobilePhone(user.getUserName());
				user.setRoleProperty(RuyTonAppContants.USER_TYPE_PARENT);
				user.setSex(sex);
				// 返回新的user对象
				user = this.getService().save(user);
				// 保存学生和家长关系表
				ScStudentParentsService scStudentParentsService = SpringContext.getBean(ScStudentParentsService.class);
				ScStudentParents scStudentParents = new ScStudentParents();
				scStudentParents.setStudentId(user.getChildId());
				scStudentParents.setParentId(user.getId());
				// 家长和学生之间的关系
				scStudentParents.setRelatedTypeId(relativeId);
				scStudentParentsService.save(scStudentParents);
				// 发送验证码给家人
				resultMap = SendSmsUtil.sendSmsToFamily(String.valueOf(randomPwd), name, user.getTrueName(), childName,
						user.getRelativeName(), user.getUserName());

			} else {
				if (currentUserName.equals(family.getUserName())) {
					resultMap.put("success", false);
					resultMap.put("info", "您不能自己邀请自己为家人");
				} else {
					// 查询该用户是否是当前孩子的当前家人类型
					Integer familyId = family.getId();
					// TODO 只要小孩和该用户已存在关系，则不允许重复添加了
					List<ScStudentParents> list = scStudentParentsService.getScStudentsParents(user.getChildId(),
							familyId, 0);
					if (list != null && list.size() > 0) {
						resultMap.put("success", false);
						resultMap.put("info", "该用户已是您的家人,不能重复添加");
					} else {
						ScStudentParents scStudentParents = new ScStudentParents();
						scStudentParents.setStudentId(user.getChildId());
						scStudentParents.setParentId(familyId);
						// 家长和学生之间的关系
						scStudentParents.setRelatedTypeId(relativeId);
						scStudentParentsService.save(scStudentParents);
						resultMap.put("success", true);
						resultMap.put("info", "宝贝关系绑定成功");
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 获取当前用户自己的所有信息
	 * 
	 * @param oldArticleBase
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMyCircleInfoList.do")
	@ResponseBody
	public Map<String, Object> getMyCircleInfoList(ArticleBase oldArticleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer currentUserId = oldArticleBase.getUserId();
			AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
			Integer totalPage = 0;
			// 查找所有可见的朋友圈数量
			Integer totalCount = appArtcileBaseService.getAllMyCircleListCount(oldArticleBase);
			// 分页查询朋友圈
			List<ArticleBase> list = appArtcileBaseService.findMyCircleInfoByPage(oldArticleBase);
			JSONArray jsonArray = new JSONArray();
			for (ArticleBase articleBase : list) {
				// 可能有多张图片,以逗号分隔
				if (!StringUtils.isEmpty(articleBase.getPicId())) {
					String[] pics = articleBase.getPicId().split(",");
					List<SysUpload> uploads = appArtcileBaseService.getPicsUrlByIds(pics);
					articleBase.setUploads(uploads);
				} else {
					// 给他一个空的集合
					articleBase.setUploads(new ArrayList<SysUpload>());
				}

				// 查找该用户对这篇文章是否点赞
				ArticleLikeService articleLikeService = SpringContext.getBean(ArticleLikeService.class);
				ArticleLike articleLike = null;
				List<User> articleLikeUsers = null;
				if (articleBase.getParentId() == 0) {
					articleLike = articleLikeService.getArticle(articleBase.getId(), currentUserId);
					// 设置该文章点赞次数
					articleLikeUsers = articleLikeService.getArticleLikeUsers(articleBase.getId());
					//
				} else {
					articleLike = articleLikeService.getArticle(articleBase.getParentId(), currentUserId);
					articleLikeUsers = articleLikeService.getArticleLikeUsers(articleBase.getParentId());
				}
				// 判断当前用户是否已经点赞
				if (articleLike != null) {
					articleBase.setLikeArticle(true);
				} else {
					articleBase.setLikeArticle(false);
				}
				// 设置点赞个数
				if (articleLikeUsers != null && articleLikeUsers.size() > 0) {
					articleBase.setArticleLikeCount(articleLikeUsers.size());
				} else {
					articleBase.setArticleLikeCount(0);
				}
				// 设置点赞人员集合
				articleBase.setArticleLikeUsers(articleLikeUsers);

				// 查找该文章的所有评论信息
				ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);
				List<ArticleComment> articleCommentList = null;
				if (articleBase.getParentId() == 0) {
					articleCommentList = articleCommentService.finAllComments(articleBase.getId());
				} else {
					articleCommentList = articleCommentService.finAllComments(articleBase.getParentId());
				}

				// 重新封装一个集合，处理一些特殊情况下的信息
				List<ArticleComment> newCommentList = new ArrayList<ArticleComment>();
				for (ArticleComment comment : articleCommentList) {
					// 有回复形式的内容
					if (comment.getParentId() != 0) {
						ArticleComment parentComment = articleCommentService
								.findCommentByParentId(comment.getParentId());
						comment.setSendName(comment.getUserName());
						comment.setGetterName(parentComment.getUserName());
					}
					newCommentList.add(comment);
				}

				articleBase.setArticleComments(newCommentList);

				AppUserService appUserService = SpringContext.getBean(AppUserService.class);
				User user = appUserService.get(articleBase.getUserId());
				if (user != null) {
					if (StringUtils.isEmpty(user.getDefaultAvatar())) {
						SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
						SysUpload sysUpload = sysUploadService.getByUserId(user.getAvatar());
						if (sysUpload != null) {
							articleBase.setArtHead(sysUpload.getFileFullPath());
							articleBase.setDefaultAvatar(false);
						}
					} else {
						articleBase.setArtHead(user.getDefaultAvatar());
						articleBase.setDefaultAvatar(true);
					}

					// 設置發送文章的用戶角色
					articleBase.setSendRoleProperty(user.getRoleProperty());
					// 设置文章发表者的昵称，如果没有昵称则用真实的姓名
					if (StringUtils.isEmpty(user.getNickName())) {
						articleBase.setNickName(user.getTrueName());
					} else {
						articleBase.setNickName(user.getNickName());
					}
					// 登錄者為家長的時候,需要設置相關小孩的信息
					if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
						User child = appArtcileBaseService.getUserById(articleBase.getStudentId());
						if (child != null) {
							articleBase.setRelateName(appUserService.getUserRelativeName(user.getId(), child.getId()));
							// 设置孩子的信息
							articleBase.setChildName(child.getTrueName());
						}

					}
					jsonArray.add(articleBase);
				} else {
					System.out.println(articleBase.getUserId());
				}

			}
			// 获得总页数
			totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
					: totalCount / oldArticleBase.getPageCount() + 1;

			resultMap.put("totalPage", totalPage);
			resultMap.put("page", oldArticleBase.getPage());
			resultMap.put("success", true);
			resultMap.put("info", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));

		return resultMap;
	}

	/**
	 * 获取该用户能查看到的通讯录信息
	 * 
	 * @param userId
	 *            用户id
	 * @param nickName
	 *            昵称
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "getContacts.do")
	@ResponseBody
	public Map<String, Object> getContacts(User user, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 根据不同的用户,可以看到的老师信息不同,园长和老师可以看到全校老师的信息,
			// 家长用户只能看到其孩子相关联的班级老师信息
			List<User> parentList = null;
			// service中根据不同的roleproperty去获取信息
			List<User> tempTeacherList = this.getService().getTeacherContacts(user);
			List<User> teacherList = new ArrayList<User>();
			List<User> resultParentsList = new ArrayList<User>();
			SysUploadService uploadService = SpringContext.getBean(SysUploadService.class);

			// 重新封装老师头像集合
			for (User entity : tempTeacherList) {
				// 网络头像
				if (StringUtils.isEmpty(entity.getDefaultAvatar())) {
					SysUpload sysUpload = uploadService.get(entity.getAvatar());
					if (sysUpload != null) {
						entity.setArtHead(sysUpload.getFileFullPath());
					} else {
						if (RuyTonAppContants.USER_SEX_WOMAN == entity.getSex()) {
							entity.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
						} else if (RuyTonAppContants.USER_SEX_MAN == entity.getSex()) {
							entity.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
						}
					}
				} else {
					entity.setArtHead(entity.getDefaultAvatar());
				}

				teacherList.add(entity);
			}

			List<ScClass> classList = null;
			// 老师 则获取他负责相关的班级
			if (user.getRoleProperty() == 2) {
				classList = this.getService().getClassByTeacher(user);
				for (ScClass scClass : classList) {
					resultParentsList = new ArrayList<User>();
					parentList = this.getService().getClassParentsContacts(scClass.getId(), user.getUserId());
					for (User entity2 : parentList) {
						// 网络头像
						if (StringUtils.isEmpty(entity2.getDefaultAvatar())) {
							SysUpload sysUpload = uploadService.get(entity2.getAvatar());
							if (sysUpload != null) {
								entity2.setArtHead(sysUpload.getFileFullPath());
							} else {
								if (RuyTonAppContants.USER_SEX_WOMAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
								} else if (RuyTonAppContants.USER_SEX_MAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
								}
							}
						} else {
							entity2.setArtHead(entity2.getDefaultAvatar());
						}
						List<User> childList = getService().getChildrens(entity2.getId());
						List<User> childrens = new ArrayList<User>();
						for (User child : childList) {
							ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
							// 如果是当前班级则加入
							if (scStudent.getClassId() == scClass.getId()) {
								childrens.add(child);
							}
						}
						entity2.setChildrens(childrens);
						resultParentsList.add(entity2);
					}
					scClass.setParentsContacts(resultParentsList);
				}

			} else if (user.getRoleProperty() == 1) {
				classList = this.getService().getClassByParents(user);
				for (ScClass scClass : classList) {
					resultParentsList = new ArrayList<User>();
					parentList = this.getService().getClassParentsContacts(scClass.getId(), user.getUserId());
					for (User entity2 : parentList) {
						if (StringUtils.isEmpty(entity2.getDefaultAvatar())) {
							SysUpload sysUpload = uploadService.get(entity2.getAvatar());
							if (sysUpload != null) {
								entity2.setArtHead(sysUpload.getFileFullPath());
							} else {
								if (RuyTonAppContants.USER_SEX_WOMAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
								} else if (RuyTonAppContants.USER_SEX_MAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
								}
							}
						} else {
							entity2.setArtHead(entity2.getDefaultAvatar());
						}
						List<User> childList = getService().getChildrens(entity2.getId());
						List<User> childrens = new ArrayList<User>();
						for (User child : childList) {
							ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
							// 如果是当前班级则加入
							if (scStudent.getClassId() == scClass.getId()) {
								childrens.add(child);
							}
						}
						entity2.setChildrens(childrens);
						resultParentsList.add(entity2);
					}
					scClass.setParentsContacts(resultParentsList);
				}
			} else if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
				List<ScClass> tempClassList = this.getService().getClassBySchool(user);

				classList = new ArrayList<ScClass>();
				for (ScClass scClass : tempClassList) {
					resultParentsList = new ArrayList<User>();
					parentList = this.getService().getClassParentsContacts(scClass.getId(), user.getUserId());
					for (User entity2 : parentList) {
						if (StringUtils.isEmpty(entity2.getDefaultAvatar())) {
							SysUpload sysUpload = uploadService.get(entity2.getAvatar());
							if (sysUpload != null) {
								entity2.setArtHead(sysUpload.getFileFullPath());
							} else {
								if (RuyTonAppContants.USER_SEX_WOMAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
								} else if (RuyTonAppContants.USER_SEX_MAN == entity2.getSex()) {
									entity2.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
								}
							}
						} else {
							entity2.setArtHead(entity2.getDefaultAvatar());
						}
						List<User> childList = getService().getChildrens(entity2.getId());
						List<User> childrens = new ArrayList<User>();
						for (User child : childList) {
							ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
							// 如果是当前班级则加入
							if (scStudent.getClassId() == scClass.getId()) {
								childrens.add(child);
							}
						}
						entity2.setChildrens(childrens);
						resultParentsList.add(entity2);
					}
					scClass.setParentsContacts(resultParentsList);
					classList.add(scClass);
				}
			}

			resultMap.put("classList", classList);
			resultMap.put("success", true);
			resultMap.put("teacherContacts", teacherList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 修改用户头像信息
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/updateParentsHead.do")
	public @ResponseBody Map<String, Object> updateParentsHead(User user, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppUserService appUserService = SpringContext.getBean(AppUserService.class);
		try {
			User newUser = appUserService.get(user.getId());
			if (newUser != null) {
				newUser.setDefaultAvatar(user.getDefaultAvatar());
				newUser = appUserService.update(newUser);
				resultMap.put("success", true);
				resultMap.put("info", newUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 将base64图片转成file文件(上传用户的图片方法)
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping(value = "/userBase64ToImage.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> base64ToImage(User user, InputStream data, int cropWidth, int cropHeight,
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, String> uploadParams = PlUploadUtil
					.getInstance(request.getSession().getServletContext().getRealPath("")).getUploadParam();
			// 绝对路径保存到电脑上
			String dirPath = request.getSession().getServletContext().getRealPath(uploadParams.get("filePath"));
			// 相对路径保存到数据库上
			String relativePath = uploadParams.get("relativeFilePath");
			String fileName = new Date().getTime() + ".jpg";
			File file = new File(fileName);
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(dirPath + "/" + fileName);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = data.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			data.close();

			SysUpload upload = new SysUpload();
			upload.setFileSize((int) file.length() / 1024);
			upload.setFileExt("jpg");
			upload.setFileWidth(cropWidth);
			upload.setFileHeight(cropHeight);
			upload.setModuleId("app");
			upload.setFuncId("home");
			upload.setFileFullPath(relativePath + "/" + fileName);
			upload.setCreatedBy(user.getUsername());
			upload.setFileTypes("jpg");
			upload.setUserId(user.getUserId());
			upload.setUserName(user.getUsername());
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			// 保存到sysuoload表中
			upload = sysUploadService.save(upload);
			AppUserService appUserService = SpringContext.getBean(AppUserService.class);
			// 更改用户头像信息
			User newUser = appUserService.get(user.getUserId());
			if (newUser != null) {
				newUser.setDefaultAvatar("");
				newUser.setAvatar(upload.getId());
				newUser.setArtHead(upload.getFileFullPath());
				newUser = appUserService.update(newUser);
			}
			resultMap.put("success", true);
			resultMap.put("info", newUser);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 保存意见反馈信息
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/writeFeedback.do")
	public @ResponseBody Map<String, Object> submitSuggest(ArticleBase articleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
		try {
			articleBase = appArtcileBaseService.save(articleBase);
			resultMap.put("success", true);
			resultMap.put("info", "提交成功");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 分页查询该用户的所有意见反馈信息
	 * 
	 * @param roleProperty
	 *            用户角色信息
	 * @param 文章信息内容
	 * @return
	 */
	@RequestMapping("/findFeedBackByPage.do")
	public @ResponseBody Map<String, Object> findFeedBackByPage(ArticleBase articleBase, HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);

		try {
			List<ArticleBase> list = new ArrayList<ArticleBase>();
			List<ArticleBase> temp = null;
			Integer totalPage = 0;
			Integer totalCount = 0;

			temp = appArtcileBaseService.findFeedBackByPage(articleBase);
			for (ArticleBase base : temp) {
				// 查找该文章的所有评论信息
				ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);
				List<ArticleComment> articleCommentList = articleCommentService.finAllComments(base.getId());
				base.setArticleComments(articleCommentList);
				list.add(base);

			}

			// 当第一页的时候获取下它的总页数，必要多次执行
			if (articleBase.getPage() == 1) {
				totalCount = appArtcileBaseService.findFeedBackCount(articleBase);
				// 获得总页数
				totalPage = totalCount % articleBase.getPageCount() == 0 ? totalCount / articleBase.getPageCount()
						: totalCount / articleBase.getPageCount() + 1;
				resultMap.put("totalPage", totalPage);
			}
			resultMap.put("success", true);
			resultMap.put("info", list);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 关于政教通
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/aboutMe.do")
	public @ResponseBody Map<String, Object> aboutMe(Integer sysClassType, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
		try {
			List<ArticleBase> list = appArtcileBaseService.aboutMe(sysClassType);
			resultMap.put("success", true);
			resultMap.put("info", list);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 获取服务器app版本
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/getVersion.do")
	public @ResponseBody Map<String, Object> getVersion(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysVersionService sysVersionService = SpringContext.getBean(SysVersionService.class);
		try {
			// 倒叙查询版本信息,最新的在最前面
			List<SysVersion> list = sysVersionService.getVersion();
			resultMap.put("success", true);
			if (list != null && list.size() > 0) {
				resultMap.put("info", list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 获取会员购买的展示信息，包括产品list，购买对象信息、以及支付方式
	 * 
	 * @param user
	 *            用户的信息
	 * @param sysClassTypeId
	 *            支付类别id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getMemberBuyInfo.do")
	public @ResponseBody Map<String, Object> getMemberBuyInfo(Integer sysClassTypeId, User user,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// List<User> userList =
			// this.getService().getChildParents(user.getChildId());
			// 通过用户id查询他所关联的所有小孩
			List<User> userList = this.getService().getChildrens(user.getUserId());
			List<User> list = new ArrayList<User>();
			for (User child : userList) {
				// parent.setRelativeName(this.getService().getUserRelativeName(parent.getId(),
				// user.getChildId()));
				// 设置头像
				if (StringUtils.isEmpty(child.getDefaultAvatar())) {
					SysUpload sysUpload = sysUploadService.get(child.getAvatar());
					if (sysUpload != null) {
						child.setArtHead(sysUpload.getFileFullPath());
					}
				} else {
					child.setArtHead(child.getDefaultAvatar());
				}
				list.add(child);
			}

			// 获取产品
			List<FinanceChargeProduct> productList = null;
			if (MemcacheManager.get("pay_product") != null) {
				productList = (List<FinanceChargeProduct>) MemcacheManager.get("pay_product");
			} else {
				productList = financeChargeProductService.getAllProductList();
				MemcacheManager.set("pay_product", productList, RuyTonAppContants.COOK_MEMCAHE_TIME);
			}
			// 支付类别
			List<SysClass> payList = null;
			if (MemcacheManager.get("sysClass_" + sysClassTypeId) != null) {
				payList = (List<SysClass>) MemcacheManager.get("sysClass_" + sysClassTypeId);
			} else {
				payList = sysClassService.getAllRelativeName(sysClassTypeId);
				MemcacheManager.set("sysClass_" + sysClassTypeId, payList, RuyTonAppContants.COOK_MEMCAHE_TIME);
			}

			resultMap.put("success", true);
			resultMap.put("familyList", list);
			resultMap.put("payList", payList);
			resultMap.put("productList", productList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 
	 * 保存订单
	 * 
	 * @param order
	 *            订单内容
	 * @return
	 */
	@RequestMapping("/saveFinanceChargeOrder.do")
	public @ResponseBody Map<String, Object> saveFinanceChargeOrder(FinanceChargeOrder order,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			order = financeChargeOrderService.save(order);
			resultMap.put("success", true);
			resultMap.put("info", order);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 
	 * 用户绑定签到卡
	 * 
	 * @param order
	 *            订单内容
	 * @return
	 */
	@RequestMapping("/updateCardNo.do")
	public @ResponseBody Map<String, Object> updateCardNo(ScStudentParents scStudentParents,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boolean cardExsist = scStudentParentsService.findCardExsist(scStudentParents);
			if (!cardExsist) {
				List<ScStudentParents> list = scStudentParentsService.getScStudentsParents(
						scStudentParents.getStudentId(), scStudentParents.getParentId(),
						scStudentParents.getRelatedTypeId());
				if (list != null && list.size() > 0) {
					ScStudentParents entity = list.get(0);
					entity.setCardNo(scStudentParents.getCardNo());
					// 对于邦卡时间,只需设置一次即可
					if (StringUtils.isEmpty(entity.getBindCardDate())) {
						entity.setBindCardDate(DateUtil.formateYMD(new Date().getTime()));
					}
					entity = scStudentParentsService.update(entity);
					resultMap.put("success", true);
					resultMap.put("info", entity);
				} else {
					resultMap.put("success", false);
					resultMap.put("info", "未能查找到您和孩子的相关数据");
				}
			} else {
				resultMap.put("success", false);
				resultMap.put("info", "卡号已存在,请联系管理人员");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	// 获取所有用户的订单
	@RequestMapping("/getMyOrderList.do")
	public @ResponseBody Map<String, Object> getOrderLigetMyOrderListst(HttpServletRequest request,
			FinanceChargeOrderSch searchEntitySch) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<FinanceChargeOrder> tempList = financeChargeOrderService.getMyOrderList(searchEntitySch);
			List<FinanceChargeOrder> list = new ArrayList<FinanceChargeOrder>();
			for (FinanceChargeOrder order : tempList) {
				String userNames = order.getChargeAccountId();
				if (!StringUtils.isEmpty(userNames)) {
					String[] userIdsArray = userNames.split(",");
					List<User> userList = this.getService().getUserbyIds(userIdsArray);
					order.setBuyerList(userList);
				}
				FinanceChargeProduct product = financeChargeProductService.get(order.getProductId());
				if (product != null) {
					order.setProduct(product);
				}
				SysClass sysClass = sysClassService.get(order.getPayType());
				if (sysClass != null) {
					order.setPayName(sysClass.getClassName());
				}
				list.add(order);

			}
			resultMap.put("success", true);
			resultMap.put("info", list);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param searchEntitySch
	 * @return
	 */
	@RequestMapping("/cancelOrder.do")
	public @ResponseBody Map<String, Object> cancelOrder(HttpServletRequest request,
			FinanceChargeOrderSch searchEntitySch) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			FinanceChargeOrder order = financeChargeOrderService.get(searchEntitySch.getIdSch());
			order.setPayStatus(RuyTonAppContants.PRODUCT_PAY_STATUS_PAY_CANCEL);
			order = financeChargeOrderService.update(order);
			resultMap.put("success", true);
			resultMap.put("info", order);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 修改订单
	 * 
	 * @param request
	 * @param searchEntitySch
	 * @return
	 */
	@RequestMapping("/updateFinanceChargeOrder.do")
	public @ResponseBody Map<String, Object> updateFinanceChargeOrder(HttpServletRequest request,
			FinanceChargeOrder financeChargeOrder) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 如果他修改的方式是微信支付，则需要获取订单号prepayId和一些参数存入数据库
			if (financeChargeOrder.getPayType() == PayConstants.WEIXIN_PAY_TYPE) {

			}
			FinanceChargeOrder order = financeChargeOrderService.get(financeChargeOrder.getId());
			order.setProductId(financeChargeOrder.getProductId());
			order.setQuantity(financeChargeOrder.getQuantity());
			order.setTotalFee(financeChargeOrder.getTotalFee());
			order.setChargeAccountId(financeChargeOrder.getChargeAccountId());
			order.setPayType(financeChargeOrder.getPayType());
			order = financeChargeOrderService.update(order);
			String userNames = order.getChargeAccountId();
			if (!StringUtils.isEmpty(userNames)) {
				String[] userNamsesArray = userNames.split(",");
				List<User> userList = this.getService().getUserbyIds(userNamsesArray);
				order.setBuyerList(userList);
			}
			FinanceChargeProduct product = financeChargeProductService.get(order.getProductId());
			// 重新封装一些信息给前台显示
			if (product != null) {
				order.setProduct(product);
			}
			SysClass sysClass = sysClassService.get(order.getPayType());
			if (sysClass != null) {
				order.setPayName(sysClass.getClassName());
			}
			resultMap.put("success", true);
			resultMap.put("info", order);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param searchEntitySch
	 * @param prepayId
	 *            订单号
	 * @return
	 */
	@RequestMapping("/savePrepayId.do")
	public @ResponseBody Map<String, Object> savePrepayId(HttpServletRequest request,
			FinanceChargeOrder searchEntitySch) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			FinanceChargeOrder order = financeChargeOrderService.get(searchEntitySch.getId());
			order.setPrepayId(searchEntitySch.getPrepayId());
			order.setTradeNo(searchEntitySch.getTradeNo());
			order = financeChargeOrderService.update(order);
			resultMap.put("success", true);
			resultMap.put("info", order);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 通过班级id查询班级下的所有家长
	 * 
	 * @param request
	 * @param searchEntitySch
	 * @param prepayId
	 *            订单号
	 * @return
	 */
	@RequestMapping("/getContactsByClassId.do")
	public @ResponseBody Map<String, Object> getContactsByClassId(HttpServletRequest request, Integer classId,
			Integer userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<User> resultParentsList = new ArrayList<User>();
			List<User> parentList = this.getService().getClassParentsContacts(classId, userId);
			for (User entity2 : parentList) {
				if (StringUtils.isEmpty(entity2.getDefaultAvatar())) {
					SysUpload sysUpload = sysUploadService.get(entity2.getAvatar());
					if (sysUpload != null) {
						entity2.setArtHead(sysUpload.getFileFullPath());
					} else {
						if (RuyTonAppContants.USER_SEX_WOMAN == entity2.getSex()) {
							entity2.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
						} else if (RuyTonAppContants.USER_SEX_MAN == entity2.getSex()) {
							entity2.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
						}
					}
				} else {
					entity2.setArtHead(entity2.getDefaultAvatar());
				}
				List<User> childList = getService().getChildrens(entity2.getId());
				List<User> childrens = new ArrayList<User>();
				for (User child : childList) {
					ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
					// 如果是当前班级则加入
					if (scStudent.getClassId() == classId) {
						childrens.add(child);
					}
				}
				entity2.setChildrens(childrens);
				resultParentsList.add(entity2);
			}
			resultMap.put("success", true);
			resultMap.put("info", resultParentsList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

}
