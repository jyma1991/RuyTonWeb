
package com.ryt.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.SpringContext;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.entity.NotifyArticleReadDetail;
import com.ryt.app.service.AppArtcileBaseService;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.JpushUtil;
import com.ryt.web.common.MemcacheManager;
import com.ryt.web.common.Utils;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.notify.NotifyArticeRead;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.notify.NotifyArticleSch;
import com.ryt.web.entity.sc.ScAuthUserStreamSch;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassCourse;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.sc.ScClassStudentSingInfo4Teacher;
import com.ryt.web.entity.sc.ScCookBook;
import com.ryt.web.entity.sc.ScCookBookSch;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScSignDay;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysClassSch;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.article.ArticleCommentService;
import com.ryt.web.service.notify.NotifyArticeReadService;
import com.ryt.web.service.notify.NotifyArticleService;
import com.ryt.web.service.sc.ScAuthUserStreamService;
import com.ryt.web.service.sc.ScClassScheduleService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScClassTeacherService;
import com.ryt.web.service.sc.ScCookBookService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScSignInOutService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sc.ScTeacherCourseService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;

@Controller
@RequestMapping(value = "/app/api/home/")
public class AppHometCtrl extends CrudController<NotifyArticle, NotifyArticleService> {
	@Autowired
	NotifyArticleService notifyArticleService;
	@Autowired
	AppUserService appUserService;
	@Autowired
	NotifyArticeReadService notifyArticeReadService;
	@Autowired
	ScClassService scClassService;
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	ScSchoolService schoolService;
	@Autowired
	SysClassService sysClassService;
	@Autowired
	ArticleBaseService articleBaseService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	AppArtcileBaseService appArtcileBaseService;
	@Autowired
	ScTeacherCourseService scTeacherCourseService;
	@Autowired
	ScSchoolService scSchoolService;

