package com.ryt.web.dao.stream;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.stream.StStream;

public interface StStreamDao extends BaseDao<StStream> {
	public List<StStream> getUnDividedStream(@Param("classId") int classId);
	public void cancelStStreamLink(@Param("classId") int classId);
	
	public String getDownloadMp4RrlByTime(String start,String end);

}