package com.ryt.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.SpringContext;
import org.durcframework.core.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.app.commom.OpenfireConstants;
import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.OpenfireUtil;
import com.ryt.app.util.SendSmsUtil;

import com.ryt.web.entity.ofmessage.OfMessageArchive;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.ofmessage.OfMessageArchiveService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sys.SysUploadService;

@Controller
@RequestMapping(value = "/app/api/chat")
public class AppChatController {

	@Autowired
	AppUserService appUserService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	OfMessageArchiveService ofMessageArchiveService;
	@Autowired
	ScStudentService scStudentService;

	/**
	 * 获取聊天信息
	 * 
	 * @param request
	 * @param archiveConversations
	 * @return
	 */
	@RequestMapping(value = "/getConversationMessage.do")
	@ResponseBody
	public Map<String, Object> getConversationMessage(HttpServletRequest request, OfMessageArchive ofMessageArchive,
			String chatType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OfMessageArchive> messageList = new ArrayList<OfMessageArchive>();
		List<OfMessageArchive> tempList = null;

		try {
			boolean groupChat = false;
			// 发送者和接收者的用户名
			String fromUserName = ofMessageArchive.getFromJID();
			String toUserName = ofMessageArchive.getToJID();
			if (RuyTonAppContants.CHAT_TYPE_PRIVATE.equals(chatType)) {
				ofMessageArchive.setFromJID(fromUserName + "@" + OpenfireConstants.SERVER_IP);
				ofMessageArchive.setToJID(toUserName + "@" + OpenfireConstants.SERVER_IP);
			} else if (RuyTonAppContants.CHAT_TYPE_GROUP.equals(chatType)) {
				ofMessageArchive.setFromJID(fromUserName + "@" + OpenfireConstants.SERVER_IP);
				ofMessageArchive.setToJID(toUserName + "@" + OpenfireConstants.ROOM_JID);
				groupChat = true;
			}

			// 重新封装下ownerjid和withjid
			if (ofMessageArchive.getMessageID() == 0) {
				tempList = ofMessageArchiveService.getMessageByPage(ofMessageArchive, groupChat);
			} else {
				tempList = ofMessageArchiveService.getOldMessageByPage(ofMessageArchive, groupChat);
			}

			for (OfMessageArchive messages : tempList) {

				User user = null;
				// 群聊的时候只需设置发送者的头像
				if (groupChat) {
					if (ofMessageArchive.getFromJID().equals(messages.getFromJID())) {
						user = appUserService.getUserByUserName(fromUserName);
					} else {
						user = appUserService.getUserByUserName(messages.getFromJID().split("@")[0]);
					}

				} else {
					// 发送者
					if (ofMessageArchive.getFromJID().equals(messages.getFromJID())) {
						user = appUserService.getUserByUserName(fromUserName);
					} else if (ofMessageArchive.getFromJID().equals(messages.getToJID())) {
						user = appUserService.getUserByUserName(toUserName);
					}
				}

				if (user != null) {
					messages.setSendName(user.getTrueName());
					// 设置头像
					if (StringUtils.isEmpty(user.getDefaultAvatar())) {
						SysUpload sysUpload = sysUploadService.get(user.getAvatar());
						if (sysUpload != null) {
							messages.setHeadUrl(sysUpload.getFileFullPath());
						} else {
							// 既没有网络头像，也没有本地头像的情况，根据性别给他一个头像
							if (user.getSex() == RuyTonAppContants.USER_SEX_MAN) {
								messages.setHeadUrl(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
							} else if (user.getSex() == RuyTonAppContants.USER_SEX_WOMAN) {
								messages.setHeadUrl(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
							}
						}
					} else {
						messages.setHeadUrl(user.getDefaultAvatar());
					}
				}

				messageList.add(messages);
			}
			resultMap.put("success", true);
			resultMap.put("info", messageList);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());

		}
		resultMap.put("token", (String) request.getAttribute("token"));
		return resultMap;

	}

	/**
	 * 获取用户状态
	 * 
	 * @param request
	 * @param archiveConversations
	 * @return
	 */
	@RequestMapping(value = "/getUserOlineStatus.do")
	@ResponseBody
	public Map<String, Object> getUserOlineStatus(HttpServletRequest request, OfMessageArchive ofMessageArchive,
			String chatType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// if(RuyTonAppContants.CHAT_TYPE_PRIVATE.equals(chatType)){
			//
			// }else if()
			short onlineStatus = OpenfireUtil.IsUserOnLine(
					OpenfireConstants.BASE_GET_ONLINE_USERS + "?jid=" + ofMessageArchive.getToJID() + "&type=xml");
			resultMap.put("success", true);
			resultMap.put("info", onlineStatus);
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
	@RequestMapping(value = "getChatContacts.do")
	@ResponseBody
	public Map<String, Object> getContacts(User user, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 根据不同的用户,可以看到的老师信息不同,园长和老师可以看到全校老师的信息,
			// 家长用户只能看到其孩子相关联的班级老师信息
			List<User> parentList = null;
			// service中根据不同的roleproperty去获取信息
			List<User> tempTeacherList = appUserService.getTeacherContacts(user);
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
				classList = appUserService.getClassByTeacher(user);
				for (ScClass scClass : classList) {
					resultParentsList = new ArrayList<User>();
					parentList = appUserService.getClassParentsContacts(scClass.getId(), user.getUserId());
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
						List<User> childList = appUserService.getChildrens(entity2.getId());
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
				classList = appUserService.getClassByParents(user);
				for (ScClass scClass : classList) {
					resultParentsList = new ArrayList<User>();
					parentList = appUserService.getClassParentsContacts(scClass.getId(), user.getUserId());
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
						List<User> childList = appUserService.getChildrens(entity2.getId());
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
				List<ScClass> tempClassList = appUserService.getClassBySchool(user);

				classList = new ArrayList<ScClass>();
				for (ScClass scClass : tempClassList) {
					resultParentsList = new ArrayList<User>();
					parentList = appUserService.getClassParentsContacts(scClass.getId(), user.getUserId());
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
						List<User> childList = appUserService.getChildrens(entity2.getId());
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
			// 把院长信息返回回去
			
			User schoolUser = appUserService.get(user.getSchoolId());
			if (StringUtils.isEmpty(schoolUser.getDefaultAvatar())) {
				SysUpload sysUpload = uploadService.get(schoolUser.getAvatar());
				if (sysUpload != null) {
					schoolUser.setArtHead(sysUpload.getFileFullPath());
				} else {
					if (RuyTonAppContants.USER_SEX_WOMAN == schoolUser.getSex()) {
						schoolUser.setArtHead(RuyTonAppContants.DEFAULT_WOMAN_HEAD_URL);
					} else if (RuyTonAppContants.USER_SEX_MAN == schoolUser.getSex()) {
						schoolUser.setArtHead(RuyTonAppContants.DEFAULT_MAN_HEAD_URL);
					}
				}
			} else {
				schoolUser.setArtHead(schoolUser.getDefaultAvatar());
			}

			resultMap.put("schoolUser", schoolUser);
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

}
