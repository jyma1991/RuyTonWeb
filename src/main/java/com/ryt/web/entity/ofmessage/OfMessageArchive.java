package com.ryt.web.entity.ofmessage;

import java.util.Date;

public class OfMessageArchive {
	private Long messageID;
	private Long conversationID;
	private String fromJID;
	private String fromJIDResource;
	private String toJID;
	private String toJIDResource;
	private Long sentDate;
	private String stanza;
	private String body;
	

	private String headUrl;
	private String sendName;

	public void setMessageID(Long messageID){
		this.messageID = messageID;
	}

	public Long getMessageID(){
		return this.messageID;
	}

	public void setConversationID(Long conversationID){
		this.conversationID = conversationID;
	}

	public Long getConversationID(){
		return this.conversationID;
	}

	public void setFromJID(String fromJID){
		this.fromJID = fromJID;
	}

	public String getFromJID(){
		return this.fromJID;
	}

	public void setFromJIDResource(String fromJIDResource){
		this.fromJIDResource = fromJIDResource;
	}

	public String getFromJIDResource(){
		return this.fromJIDResource;
	}

	public void setToJID(String toJID){
		this.toJID = toJID;
	}

	public String getToJID(){
		return this.toJID;
	}

	public void setToJIDResource(String toJIDResource){
		this.toJIDResource = toJIDResource;
	}

	public String getToJIDResource(){
		return this.toJIDResource;
	}

	public void setSentDate(Long sentDate){
		this.sentDate = sentDate;
	}

	public Long getSentDate(){
		return this.sentDate;
	}

	public void setStanza(String stanza){
		this.stanza = stanza;
	}

	public String getStanza(){
		return this.stanza;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return this.body;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	
	
	
}