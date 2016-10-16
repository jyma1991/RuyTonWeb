package com.ryt.web.entity.ofmessage;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class OfMessageArchiveSch extends SearchEasyUI{

	//根据时间进行搜索的部分
	private Date createdStartSch;
	private Date createdEndSch;

	@ValueField(column = "createdTime", equal = ">=")
	public Date getCreatedStartSch() {
		return createdStartSch;
	}

	public void setCreatedStartSch(Date createdStartSch) {
		this.createdStartSch = createdStartSch;
	}

	@ValueField(column = "createdTime", equal = "<")
	public Date getCreatedEndSch() {
		if (createdEndSch != null) {
			return DateUtil.getDateAfterDay(createdEndSch, 1);
		}
		return createdEndSch;
	}

	public void setCreatedEndSch(Date createdEndSch) {
		this.createdEndSch = createdEndSch;
	}


    private Long messageIDSch;
    private Long conversationIDSch;
    private String fromJIDSch;
    private String fromJIDResourceSch;
    private String toJIDSch;
    private String toJIDResourceSch;
    private Long sentDateSch;
    private String stanzaSch;
    private String bodySch;

    public void setMessageIDSch(Long messageIDSch){
        this.messageIDSch = messageIDSch;
    }
    
    @ValueField(column = "messageID")
    public Long getMessageIDSch(){
        return this.messageIDSch;
    }

    public void setConversationIDSch(Long conversationIDSch){
        this.conversationIDSch = conversationIDSch;
    }
    
    @ValueField(column = "conversationID")
    public Long getConversationIDSch(){
        return this.conversationIDSch;
    }

    public void setFromJIDSch(String fromJIDSch){
        this.fromJIDSch = fromJIDSch;
    }
    
    @ValueField(column = "fromJID")
    public String getFromJIDSch(){
        return this.fromJIDSch;
    }

    public void setFromJIDResourceSch(String fromJIDResourceSch){
        this.fromJIDResourceSch = fromJIDResourceSch;
    }
    
    @ValueField(column = "fromJIDResource")
    public String getFromJIDResourceSch(){
        return this.fromJIDResourceSch;
    }

    public void setToJIDSch(String toJIDSch){
        this.toJIDSch = toJIDSch;
    }
    
    @ValueField(column = "toJID")
    public String getToJIDSch(){
        return this.toJIDSch;
    }

    public void setToJIDResourceSch(String toJIDResourceSch){
        this.toJIDResourceSch = toJIDResourceSch;
    }
    
    @ValueField(column = "toJIDResource")
    public String getToJIDResourceSch(){
        return this.toJIDResourceSch;
    }

    public void setSentDateSch(Long sentDateSch){
        this.sentDateSch = sentDateSch;
    }
    
    @ValueField(column = "sentDate")
    public Long getSentDateSch(){
        return this.sentDateSch;
    }

    public void setStanzaSch(String stanzaSch){
        this.stanzaSch = stanzaSch;
    }
    
    @ValueField(column = "stanza")
    public String getStanzaSch(){
        return this.stanzaSch;
    }

    public void setBodySch(String bodySch){
        this.bodySch = bodySch;
    }
    
    @ValueField(column = "body")
    public String getBodySch(){
        return this.bodySch;
    }


}