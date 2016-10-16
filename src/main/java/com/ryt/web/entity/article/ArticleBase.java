package com.ryt.web.entity.article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;

public class ArticleBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String picId;
	private String author;
	private String source;
	private String brief;
	private String keyWords;
	private String content;
	private Integer hits;
	private Integer parentId;
	private Integer replyArticleBaseId;//2016.1.29新增 by wyp
	private Integer articleProperty;
	private Integer articleReceiver;
	private Integer sysClassId;

	private Integer agentId;
	private Integer schoolId;
	private Integer classId;
	private Integer teacherId;
	private Integer studentId;

	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private String extFields;
	private String location;
	private String lng;
	private String lat;
	// 鏂囩珷鍙戦�鑰呭ご鍍�
	private String artHead;
	// 是否使用了本地头像
	private boolean defaultAvatar;
	// 音乐播放的url
	private String url;
	private String artist;
	private String title;
	private String art;
	private boolean articleRead;
	// 发送者类别
	private Integer sendRoleProperty;
	private String schoolName;
	// 老師負責班級id數組
	private int[] classIds;
	// 家长圈
	private String nickName;
	//设置代理商
	private User agent;
	//老师
	private User teacher;
	//设置学校
	private ScSchool school;
	//设置班级
	private ScClass scClass;
	//点赞人员集合
	List<User> articleLikeUsers;
	//设置社区文章发送人
	private User forumAuthor;
	private Integer firstLastId;
	//论坛回复列表
	private List<ArticleComment> forumReplyComment;
	//论坛回复列表(父评论)
	private List<ArticleBase> mainForumReply;
	//子评论数量
	private Integer assisForumReplyNums;
	//父评论数量
	private Integer mainForumReplyNums;
	//被评论人
	private User receiver;
	//楼层数
	private String floor;
	//论坛文章的主类以及子类
	private SysClass mainType;
	private SysClass assisType;
	//语音回复打分内容
	private List<ArticleBase> audioReplyArticle;
	//作业发布老师担任课程
	private List<ScTeacherCourse> teacherCourses;
	//作业发布中的语音
	private SysUpload homeworkAudio;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	// 鏂囩珷鍥剧墖闆嗗悎
	List<SysUpload> uploads;
	// 鍜屽皬瀛╃殑鍏崇郴
	private String relateName;
	// 灏忓鍚嶅瓧
	private String childName;
	// 褰撳墠鐢ㄦ埛鏄惁鐐硅禐
	private boolean likeArticle;
	// 鐐硅禐娆℃暟
	private Integer articleLikeCount;
	// 璇勮淇℃伅
	List<ArticleComment> articleComments;
	// 鍒嗛〉鏁伴噺
	private Integer pageCount;
	// 褰撳墠椤垫暟
	private Integer page;
	// 浠庡摢涓紑濮嬫煡璇�
	private Integer firstResult;
	private Integer lastId;

	public String getExtFields() {
		return extFields;
	}

	public void setExtFields(String extFields) {
		this.extFields = extFields;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getPicId() {
		return this.picId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return this.source;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getHits() {
		return this.hits;
	}

	public Integer getReplyArticleBaseId() {
		return replyArticleBaseId;
	}

	public void setReplyArticleBaseId(Integer replyArticleBaseId) {
		this.replyArticleBaseId = replyArticleBaseId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setArticleProperty(Integer articleProperty) {
		this.articleProperty = articleProperty;
	}

	public Integer getArticleProperty() {
		return this.articleProperty;
	}

	public void setArticleReceiver(Integer articleReceiver) {
		this.articleReceiver = articleReceiver;
	}

	public Integer getArticleReceiver() {
		return this.articleReceiver;
	}

	public void setSysClassId(Integer sysClassId) {
		this.sysClassId = sysClassId;
	}

	public Integer getSysClassId() {
		return this.sysClassId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Integer getSortId() {
		return this.sortId;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}

	public Integer getOperaterId() {
		return this.operaterId;
	}

	public void setEditedTime(String editedTime) {
		this.editedTime = editedTime;
	}

	public String getEditedTime() {
		return this.editedTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public String getArtHead() {
		return artHead;
	}

	public void setArtHead(String artHead) {
		this.artHead = artHead;
	}

	public List<SysUpload> getUploads() {
		return uploads;
	}

	public void setUploads(List<SysUpload> uploads) {
		this.uploads = uploads;
	}

	public String getRelateName() {
		return relateName;
	}

	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public boolean isLikeArticle() {
		return likeArticle;
	}

	public void setLikeArticle(boolean likeArticle) {
		this.likeArticle = likeArticle;
	}

	public Integer getArticleLikeCount() {
		return articleLikeCount;
	}

	public void setArticleLikeCount(Integer articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}

	public List<ArticleComment> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

	public boolean isDefaultAvatar() {
		return defaultAvatar;
	}

	public void setDefaultAvatar(boolean defaultAvatar) {
		this.defaultAvatar = defaultAvatar;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public boolean isArticleRead() {
		return articleRead;
	}

	public void setArticleRead(boolean articleRead) {
		this.articleRead = articleRead;
	}

	public Integer getSendRoleProperty() {
		return sendRoleProperty;
	}

	public void setSendRoleProperty(Integer sendRoleProperty) {
		this.sendRoleProperty = sendRoleProperty;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int[] getClassIds() {
		return classIds;
	}

	public void setClassIds(int[] classIds) {
		this.classIds = classIds;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public ScSchool getSchool() {
		return school;
	}

	public void setSchool(ScSchool school) {
		this.school = school;
	}

	public ScClass getScClass() {
		return scClass;
	}

	public void setScClass(ScClass scClass) {
		this.scClass = scClass;
	}

	public List<User> getArticleLikeUsers() {
		return articleLikeUsers;
	}

	public void setArticleLikeUsers(List<User> articleLikeUsers) {
		this.articleLikeUsers = articleLikeUsers;
	}

	public User getForumAuthor() {
		return forumAuthor;
	}

	public void setForumAuthor(User forumAuthor) {
		this.forumAuthor = forumAuthor;
	}

	public List<ArticleComment> getForumReplyComment() {
		return forumReplyComment;
	}

	public void setForumReplyComment(List<ArticleComment> forumReplyComment) {
		this.forumReplyComment = forumReplyComment;
	}

	public List<ArticleBase> getMainForumReply() {
		return mainForumReply;
	}

	public void setMainForumReply(List<ArticleBase> mainForumReply) {
		this.mainForumReply = mainForumReply;
	}

	public Integer getAssisForumReplyNums() {
		return assisForumReplyNums;
	}

	public void setAssisForumReplyNums(Integer assisForumReplyNums) {
		this.assisForumReplyNums = assisForumReplyNums;
	}

	public Integer getMainForumReplyNums() {
		return mainForumReplyNums;
	}

	public void setMainForumReplyNums(Integer mainForumReplyNums) {
		this.mainForumReplyNums = mainForumReplyNums;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Integer getFirstLastId() {
		return firstLastId;
	}

	public void setFirstLastId(Integer firstLastId) {
		this.firstLastId = firstLastId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public SysClass getMainType() {
		return mainType;
	}

	public void setMainType(SysClass mainType) {
		this.mainType = mainType;
	}

	public SysClass getAssisType() {
		return assisType;
	}

	public void setAssisType(SysClass assisType) {
		this.assisType = assisType;
	}

	/**
	 * @return the teacher
	 */
	public User getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public List<ArticleBase> getAudioReplyArticle() {
		return audioReplyArticle;
	}

	public void setAudioReplyArticle(List<ArticleBase> audioReplyArticle) {
		this.audioReplyArticle = audioReplyArticle;
	}

	public List<ScTeacherCourse> getTeacherCourses() {
		return teacherCourses;
	}

	public void setTeacherCourses(List<ScTeacherCourse> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}

	public SysUpload getHomeworkAudio() {
		return homeworkAudio;
	}

	public void setHomeworkAudio(SysUpload homeworkAudio) {
		this.homeworkAudio = homeworkAudio;
	}
	
}