	/**
	 * 鏌ヨ姣忓懆姣忓ぉ鐨勮绋�
	 * 
	 * @param ScClassSchedule.schoolId
	 * @param ScClassSchedule.classId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getScClassScheduleByWeekDay.do")
	public @ResponseBody Map<String, Object> getScClassScheduleByWeekDay(Integer roleProperty, Integer schoolIdSch,
			Integer userId, Integer classId, HttpServletRequest request) {
		ScClassScheduleService ScClassScheduleService = (ScClassScheduleService) SpringContext
				.getBean(ScClassScheduleService.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<ScClassCourse> list = null;
			if (roleProperty == RuyTonAppContants.USER_TYPE_PARENT) {
				if (MemcacheManager.get(schoolIdSch + "_" + classId + "_course") != null) {
					list = (List<ScClassCourse>) MemcacheManager.get(schoolIdSch + "_" + classId + "_course");
				} else {
					list = ScClassScheduleService.getScClassScheduleByWeekDay(userId, classId);
					MemcacheManager.set(schoolIdSch + "_" + classId + "_course", list,
							RuyTonAppContants.COURSE_MEMCAHE_TIME);
				}
			}
			// TODO 除了家长之外的用户角色，其他未采用memcahe方式
			else {
				list = ScClassScheduleService.getScClassScheduleByWeekDay(userId, classId);
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
	 * 鏌ヨ姣忓懆椋熻氨
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getCookBookByWeekDay.do")
	public @ResponseBody Map<String, Object> getCookBookByWeekDay(ScCookBookSch searchEntitySch,
			HttpServletRequest request) {
		
		ScCookBookService ScCookBookService = (ScCookBookService) SpringContext.getBean(ScCookBookService.class);
		SysUploadService sysUploadService = (SysUploadService) SpringContext.getBean(SysUploadService.class);
		SysClassService sysClassService = (SysClassService) SpringContext.getBean(SysClassService.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ScCookBook> list = null;

		try {
			if (MemcacheManager.get(searchEntitySch.getSchoolIdSch() + "_cook") != null) {
				list = (List<ScCookBook>) MemcacheManager.get(searchEntitySch.getSchoolIdSch() + "_cook");
			} else {
//				searchEntitySch.setPublishDateStartSch(Utils.getMondayOFWeek());
//				searchEntitySch.setPublishDateEndSch(Utils.getCurrentWeekday());
				searchEntitySch.setIsDeletedSch(RuyTonAppContants.ARTICLE_DELETE_FALSE);
				ExpressionQuery query = ExpressionQuery.buildQueryAll();
				query.addAnnotionExpression(searchEntitySch);	
				query.addSort("publishDate");
				list = ScCookBookService.find(query);
				for (ScCookBook scCookBook : list) {
					int picId = scCookBook.getFoodPicId();
					int foodTypeId = scCookBook.getFoodTypeId();
					if (sysUploadService.get(picId) != null) {
						scCookBook.setFileFullPath(sysUploadService.get(picId).getFileFullPath());
					}
					if (sysClassService.get(foodTypeId) != null) {
						scCookBook.setFoodType(sysClassService.get(foodTypeId).getClassName());
					}

				}
				MemcacheManager.set(searchEntitySch.getSchoolIdSch() + "_cook", list,
						RuyTonAppContants.COOK_MEMCAHE_TIME);
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
	 * 鏌ヨ娴�
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/getStreams.do")
	public @ResponseBody Map<String, Object> getStreams(ScAuthUserStreamSch entity, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ScAuthUserStreamService scAuthUserStreamService = (ScAuthUserStreamService) SpringContext
				.getBean(ScAuthUserStreamService.class);
		try {
			ScSchool school = scSchoolService.getSchoolBySchoolId(entity.getUSchoolIdSch());
			resultMap.put("success", true);
			resultMap.put("info", scAuthUserStreamService.getUserStreams(entity));
			resultMap.put("freeVideoDay",school.getFreeVideoDay());

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 瀹堕暱鏌ョ湅绛惧埌璁板綍
	 * 
	 * @param 瀹堕暱鏌ョ湅绛惧埌璁板綍
	 * @return
	 */
	@RequestMapping("/getStudentSignInfo.do")
	public @ResponseBody Map<String, Object> getStudentSignInfo(ScSignInOut entity, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ScSignInOutService scSignInOutService = (ScSignInOutService) SpringContext.getBean(ScSignInOutService.class);
		try {
			List<ScSignDay> list = scSignInOutService.getSignInfoStudent4Student(entity);

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
	 * 淇敼鐢ㄦ埛澶村儚淇℃伅
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/updateHead.do")
	public @ResponseBody Map<String, Object> updateHead(User user, HttpServletRequest request) {
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
	 * 淇濆瓨涓�潯鍥暱淇＄淇℃伅
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping("/submitSuggest.do")
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
	 * 鍒嗛〉鏌ヨ璇ョ敤鎴锋牎闀夸俊绠辩殑鍐呭
	 * 
	 * @param roleProperty
	 *            鐢ㄦ埛瑙掕壊淇℃伅
	 * @param 鏂囩珷淇℃伅鍐呭
	 * @return
	 */
	@RequestMapping("/findSuggestByPage.do")
	public @ResponseBody Map<String, Object> getSuggest(Integer roleProperty, ArticleBase articleBase,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
		ScSchoolService schoolService = SpringContext.getBean(ScSchoolService.class);
		AppUserService appUserService = SpringContext.getBean(AppUserService.class);
		SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
		try {
			List<ArticleBase> list = new ArrayList<ArticleBase>();
			List<ArticleBase> tempList = null;
			Integer totalPage = 0;
			Integer totalCount = 0;

			if (roleProperty == 4) {
				// 棣栧厛鏌ヨ鏍￠暱鎵�湪鐨勫鏍�
				ScSchool school = schoolService.getSchoolByUserId(articleBase.getUserId());
				if (school != null) {
					// 鏌ヨ璇ュ鏍℃墍鏈夌殑鍥暱淇＄鍐呭
					articleBase.setSchoolId(school.getSchoolId());
					totalCount = appArtcileBaseService.getSuggestCountBySchoolId(articleBase);
					tempList = appArtcileBaseService.findSuggestBySchoolId(articleBase);
					for (ArticleBase base : tempList) {
						User user = appUserService.get(base.getUserId());
						// 鍒ゆ柇鐢ㄦ埛鏄惁鏄湰鍦伴粯璁ゅご鍍�
						if (!StringUtils.isEmpty(user.getDefaultAvatar())) {
							base.setArtHead(user.getDefaultAvatar());
						} else {
							// 閫氳繃鐢ㄦ埛id鏌ユ壘澶村儚淇℃伅瀵硅薄
							SysUpload sysUpload = sysUploadService.getByUserId(user.getAvatar());
							if (sysUpload != null) {
								base.setArtHead(sysUpload.getFileFullPath());
							}

						}
						// 璁剧疆鏂囩珷鍐呯殑璇勮:
						// 鏌ユ壘璇ユ枃绔犵殑鎵�湁璇勮淇℃伅
						ArticleCommentService articleCommentService = SpringContext
								.getBean(ArticleCommentService.class);
						List<ArticleComment> articleCommentList = articleCommentService.finAllComments(base.getId());
						base.setArticleComments(articleCommentList);
						base.setUserName(user.getTrueName());
						list.add(base);
					}
				}
			} else {
				totalCount = appArtcileBaseService.getSuggestTotalPage(articleBase);
				// 鏌ヨ璇ョ敤鎴锋墍鏈夋牎鍥俊绠辩殑鍐呭闆嗗悎
				tempList = appArtcileBaseService.findSuggestByPage(articleBase);
				for (ArticleBase base : tempList) {
					User user = appUserService.get(articleBase.getUserId());
					// 鍒ゆ柇鐢ㄦ埛鏄惁鏄湰鍦伴粯璁ゅご鍍�
					if (StringUtils.isEmpty(user.getDefaultAvatar())) {
						base.setArtHead(user.getDefaultAvatar());
					} else {
						// 閫氳繃鐢ㄦ埛id鏌ユ壘澶村儚淇℃伅瀵硅薄
						SysUpload sysUpload = sysUploadService.getByUserId(user.getAvatar());
						if (sysUpload != null) {
							base.setArtHead(sysUpload.getFileFullPath());
						}

					}
					// 璁剧疆鏂囩珷鍐呯殑璇勮:
					// 鏌ユ壘璇ユ枃绔犵殑鎵�湁璇勮淇℃伅
					ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);
					List<ArticleComment> articleCommentList = articleCommentService.finAllComments(base.getId());
					base.setArticleComments(articleCommentList);
					base.setUserName(user.getTrueName());
					list.add(base);
				}
			}

			// 褰撶涓�〉鐨勬椂鍊欒幏鍙栦笅瀹冪殑鎬婚〉鏁帮紝蹇呰澶氭鎵ц
			if (articleBase.getPage() == 1) {
				// 鑾峰緱鎬婚〉鏁�
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
	 * 涓婁紶缃戠粶澶村儚
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@RequestMapping(value = "/updateNetHead.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> uploadNetHead(User user, SysUpload sysUpload, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			// 淇濆瓨鍒皊ysuoload琛ㄤ腑
			sysUpload = sysUploadService.save(sysUpload);
			AppUserService appUserService = SpringContext.getBean(AppUserService.class);
			User newUser = null;
			// 瀛╁瓙id涓虹┖锛屽垯鏄敤鎴蜂慨鏀硅嚜宸辩殑澶村儚
			if (user.getChildId() == null) {
				// 淇敼鐢ㄦ埛鍖�
				newUser = appUserService.get(user.getUserId());
				newUser.setArtHead(sysUpload.getFileFullPath());
			} else {
				newUser = appUserService.get(user.getChildId());
				newUser.setBabyHead(sysUpload.getFileFullPath());
			}

			newUser.setDefaultAvatar("");
			newUser.setAvatar(sysUpload.getId());
			newUser = appUserService.update(newUser);

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
	 * 鍥暱鍥炲瀹堕暱淇℃伅
	 * 
	 * @param articleBase
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "replyParents.do")
	@ResponseBody
	public Map<String, Object> replyParents(ArticleComment articleComment, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArticleCommentService articleCommentService = SpringContext.getBean(ArticleCommentService.class);

			ArticleComment entity = articleCommentService.save(articleComment);

			// 閲嶆柊鍙栧緱璇ユ枃绔犵殑鎵�湁璇勮淇℃伅
			List<ArticleComment> list = articleCommentService.finAllComments(articleComment.getArticleBaseId());
			// 閲嶆柊灏佽涓�釜闆嗗悎锛屽鐞嗕竴浜涚壒娈婃儏鍐典笅鐨勪俊鎭�
			List<ArticleComment> newCommentList = new ArrayList<ArticleComment>();
			//
			for (ArticleComment comment : list) {
				// 鏈夊洖澶嶅舰寮忕殑鍐呭
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
	 * 鑰佸笀鏌ョ湅绛惧埌璁板綍
	 * 
	 * @param 鑰佸笀鏌ョ湅绛惧埌璁板綍
	 * @return
	 */
	@RequestMapping("/getSignInfoTeacher.do")
	public @ResponseBody Map<String, Object> getSignInfoTeacher(ScClassSch scClassSch, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ScSignInOutService scSignInOutService = (ScSignInOutService) SpringContext.getBean(ScSignInOutService.class);
		try {
			List<ScClassStudentSingInfo4Teacher> list = scSignInOutService.getSignInfo4Teacher(scClassSch);

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
	 * 鍥暱鏌ョ湅绛惧埌缁熻
	 * 
	 * @param scClassSch.schoolIdSch
	 * @param scClassSch.signDateSch
	 */
	@RequestMapping("/getSignInfoSchool.do")
	public @ResponseBody Map<String, Object> getSignInfoSchool(ScClassSch scClassSch, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ScSignInOutService scSignInOutService = (ScSignInOutService) SpringContext.getBean(ScSignInOutService.class);
		try {
			List<ScClassStudentSingInfo4Teacher> list = scSignInOutService.getClassSignInfo4School(scClassSch);

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
	 * 閫氳繃sysClassType id鍊艰幏鍙栧叾瀛愮被淇℃伅
	 * 
	 * @param ScCookBook.schoolId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getSysClassList.do")
	public @ResponseBody Map<String, Object> getSysClassList(SysClass sysClass, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysClassService sysClassService = SpringContext.getBean(SysClassService.class);
		SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
		try {
			List<SysClass> allSysList = null;
			List<SysClass> list = new ArrayList<SysClass>();
			if (MemcacheManager.get("wiki_" + sysClass.getParentId()) != null) {
				list = (List<SysClass>) MemcacheManager.get("wiki_" + sysClass.getParentId());
			} else {
				// parentId=null 获取最上层育儿百科目录
				if (sysClass.getParentId() == 0) {
					allSysList = sysClassService.getSysClassByTypeId(sysClass);
					for (SysClass entity : allSysList) {
						if (entity.getParentId() == sysClass.getParentId()) {
							SysUpload sysUpload = sysUploadService.getByUserId(entity.getPicId());
							if (sysUpload != null) {
								entity.setPicUrl(sysUpload.getFileFullPath());
							}
							list.add(entity);
						}
					}

				} else {

					// 查询某育儿百科下的子目录
					allSysList = sysClassService.getChildSysClassesListByParentId(sysClass);
					for (SysClass entity : allSysList) {
						SysUpload sysUpload = sysUploadService.getByUserId(entity.getPicId());
						if (sysUpload != null) {
							entity.setPicUrl(sysUpload.getFileFullPath());
						}
						list.add(entity);
					}

				}
				MemcacheManager.set("wiki_" + sysClass.getParentId(), list, RuyTonAppContants.WIKI_MEMCAHE_TIME);
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
	 * 閫氳繃sysClassId 鏌ユ壘鎵�湁鐨勬枃绔犱俊鎭�
	 * 
	 * @param SysClass
	 *            绫诲埆瀵硅薄
	 * @return
	 */
	@RequestMapping("/getArticleBySysClassId.do")
	public @ResponseBody Map<String, Object> getArticleBySysClassId(SysClass sysClass, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ArticleBase> list = new ArrayList<ArticleBase>();
		List<ArticleBase> temp = null;
		try {
			AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			AppUserService appUserService = SpringContext.getBean(AppUserService.class);
			temp = appArtcileBaseService.getArticleBySysClassId(sysClass);
			for (ArticleBase articleBase : temp) {
				User user = appUserService.get(articleBase.getUserId());
				if (user != null) {
					// 濡傛灉鐢ㄦ埛浣跨敤鐨勪笉鏄湰鍦板浘鐗�鍒欓渶瑕佹煡鎵緎ysupload琛�杩斿洖鍥剧墖鐨勭綉缁滃湴鍧�
					if (StringUtils.isEmpty(user.getDefaultAvatar())) {
						SysUpload sysUpload = sysUploadService.get(user.getAvatar());
						if (sysUpload != null) {
							articleBase.setArtHead(sysUpload.getFileFullPath());
							articleBase.setDefaultAvatar(false);
						}
					} else {
						articleBase.setArtHead(user.getDefaultAvatar());
						articleBase.setDefaultAvatar(true);
					}

				}
				list.add(articleBase);
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
	 * 鑾峰彇閫氱煡瀵硅薄鍐呭
	 * 
	 * @param SysClass
	 *            绫诲埆瀵硅薄
	 * @return
	 */
	@RequestMapping("/getNotifyContacts.do")
	public @ResponseBody Map<String, Object> getNotifyContacts(User user, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppUserService appUserService = SpringContext.getBean(AppUserService.class);
		SysUploadService uploadService = SpringContext.getBean(SysUploadService.class);
		List<ScClass> classList = null;
		List<User> tempList = null;

		try {
			// 鑰佸笀
			if (user.getRoleProperty() == 2) {
				// 鑾峰彇鑰佸笀璐熻矗鐨勬墍鏈夌彮绾у拰瀹堕暱淇℃伅
				classList = appUserService.getClassByTeacher(user);
				for (ScClass scClass : classList) {
					List<User> parentList = new ArrayList<User>();
					// 鑾峰彇璇ヨ�甯堣繖涓彮绾х殑鎵�湁瀹堕暱淇℃伅
					tempList = appUserService.getTeacherChargeParentsList(scClass);
					for (User entity2 : tempList) {
						// 缁欏闀夸俊鎭缃ご鍍�
						if (StringUtils.isEmpty(entity2.getDefaultAvatar())) {
							SysUpload sysUpload = uploadService.get(entity2.getAvatar());
							if (sysUpload != null) {
								entity2.setArtHead(sysUpload.getFileFullPath());
							}
						} else {
							entity2.setArtHead(entity2.getDefaultAvatar());
						}
						parentList.add(entity2);
					}
					scClass.setParentsContacts(parentList);
				}
			}
			// 鍥暱
			else if (user.getRoleProperty() == 4) {
				// 閫氳繃鍥暱id鏌ユ壘瀛︽牎涓嬬殑鎵�湁鐝骇淇℃伅
				classList = appUserService.getClassBySchool(user);
			}
			resultMap.put("success", true);
			resultMap.put("info", classList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 淇濆瓨閫氱煡淇℃伅
	 * 
	 * @param SysClass
	 *            绫诲埆瀵硅薄
	 * @param sendAllClass
	 *            鏄惁鍙戦�鎵�湁鐨勭彮绾�
	 * @param splitClasses
	 *            浠ラ�鍙峰垎闅旂殑鐝骇id
	 * @param splitContacts
	 *            浠ョ壒娈婄鍙峰垎闅斾笉鍚岀彮绾т笅鐨勮仈绯讳汉淇℃伅
	 * @return
	 */
	@RequestMapping("/writeNotify.do")
	public @ResponseBody Map<String, Object> writeNotify(int[] scIds, ArticleBase articleBase, boolean sendAllClass,
			String splitClasses, String splitContacts, Integer roleProperty, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
			AppUserService appUserService = SpringContext.getBean(AppUserService.class);
			ScClassService classService = SpringContext.getBean(ScClassService.class);
			NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
			// 鍥暱
			List<String> audienceList = new ArrayList<String>();
			List<String> tagsList = new ArrayList<String>();
			if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {

				// 鍙戦�鎵�湁鐨勭彮绾ч�鐭�
				if (sendAllClass) {
					articleBase = appArtcileBaseService.save(articleBase);
					// 鏋勫缓notifyArticle瀵硅薄
					NotifyArticle notifyArticle = new NotifyArticle();
					notifyArticle.setSchoolId(articleBase.getSchoolId());
					notifyArticle.setUserId(articleBase.getUserId());
					notifyArticle.setArticleId(articleBase.getId());
					notifyArticle.setNotifyType(articleBase.getSysClassId());
					notifyArticleService.save(notifyArticle);

					// 鏍￠暱鑷繁娣诲姞閫氱煡鍏憡璁板綍
					NotifyArticle notifyArticle2 = new NotifyArticle();
					notifyArticle2.setSenderId(articleBase.getSchoolId());
					notifyArticle2.setUserId(articleBase.getUserId());
					notifyArticle2.setArticleId(articleBase.getId());
					notifyArticle2.setNotifyType(articleBase.getSysClassId());
					notifyArticleService.save(notifyArticle2);

					tagsList.add("school" + articleBase.getSchoolId());
				} else {

					for (int i = 0; scIds != null && i < scIds.length; i++) {
						if (i == 0) {
							// 鏂囩珷鍙坊鍔犱竴娆�
							articleBase = appArtcileBaseService.save(articleBase);
						}
						NotifyArticle notifyArticle = new NotifyArticle();
						notifyArticle.setClassId(scIds[i]);
						notifyArticle.setUserId(articleBase.getUserId());
						notifyArticle.setArticleId(articleBase.getId());
						notifyArticle.setNotifyType(articleBase.getSysClassId());
						notifyArticleService.save(notifyArticle);
						if (i == 0) {
							// 榛樿娣诲姞鍥暱鐨勯�鐭ヤ俊鎭暟鎹�
							notifyArticle.setClassId(0);
							notifyArticle.setSenderId(articleBase.getUserId());
							notifyArticle.setId(notifyArticle.getId() + 1);
							notifyArticleService.save(notifyArticle);

						}
						tagsList.add("scClass" + scIds[i]);
					}
				}
				// JpushUtil.sendByTagList(tagsList, articleBase.getSubject(),
				// articleBase.getSubject());
			} else if (roleProperty == RuyTonAppContants.USER_TYPE_TEACHER) {

				if (!StringUtils.isEmpty(splitClasses)) {
					String[] classes = splitClasses.split(",");

					for (int i = 0; i < classes.length; i++) {
						Integer contactCount = classService.getClassContactCount(Integer.parseInt(classes[i]));
						if (!StringUtils.isEmpty(splitContacts)) {
							// 鐢ㄦ潵鍘婚櫎鑰佸笀璇诲彇鐩稿悓鐨勭彮绾ч�鐭�
							if (i == 0) {
								// 鍙繚瀛樹竴鏉′俊鎭�
								articleBase.setOperaterId(articleBase.getUserId());
								appArtcileBaseService.save(articleBase);
								// 缁欐牎闀挎坊鍔犱竴鏉＄彮绾ч�鐭�
								NotifyArticle notifyArticle = new NotifyArticle();
								notifyArticle.setArticleId(articleBase.getId());
								notifyArticle.setSenderId(articleBase.getSchoolId());
								notifyArticle.setNotifyType(articleBase.getSysClassId());
								notifyArticleService.save(notifyArticle);

								// 缁欒嚜宸变篃娣诲姞涓�潯鏁版嵁
								NotifyArticle notifyArticle2 = new NotifyArticle();
								notifyArticle2.setArticleId(articleBase.getId());
								notifyArticle2.setSenderId(articleBase.getUserId());
								notifyArticle2.setNotifyType(articleBase.getSysClassId());
								notifyArticleService.save(notifyArticle2);

							}

							String[] tempContactsArray = splitContacts.split(",");
							String contacts = tempContactsArray[i];
							if (!StringUtils.isEmpty(contacts)) {
								// 鑾峰彇鏌愪釜鐝骇涓嬭閫変腑鐨勫闀夸俊鎭�
								String[] contactArray = contacts.split("-");
								// 鍙戦�鐝骇涓嬬殑鎵�湁鑱旂郴浜轰俊鎭�鎸夌彮绾ag杩涜鍙戦�
								if (contactCount == contactArray.length) {
									Integer classId = Integer.parseInt(classes[i]);
									NotifyArticle notifyArticle = new NotifyArticle();
									notifyArticle.setClassId(classId);
									notifyArticle.setArticleId(articleBase.getId());
									notifyArticle.setNotifyType(articleBase.getSysClassId());
									notifyArticleService.save(notifyArticle);
									// 鎸夌彮绾х兢鍙�
									tagsList.add("scClass" + classId);
								}
								// 鎸塧lins杩涜鍙戦�
								else {

									for (int j = 0; contactArray != null && j < contactArray.length; j++) {
										User user = appUserService.get(Integer.parseInt(contactArray[j]));
										NotifyArticle notifyArticle = new NotifyArticle();
										notifyArticle.setArticleId(articleBase.getId());
										notifyArticle.setNotifyType(articleBase.getSysClassId());
										notifyArticle.setSenderId(user.getId());
										notifyArticle.setNotifyType(articleBase.getSysClassId());
										notifyArticleService.save(notifyArticle);
										// 灏嗘墍鏈夐儴鍒嗗闀夸俊鎭斁鍏ユ帹閫佸璞′腑
										audienceList.add(user.getUserName());

									}

								}
							}

						}
					}
					// 鑰佸笀鍙兘鍙戦�鐝骇閫氱煡,鍙堝彲鑳芥槸鍗曚釜瀹堕暱閫氱煡
					// JpushUtil.sendByAudienceList(audienceList,
					// articleBase.getSubject(), articleBase.getSubject());
					// JpushUtil.sendByTagList(tagsList,
					// articleBase.getSubject(), articleBase.getSubject());
				}

			}

			resultMap.put("success", true);
			resultMap.put("info", articleBase);
			resultMap.put("tagsList", tagsList);
			resultMap.put("audienceList", audienceList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * 鑾峰彇閫氱煡鍏憡涓嬫煇涓被鍒笅鐨勬墍鏈夐�鐭ヤ俊鎭�
	 * 
	 * @param SysClass
	 *            绫诲埆瀵硅薄
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNotifyArticleList.do")
	public @ResponseBody Map<String, Object> getNotifyArticleList(Integer userId, NotifyArticleSch searchEntitySch,
			Integer roleProperty, HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ArticleBase> list = new ArrayList<ArticleBase>();
		List<NotifyArticle> notifyArticles = null;
		try {

			NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
			DefaultGridResult resultGrid = null;

			if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {
				resultGrid = (DefaultGridResult) this.query(searchEntitySch);
				notifyArticles = (List<NotifyArticle>) resultGrid.getRows();
				list = setArtBaseInfo(notifyArticles, userId);
				resultMap.put("currentPageIndex", resultGrid.getCurrentPageIndex());
				resultMap.put("pageCount", resultGrid.getPageCount());

			} else if (roleProperty == RuyTonAppContants.USER_TYPE_TEACHER) {
				// 鐧诲綍鑰呬负鑰佸笀锛屽苟涓旀煡鐪嬬殑鏄洯鏂归�鐭ョ殑鎯呭喌
				if (String.valueOf(RuyTonAppContants.NOTIFY_TYPE_SCHOOL)
						.equals(String.valueOf(searchEntitySch.getNotifyTypeSch()))) {
					notifyArticles = notifyArticleService.getNotifyArticleListByTeacher(searchEntitySch);
					list = setArtBaseInfo(notifyArticles, userId);
					Integer totalCount = notifyArticleService.getNotifyArticleListCountByTeacher(searchEntitySch);
					int pageCount = totalCount % searchEntitySch.getPageSize() == 0
							? totalCount / searchEntitySch.getPageSize()
							: totalCount / searchEntitySch.getPageSize() + 1;
					resultMap.put("currentPageIndex", searchEntitySch.getPageIndex());
					resultMap.put("pageCount", pageCount);
				} else if (String.valueOf(RuyTonAppContants.NOTIFY_TYPE_CLASS)
						.equals(String.valueOf(searchEntitySch.getNotifyTypeSch()))) {
					notifyArticles = notifyArticleService.getClassNotifyListByTeacher(searchEntitySch);
					list = setArtBaseInfo(notifyArticles, userId);

					Integer totalCount = notifyArticleService.getClassNotifyListCountByTeacher(searchEntitySch);
					int pageCount = totalCount % searchEntitySch.getPageSize() == 0
							? totalCount / searchEntitySch.getPageSize()
							: totalCount / searchEntitySch.getPageSize() + 1;
					resultMap.put("currentPageIndex", searchEntitySch.getPageIndex());
					resultMap.put("pageCount", pageCount);

				}

			} else if (roleProperty == RuyTonAppContants.USER_TYPE_PARENT) {
				// 瀹堕暱鏌ョ湅鍥暱閫氱煡
				if (String.valueOf(RuyTonAppContants.NOTIFY_TYPE_SCHOOL)
						.equals(String.valueOf(searchEntitySch.getNotifyTypeSch()))) {
					notifyArticles = notifyArticleService.getNotifyArticleListByParent(searchEntitySch);
					list = setArtBaseInfo(notifyArticles, userId);
					Integer totalCount = notifyArticleService.getNotifyArticleListCountByParent(searchEntitySch);
					int pageCount = totalCount % searchEntitySch.getPageSize() == 0
							? totalCount / searchEntitySch.getPageSize()
							: totalCount / searchEntitySch.getPageSize() + 1;
					resultMap.put("currentPageIndex", searchEntitySch.getPageIndex());
					resultMap.put("pageCount", pageCount);
				} else if (String.valueOf(RuyTonAppContants.NOTIFY_TYPE_CLASS)
						.equals(String.valueOf(searchEntitySch.getNotifyTypeSch()))) {
					notifyArticles = notifyArticleService.getClassNotifyListByParent(searchEntitySch);
					list = setArtBaseInfo(notifyArticles, userId);

					Integer totalCount = notifyArticleService.getClassNotifyListCountByParent(searchEntitySch);
					int pageCount = totalCount % searchEntitySch.getPageSize() == 0
							? totalCount / searchEntitySch.getPageSize()
							: totalCount / searchEntitySch.getPageSize() + 1;
					resultMap.put("currentPageIndex", searchEntitySch.getPageIndex());
					resultMap.put("pageCount", pageCount);
				}

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

	// 灏佽鎴愰渶瑕佺殑鏂囩珷瀵硅薄
	private List<ArticleBase> setArtBaseInfo(List<NotifyArticle> notifyArticles, Integer userId) {
		AppUserService appUserService = SpringContext.getBean(AppUserService.class);
		SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
		AppArtcileBaseService appArtcileBaseService = SpringContext.getBean(AppArtcileBaseService.class);
		NotifyArticeReadService notifyArticeReadService = SpringContext.getBean(NotifyArticeReadService.class);

		List<ArticleBase> list = new ArrayList<ArticleBase>();
		for (NotifyArticle notifyArticle : notifyArticles) {
			ArticleBase articleBase = appArtcileBaseService.get(notifyArticle.getArticleId());

			if (articleBase != null) {
				// 鏌ヨ褰撳墠鐢ㄦ埛鏄惁璇昏繖绡囬�鐭�
				Integer sendUserId = articleBase.getUserId();
				// 璁剧疆杩欑瘒鏂囩珷闃呰鑰卛d
				articleBase.setUserId(userId);
				// 鍒ゆ柇璇ユ潯閫氱煡鏂囩珷鏄惁宸茶
				boolean isRead = notifyArticeReadService.notifyIsRead(articleBase);
				articleBase.setArticleRead(isRead);
				User user = appUserService.get(sendUserId);
				if (user != null) {
					// 濡傛灉鐢ㄦ埛浣跨敤鐨勪笉鏄湰鍦板浘鐗�鍒欓渶瑕佹煡鎵緎ysupload琛�杩斿洖鍥剧墖鐨勭綉缁滃湴鍧�
					if (StringUtils.isEmpty(user.getDefaultAvatar())) {
						SysUpload sysUpload = sysUploadService.get(user.getAvatar());
						if (sysUpload != null) {
							articleBase.setArtHead(sysUpload.getFileFullPath());
							articleBase.setDefaultAvatar(false);
						}
					} else {
						articleBase.setArtHead(user.getDefaultAvatar());
						articleBase.setDefaultAvatar(true);
					}

				}

				// 鍙兘鏈夊寮犲浘鐗�浠ラ�鍙峰垎闅�
				if (!StringUtils.isEmpty(articleBase.getPicId())) {
					String[] pics = articleBase.getPicId().split(",");
					List<SysUpload> uploads = appArtcileBaseService.getPicsUrlByIds(pics);
					articleBase.setUploads(uploads);
				} else {
					// 缁欎粬涓�釜绌虹殑闆嗗悎
					articleBase.setUploads(new ArrayList<SysUpload>());
				}
				// 鏈�悗杩樻槸璁剧疆鏂囩珷鐨勫彂甯冭�涓哄師鍏堢殑
				articleBase.setUserId(sendUserId);
				list.add(articleBase);
			}

		}

		return list;
	}

	/**
	 * 鏍囪涓�潯閫氱煡涓哄凡璇婚�鐭�
	 * 
	 * @param SysClass
	 *            绫诲埆瀵硅薄
	 * @return
	 */
	@RequestMapping("/readNotify.do")
	public @ResponseBody Map<String, Object> readNotify(NotifyArticeRead notifyArticeRead, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		NotifyArticeReadService notifyArticleService = SpringContext.getBean(NotifyArticeReadService.class);
		try {
			notifyArticeRead = notifyArticleService.save(notifyArticeRead);
			resultMap.put("success", true);
			resultMap.put("info", notifyArticeRead);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	/**
	 * jpush鎺ㄩ�
	 * 
	 * @param tagList
	 *            鏍囩闆嗗悎
	 * @param audienceList
	 *            鍒悕闆嗗悎
	 * @param subject
	 *            鍐呭
	 * @param type
	 *            绫诲瀷 1:绛惧埌 2锛氶�鐭�
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendNotify.do")
	public @ResponseBody Map<String, Object> sendNotify(String[] tagsList, String[] audienceList, String subject,
			String content, String type, Integer roleProperty, HttpServletRequest request,NotifyArticle notifyArticle) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取ios中为读通知个数
//		NotifyArticleService notifyArticleService = SpringContext.getBean(NotifyArticleService.class);
//		notifyArticle.setNotifyType(RuyTonAppContants.NOTIFY_TYPE_CLASS);
//		int classUnreadCount = notifyArticleService.getClassNotifyUnreadCount(notifyArticle, roleProperty);
//		notifyArticle.setNotifyType(RuyTonAppContants.NOTIFY_TYPE_SCHOOL);
//		int schoolUnreadCount = notifyArticleService.getSchoolNotifyUnreadCount(notifyArticle, roleProperty);
		try {
			if (tagsList != null && tagsList.length > 0) {
				JpushUtil.sendByTagList(tagsList, content, subject,1);
			}
			if (audienceList != null && audienceList.length > 0) {
				JpushUtil.sendByAudienceList(audienceList, type, subject, 1);
			}
			resultMap.put("success", true);
			resultMap.put("info", "发送成功");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", "发送失败");
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

	@RequestMapping("/findNotifyReadDetail.do")
	public @ResponseBody Map<String, Object> findNotifyReadDetail(Integer roleProperty, NotifyArticle notifyArticle,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 榛樿鏌ヨ鐨勬槸鏁欏鐝骇绫诲瀷
		notifyArticle.setNotifyType(RuyTonAppContants.NOTIFY_TYPE_CLASS);
		List<User> unreadUserList = new ArrayList<User>();
		List<User> alearyReadUserList = new ArrayList<User>();
		List<User> allUser = new ArrayList<User>();
		NotifyArticleReadDetail info = new NotifyArticleReadDetail();
		try {
			// 鑰佸笀锛屼篃灏辨槸鏌ョ湅鐝骇閫氱煡鐨勯槄璇昏鎯�
			if (RuyTonAppContants.USER_TYPE_TEACHER == roleProperty) {
				ScClass scClass = scClassService.get(notifyArticle.getClassId());
				// 鍙戠殑杩欑瘒閫氱煡涓哄叏鐝�
				if (notifyArticleService.findByArticleIdAndClassId(notifyArticle) != null) {
					allUser = appUserService.getClassParentsContacts(notifyArticle.getClassId(), 0);
				} else {
					allUser = appUserService.getSenderUserList(notifyArticle);

				}
				// 鏌ヨnotifyRead琛紝鏌ョ湅鐢ㄦ埛鏄惁宸查槄璇�
				if (allUser != null) {
					for (User user : allUser) {
						List<User> childList = appUserService.getChildrens(user.getId());
						List<User> childrens = new ArrayList<User>();
						for (User child : childList) {
							ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
							// 濡傛灉鏄綋鍓嶇彮绾у垯鍔犲叆
							if (scStudent.getClassId() == scClass.getId()) {
								childrens.add(child);
							}
						}
						// 璁剧疆灏忓鐨勪俊鎭�
						user.setChildrens(childrens);
						NotifyArticeRead notifyArticeRead = notifyArticeReadService.findNotifyRead(user.getId(),
								notifyArticle.getArticleId());
						if (notifyArticeRead == null) {
							unreadUserList.add(user);
						} else {
							alearyReadUserList.add(user);
						}
					}
				}

				info.setUnreadUserList(unreadUserList);
				info.setReadUserList((alearyReadUserList));
				info.setScClass(scClass);

			} else if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {
				// 鏌ヨ璇ョ彮绾т笅闃呰銆佹湭璇讳汉鍛樿鎯�绗簩涓弬鏁版棤鎰忎箟)
				ScClass scClass = scClassService.get(notifyArticle.getClassId());
				allUser = appUserService.getClassParentsContacts(notifyArticle.getClassId(), 0);
				for (User user : allUser) {
					List<User> childList = appUserService.getChildrens(user.getId());
					List<User> childrens = new ArrayList<User>();
					for (User child : childList) {
						ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
						// 濡傛灉鏄綋鍓嶇彮绾у垯鍔犲叆
						if (scStudent.getClassId() == notifyArticle.getClassId()) {
							childrens.add(child);
						}
					}
					// 璁剧疆灏忓鐨勪俊鎭�
					user.setChildrens(childrens);
					NotifyArticeRead notifyArticeRead = notifyArticeReadService.findNotifyRead(user.getId(),
							notifyArticle.getArticleId());
					if (notifyArticeRead == null) {
						unreadUserList.add(user);
					} else {
						alearyReadUserList.add(user);
					}
				}
				info.setReadUserList(alearyReadUserList);
				info.setUnreadUserList(unreadUserList);
				info.setScClass(scClass);

			}
			resultMap.put("info", info);
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
	 * 鏌ヨ鍥柟閫氱煡鐝骇宸茶鏁伴噺
	 * 
	 * @param tagList
	 * @param audienceList
	 * @param subject
	 * @param content
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/getNotifyReadCount.do")
	public @ResponseBody Map<String, Object> getNotifyReadCount(Integer roleProperty, NotifyArticle notifyArticle,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<NotifyArticleReadDetail> resultList = new ArrayList<NotifyArticleReadDetail>();
		List<NotifyArticle> list = null;
		List<ScClass> scClassList = null;
		try {
			if (roleProperty == RuyTonAppContants.USER_TYPE_SCHOOL) {
				list = notifyArticleService.findByArticleIdAndSchoolId(notifyArticle);
				// 鍙戦�鐨勬槸鍏ㄦ牎鎵�湁鐝骇閫氱煡
				if (list != null && list.size() > 0) {
					// 鏌ヨ鎵�湁璇ュ鏍＄殑鏁欏笀闆嗗悎(鎺掗櫎鍏叡鍦哄湴)
					scClassList = scClassService.getClassListBySchoolId(notifyArticle.getSchoolId(),
							RuyTonAppContants.CLASS_TYPE_CLASSROOM);
					for (ScClass scClass : scClassList) {
						// 鏌ヨ姣忎釜鐝骇涓嬫墍鏈夊闀挎煡璇㈣鏂囩珷闃呰鏁伴噺
						notifyArticle.setClassId(scClass.getId());
						Integer allUserCount = appUserService.getClassParentsContactsCount(scClass);
						Integer readCount = notifyArticleService.getUserReadCount(notifyArticle);
						NotifyArticleReadDetail notifyArticleReadDetail = new NotifyArticleReadDetail();
						notifyArticleReadDetail.setReadUserCount(readCount);
						notifyArticleReadDetail.setAllUserCount(allUserCount);
						notifyArticleReadDetail.setScClass(scClass);
						resultList.add(notifyArticleReadDetail);

					}
				} else {
					// 鏌ヨ鍥暱鎵�彂閫氱煡鐨勪釜鍒彮绾ч槄璇绘儏鍐�
					scClassList = scClassService.getClassListBySchoolId(notifyArticle.getSchoolId(),
							RuyTonAppContants.CLASS_TYPE_CLASSROOM);
					for (ScClass scClass : scClassList) {
						notifyArticle.setClassId(scClass.getId());
						// 閫氳繃鐝骇id鍜屾枃绔爄d鏌ユ壘绗﹀悎鐨�濡傛灉鍥暱閫氱煡浜嗚鐝骇,鍒欐煡璇㈣鐝骇涓嬬殑闃呰璇︽儏
						if (notifyArticleService.findByArticleIdAndClassId(notifyArticle) != null) {
							Integer allUserCount = appUserService.getClassParentsContactsCount(scClass);
							Integer readCount = notifyArticleService.getUserReadCount(notifyArticle);
							NotifyArticleReadDetail notifyArticleReadDetail = new NotifyArticleReadDetail();
							notifyArticleReadDetail.setReadUserCount(readCount);
							notifyArticleReadDetail.setAllUserCount(allUserCount);
							notifyArticleReadDetail.setScClass(scClass);
							resultList.add(notifyArticleReadDetail);
						}
					}

				}
			} else if (roleProperty == RuyTonAppContants.USER_TYPE_TEACHER) {
				// 鑰佸笀鎵�礋璐ｇ殑涓�簺鐝骇
				List<Integer> classIds = notifyArticle.getClassIdsSch();
				for (Integer classId : classIds) {
					ScClass scClass = scClassService.get(classId);
					notifyArticle.setClassId(classId);
					// 鍙戠殑杩欑瘒閫氱煡涓哄叏鐝�
					if (notifyArticleService.findByArticleIdAndClassId(notifyArticle) != null) {
						Integer allUserCount = appUserService.getClassParentsContactsCount(scClass);
						Integer readCount = notifyArticleService.getUserReadCount(notifyArticle);
						NotifyArticleReadDetail notifyArticleReadDetail = new NotifyArticleReadDetail();
						notifyArticleReadDetail.setReadUserCount(readCount);
						notifyArticleReadDetail.setAllUserCount(allUserCount);
						notifyArticleReadDetail.setScClass(scClass);
						resultList.add(notifyArticleReadDetail);
					}
					// 鍙戠殑閫氱煡涓轰釜鍒汉鍛�
					else {
						Integer allUserCount = appUserService.getSenderUserCount(notifyArticle);
						Integer readCount = notifyArticleService.getSenderUserReadCount(notifyArticle);
						NotifyArticleReadDetail notifyArticleReadDetail = new NotifyArticleReadDetail();
						notifyArticleReadDetail.setReadUserCount(readCount);
						notifyArticleReadDetail.setAllUserCount(allUserCount);
						notifyArticleReadDetail.setScClass(scClass);
						resultList.add(notifyArticleReadDetail);
					}
				}
			}

			resultMap.put("success", true);
			resultMap.put("info", resultList);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	/**
	 * 获取论坛分类信息
	 * 论坛分类类型为17(常量)
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getForumClass.do")
	public @ResponseBody Map<String, Object> getForumClass(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysClassSch sysClassSch = new SysClassSch();
			sysClassSch.setSysClassTypeIdSch(RuyTonAppContants.SYSCLASSTYPEID_FORUM);
			List<SysClass> list=sysClassService.getListSysClassAndArticleCount(sysClassSch);
			for(int i = 0; i<list.size(); i++){
				SysClass sysClass = list.get(i);
				if(sysClass.getParentId().equals(0)){
					SysUpload pic = sysUploadService.get(sysClass.getPicId());
					sysClass.setPicUrl(pic.getFileFullPath());
				}
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
	
	@RequestMapping("/getLastForumArticle.do")
	public @ResponseBody Map<String, Object> getLastForumArticle(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//获取横幅数据
			List<ArticleBase> banners = appArtcileBaseService.getScrollForumBanner(null);
			for(int i = 0; i<banners.size(); i++){
				ArticleBase banner = banners.get(i);
				String[] pics = banner.getPicId().split(",");
				List<SysUpload> uploads = appArtcileBaseService.getPicsUrlByIds(pics);
				banner.setUploads(uploads);
			}
			resultMap.put("success", true);
			resultMap.put("info", articleBaseService.getLastForumArticle(RuyTonAppContants.SYSCLASSTYPEID_FORUM));
			resultMap.put("banners", banners);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	@RequestMapping("/getTeacherCourseByClassId.do")
	public @ResponseBody Map<String, Object> getTeacherCourseByClassId(Integer classId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("success", true);
			resultMap.put("info", scTeacherCourseService.findScTecherWithCoursesByClassId(classId));

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	@RequestMapping("/getTeacherCoursesByTeacherId.do")
	public @ResponseBody Map<String, Object> getTeacherCoursesByTeacherId(Integer teacherId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("success", true);
			resultMap.put("info", scTeacherCourseService.getTeacherCoursesByTeacherId(teacherId));

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	/**
	 * 保存作业图片
	 * @param sysUpload
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadHomeworkPic.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> uploadHomeworkPic(SysUpload sysUpload, HttpServletRequest request) {
		System.out.println("进入了uploadHomeworkPic的action中");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			// 淇濆瓨鍒皊ysuoload琛ㄤ腑
			sysUpload = sysUploadService.save(sysUpload);

			resultMap.put("success", true);
			resultMap.put("info", sysUpload);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	@RequestMapping(value = "/addHomework.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addHomework(ArticleBase homework, HttpServletRequest request) {
		System.out.println("进入了addHomework的action中");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String audioReplyContent[] = homework.getBrief().split(".brief.");
			//清空简介
			homework.setBrief("");
			homework = articleBaseService.save(homework);
			Integer parentId = homework.getId();
			List<ArticleBase> audioReplyArticle = new ArrayList<ArticleBase>();
			for(int i = 0; i<audioReplyContent.length; i++){
				ArticleBase audioReply = new ArticleBase();
				audioReply.setId(0);
				audioReply.setParentId(parentId);
				audioReply.setContent(audioReplyContent[i]);
				audioReply.setSource("");
				audioReply.setTeacherId(homework.getTeacherId());
				audioReply.setUserId(homework.getUserId());
				audioReply.setSubject(homework.getSubject());
				audioReply.setClassId(homework.getClassId());
				audioReply.setSysClassId(homework.getSysClassId());
				audioReply = articleBaseService.save(audioReply);
				audioReplyArticle.add(audioReply);
			}
			homework.setAudioReplyArticle(audioReplyArticle);
			resultMap.put("success", true);
			resultMap.put("info", homework);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}

		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	
	@RequestMapping("/getArticleByClassId.do")
	public @ResponseBody Map<String, Object> getArticleByClassId(ArticleBase oldArticleBase,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<ArticleBase> articleBases = articleBaseService.getArticleById(oldArticleBase);
			int totalCount = articleBaseService.getTotalList(oldArticleBase);
			int totalPage = totalCount % oldArticleBase.getPageCount() == 0 ? totalCount / oldArticleBase.getPageCount()
					: totalCount / oldArticleBase.getPageCount() + 1;
			resultMap.put("totalPage", totalPage);
			resultMap.put("page", oldArticleBase.getPage());
			resultMap.put("success", true);
			resultMap.put("info", articleBases);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	
	@RequestMapping("/getAudioReplyArticleByParentId.do")
	public @ResponseBody Map<String, Object> getAudioReplyArticleByParentId(Integer parentId,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			resultMap.put("success", true);
			resultMap.put("info", articleBaseService.findChildArticleByParentId(parentId));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}
	 
}
