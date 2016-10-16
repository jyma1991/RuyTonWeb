package com.ryt.web.entity.article;

import java.util.Date;
import java.util.List;

import com.ryt.web.entity.user.User;

public class ArticleComment {
	private Integer articleId;
	private String subject;
	private String createdIP;
	private String content;
	private Integer sysClassId;
	private Integer articleBaseId;
	private Integer commentProperty;
	private Integer parentId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer score;
	private Integer voiceId;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
    private String state = "closed";
    
    //发送者名字
    private String sendName;
    //接收者名字
    private String getterName;
    //回复人信息
    private User forumAuthor;
    
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCreatedIP() {
		return createdIP;
	}
	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSysClassId() {
		return sysClassId;
	}
	public void setSysClassId(Integer sysClassId) {
		this.sysClassId = sysClassId;
	}
	public Integer getArticleBaseId() {
		return articleBaseId;
	}
	public void setArticleBaseId(Integer articleBaseId) {
		this.articleBaseId = articleBaseId;
	}
	public Integer getCommentProperty() {
		return commentProperty;
	}
	public void setCommentProperty(Integer commentProperty) {
		this.commentProperty = commentProperty;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public Byte getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}
	public String getEditedTime() {
		return editedTime;
	}
	public void setEditedTime(String editedTime) {
		this.editedTime = editedTime;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getGetterName() {
		return getterName;
	}
	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}
	public User getForumAuthor() {
		return forumAuthor;
	}
	public void setForumAuthor(User forumAuthor) {
		this.forumAuthor = forumAuthor;
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * @return the voiceId
	 */
	public Integer getVoiceId() {
		return voiceId;
	}
	/**
	 * @param voiceId the voiceId to set
	 */
	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}
}