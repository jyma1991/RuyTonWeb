package com.ryt.web.dao.stream;

import java.util.ArrayList;
import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.ryt.web.entity.pili.API;
import com.ryt.web.entity.pili.Pili;
import com.ryt.web.entity.pili.PiliException;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.stream.StStream.Status;
import com.ryt.web.entity.stream.StStream.StreamList;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;




public class StreamDaoImpl extends HibernateDaoSupport implements StreamDao{

	private static final String INVALID_STREAM_ID = "_invalidStreamId";
    private static final String INVALID_HUB_NAME = "_invalidHubName";
    private static final String INVALID_PUBLISH_SECURITY = "_invalidPublisSecurity";

    private static final String NULL_STREAM_ID_EXCEPTION_MSG = "FATAL EXCEPTION: streamId is null!";
    private static final String NULL_HUBNAME_EXCEPTION_MSG = "FATAL EXCEPTION: hubName is null!";
    private static final String OK_DELETE_STREAM_RES_MSG = "No Content";
    private static final String NOT_FOUND_MSG = "Not Found";
    private static final String BAD_REQ_MSG = "Bad Request";
    private static final String ILLEGAL_TIME_MSG = "Illegal startTime or endTime!";
    private static final String SEGMENTS_IS_NULL = "Segments is null";

    private static final String[] DEFAULT_PRESETS = new String[] {null, "240p", "360p", "480p"};

    private static final String STREAM_STATUS_DISCONNECTED = "disconnected";
    private static final String STREAM_STATUS_CONNECTED = "connected";
    private static final String PRE_STREAM_PRESET_PUBLISH_SECURITY = "static";
    private static final String PRE_STREAM_PRESET_TITLE = "testTitle";
    private static final String EXPECTED_BASE_PUBLISH_URL = "rtmp://" + RTMP_PUBLISH_HOST + "/" + HUB_NAME + "/" + PRE_STREAM_PRESET_TITLE;
    private static final String EXPECTED_BASE_RTMP_LIVEURL = "rtmp://" + RTMP_PLAY_HOST + "/" + HUB_NAME;
    private static final String EXPECTED_BASE_HLS_LIVEURL = "http://" + HLS_PLAY_HOST + "/" + HUB_NAME;
    
	private static final Pili newP = new Pili(ACCESS_KEY, SECRET_KEY, HUB_NAME);
	private static final API api = new API();
	
	
	//根据流Id查询服务器上对应的流对象
	public StStream findServerStreamByStreamId(String streamId) throws PiliException{
		return api.getStream(newP.getmAuth(), streamId);
	}
	//同步流
	public List<StStream> synchronizedStream() {
		StreamList list = null;
		List<StStream> item = new ArrayList<StStream>();
		try {
			list = newP.listStreams(null,10000);
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//同步
		for(int i = 0; i<list.getStreams().size(); i++){
			StStream s = list.getStreams().get(i);
			Status status = null;
			try {
				status = newP.getStream(s.getStreamId()).status();
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.setStreamStatus(status.getStatus());
			s.setAddr(status.getAddr());
			//获取新增的开始时间以及获取每秒字节数
			s.setStartTime(s.switchString(status.getStartFrom()));
			s.setBytesPerSecond(status.getBytesPerSecond());
			s.setDisabled(0);
			//s.setPlayHlsUrl(s.);
			s.setPlayRtmpUrl(s.rtmpLiveUrls().get("ORIGIN"));
			s.setPlayHlsUrl(s.hlsLiveUrls().get("ORIGIN"));
			try {
				s.setPublishRtmpUrl(s.rtmpPublishUrl());
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item.add(s);
			
		}
		return item;
		
	}
	
	/**
	 * 创建流
	 * @param title
	 * @param publishKey
	 * @param publishSecurity
	 * @return
	 */
	public StStream createStream(String title,String publishKey,String publishSecurity){
		try {
			return newP.createStream(title, publishKey, publishSecurity);
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据id查询流的状态
	 * @param id
	 * @return
	 */
	public Status getStatusById(String id){
		try {
			return api.getStreamStatus(newP.getmAuth(), id);
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 更新流
	 * @param streamId
	 * @param publishKey
	 * @param publishSecrity
	 * @param disabled
	 * @return
	 * @throws PiliException
	 */
	 public StStream update(String streamId,String publishKey, String publishSecrity, boolean disabled) throws PiliException {
	        return API.updateStream(newP.getmAuth(),streamId, publishKey, publishSecrity, disabled);
	  }
	 /**
	  * 删除服务器上的流
	  * @param stream
	  * @return
	  */
	 public String delete(StStream stream){
		  try {
			return API.deleteStream(newP.getmAuth(), stream.getStreamId());
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	 }
	
	 /**
	  * 获取服务器上所有未连接的流
	  * @return
	  */
	public  Object listServerUndisabledStreams(){
		StreamList list = null;
		try {
			list = API.listStreams(newP.getmAuth(), HUB_NAME, "connected", "",100000000, "");
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<StStream> streams = list.getStreams();
		System.out.println("服务器上所有连接的流：");
		for(int i = 0; i<streams.size(); i++){
			System.out.println((i+1)+".  "+streams.get(i).getStreamId());
		}
		
		return streams;
	}
	

}
