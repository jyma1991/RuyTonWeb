package com.ryt.web.controller.sc;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sc.ScAuthUserStream;
import com.ryt.web.entity.sc.ScAuthUserStreamSch;
import com.ryt.web.entity.sc.ScTeacherSch;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScAuthUserStreamService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.stream.StStreamService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScAuthUserStreamController extends
		CrudController<ScAuthUserStream, ScAuthUserStreamService> {

	@Autowired
	UserService userService;
	@Autowired
	ScTeacherService scTeacherService;
	@Autowired
	StStreamService stStreamService;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	
		//新增记录
	@RequestMapping("/addScAuthUserStream.do")
	public @ResponseBody
	MessageResult addScAuthUserStream(ScAuthUserStream entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScAuthUserStream.do")
	public @ResponseBody
	MessageResult delScAuthUserStream(ScAuthUserStream entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScAuthUserStream.do")
	public @ResponseBody
	MessageResult updateScAuthUserStream(ScAuthUserStream entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listScAuthUserStream.do")
	public @ResponseBody
	GridResult listScAuthUserStream(ScAuthUserStreamSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScAuthUserStream.do")
    public @ResponseBody Object listAllScAuthUserStream(ScAuthUserStreamSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScAuthUserStreamById.do")
    public @ResponseBody ScAuthUserStream getScAuthUserStreamById(ScAuthUserStream entity) {
        return this.getService().get(entity.getId());
    }

 	@RequestMapping("/listAuthTeacherMsg.do")
 	public @ResponseBody Object listAuthTeacherMsg(int schoolId,int classId,String userRole,int publicId){
 		int roleProperty = 0;
 		//查询该学校所有的老师
 		ScTeacherSch teacherSch = new ScTeacherSch();
 		teacherSch.setSchoolIdSch(schoolId);
 		List<User> users = null;
 		//roleProperty 1表示家长，2表示老师
 		if(userRole.equals("teacher")){
 			roleProperty = 2;
 			users = scTeacherService.findTeachersBySchoolId(teacherSch);
 		}else{
 			roleProperty = 1;
 			users = userService.getParentsUserByClassId(classId);
 		}
 		//查询该学校该班级的摄像头设置给老师或者家长的信息
 		ScAuthUserStream scAuthUserStream = new ScAuthUserStream();
 		scAuthUserStream.setUSchoolId(schoolId);
 		//这里设置的AuthType仅仅是表示设置流的信息对象的角色是什么，并不是真正的包含或者排除类别
 		scAuthUserStream.setAuthType(roleProperty);
 		//判断是否是设置公共场所
 		if(publicId > 0){
 			scAuthUserStream.setUClassId(publicId);
 		}else{
 			scAuthUserStream.setUClassId(classId);
 		}
 		List<ScAuthUserStream> authUserStreams = this.getService().listAuthTeacherStream(scAuthUserStream);
 		for(int i = 0; i<authUserStreams.size(); i++){
 			//包含
 			if(authUserStreams.get(i).getAuthType().equals(0)){
 				for(int j = 0; j<users.size(); j++){
 					if(users.get(j).getId().equals(authUserStreams.get(i).getUId())){
 						users.get(j).setStreamIncluded("true");
 						break;
 					}
 				}
 			//排除	
 			}else{
 				for(int j = 0; j<users.size(); j++){
 					if(users.get(j).getId().equals(authUserStreams.get(i).getUId())){
 						users.get(j).setStreamIncluded("false");
 						break;
 					}
 				}
 			}
 		}
 		HashMap<String, Object> map = new HashMap<String, Object>();
 		map.put("rows", users);
 		return map;
 	}
 	
 	@RequestMapping("/listAuthTeacherStream.do")
 	public @ResponseBody Object listAuthTeacherStream(ScAuthUserStream scAuthUserStream) {
        return this.getService().listAuthTeacherStream(scAuthUserStream);
    }
 	
 	//批量修改记录
 	@RequestMapping("/batchUpdateScAuthUserStream.do")
 	public @ResponseBody
	MessageResult BatchUpdateScAuthUserStream(String ids,int classId,int streamId,int schoolId){
 		String idArray[] = ids.split(",");
 		for(int i = 0 ; i<idArray.length; i++){
 			//通过用户id以及班级id查询
 			ScAuthUserStream scAuthUserStream = this.getService().getScAuthUserStreamByUid(idArray[i], classId,streamId);
 			//表示已经存在数据库中
 			if(scAuthUserStream != null){
 				scAuthUserStream.setAuthType(1-scAuthUserStream.getAuthType());
 				this.getService().update(scAuthUserStream);
 			//表示第一次设置,并未存在数据库中，则此时原先数据都是包含状态，此时获取到这个数据一定是设置成排除状态
 			}else{
 				scAuthUserStream = new ScAuthUserStream();
 				//获取流对象
 				StStream stream = stStreamService.get(streamId);
 				//设置数据
 				scAuthUserStream.setAuthType(1);
 				scAuthUserStream.setStreamId(streamId);
 				scAuthUserStream.setStreamUrl(stream.getPlayRtmpUrl());
 				scAuthUserStream.setStreamClassId(stream.getClassId());
 				scAuthUserStream.setStreamSchoolId(stream.getSchoolId());
 				scAuthUserStream.setUId(Integer.parseInt(idArray[i]));
 				scAuthUserStream.setUSchoolId(schoolId);
 				scAuthUserStream.setUClassId(classId);
 				this.getService().save(scAuthUserStream);
 			}
 		}
 		return success();
 	}
 	
	//获取详细信息
	@RequestMapping("/getUserStreams.do")
	public @ResponseBody Object getUserStreams(ScAuthUserStreamSch entitySch) throws ParseException {
		return this.getService().getUserStreams(entitySch);
	}
}