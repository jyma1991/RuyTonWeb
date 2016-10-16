package com.ryt.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.SpringContext;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.service.AppArtcileBaseService;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.DateUtil;
import com.ryt.web.common.MemcacheManager;
import com.ryt.web.common.QiniuManager;
import com.ryt.web.controller.sys.SysUploadController;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleBaseSch;
import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.article.ArticleLike;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.area.AreaCityService;
import com.ryt.web.service.area.AreaProvinceService;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.article.ArticleCommentService;
import com.ryt.web.service.article.ArticleLikeService;
import com.ryt.web.service.notify.NotifyArticleService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.user.UserService;

@Controller
@RequestMapping(value = "app/api/article")
public class AppArtcileBaseController extends CrudController<ArticleBase, AppArtcileBaseService> {
	@Autowired
	AreaProvinceService provinceService;
	@Autowired
	AreaCityService cityService;
	@Autowired
	UserService userService;
	@Autowired
	SysUploadController sysUploadController;
	@Autowired
	SysClassService sysClassService;
	@Autowired
	SysUploadService uploadService;
	@Autowired
	ArticleCommentService articleCommentService;
	@Autowired
	ArticleBaseService articleBaseService;
	@Autowired
	SysUploadService sysUploadService;
	public static String pics = "";

	/**
	 * 根据孩子的id,获取家长圈信息
	 * 
	 * @page 当前页数
	 * @page 每页显示数量
	 * @childId 孩子的id currentUserId 请求用户的id
	 * @param isMyPhoto
	 *            是否是我的相册进入
	 * @return
	 */
	@RequestMapping(value = "/getCircleInfoList.do")
	@ResponseBody
	public Map<String, Object> getCircleInfoList(ArticleBase oldArticleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer currentUserId = oldArticleBase.getUserId();
			List<ArticleBase> list = null;
			Integer totalPage = 0;
			Integer totalCount = 0;
			// 查找所有可见的朋友圈数量
			if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
				totalCount = getService().getAllCircleListCount(oldArticleBase);
				// 分页查询朋友圈
				list = getService().findByPage(oldArticleBase);
			} else if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
				totalCount = getService().getAllCircleListCountBySchool(oldArticleBase);
				// 分页查询朋友圈
				list = getService().findByPageBySchool(oldArticleBase);

			} else if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_TEACHER) {
				totalCount = getService().getAllCircleListCountByTeacher(oldArticleBase);
				// 分页查询朋友圈
				list = getService().findByPageByTeacher(oldArticleBase);
			}

			JSONArray jsonArray = new JSONArray();
			for (ArticleBase articleBase : list) {
				// 可能有多张图片,以逗号分隔
				if (!StringUtils.isEmpty(articleBase.getPicId())) {
					String[] pics = articleBase.getPicId().split(",");
					List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
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
						User child = getService().getUserById(articleBase.getStudentId());
						if (child != null) {
							articleBase.setRelateName(appUserService.getUserRelativeName(user.getId(), child.getId()));
							// 设置孩子的信息
							articleBase.setChildName(child.getTrueName());
						}

					}
					jsonArray.add(articleBase);
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
	 * 保存文章信息
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "writeArticle.do")
	@ResponseBody
	public Map<String, Object> writeArticle(ArticleBase oldArticleBase, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("writeArticle.do.....");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppUserService userService = SpringContext.getBean(AppUserService.class);
		try {
			System.out.println(oldArticleBase.getArticleReceiver());
			ArticleBase articleBase = null;

			// 如果发送者是老师，并且发送的是班级对象,进行一些特殊处理
			if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_TEACHER
					&& oldArticleBase.getArticleReceiver() == RuyTonAppContants.USER_TYPE_TEACHER) {

				int[] classIds = oldArticleBase.getClassIds();
				// 先将老师的信息保存一条
				oldArticleBase.setClassId(0);
				articleBase = getService().save(oldArticleBase);
				Integer parentId = articleBase.getId();
				// 在保存相应班级的文章
				for (int i = 0; i < classIds.length; i++) {
					oldArticleBase.setId(articleBase.getId() + 1);
					oldArticleBase.setClassId(classIds[i]);
					oldArticleBase.setParentId(parentId);
					articleBase = getService().save(oldArticleBase);
				}
				// 设置最初的id返回回去
				articleBase.setId(parentId);
				articleBase.setParentId(null);

			} else {
				articleBase = getService().save(oldArticleBase);
			}

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
				if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
					// 获取小孩信息和关系
					if (articleBase.getStudentId() != 0) {
						String relativeName = userService.getUserRelativeName(articleBase.getUserId(),
								articleBase.getStudentId());
						articleBase.setRelateName(relativeName);
					}
				}
				// 设置文章发表者的昵称，如果没有昵称则用真实的姓名
				if (StringUtils.isEmpty(user.getNickName())) {
					articleBase.setNickName(user.getTrueName());
				} else {
					articleBase.setNickName(user.getNickName());
				}
				articleBase.setSendRoleProperty(user.getRoleProperty());
			}

			articleBase.setArticleLikeCount(0);
			articleBase.setArticleComments(new ArrayList<ArticleComment>());
			articleBase.setIsDeleted(RuyTonAppContants.ARTICLE_DELETE_FALSE);
			articleBase.setCreatedTime(DateUtil.formateDate(new Date().getTime()));

			resultMap.put("success", true);
			resultMap.put("info", articleBase);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 文章点赞操作
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "articleLike.do")
	@ResponseBody
	public Map<String, Object> articleLike(ArticleLike articleLike, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArticleLikeService articleLikeService = SpringContext.getBean(ArticleLikeService.class);
			ArticleLike entity = articleLikeService.getArticle(articleLike.getArticleId(), articleLike.getUserId());
			// 删除该条信息
			if (entity != null) {
				articleLikeService.del(entity);
			}
			// 插入
			else {
				articleLikeService.save(articleLike);
			}

			resultMap.put("success", true);
			resultMap.put("articleCount", articleLikeService.getArticleLikeCount(articleLike.getArticleId()));

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 评论
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "writeComment.do")
	@ResponseBody
	public Map<String, Object> writeComment(Integer parentArticleId, ArticleComment articleComment,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);
			// 说明此次评论的文章只是个副本，真正评论的是parentArticleId的文章id
			ArticleComment entity = articleCommentService.save(articleComment);

			// 重新取得该文章的所有评论信息
			List<ArticleComment> list = articleCommentService.finAllComments(articleComment.getArticleBaseId());
			// 重新封装一个集合，处理一些特殊情况下的信息
			List<ArticleComment> newCommentList = new ArrayList<ArticleComment>();
			//
			for (ArticleComment comment : list) {
				// 有回复形式的内容
				if (comment.getParentId() != 0) {
					ArticleComment parentComment = articleCommentService.findCommentByParentId(comment.getParentId());
					comment.setSendName(comment.getUserName());
					comment.setGetterName(parentComment.getUserName());
				}
				newCommentList.add(comment);
			}
			resultMap.put("success", true);
			resultMap.put("info", newCommentList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 下拉刷新根据最后条数据的id查找更新的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNewCircleInfoList.do")
	@ResponseBody
	public Map<String, Object> getNewCircleInfoList(ArticleBase oldArticleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<ArticleBase> list = null;
			Integer currentUserId = oldArticleBase.getUserId();
			if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
				list = getService().getNewCircleInfoList(oldArticleBase);
			} else if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
				list = getService().getNewCircleInfoListBySchool(oldArticleBase);
			} else if (oldArticleBase.getSendRoleProperty() == RuyTonAppContants.USER_TYPE_TEACHER) {
				list = getService().getNewCircleInfoListByTeacher(oldArticleBase);
			}

			JSONArray jsonArray = new JSONArray();
			for (ArticleBase articleBase : list) {
				// 可能有多张图片,以逗号分隔
				if (!StringUtils.isEmpty(articleBase.getPicId())) {
					String[] pics = articleBase.getPicId().split(",");
					List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
					articleBase.setUploads(uploads);
				} else {
					// 给他一个空的集合
					articleBase.setUploads(new ArrayList<SysUpload>());
				}

				// 查找该用户对这篇文章是否点赞
				ArticleLikeService articleLikeService = SpringContext.getBean(ArticleLikeService.class);
				ArticleLike articleLike = articleLikeService.getArticle(articleBase.getId(), currentUserId);
				if (articleLike != null) {
					articleBase.setLikeArticle(true);
				} else {
					articleBase.setLikeArticle(false);
				}
				// 设置该文章点赞次数
				// articleBase.setArticleLikeCount(articleLikeService.getArticleLikeCount(articleBase.getId()));
				List<User> articleLikeUsers = articleLikeService.getArticleLikeUsers(articleBase.getId());
				if (articleLikeUsers != null && articleLikeUsers.size() > 0) {
					articleBase.setArticleLikeCount(articleLikeUsers.size());
				} else {
					articleBase.setArticleLikeCount(0);
				}
				articleBase.setArticleLikeUsers(articleLikeUsers);

				// 查找该文章的所有评论信息
				ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);
				List<ArticleComment> articleCommentList = articleCommentService.finAllComments(articleBase.getId());
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
					if (StringUtils.isEmpty(user.getNickName())) {
						articleBase.setNickName(user.getTrueName());
					} else {
						articleBase.setNickName(user.getNickName());
					}

				}

				// 登錄者為家長的時候,需要設置相關小孩的信息
				if (user.getRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
					User child = getService().getUserById(articleBase.getStudentId());
					if (child != null) {
						articleBase.setRelateName(appUserService.getUserRelativeName(user.getId(), child.getId()));
						// 设置孩子的信息
						articleBase.setChildName(child.getTrueName());
					}

				}
				jsonArray.add(articleBase);

			}
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
	 * 
	 * @param sysClass.userId
	 *            园长ID
	 * @param sysClass.parentId
	 *            父类ID
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	// userId=1&parentId=0 parentId上级ID
	// 库表中记录的为添加人的ID 即园长
	@RequestMapping("/getScAbout.do")
	@ResponseBody
	public Map<String, Object> getScAbout(SysClass sysClass, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (sysClass.getParentId() == null) {
				sysClass.setParentId(0);
			}
			if (sysClass.getSysClassTypeId() == null) {
				sysClass.setSysClassTypeId(2);
			}
			resultMap.put("success", true);
			resultMap.put("info", this.getService().getScAbout(sysClass));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));

		return resultMap;
	}

	/**
	 * getScArticle.do?sysClassIdSch=89 获取分类ID
	 * 
	 * @param searchEntitySch。sysClassIdSch分类类别ID
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getScArticle.do")
	@ResponseBody
	public Map<String, Object> getScArticle(ArticleBaseSch searchEntitySch, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			DefaultGridResult resultGrid = null;
			SysClass sysClass = null;
			if (MemcacheManager
					.get("WIKI_" + searchEntitySch.getSysClassIdSch() + "_" + searchEntitySch.getPage()) != null) {
				resultGrid = (DefaultGridResult) MemcacheManager
						.get("WIKI_" + searchEntitySch.getSysClassIdSch() + "_" + searchEntitySch.getPage());
			} else {
				AppUserService appUserService = SpringContext.getBean(AppUserService.class);
				SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
				resultGrid = (DefaultGridResult) this.query(searchEntitySch);
				if (resultGrid != null && resultGrid.getRows().size() > 0) {
					for (int i = 0; i < resultGrid.getRows().size(); i++) {
						ArticleBase articleBase = (ArticleBase) resultGrid.getRows().get(i);
						User user = appUserService.get(articleBase.getUserId());
						articleBase.setUserName(user.getTrueName());
						if (user != null) {
							if (StringUtils.isEmpty(user.getDefaultAvatar())) {
								SysUpload sysUpload = sysUploadService.get(user.getAvatar());
								if (sysUpload != null) {
									articleBase.setArtHead(sysUpload.getFileFullPath());
								}
							} else {
								articleBase.setArtHead(user.getDefaultAvatar());

							}
						}

						if (!StringUtils.isEmpty(articleBase.getPicId())) {
							String[] pics = articleBase.getPicId().split(",");
							List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
							articleBase.setUploads(uploads);
						} else {
							// 给他一个空的集合
							articleBase.setUploads(new ArrayList<SysUpload>());
						}
					}
					// 设置一天的缓存时间
					MemcacheManager.set("WIKI_" + searchEntitySch.getSysClassIdSch() + "_" + searchEntitySch.getPage(),
							resultGrid, RuyTonAppContants.WIKI_MEMCAHE_TIME);

				}

			}

			if (MemcacheManager.get("SYSCLASS_" + searchEntitySch.getSysClassIdSch()) != null) {
				sysClass = (SysClass) MemcacheManager.get("SYSCLASS_" + searchEntitySch.getSysClassIdSch());
			} else {
				sysClass = sysClassService.get(searchEntitySch.getSysClassIdSch());
				if (sysClass != null) {
					MemcacheManager.set("SYSCLASS_" + searchEntitySch.getSysClassIdSch(), sysClass,
							RuyTonAppContants.WIKI_MEMCAHE_TIME);
				}

			}
			resultMap.put("success", true);
			resultMap.put("info", resultGrid);
			resultMap.put("sysClass", sysClass);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));

		return resultMap;
	}

	@RequestMapping("/getSysClassByApp.do")
	@ResponseBody
	public Map<String, Object> getSysClassByApp(SysClass sysClass, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (sysClass.getParentId() == null) {
				sysClass.setParentId(0);
			}
			resultMap.put("success", true);
			resultMap.put("info", this.getService().getSysClassByTypeId(sysClass));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));

		return resultMap;
	}

	// /**
	// * 上传到七牛云
	// * @param userId
	// * @param childId
	// * @param trueName
	// * @param request
	// * @param response
	// * @return
	// * @throws ServletException
	// * @throws IOException
	// */
	// @RequestMapping(value = "/uploadPicsToQiniu.do")
	// @ResponseBody
	// protected Map<String, Object> uploadPicsToQiniu(Integer userId, Integer
	// childId, String trueName,
	// HttpServletRequest request, HttpServletResponse response) throws
	// ServletException, IOException {
	//
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// SysUpload sysUpload = null;
	// QiniuManager qiniuManager = new QiniuManager();
	//
	// try {
	// // 创建一个通用的多部分解析器
	// CommonsMultipartResolver multipartResolver = new
	// CommonsMultipartResolver(
	// request.getSession().getServletContext());
	// // 判断 request 是否有文件上传,即多部分请求
	// if (multipartResolver.isMultipart(request)) {
	// // 转换成多部分request
	// MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)
	// request;
	// // 取得request中的所有文件名
	// Iterator<String> iter = multiRequest.getFileNames();
	// while (iter.hasNext()) {
	// // 取得上传文件
	// MultipartFile file = multiRequest.getFile(iter.next());
	// if (file != null) {
	// // 取得当前上传文件的文件名称
	// String myFileName = file.getOriginalFilename();
	// HashMap<String, Object> res =
	// qiniuManager.upload(Utils.multipartToFile(file));
	// if (res != null) {
	// SysUpload upload = new SysUpload();
	// // 设置一些文件参数
	// upload.setFileSize((int) (file.getSize() / 1024));
	// upload.setFileExt(res.get("ext").toString());
	// if(res.get("imageInfo")!=null){
	// LinkedTreeMap<String,Object> imageInfo =(LinkedTreeMap<String, Object>)
	// res.get("imageInfo");
	// double d = (Double) imageInfo.get("width");
	// Integer width =new Double(d).intValue();
	// upload.setFileWidth(width);
	// double h = (Double)imageInfo.get("height");
	// Integer height = new Double(h).intValue();
	// upload.setFileHeight(height);
	// upload.setFileTypes("jpg");
	// }
	//
	// upload.setModuleId("app");
	// upload.setFuncId("Article");
	// upload.setFileFullPath(qiniuManager.domain + res.get("key"));
	// upload.setCreatedBy(trueName);
	// upload.setUserId(userId);
	// upload.setUserName(trueName);
	// SysUploadService sysUploadService = (SysUploadService) SpringContext
	// .getBean(SysUploadService.class);
	// // 保存到sysuoload表中
	// sysUpload = sysUploadService.save(upload);
	// }
	// }
	// }
	// }
	// resultMap.put("success", true);
	// resultMap.put("info", sysUpload);
	// } catch (Exception e) {
	// e.printStackTrace();
	// resultMap.put("success", false);
	// resultMap.put("info", e.getMessage());
	// }
	// resultMap.put("token", (String) request.getAttribute("token"));
	// return resultMap;
	// }

	@RequestMapping("/getQiniuPublicTokenByApp.do")
	public @ResponseBody Map<String, Object> getQiniuPublicTokenByApp(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		QiniuManager qManager = new QiniuManager();
		resultMap.put("token", (String) request.getAttribute("token"));
		resultMap.put("success", true);
		resultMap.put("info", qManager.getQiniuPublicToken());
		return resultMap;
	}

	/**
	 * 获取用户通知未读个数
	 * 
	 * @param sysUpload
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNotifyUnReadCount.do")
	public @ResponseBody Map<String, Object> getNotifyUnReadCount(Integer roleProperty, NotifyArticle notifyArticle,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
			notifyArticle.setNotifyType(RuyTonAppContants.NOTIFY_TYPE_CLASS);
			int classUnreadCount = notifyArticleService.getClassNotifyUnreadCount(notifyArticle, roleProperty);
			notifyArticle.setNotifyType(RuyTonAppContants.NOTIFY_TYPE_SCHOOL);
			int schoolUnreadCount = notifyArticleService.getSchoolNotifyUnreadCount(notifyArticle, roleProperty);
			resultMap.put("success", true);
			resultMap.put("info", classUnreadCount + schoolUnreadCount);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", e.getMessage());
			resultMap.put("success", false);
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 保存文章图片信息到sysupload表中
	 * 
	 * @param sysUpload
	 * @param request
	 * @return
	 */
	@RequestMapping("/savePictureInfo.do")
	public @ResponseBody Map<String, Object> savePictureInfo(SysUpload sysUpload, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			sysUpload = sysUploadService.save(sysUpload);
			resultMap.put("success", true);
			resultMap.put("info", sysUpload);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", e.getMessage());
			resultMap.put("success", false);
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 保存文章图片信息到sysupload表中
	 * 
	 * @param articleBase
	 *            文章的id
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteArticle.do")
	public @ResponseBody Map<String, Object> deleteArticle(ArticleBase articleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			articleBase = this.get(articleBase.getId());
			articleBase.setIsDeleted(RuyTonAppContants.ARTICLE_DELETE_TRUE);
			articleBase.setOperaterId(articleBase.getUserId());
			articleBase = getService().update(articleBase);
			// 如果文章是班级文章，则把相关连的文章删除
			if (articleBase.getArticleReceiver() !=null && articleBase.getArticleReceiver() == RuyTonAppContants.USER_TYPE_TEACHER) {
				getService().deleteArticleByParentId(articleBase.getId());
			}
			//如果删除的是论坛文章，则把相关联的评论都删除
			if(articleBase.getHits() !=null && !articleBase.getHits().equals(0)){
				getService().deleteArticleByParentId(articleBase.getId());
			}
			resultMap.put("success", true);
			resultMap.put("info", articleBase);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", e.getMessage());
			resultMap.put("success", false);
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 保存文章图片信息到sysupload表中
	 * 
	 * @param articleBase
	 *            文章的id
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteComment.do")
	public @ResponseBody Map<String, Object> deleteComment(ArticleComment articleComment, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			articleComment = articleCommentService.get(articleComment.getId());
			articleComment.setIsDeleted(RuyTonAppContants.ARTICLE_DELETE_TRUE);
			articleComment.setOperaterId(articleComment.getUserId());
			articleComment = articleCommentService.update(articleComment);
			resultMap.put("success", true);
			resultMap.put("info", articleComment);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", e.getMessage());
			resultMap.put("success", false);
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	@RequestMapping("/getForumInfoList.do")
	public @ResponseBody Map<String, Object> getForumInfoList(ArticleBase oldArticleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ArticleBase> list = null;
		Integer totalPage = 0;
		Integer totalCount = 0;
		try {
			list = articleBaseService.getArticlesBySysClassTypeId(oldArticleBase.getSysClassId(),
					oldArticleBase.getPageCount(), oldArticleBase.getPage());
			totalCount = articleBaseService.getArticlesNumBySysClassId(oldArticleBase.getSysClassId());
			// 获得总页数
			totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
					: totalCount / oldArticleBase.getPageCount() + 1;
			resultMap.put("totalPage", totalPage);
			resultMap.put("page", oldArticleBase.getPage());
			resultMap.put("success", true);
			// 填充数据
			for (int i = 0; i < list.size(); i++) {
				// 获取每篇文章的评论数量
				ArticleBase articleBase = list.get(i);
				articleBase.setMainForumReplyNums(articleBaseService.getMainForumCommentNums(articleBase.getId()));
				//articleBase.setAssisForumReplyNums(articleBaseService.getAllForumCommentNums(articleBase.getId()));
				/*User author = userService.get(articleBase.getUserId());
				String adress = "";
				// 设置作者所在地
				if (!author.getProvinceId().equals(0)) {
					adress += provinceService.get(author.getProvinceId()).getProvinceName();
					if (!author.getCityId().equals(0)) {
						String cityName = cityService.get(author.getCityId()).getCityName();
						if (!cityName.equals(adress)) {
							adress += " " + cityName;
						}
					}
				}
				author.setAddressDetail(adress);
				// 获取作者头像
				if (author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")) {
					author.setDefaultAvatar("");
					Integer avatarId = author.getAvatar();
					if (!avatarId.equals(0)) {
						author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
					}

				}*/
				//获取每篇文章的作者
				User author = userService.get(articleBase.getUserId());
				articleBase.setForumAuthor(author);
			}

			resultMap.put("info", list);
			SysClass assisType = sysClassService.get(oldArticleBase.getSysClassId());
			SysClass mainType = sysClassService.get(assisType.getParentId());
			resultMap.put("assisType", assisType);
			resultMap.put("mainType", mainType);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 下拉刷新根据最后条数据的id查找更新的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNewForumInfoList.do")
	@ResponseBody
	public Map<String, Object> getNewForumInfoList(ArticleBase oldArticleBase, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ArticleBase> list = null;
		Integer totalPage = 0;
		Integer totalCount = 0;
		try {
			list = this.getService().getNewForumInfoList(oldArticleBase);
			totalCount = articleBaseService.getArticlesNumBySysClassId(oldArticleBase.getSysClassId());
			// 获得总页数
			totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
					: totalCount / oldArticleBase.getPageCount() + 1;
			resultMap.put("totalPage", totalPage);
			resultMap.put("page", oldArticleBase.getPage());
			resultMap.put("success", true);
			// 填充数据
			for (int i = 0; i < list.size(); i++) {
				// 获取每篇文章的评论数量
				ArticleBase articleBase = list.get(i);
				articleBase.setMainForumReplyNums(articleBaseService.getMainForumCommentNums(articleBase.getId()));
				//articleBase.setArticleComments(articleCommentService.finAllComments(articleBase.getId()));
				User author = userService.get(articleBase.getUserId());
				String adress = "";
				if (!author.getProvinceId().equals(0)) {
					adress += provinceService.get(author.getProvinceId()).getProvinceName();
					if (!author.getCityId().equals(0)) {
						String cityName = cityService.get(author.getCityId()).getCityName();
						if (!cityName.equals(adress)) {
							adress += " " + cityName;
						}
					}
				}
				author.setAddressDetail(adress);
				// 获取每篇文章的作者
				articleBase.setForumAuthor(author);
			}
			resultMap.put("info", list);
			SysClass assisType = sysClassService.get(oldArticleBase.getSysClassId());
			SysClass mainType = sysClassService.get(assisType.getParentId());
			resultMap.put("assisType", assisType);
			resultMap.put("mainType", mainType);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 更新论坛+1
	 * 
	 * @param articleBase
	 *            文章的id
	 * @param request
	 * @return
	 */
	@RequestMapping("/readPlusOne.do")
	public @ResponseBody Map<String, Object> readPlusOne(Integer articleId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			this.getService().updateHitsPlusOne(articleId);
			resultMap.put("success", true);
			resultMap.put("info", null);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", e.getMessage());
			resultMap.put("success", false);
		}

		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}
	
	/**
	 * 根据parentId或者articleId来获取文章内容
	 * @param parentId
	 * @param articleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getForumDetail.do")
	public @ResponseBody Map<String, Object>  getForumDetail(Integer parentId,Integer articleId,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArticleBase article = null;
		//获取文章
		if(parentId!=null && !parentId.equals(0)){
			article = articleBaseService.get(parentId);
		}else{
			article = articleBaseService.get(articleId);
		}
		try {
			User author = userService.get(article.getUserId());
			String adress = "";
			//设置作者所在地
			if(!author.getProvinceId().equals(0)){
				adress += provinceService.get(author.getProvinceId()).getProvinceName();
				if(!author.getCityId().equals(0)){
					String cityName = cityService.get(author.getCityId()).getCityName();
					if(!cityName.equals(adress)){
						adress += " "+cityName;
					}
				}
			}
			author.setAddressDetail(adress);
			//获取作者头像
			if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
				author.setDefaultAvatar("");
				Integer avatarId = author.getAvatar();
				if(!avatarId.equals(0)){
					author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
				}
			}
			article.setForumAuthor(author);
			// 可能有多张图片,以逗号分隔
			if (!StringUtils.isEmpty(article.getPicId())) {
				String[] pics = article.getPicId().split(",");
				List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
				article.setUploads(uploads);
			} else {
				// 给他一个空的集合
				article.setUploads(new ArrayList<SysUpload>());
			}
			SysClass assisType = sysClassService.get(article.getSysClassId());
			SysClass mainType = sysClassService.get(assisType.getParentId());
			article.setMainType(mainType);
			article.setAssisType(assisType);
			article.setMainForumReplyNums(articleBaseService.getMainForumCommentNums(article.getId()));
			resultMap.put("info", article);
			resultMap.put("success", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	@RequestMapping(value = "readForumDetail.do")
	public @ResponseBody Map<String, Object> readForumDetail(Integer articleId,ArticleBase oldArticleBase,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer totalPage = 0;
		Integer totalCount = 0;
		List<ArticleBase> parentList = null;
		//如果是上拉刷新加载更多评论
		if(oldArticleBase.getFirstLastId() != null && !oldArticleBase.getFirstLastId().equals(0)){
			oldArticleBase.setParentId(articleId);
			parentList = this.getService().getOldForumReplyList(oldArticleBase);
		}else{
			// 重新取得该文章的评论信息(分页,每页10个回复),把每条评论看成是一篇文章
			parentList = articleBaseService.findCommentsByPages(articleId, (oldArticleBase.getPage()-1)*oldArticleBase.getPageCount(), oldArticleBase.getPageCount());
		}
		if(parentList != null){
			try {
				totalCount = articleBaseService.getMainForumCommentNums(articleId);
				// 获得总页数
				totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
						: totalCount / oldArticleBase.getPageCount() + 1;
				resultMap.put("totalPage", totalPage);
				resultMap.put("page", oldArticleBase.getPage());
				resultMap.put("success", true);
				int count = totalCount;
				// 重新封装一个集合，处理一些特殊情况下的信息
				for (ArticleBase articleBase : parentList) {
					/*List<ArticleComment> forumReplyComment = articleCommentService.finAllComments(comment.getId());
					
					for(ArticleComment reply : forumReplyComment){
						if(reply.getParentId().equals(comment.getId())){
							reply.setSendName(userService.get(reply.getUserId()).getTrueName());
							reply.setGetterName("");
						}else{
							ArticleComment parentComment = articleCommentService.findCommentByParentId(reply.getParentId());
							reply.setSendName(userService.get(reply.getUserId()).getTrueName());
							reply.setGetterName(userService.get(parentComment.getUserId()).getTrueName());
						}
					}
					*/
					//设置楼层
					articleBase.setFloor((count--)+"楼");
					// 可能有多张图片,以逗号分隔
					if (!StringUtils.isEmpty(articleBase.getPicId())) {
						String[] pics = articleBase.getPicId().split(",");
						List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
						articleBase.setUploads(uploads);
					} else {
						// 给他一个空的集合
						articleBase.setUploads(new ArrayList<SysUpload>());
					}
					
					User author = userService.get(articleBase.getUserId());
					//获取作者头像
					if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
						author.setDefaultAvatar("");
						Integer avatarId = author.getAvatar();
						if(!avatarId.equals(0)){
							author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
						}
					}
					articleBase.setForumAuthor(author);
					//获取评论的回复人信息
					if(articleBase.getReplyArticleBaseId()!= null && !articleBase.getReplyArticleBaseId().equals(0)){
						articleBase.setReceiver(userService.get(articleBaseService.get(articleBase.getReplyArticleBaseId()).getUserId()));
					}
					/*
					if(articleId.equals(articleBase.getParentId())){
						reply.setSendName(userService.get(reply.getUserId()).getTrueName());
						reply.setGetterName("");
					}else{
						ArticleComment parentComment = articleCommentService.findCommentByParentId(reply.getParentId());
						reply.setSendName(userService.get(reply.getUserId()).getTrueName());
						reply.setGetterName(userService.get(parentComment.getUserId()).getTrueName());
					}*/
					
				}
				
				resultMap.put("info", parentList);
	
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("success", false);
				resultMap.put("info", e.getMessage());
			}
		}else{
			resultMap.put("success", false);
			resultMap.put("info", "已经加载所有数据！！！");
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	
	/**
	 * 评论
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "writeForumComment.do")
	@ResponseBody
	public Map<String, Object> writeForumComment(Integer parentArticleId, ArticleComment articleComment,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//表示是回复论坛的直接评论
			if(articleComment.getParentId().equals(articleComment.getArticleBaseId())){
				ArticleBase base = new ArticleBase();
				base.setParentId(articleComment.getParentId());
				base.setContent(articleComment.getContent());
				base.setUserId(articleComment.getUserId());
				base.setUserName(articleComment.getUserName());	
				base = articleBaseService.save(base);
				User author = userService.get(base.getUserId());
				//获取作者头像
				if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
					author.setDefaultAvatar("");
					Integer avatarId = author.getAvatar();
					if(!avatarId.equals(0)){
						author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
					}
				}
				//设置发表的用户信息
				base.setForumAuthor(author);
				resultMap.put("info", base);
			}else{
				// 说明此次评论的文章只是个副本，真正评论的是parentArticleId的文章id
				ArticleComment entity = articleCommentService.save(articleComment);
				User author = userService.get(entity.getUserId());
				//获取作者头像
				if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
					author.setDefaultAvatar("");
					Integer avatarId = author.getAvatar();
					if(!avatarId.equals(0)){
						author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
					}
				}
				//设置发送者以及接收者
				ArticleComment receive = null;
				if(entity.getParentId().equals(0)){
					entity.setSendName(author.getTrueName());
				}else{
					receive = articleCommentService.get(entity.getParentId());
					entity.setGetterName(userService.get(receive.getUserId()).getTrueName());
					entity.setSendName(author.getTrueName());
				}
				
				//设置发表的用户信息
				entity.setForumAuthor(author);
				resultMap.put("info", entity);
			}
			/**
			// 重新取得该文章的所有评论信息
			List<ArticleComment> list = articleCommentService.finAllComments(articleComment.getArticleBaseId());
			// 重新封装一个集合，处理一些特殊情况下的信息
			List<ArticleComment> newCommentList = new ArrayList<ArticleComment>();
			//
			for (ArticleComment comment : list) {
				// 有回复形式的内容
				if (comment.getParentId() != 0) {
					ArticleComment parentComment = articleCommentService.findCommentByParentId(comment.getParentId());
					comment.setSendName(comment.getUserName());
					comment.setGetterName(parentComment.getUserName());
				}
				newCommentList.add(comment);
			}**/
			resultMap.put("success", true);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));

		return resultMap;

	}
	
	/**
	 * 下拉刷新或许最新评论
	 * @param parentArticleId
	 * @param articleComment
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "getNewForumReplyList.do")
	@ResponseBody
	public Map<String, Object> getNewForumReplyList(Integer parentArticleId, ArticleBase oldArticleBase,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer totalPage = 0;
		Integer totalCount = 0;
		oldArticleBase.setParentId(parentArticleId);
		try {
			List<ArticleBase> list = this.getService().getNewForumInfoList(oldArticleBase);
			totalCount = articleBaseService.getMainForumCommentNums(parentArticleId);
			// 获得总页数
			totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
					: totalCount / oldArticleBase.getPageCount() + 1;
			int count = totalCount;
			resultMap.put("totalPage", totalPage);
			resultMap.put("page", oldArticleBase.getPage());
			resultMap.put("success", true);
			// 重新封装一个集合，处理一些特殊情况下的信息
			for (ArticleBase articleBase : list) {
				/*List<ArticleComment> forumReplyComment = articleCommentService.finAllComments(comment.getId());
				
				for(ArticleComment reply : forumReplyComment){
					if(reply.getParentId().equals(comment.getId())){
						reply.setSendName(userService.get(reply.getUserId()).getTrueName());
						reply.setGetterName("");
					}else{
						ArticleComment parentComment = articleCommentService.findCommentByParentId(reply.getParentId());
						reply.setSendName(userService.get(reply.getUserId()).getTrueName());
						reply.setGetterName(userService.get(parentComment.getUserId()).getTrueName());
					}
				}
				*/
				//设置楼层
				articleBase.setFloor((count--)+"楼");
				// 可能有多张图片,以逗号分隔
				if (!StringUtils.isEmpty(articleBase.getPicId())) {
					String[] pics = articleBase.getPicId().split(",");
					List<SysUpload> uploads = getService().getPicsUrlByIds(pics);
					articleBase.setUploads(uploads);
				} else {
					// 给他一个空的集合
					articleBase.setUploads(new ArrayList<SysUpload>());
				}
				//获取文章评论人信息
				User author = userService.get(articleBase.getUserId());
				articleBase.setForumAuthor(author);
				//获取评论的回复人信息
				if(articleBase.getReplyArticleBaseId()!= null && !articleBase.getReplyArticleBaseId().equals(0)){
					articleBase.setReceiver(userService.get(articleBaseService.get(articleBase.getReplyArticleBaseId()).getUserId()));
				}
				/*
				if(articleId.equals(articleBase.getParentId())){
					reply.setSendName(userService.get(reply.getUserId()).getTrueName());
					reply.setGetterName("");
				}else{
					ArticleComment parentComment = articleCommentService.findCommentByParentId(reply.getParentId());
					reply.setSendName(userService.get(reply.getUserId()).getTrueName());
					reply.setGetterName(userService.get(parentComment.getUserId()).getTrueName());
				}*/
				
			}
			resultMap.put("info", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;
	}
	/**
	 * 评论
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "writeForumDirectComment.do")
	@ResponseBody
	public Map<String, Object> writeForumDirectComment(Integer parentArticleId, ArticleBase articleBase,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int totalCount = articleBaseService.getMainForumCommentNums(articleBase.getParentId());
			User user = userService.get(articleBase.getUserId());
			//设置头像
			if (user != null) {
				if (StringUtils.isEmpty(user.getDefaultAvatar())) {
					SysUpload sysUpload = sysUploadService.getByUserId(user.getAvatar());
					if (sysUpload != null) {
						articleBase.setArtHead(sysUpload.getFileFullPath());
						articleBase.setDefaultAvatar(false);
					}
				} else {
					articleBase.setArtHead(user.getDefaultAvatar());
					articleBase.setDefaultAvatar(true);
				}
			}
			//保存文章
			articleBase = articleBaseService.save(articleBase);
			articleBase = articleBaseService.get(articleBase.getId());
			//设置发表的用户信息
			articleBase.setForumAuthor(user);
			//设置楼层
			articleBase.setFloor((totalCount+1)+"楼");
			if(!articleBase.getReplyArticleBaseId().equals(0)){
				articleBase.setReceiver(userService.get(articleBaseService.get(articleBase.getReplyArticleBaseId()).getUserId()));
			}
			resultMap.put("info", articleBase);
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;

	}
	
	@RequestMapping(value = "writeForumArticle.do")
	@ResponseBody
	public Map<String, Object> writeForumArticle(ArticleBase articleBase){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		articleBase = this.getService().save(articleBase);
		articleBase.setMainForumReplyNums(articleBaseService.getMainForumCommentNums(articleBase.getId()));
		//articleBase.setAssisForumReplyNums(articleBaseService.getAllForumCommentNums(articleBase.getId()));
		User author = userService.get(articleBase.getUserId());
		String adress = "";
		//设置作者所在地
		if(!author.getProvinceId().equals(0)){
			adress += provinceService.get(author.getProvinceId()).getProvinceName();
			if(!author.getCityId().equals(0)){
				String cityName = cityService.get(author.getCityId()).getCityName();
				if(!cityName.equals(adress)){
					adress += " "+cityName;
				}
			}
		}
		author.setAddressDetail(adress);
		//获取作者头像
		if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
			author.setDefaultAvatar("");
			Integer avatarId = author.getAvatar();
			if(!avatarId.equals(0)){
				author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
			}
		}
		//获取每篇文章的作者
		articleBase.setForumAuthor(author);
		resultMap.put("info", articleBase);
		resultMap.put("success", true);
		return resultMap;
	}


}
