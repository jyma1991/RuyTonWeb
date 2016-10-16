package com.ryt.web.service.sc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.sc.ScAuthUserStreamDao;
import com.ryt.web.entity.sc.ScAuthUserStream;
import com.ryt.web.entity.sc.ScAuthUserStreamSch;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.service.stream.StStreamService;
import com.ryt.web.service.user.UserService;

@Service
public class ScAuthUserStreamService extends CrudService<ScAuthUserStream, ScAuthUserStreamDao> {
	@Autowired
	ScClassTeacherService scClassTeacherService;
	@Autowired
	UserService userService;
	@Autowired
	ScClassService scClassService;
	@Autowired
	StStreamService stStreamService;

	public Collection<ScClass> getUserStreams(ScAuthUserStreamSch entity) throws ParseException {
		HashMap<Integer, ScClass> mapCommonStreams = null;
		// 园长
		if (4 == userService.get(entity.getUIdSch()).getRoleProperty()) {
			mapCommonStreams = scClassService.getSchoolStreams(entity.getUSchoolIdSch());
		} else if (2 == userService.get(entity.getUIdSch()).getRoleProperty()) {
			mapCommonStreams = scClassService.getTeacherStreams(entity.getUIdSch(), entity.getUSchoolIdSch());
		} else {
			ScClass scClass = new ScClass();
			scClass.setSchoolId(entity.getUSchoolIdSch());
			scClass.setId(entity.getUClassIdSch());
			mapCommonStreams = scClassService.getCommonStreams(scClass);// 本
																		// 班级+公共
		}

		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		query.add(new ValueExpression("uId", entity.getUIdSch()));
		List<ScAuthUserStream> list = this.find(query);
		for (ScAuthUserStream scAuthUserStream : list) {
			// 包含其他班级的则添加
			if (0 == scAuthUserStream.getAuthType()
					&& !mapCommonStreams.containsKey(scAuthUserStream.getStreamClassId())) {
				mapCommonStreams.put(scAuthUserStream.getStreamClassId(),
						scClassService.get(scAuthUserStream.getStreamClassId()));
			}
			// 排除默认的则删除
			if (1 == scAuthUserStream.getAuthType()
					&& mapCommonStreams.containsKey(scAuthUserStream.getStreamClassId())) {
				mapCommonStreams.remove(scAuthUserStream.getStreamClassId());
			}
		}

		Iterator iter = mapCommonStreams.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			ScClass sc = (ScClass) entry.getValue();
			// 如果场地中保存的视频截取图片地址为空
			if (sc.getSnapShotStreamUrl() == null) {
				StStream stream = stStreamService.get(sc.getStreamId());
				String snapShotStreamUrl = null;
				if (stream != null) {
					sc.setStreamUrl(stream.getPlayRtmpUrl());
					try {
						snapShotStreamUrl = stStreamService.snapShotStreamByStreamId(stream.getStreamId());
						// 更新班级视频截取图片地址
						sc.setSnapShotStreamUrl(snapShotStreamUrl);
						sc.setEditedTime(null);
						scClassService.update(sc);
						sc.setStreamPic(snapShotStreamUrl);
					} catch (Exception e) {
						continue;
					}
				}
			} else {
				// 获取上次截图的时间
				String editTime = sc.getEditedTime();
				// 获取当前时间
				Calendar calendar = Calendar.getInstance();
				Date date = calendar.getTime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date editDate = format.parse(editTime);
				long nowMillionSecond = date.getTime();
				long editMillionSecond = editDate.getTime();
				// 访问间隔时间为10分钟重新去截图
				long time = 600000;
				// 如果超时则重新截取
				if (nowMillionSecond - editMillionSecond >= time) {
					StStream stream = stStreamService.get(sc.getStreamId());
					String snapShotStreamUrl = null;
					if (stream != null) {
						sc.setStreamUrl(stream.getPlayRtmpUrl());
						try {
							snapShotStreamUrl = stStreamService.snapShotStreamByStreamId(stream.getStreamId());
							// 更新班级视频截取图片地址
							sc.setSnapShotStreamUrl(snapShotStreamUrl);
							sc.setEditedTime(null);
							scClassService.update(sc);
							sc.setStreamPic(snapShotStreamUrl);
						} catch (Exception e) {
							continue;
						}
					}
				} else {
					StStream stream = stStreamService.get(sc.getStreamId());
					if (stream != null) {
						sc.setStreamUrl(stream.getPlayRtmpUrl());
					}
					sc.setStreamPic(sc.getSnapShotStreamUrl());
				}

			}

		}
		return mapCommonStreams.values();
	}

	// 查询某个学校老师对于某个场地是否设置流的包含或者排除
	public List<ScAuthUserStream> listAuthTeacherStream(ScAuthUserStream scAuthUserStream) {
		return this.getDao().listAuthTeacherStream(scAuthUserStream);
	}

	/**
	 * 根据用户id以及用户classId以及streamId查询授权用户流对象（唯一）
	 * 
	 * @param uid
	 * @return
	 */
	public ScAuthUserStream getScAuthUserStreamByUid(String uid, int classId, int streamId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("uId", uid));
		query.add(new ValueExpression("uClassId", classId));
		query.add(new ValueExpression("streamId", streamId));
		query.setQueryAll(true);
		List<ScAuthUserStream> scAuthUserStream = this.find(query);
		if (scAuthUserStream == null || scAuthUserStream.size() == 0) {
			return null;
		}
		return scAuthUserStream.get(0);
	}
}