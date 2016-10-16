package com.ryt.web.controller.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ryt.web.dao.stream.StreamDaoImpl;
import com.ryt.web.entity.pili.PiliException;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.stream.StStream.Status;
import com.ryt.web.entity.stream.StStreamSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.stream.StStreamService;
import com.ryt.web.service.user.UserService;

@Controller
public class StStreamController extends
		CrudController<StStream, StStreamService> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScSchoolService schoolService;
	
	@Autowired
	private ScClassService classService;

	//新增记录
	@RequestMapping("/addStStream.do")
	public @ResponseBody
	MessageResult addStStream(StStream entity,String batch) {
		int batchNumber = Integer.parseInt(batch);
		MessageResult result = null;
		//title的格式为XXX001,后三位为数字，推流键以及设备名称相同
		if(batchNumber > 1){
			String titleBefore = entity.getTitle().substring(0, entity.getTitle().length()-3);
			String publishKeyBefore = entity.getPublishKey().substring(0, entity.getPublishKey().length()-3);
			String deviceNameBefore = entity.getDeviceName().substring(0, entity.getDeviceName().length()-3);
			int index = 0;
			try {
				index = Integer.parseInt(entity.getTitle().substring(entity.getTitle().length()-3));
			} catch (Exception e) {
				return error("主题的格式有问题，请按照格式xxx001，后三位必须为数字的格式来进行修改！！！");
			}
			for(int i = 0; i<batchNumber; i++){
				int number = index + i;
				String after = "";
				if(number / 100 >0){
					after = "";
				}else if(number /10 >0){
					after = "0";
				}else{
					after ="00";
				}
				after += number;
				StStream stream = (StStream)this.getService().getStreamDao().createStream(titleBefore+after,publishKeyBefore+after, entity.getPublishSecurity());
				stream.setDeviceId(entity.getDeviceId());
				stream.setDeviceName(deviceNameBefore+after);
				stream.setDescription(entity.getDescription());
				stream.setOperaterId(entity.getOperaterId());
				stream.setAgentId(entity.getAgentId());
				stream.setSchoolId(entity.getSchoolId());
				stream.setClassId(entity.getClassId());
				result = this.save(stream);
			}
		}else{
			StStream stream = (StStream)this.getService().getStreamDao().createStream(entity.getTitle(), entity.getPublishKey(), entity.getPublishSecurity());
			stream.setDeviceId(entity.getDeviceId());
			stream.setDeviceName(entity.getDeviceName());
			stream.setDescription(entity.getDescription());
			stream.setOperaterId(entity.getOperaterId());
			return this.save(stream);
		}
		return result;
	}

	//假删除记录
	@RequestMapping("/delStStream.do")
	public @ResponseBody
	MessageResult delStStream(StStream entity) {
		 StStream stream = this.get(entity.getId());
		 //设置删除属性
		 stream.setIsDeleted(new Byte("1"));
		 return this.update(stream);
		 /*
		//真删除，但不删除服务器上的流数据,暂时要保留此注释
		return this.delete(entity);
		*/
	}
	
	//真删除所有需要删除的流数据
	@RequestMapping("/realDelAllStStream.do")
	public @ResponseBody String  realDelAllStStream(StStreamSch searchEntitySch){
		searchEntitySch.setIsDeletedSch(new Byte("1"));
		DefaultGridResult defaultGridResult = (DefaultGridResult) this.query(searchEntitySch);
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		for(int i = 0; i<defaultGridResult.getRows().size(); i++){
			StStream stream = (StStream)defaultGridResult.getRows().get(i);
			//删除服务器上的流
			this.getService().getStreamDao().delete(stream);
			//删除本地服务器上的流
			this.delete(stream);
		}
		if(defaultGridResult.getRows().size() == 0){
			map.put("success", "false");
		}
		return JSONObject.toJSONString(map);
	}
	
	//修改记录
	@RequestMapping("/updateStStream.do")
	public @ResponseBody
	MessageResult updateStStream(StStream entity) {
		//获取流对象
		StStream stream = this.getService().get(entity.getId());
		if(stream != null){
			try {
				this.getService().getStreamDao().update(stream.getStreamId(), entity.getPublishKey(), entity.getPublishSecurity(), stream.getDisabled() == 0?false:true);
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//是否要清除设备设置班级
		if(entity.getAgentId() !=null && entity.getAgentId().equals(0) && entity.getClassId() !=null && !entity.getClassId().equals(0)){
			this.getService().cancelStStreamLink(entity.getClassId());
			entity.setAgentId(null);
			entity.setSchoolId(null);
			entity.setClassId(null);
		}
		return this.update(entity);
	}
	//分配班级
	@RequestMapping("/allocateStStream.do")
	public @ResponseBody
	MessageResult updateStStream(StStream entity,ScClass scClass) {
		//获取流对象
		StStream stream = this.getService().get(entity.getId());
		stream.setAgentId(scClass.getAgentId());
		stream.setClassId(scClass.getId());
		stream.setSchoolId(scClass.getSchoolId());
		return this.update(stream);
	}

	//条件查询分页操作
	@RequestMapping("/listStStream.do")
	public @ResponseBody
	GridResult listStStream(StStreamSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
		DefaultGridResult defaultGridResult = (DefaultGridResult) this.query(searchEntitySch);
		for(int i = 0; i<defaultGridResult.getRows().size(); i++){
			int agentId = ((StStream)defaultGridResult.getRows().get(i)).getAgentId() == null ? 0 : ((StStream)defaultGridResult.getRows().get(i)).getAgentId();
			int schoolId = ((StStream)defaultGridResult.getRows().get(i)).getSchoolId() == null ? 0 : ((StStream)defaultGridResult.getRows().get(i)).getSchoolId();
			int classId = ((StStream)defaultGridResult.getRows().get(i)).getClassId() == null ? 0 : ((StStream)defaultGridResult.getRows().get(i)).getClassId();
			if(agentId > 0){
				User agent = userService.get(agentId);
				((StStream)defaultGridResult.getRows().get(i)).setScAgent(agent);
			}
			if(schoolId > 0){
				ScSchool school = schoolService.getSchoolBySchoolId(schoolId);
				((StStream)defaultGridResult.getRows().get(i)).setScSchool(school);
			}
			if(classId > 0){
				ScClass scClass = classService.get(classId);
				((StStream)defaultGridResult.getRows().get(i)).setScClass(scClass);
			}
		}
		
		return defaultGridResult;
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllStStream.do")
    public @ResponseBody Object listAllStStream(StStreamSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getStStreamById.do")
    public @ResponseBody StStream getStStreamById(StStream entity) {
        return this.getService().get(entity.getId());
    }

	//同步服务器上流数据  modify by wyp at 2015-10-28 16:41
	@RequestMapping("/synchronizedStream.do")
	public @ResponseBody
	String synchronizedStStream(StStream entity) {
		StreamDaoImpl streamDao = this.getService().getStreamDao();
		//获取服务器上的所有流数据
		List<StStream> list = streamDao.synchronizedStream();
		
		//条件查询并返回所有记录
		DefaultGridResult object = (DefaultGridResult)this.queryAll(new StStreamSch());
		System.out.println("-----------------总数："+object.getTotal());	
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		//如果存在记录
		if(object.getTotal()>0){
			List<StStream> streams = (List<StStream>)object.getRows();
			for(int j = 0; j<list.size(); j++){
				boolean flag = true;
				//遍历数据库中获取的流list
				for(int i = 0; i<streams.size(); i++){
					//比较服务器上与数据库中的流是否相同
					if(list.get(j).getStreamId().equals(streams.get(i).getStreamId())){
						flag = false;
						break;
					}
				}
				//如果遍历数据库中的所有流，如果没有重复的，则添加到本地的数据库中
				if(flag){
					this.save(list.get(j));
					map.put("success", "true");
				}
			}	
			if(map.get("success").equals("false")){
				map.put("error","已经同步，不存在遗漏流数据！");
			}
		//如果服务器中没有数据	
		}else{
			map.put("success", "true");
			for(int i = 0; i<list.size(); i++){
				this.save(list.get(i));
			}
		}
		return new JSONObject().toJSONString(map);
	}
	
	//更新流的状态
	@RequestMapping("/refreshAllStream.do")
    public @ResponseBody String refreshAllStream(StStreamSch searchEntitySch) throws PiliException {
		/**
		//条件查询并返回所有记录
		DefaultGridResult object = (DefaultGridResult)this.queryAll(searchEntitySch);
		System.out.println("-----------------总数："+object.getTotal());	
		Map<String, String> map = new HashMap<String, String>();
		//如果存在记录
		if(object.getTotal()>0){
			List<StStream> streams = (List<StStream>)object.getRows();
			for(int i = 0; i<streams.size(); i++){
				Status st = this.getService().getStreamDao().getStatusById(streams.get(i).getStreamId());
				String status = st.getStatus();
				StStream serverStream = this.getService().getStreamDao().findServerStreamByStreamId(streams.get(i).getStreamId());
				//刷新是否阻止推流以及每秒字节数(服务器上获取的disabled值0表示阻止推流，1表示允许推流，在这里反转保存到数据库中)
				streams.get(i).setDisabled(serverStream.getDisabled());
				streams.get(i).setBytesPerSecond(st.getBytesPerSecond());
				//如果数据库中的流状态和服务器上的流状态不同，那么需要进行更新操作
				if(!streams.get(i).getStreamStatus().equals(status)){
					//更新StStream对象的streamStatus属性的值
					streams.get(i).setStreamStatus(status);
					System.out.println(streams.get(i).getStreamId()+"---发生了变化---"+streams.get(i).getStreamStatus());
				}else{
					System.out.println("streamId为："+streams.get(i).getStreamId()+"对应的流状态没有发生改变 ");
				}
				this.update(streams.get(i));
			}
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		**/
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		//获取服务器上所有连接中的流
		List<StStream> list = (List<StStream>)this.getService().getStreamDao().listServerUndisabledStreams();
		ExpressionQuery query = new ExpressionQuery();
		query.setQueryAll(true);
		//获取本地所有的流
		List<StStream> allStreams = this.getService().find(query);
		for(int i= 0; i<allStreams.size(); i++){
			map.put("success", "true");
			boolean flag = false;
			boolean updateFlag = false;
			for(int j = 0; j<list.size(); j++){
				//如果是同一个流
				if(allStreams.get(i).getStreamId().equals(list.get(j).getStreamId())){
					flag = true;
					Status st = this.getService().getStreamDao().getStatusById(list.get(j).getStreamId());
					String status = st.getStatus();
					//判断服务器上的流推流键与本地的推流键是否相同
					if(!allStreams.get(i).getPublishKey().equals(list.get(j).getPublishKey())){
						//allStreams.get(i).get
						allStreams.get(i).setPublishKey(list.get(j).getPublishKey());
						allStreams .get(i).setDisabled(list.get(j).getDisabled());
						updateFlag =true;
						System.out.println("publishKey发生变化的流Id:"+allStreams.get(i).getStreamId());
					}
					//如果服务器上的流状态与本地的流状态不同
					if(!status.equals(allStreams.get(i).getStreamStatus())){
						updateFlag =true;
						allStreams.get(i).setStreamStatus(status);
						allStreams.get(i).setBytesPerSecond(st.getBytesPerSecond());
						allStreams .get(i).setDisabled(list.get(j).getDisabled());
						System.out.println("更新为connected的流Id:"+allStreams.get(i).getStreamId());
					}
					if(updateFlag){
						this.update(allStreams.get(i));
					}
					break;
				}
			}
			if(!flag){
				//如果本地的流状态为连接，则改为未连接
				if(allStreams.get(i).getStreamStatus().equals("connected")){
					allStreams.get(i).setStreamStatus("disconnected");
					allStreams.get(i).setBytesPerSecond(new Float("0"));
					this.update(allStreams.get(i));
					System.out.println("更新为disconnected的流Id:"+allStreams.get(i).getStreamId());
				}
			}
		}
		return new JSONObject().toJSONString(map);
    }
	
	//更改流是否往服务器推数据
	@RequestMapping("/changeStreamDisabled.do")
	public @ResponseBody
	MessageResult changeStreamDisabled(StStream entity) {
		//获取流对象
		StStream stream = this.getService().get(entity.getId());
		if(stream != null){
			try {
				this.getService().getStreamDao().update(stream.getStreamId(), entity.getPublishKey(), entity.getPublishSecurity(), entity.getDisabled() == 0?false:true);
			} catch (PiliException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stream.setDisabled(entity.getDisabled());
			return this.update(stream);
		}
		return error("流对象不存在！");
	}
	
	
	//读取所有未被连接的流
	@RequestMapping("/listDisConnectedStream.do")
	public @ResponseBody  	
	GridResult listDisConnectedStream(String classId){
		int id = 0;
		if(classId != null){
			id = Integer.parseInt(classId);
		}
		List<StStream> list = this.getService().listUnDividedStream(id);
		DefaultGridResult result = new DefaultGridResult(list);
		return result;
	}
	
	//读取某个学校的流
	@RequestMapping("/listSchoolStream.do")
	public @ResponseBody  	
	GridResult listSchoolStream(String schoolId,String classId){
		int sId = 0;
		int cId = 0;
		if(schoolId != null){
			sId = Integer.parseInt(schoolId);
		}
		if(classId != null){
			cId = Integer.parseInt(classId);
		}
		List<StStream> list = this.getService().listSchoolStream(sId,cId);
		DefaultGridResult result = new DefaultGridResult(list);
		return result;
	}
		
	
	
	//根据流id、名字以及开始和结束时间获取MP4文件下载地址
	@RequestMapping("/getDownloadMp4Rrl.do")
	public @ResponseBody  Object getDownloadMp4RrlByTime(int id,String videoName,String start,String end){
		String msg =  this.getService().getDownloadMp4RrlByTime(id, videoName, start, end);
		JSONObject object = null;
		if(msg == null){
			object =new JSONObject();
			object.put("success", false);
		}else{
			// 把JSON文本parse成JSONObject
			object = new JSONObject().parseObject(msg);
			object.put("success", true);
			String targetUrl = object.getString("targetUrl");
			String persistentId = object.getString("persistentId");
			String downloadUrl = targetUrl+"?id={"+persistentId+"}";
			object.put("downloadUrl", downloadUrl);
			//System.out.println(downloadUrl);
		}
		return object;
	}
	
	/**
	 * 获取服务器上所有连接中的流
	 * @return
	 */
	@RequestMapping("/listServerUndisabledStreams.do")
	public @ResponseBody  Object listServerUndisabledStreams(){
		//获取服务器上所有连接中的流
		List<StStream> list = (List<StStream>)this.getService().getStreamDao().listServerUndisabledStreams();
		ExpressionQuery query = new ExpressionQuery();
		query.setQueryAll(true);
		//获取本地所有的流
		List<StStream> allStreams = this.getService().find(query);
		for(int i= 0; i<allStreams.size(); i++){
			boolean flag = false;
			for(int j = 0; j<list.size(); j++){
				//如果是同一个流
				if(allStreams.get(i).getStreamId().equals(list.get(j).getStreamId())){
					flag = true;
					Status st = this.getService().getStreamDao().getStatusById(list.get(j).getStreamId());
					String status = st.getStatus();
					//如果服务器上的流状态与本地的流状态不同
					if(!status.equals(allStreams.get(i).getStreamStatus())){
						allStreams.get(i).setStreamStatus(status);
						allStreams.get(i).setBytesPerSecond(st.getBytesPerSecond());
						this.update(allStreams.get(i));
						System.out.println("更新为connected的流Id:"+allStreams.get(i).getStreamId());
					}
					break;
				}
			}
			if(!flag){
				//如果本地的流状态为连接，则改为未连接
				if(allStreams.get(i).getStreamStatus().equals("connected")){
					allStreams.get(i).setStreamStatus("disconnected");
					allStreams.get(i).setBytesPerSecond(new Float("0"));
					this.update(allStreams.get(i));
					System.out.println("更新为disconnected的流Id:"+allStreams.get(i).getStreamId());
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据streamId截取当前时间stream的图片
	 * @param streamId
	 * @return
	 */
	@RequestMapping("/snapShotStreamByStreamId.do")
	public @ResponseBody  String snapShotStreamByStreamId(String streamId){
		String object = this.getService().snapShotStreamByStreamId(streamId);
		return object;
	}
	
}