package com.ryt.web.entity.stream;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ryt.web.entity.pili.API;
import com.ryt.web.entity.pili.Auth;
import com.ryt.web.entity.pili.PiliException;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.user.User;

public class StStream implements Serializable{
	private Integer id;
	private String streamId;
	private String publishSecurity;
	private String publishKey;
	private String publishRtmp;
	private String publishRtmpUrl;
	private String playRtmp;
	private String playRtmpUrl;
	private String playHls;
	private String playHlsUrl;
	private String streamStatus;
	private String title;
	private String description;
	private String uuid;
	private Integer userId;
	private String addr;
	private Integer disabled;
	private String hub;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private String startTime;
	private Float bytesPerSecond;
	private String deviceName;
	private String deviceId;
	private Integer agentId;
	private Integer schoolId;
	private Integer classId;
	
	private User scAgent;
	private ScSchool scSchool;
	private ScClass scClass;
	
	private String[] profiles;//简介，用于获取
	private String mStreamJsonStr;//流json字符串格式
	private Auth mAuth;//认证
	public static final String ORIGIN = "ORIGIN";
	
	public StStream(){
		
	}

	public StStream(JsonObject jsonObj) {
		streamId = jsonObj.get("id").getAsString();
        hub = jsonObj.get("hub").getAsString();
        createdTime = switchString(jsonObj.get("createdAt").getAsString());//switchStringtoDateTime(jsonObj.get("createdAt").getAsString());
        editedTime = switchString(jsonObj.get("updatedAt").getAsString());//switchStringtoDateTime(jsonObj.get("updatedAt").getAsString());
        title = jsonObj.get("title").getAsString();
        publishKey = jsonObj.get("publishKey").getAsString();
        publishSecurity = jsonObj.get("publishSecurity").getAsString();
        disabled = jsonObj.get("disabled").getAsBoolean() == true? 1: 0;
        /*if(disabled){
        	disabledFlag = 0;
        }else{
        	disabledFlag = 1;
        }
        
        try {
			status = status();
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
        
        Type arrType = new TypeToken<String[]>() {}.getType();
        profiles = new Gson().fromJson(jsonObj.get("profiles"), arrType);
		
        JsonObject hosts = jsonObj.getAsJsonObject("hosts");
        JsonObject publish = hosts.getAsJsonObject("publish");
        JsonObject live = hosts.getAsJsonObject("live");
        JsonObject playback = hosts.getAsJsonObject("playback");
        
        if (publish != null) {
        	publishRtmp = publish.get("rtmp").getAsString();
        	try {
				publishRtmpUrl = this.rtmpPublishUrl();
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (live != null) {
            //liveHdlHost = live.get("hdl").getAsString();
            playHls = live.get("hls").getAsString();
            playHlsUrl = this.hlsLiveUrls().get("ORIGIN");
            playRtmp = live.get("rtmp").getAsString();
            playRtmpUrl = this.rtmpLiveUrls().get("ORIGIN");
        }
        if (playback != null) {
            //playbackHlshost = playback.get("hls").getAsString();
        }

        mStreamJsonStr = jsonObj.toString();
        
        if(mAuth != null){
        	try {
        		Status s = status();
				this.streamStatus = s.status;
				this.addr = s.addr;
				this.startTime = switchString(s.getStartFrom());
				this.bytesPerSecond = s.getBytesPerSecond();
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

	public StStream(JsonObject jsonObject, Auth auth) {
        this(jsonObject);
        mAuth = auth;
        if(mAuth != null){
        	try {
        		Status s = status();
				this.streamStatus = s.status;
				this.addr = s.addr;
				this.startTime = switchString(s.getStartFrom());
				this.bytesPerSecond = s.getBytesPerSecond();
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setStreamId(String streamId){
		this.streamId = streamId;
	}

	public String getStreamId(){
		return this.streamId;
	}

	public void setPublishSecurity(String publishSecurity){
		this.publishSecurity = publishSecurity;
	}

	public String getPublishSecurity(){
		return this.publishSecurity;
	}

	public void setPublishKey(String publishKey){
		this.publishKey = publishKey;
	}

	public String getPublishKey(){
		return this.publishKey;
	}

	public void setPublishRtmp(String publishRtmp){
		this.publishRtmp = publishRtmp;
	}

	public String getPublishRtmp(){
		return this.publishRtmp;
	}

	public void setPublishRtmpUrl(String publishRtmpUrl){
		this.publishRtmpUrl = publishRtmpUrl;
	}

	public String getPublishRtmpUrl(){
		return this.publishRtmpUrl;
	}

	public void setPlayRtmp(String playRtmp){
		this.playRtmp = playRtmp;
	}

	public String getPlayRtmp(){
		return this.playRtmp;
	}

	public void setPlayRtmpUrl(String playRtmpUrl){
		this.playRtmpUrl = playRtmpUrl;
	}

	public String getPlayRtmpUrl(){
		return this.playRtmpUrl;
	}

	public void setPlayHls(String playHls){
		this.playHls = playHls;
	}

	public String getPlayHls(){
		return this.playHls;
	}

	public void setPlayHlsUrl(String playHlsUrl){
		this.playHlsUrl = playHlsUrl;
	}

	public String getPlayHlsUrl(){
		return this.playHlsUrl;
	}

	public void setStreamStatus(String streamStatus){
		this.streamStatus = streamStatus;
	}

	public String getStreamStatus(){
		return this.streamStatus;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getAddr(){
		return this.addr;
	}

	public void setDisabled(Integer disabled){
		this.disabled = disabled;
	}

	public Integer getDisabled(){
		return this.disabled;
	}

	public void setHub(String hub){
		this.hub = hub;
	}

	public String getHub(){
		return this.hub;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}

	public Integer getSortId(){
		return this.sortId;
	}


	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setOperaterId(Integer operaterId){
		this.operaterId = operaterId;
	}

	public Integer getOperaterId(){
		return this.operaterId;
	}

	public void setEditedTime(String editedTime){
		this.editedTime = editedTime;
	}

	public String getEditedTime(){
		return this.editedTime;
	}

	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}

	public String getCreatedTime(){
		return this.createdTime;
	}
	
	public String[] getProfiles() {
        return profiles;
    }
	
	//-------------------------------------新增20151027------------------------------
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Float getBytesPerSecond() {
		return bytesPerSecond;
	}

	public void setBytesPerSecond(Float bytesPerSecond) {
		this.bytesPerSecond = bytesPerSecond;
	}
	//-----------------------------------------------------------------------------
	
	//--------------------------------------新增20151105----------------------------	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	
	public User getScAgent() {
		return scAgent;
	}

	public void setScAgent(User scAgent) {
		this.scAgent = scAgent;
	}

	public ScSchool getScSchool() {
		return scSchool;
	}

	public void setScSchool(ScSchool scSchool) {
		this.scSchool = scSchool;
	}

	public ScClass getScClass() {
		return scClass;
	}

	public void setScClass(ScClass scClass) {
		this.scClass = scClass;
	}


	//-----------------------------------------------------------------------------
	public static class Segment {
        private long start;
        private long end;

        public Segment(long start, long end) {
            this.start = start;
            this.end = end;
        }
        public long getStart() {
            return start;
        }
        public long getEnd() {
            return end;
        }
    }
	
	public Auth getmAuth() {
		return mAuth;
	}

	public void setmAuth(Auth mAuth) {
		this.mAuth = mAuth;
	}


	public static class SaveAsResponse {
        private String url;
        private String targetUrl;
        private String persistentId;
        private String mJsonString;

        public SaveAsResponse(JsonObject jsonObj) {
            url = jsonObj.get("url").getAsString();
            targetUrl = jsonObj.get("targetUrl").getAsString();
            persistentId = jsonObj.get("persistentId").getAsString();
            mJsonString = jsonObj.toString();
        }

        public String getUrl() {
            return url;
        }
        public String getTargetUrl() {
            return targetUrl;
        }
        public String getPersistentId() {
            return persistentId;
        }
        
        @Override
        public String toString() {
            return mJsonString;
        }
    }

    public static class SnapshotResponse {
        private String targetUrl;
        private String persistentId;
        private String mJsonString;
        public SnapshotResponse(JsonObject jsonObj) {
            targetUrl = jsonObj.get("targetUrl").getAsString();
            persistentId = jsonObj.get("persistentId").getAsString();
            mJsonString = jsonObj.toString();
        }
        
        public String getTargetUrl() {
            return targetUrl;
        }
        public String getPersistentId() {
            return persistentId;
        }
        
        @Override
        public String toString() {
            return mJsonString;
        }
    }

    public static class FramesPerSecond {
        private float audio;
        private float video;
        private float data;
        public FramesPerSecond(float audio, float video, float data) {
            this.audio = audio;
            this.video = video;
            this.data = data;
        }
        
        public float getAudio() {
            return audio;
        }
        public float getVideo() {
            return video;
        }
        public float getData() {
            return data;
        }
    }

    public static class SegmentList {
        private List<Segment> segmentList;

        public SegmentList(JsonObject jsonObj) {
            JsonArray respArray = jsonObj.getAsJsonArray("segments");
            Iterator<JsonElement> it = respArray.iterator();
            segmentList = new ArrayList<Segment>();
            while (it.hasNext()) {
                JsonObject json = it.next().getAsJsonObject();
                segmentList.add(new Segment(json.get("start").getAsLong(), json.get("end").getAsLong()));
            }
        }

        public List<Segment> getSegmentList() {
            return segmentList;
        }
    }
	
    public static class Status {
        private String addr;
        private String status;
        private float bytesPerSecond;
        private FramesPerSecond framesPerSecond;
        private String startFrom;
        private String mJsonString;
        public Status(JsonObject jsonObj) {
            addr = jsonObj.get("addr").getAsString();
            status = jsonObj.get("status").getAsString();
            try {
                startFrom = jsonObj.get("startFrom").getAsString();
                bytesPerSecond = jsonObj.get("bytesPerSecond").getAsFloat();
                JsonObject framesPerSecondJsonObj = jsonObj.getAsJsonObject("framesPerSecond");
                float audio = framesPerSecondJsonObj.get("audio").getAsFloat();
                float video = framesPerSecondJsonObj.get("video").getAsFloat();
                float data = framesPerSecondJsonObj.get("data").getAsFloat();
                framesPerSecond = new FramesPerSecond(audio, video, data);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            mJsonString = jsonObj.toString();
        }
        public String getAddr() {
            return addr;
        }
        public String getStatus() {
            return status;
        }
        public float getBytesPerSecond() {
            return bytesPerSecond;
        }
        public FramesPerSecond getFramesPerSecond() {
            return framesPerSecond;
        }
        
        public String getStartFrom() {
            return startFrom;
        }
		
		public void setBytesPerSecond(float bytesPerSecond) {
			this.bytesPerSecond = bytesPerSecond;
		}
		@Override
        public String toString() {
            return mJsonString;
        }
    }

    public static class StreamList {
        private String marker;
        private List<StStream> itemList;
        public StreamList(JsonObject jsonObj) {
            this.marker = jsonObj.get("marker").getAsString();

            JsonArray respArray = jsonObj.getAsJsonArray("items");
            Iterator<JsonElement> it = respArray.iterator();
            itemList = new ArrayList<StStream>();
            while (it.hasNext()) {
              JsonObject json = it.next().getAsJsonObject();
              itemList.add(new StStream(json));
            }
        }
        
        public StreamList(JsonObject jsonObj, Auth auth) {
            this.marker = jsonObj.get("marker").getAsString();

            try {
                JsonArray respArray = jsonObj.getAsJsonArray("items");
                Iterator<JsonElement> it = respArray.iterator();
                itemList = new ArrayList<StStream>();
                while (it.hasNext()) {
                  JsonObject json = it.next().getAsJsonObject();
                  itemList.add(new StStream(json, auth));
                }
            } catch (java.lang.ClassCastException e) {
                e.printStackTrace();
            }
        }

        public String getMarker() {
            return marker;
        }
        public List<StStream> getStreams() {
            return itemList;
        }
    }

    public StStream update(String publishKey, String publishSecrity, boolean disabled) throws PiliException {
        return API.updateStream(mAuth, this.streamId, publishKey, publishSecrity, disabled);
    }

    public SegmentList segments() throws PiliException {
        return API.getStreamSegments(mAuth, this.streamId, 0, 0);
    }

    public SegmentList segments(long start, long end) throws PiliException {
        return API.getStreamSegments(mAuth, this.streamId, start, end);
    }
    
    public SegmentList segments(long start, long end, int limit) throws PiliException {
        return API.getStreamSegments(mAuth, this.streamId, start, end, limit);
    }

    public Status status() throws PiliException {
        return API.getStreamStatus(mAuth, this.streamId);
    }

    public String rtmpPublishUrl() throws PiliException {
        return API.publishUrl(this.publishRtmp, this.streamId, this.publishKey, this.publishSecurity, 0);
    }
    public Map<String, String> rtmpLiveUrls() {
        return API.rtmpLiveUrl(this.playRtmp, this.streamId, this.profiles);
    }
    public Map<String, String> hlsLiveUrls() {
        return API.hlsLiveUrl(this.playHls, this.streamId, this.profiles);
    }
    public Map<String, String> hlsPlaybackUrls(long start, long end) throws PiliException {
        return API.hlsPlaybackUrl(this.playHls,  this.streamId, start, end,  this.profiles);
    }
    public String delete() throws PiliException {
        return API.deleteStream(mAuth, this.streamId);
    }

    public String toJsonString() {
        return mStreamJsonStr;
    }

	 public Timestamp switchStringtoDateTime(String str){
    	//System.out.println(str);
    	int endIndex = str.indexOf("+") == -1? str.length() : str.indexOf("+");	
    	int length = str.substring(0, endIndex).split("T")[1].length();
    	String temp[] = str.substring(0, endIndex-(length-8)).split("T");
    	String timeStr = temp[0]+" "+temp[1];
    	//System.out.println(timeStr);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
    	Timestamp time = null;
    	
		//System.out.println(timeStr);
		time = Timestamp.valueOf(timeStr);
    	return time;
    }
	 
	 public String switchString(String str){
	    	//System.out.println(str);
	    	int endIndex = str.indexOf("+") == -1? str.length() : str.indexOf("+");	
	    	int length = str.substring(0, endIndex).split("T")[1].length();
	    	String temp[] = str.substring(0, endIndex-(length-8)).split("T");
	    	String timeStr = temp[0]+" "+temp[1];
	    	//如果获取的开始时间为默认的，在这里手动把它设置为当前时间
	    	if(timeStr.equals("0001-01-01 00:00:00")){
	    		Calendar date =  Calendar.getInstance();
	    		timeStr = ""+date.get(Calendar.YEAR)+"-"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.DATE)+" 00:00:00";
	    	}
	    	/*
	    	//System.out.println(timeStr);
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
	    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
	    	Timestamp time = null;
	    	
			//System.out.println(timeStr);
			time = Timestamp.valueOf(timeStr);*/
	    	return timeStr;
	    }
	 
    public SaveAsResponse saveAs(String fileName, String format, long startTime, long endTime, String notifyUrl) throws PiliException {
        return API.saveAs(mAuth, this.streamId, fileName, format, startTime, endTime, notifyUrl);
    }
    public SaveAsResponse saveAs(String fileName, String format, long startTime, long endTime) throws PiliException {
        return saveAs(fileName, format, startTime, endTime, null);
    }
}