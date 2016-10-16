package com.ryt.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ryt.web.entity.pili.PiliException;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.stream.StStreamService;

/**
 * 作业调度类
 * @author wyp
 * 
 */
public class JobScheduling {
	
	@Autowired
	public ScClassService scClassService;
	@Autowired
	public StStreamService stStreamService;
	private static int count = 0;
	
	//作业调度调用的方法  
    public void execute(){
        //需要做的事情  
    	count++;
    	//格式化现在的时间
    	Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String nowTimeString = format.format(date);
		//表示服务重新启动
    	if(count == 1){
    		//查询所有全天开放的班级
    		List<ScClass> allDayStart = scClassService.getAllDayVedio(null);
    		if(allDayStart!= null){
    			System.out.println("全天开启班级的数量为："+allDayStart.size());
    		}else{
    			System.out.println("全天开启班级的数量为：0");
    		}
    		//System.out.println(allDayStart.size());
    		for(int i = 0; i<allDayStart.size(); i++){
    			if(allDayStart.get(i).getVideoStart()==null || allDayStart.get(i).getVideoStart().trim().length() == 0){
    				if(allDayStart.get(i).getStreamId() != null && allDayStart.get(i).getStreamId()>0){
	    				StStream stStream = stStreamService.get(allDayStart.get(i).getStreamId());
	    				//diabled为false表示允许推流，为true时表示阻止推流。
						try {
							if(stStream != null){
								//如果该流处于禁止推流状态
								if(stStream.getDisabled().equals(1)){
									stStreamService.getStreamDao().update(stStream.getStreamId(), stStream.getPublishKey(), stStream.getPublishSecurity(), false);
									stStream.setDisabled(0);
									stStreamService.update(stStream);
								}
							}
						} catch (PiliException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
    			}
    		}
    		//查询在该时间段已经开发视频的场地,在第一次启动服务的时候去操作（为了防止程序崩溃导致在程序一开启的时候摄像头没有推流）
    		List<ScClass> classes = scClassService.getBetweenStartAndStopVedio(nowTimeString);
    		if(classes !=null && classes.size()>0){
    			System.out.println("现在正在开启或者说已经开启班级的数量为："+classes.size());
    			for(int i = 0; i<classes.size(); i++){
    				if(classes.get(i).getStreamId() != null && classes.get(i).getStreamId()>0){
	    				StStream stStream = stStreamService.get(classes.get(i).getStreamId());
	    				//diabled为false表示允许推流，为true时表示阻止推流。
						try {
							if(stStream != null){
								//如果该流处于禁止推流状态
								if(stStream.getDisabled().equals(1)){
									stStreamService.getStreamDao().update(stStream.getStreamId(), stStream.getPublishKey(), stStream.getPublishSecurity(), false);
									stStream.setDisabled(0);
									stStreamService.update(stStream);
								}
							}
						} catch (PiliException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}

        		}
    		}
    		
    		//查询所有此时应该关闭的视频流
    		List<ScClass> stopClass = scClassService.getSurpassStopVedio(nowTimeString);
    		if(stopClass !=null && stopClass.size()>0){
    			System.out.println("现在应该处在关闭的视频流班级的数量为："+stopClass.size());
    			for(int i = 0; i<stopClass.size(); i++){
    				if(stopClass.get(i).getStreamId() != null && stopClass.get(i).getStreamId()>0){
	    				StStream stStream = stStreamService.get(stopClass.get(i).getStreamId());
	    				//diabled为false表示允许推流，为true时表示阻止推流。
						try {
							if(stStream != null){
								//如果该流处于允许推流状态
								if(stStream.getDisabled().equals(0)){
									stStreamService.getStreamDao().update(stStream.getStreamId(), stStream.getPublishKey(), stStream.getPublishSecurity(), true);
									stStream.setDisabled(1);
									stStreamService.update(stStream);
								}
							}
						} catch (PiliException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}

        		}
    		}
    	}
		
		//System.out.println(nowTimeString);
		ScClassSch scClassSch = new ScClassSch();
		scClassSch.setVideoStart(nowTimeString);
		//查询当前时间所有需要被设置开启推流的班级
		List<ScClass> videoStartClass = scClassService.getClassList(scClassSch);	
		if(videoStartClass !=null && videoStartClass.size()>0){
			System.out.println("开启班级的数量为："+videoStartClass.size());
			for(int i = 0; i<videoStartClass.size(); i++){
				if(videoStartClass.get(i).getStreamId() != null && videoStartClass.get(i).getStreamId()>0){
					StStream stStream = stStreamService.get(videoStartClass.get(i).getStreamId());
					try {
						//diabled为false表示允许推流，为true时表示阻止推流。
						if(stStream != null){
							stStreamService.getStreamDao().update(stStream.getStreamId(), stStream.getPublishKey(), stStream.getPublishSecurity(), false);
							stStream.setDisabled(0);
							stStreamService.update(stStream);
						}
					} catch (PiliException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//查询当前时间所有需要被设置关闭推流的班级
		scClassSch.setVideoStart(null);
		scClassSch.setVideoStop(nowTimeString);
		List<ScClass> videoStopClass = scClassService.getClassList(scClassSch);
		if(videoStopClass !=null && videoStopClass.size()>0){
			System.out.println("关闭班级的数量为："+videoStopClass.size());
			for(int i = 0; i<videoStopClass.size(); i++){
				if(videoStopClass.get(i).getStreamId() != null && videoStopClass.get(i).getStreamId()>0){
					StStream stStream = stStreamService.get(videoStopClass.get(i).getStreamId());
					try {
						if(stStream != null){
							stStreamService.getStreamDao().update(stStream.getStreamId(), stStream.getPublishKey(), stStream.getPublishSecurity(), true);
							stStream.setDisabled(1);
							stStreamService.update(stStream);
						}
					} catch (PiliException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
    }  

}
