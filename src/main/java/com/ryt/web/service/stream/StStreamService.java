package com.ryt.web.service.stream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.stream.StStreamDao;
import com.ryt.web.dao.stream.StreamDao;
import com.ryt.web.dao.stream.StreamDaoImpl;
import com.ryt.web.entity.pili.API;
import com.ryt.web.entity.pili.Pili;
import com.ryt.web.entity.pili.PiliException;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.stream.StStream.SaveAsResponse;
import com.ryt.web.entity.stream.StStream.SnapshotResponse;

@Service
public class StStreamService extends CrudService<StStream, StStreamDao> {
	
	private  StreamDaoImpl streamDao = new StreamDaoImpl();

	public StreamDaoImpl getStreamDao() {
		return streamDao;
	}

	public void setStreamDao(StreamDaoImpl streamDao) {
		this.streamDao = streamDao;
	}

	/**
	 * 根据班级id查询这个班级以及其他没有被分配的流列表
	 * @param classId
	 * @return
	 */
	public List<StStream> listUnDividedStream(int classId){
		return this.getDao().getUnDividedStream(classId);
	}
	
	/**
	 * 根据学校id查询这个学校所有的流
	 * @param schoolId
	 * @return
	 */
	public List<StStream> listSchoolStream(int schoolId,int classId){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", schoolId));
		query.setQueryAll(true);
		List<StStream> list = this.find(query);
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getClassId() == null){
				continue;
			}
			if((int)list.get(i).getClassId() >0 && (int)list.get(i).getClassId() != classId){
				list.remove(i);
				i--;
				continue;
			}
		}
		return list;
	}
	
	/**
	 * 根据班级号查询流
	 * @param classId
	 * @return
	 */
	public StStream getStStreamByClassId(int classId){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("classId", classId));
		List<StStream> list = this.find(query);
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}
	

	/**
	 * 根据班级号取消流连接
	 * @param classId
	 */
	public void cancelStStreamLink(int classId){
		this.getDao().cancelStStreamLink(classId);
	}
	
	/**
	 * 根据流id、名字以及开始和结束时间获取MP4文件下载地址
	 * @param id
	 * @param videoName
	 * @param start
	 * @param end
	 * @return
	 */
	public String getDownloadMp4RrlByTime(int id,String videoName,String start,String end){
		System.out.println(start+"  "+end);
		Pili mPili = new Pili(StreamDao.ACCESS_KEY,StreamDao.SECRET_KEY, "ruiyantong");
		StStream stream = this.get(id);
		stream.setmAuth(mPili.getmAuth());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = format.parse(start);
			endDate = format.parse(end);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String saveAsFormat    = "mp4";                            // required
		String saveAsName      = videoName + "." + saveAsFormat; // required
		long saveAsStart       = startDate.getTime()/1000;     		   //1440315411;// required, in second, unix timestamp
		long saveAsEnd         = endDate.getTime()/1000;                // 1440315435;required, in second, unix timestamp
		String saveAsNotifyUrl = null;  						   // optional
		SaveAsResponse response = null;
		//System.out.println(saveAsName+" "+saveAsStart+" "+saveAsEnd);
		try {
			response = stream.saveAs(saveAsName, saveAsFormat, saveAsStart, saveAsEnd, saveAsNotifyUrl);
			 /*
		     {
		         "url":"http://ey636h.vod1.z1.pili.qiniucdn.com/recordings/z1.test-hub.55d81a72e3ba5723280000ec/videoName.m3u8",
		         "targetUrl":"http://ey636h.vod1.z1.pili.qiniucdn.com/recordings/z1.test-hub.55d81a72e3ba5723280000ec/videoName.mp4",
		         "persistentId":"z1.55d81c6c7823de5a49ad77b3"
		     }
		    */
			//curl -D GET http://api.qiniu.com/status/get/prefop?id={persistentId}  下载地址
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return response.toString();
	}
	
	/**
	 * 根据streamId截取当前时间stream的图片
	 * @param streamId
	 * @return
	 */
	public String snapShotStreamByStreamId(String streamId){
		Pili mPili = new Pili(StreamDao.ACCESS_KEY,StreamDao.SECRET_KEY, "ruiyantong");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SnapshotResponse object = null;
		String format    = "jpg";                      // required
		long time        = date.getTime()/1000;                 // optional, in second, unix timestamp
		String name      = time + "." + format; // required
		String notifyUrl = null;                       // optional
		String pipeline = "ruyton";						   // optional
		try {
			object = API.snapshot(mPili.getmAuth(), streamId, name, format, time, notifyUrl,pipeline);
		} catch (PiliException e) {
			return "";
		}
		return object.getTargetUrl();
	}
	
}