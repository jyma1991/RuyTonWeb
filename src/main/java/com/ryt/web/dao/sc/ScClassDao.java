package com.ryt.web.dao.sc;

import java.util.List;

import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.sc.ScClass;

public interface ScClassDao extends BaseDao<ScClass> {
	public List<ScClass> getCommonStreams(ScClass scClass);
	public List<ScClass> getPublicStreams(Integer schoolId);
	public Integer getClassContactCount(int classId);
	public List<ScClass> getAllDayVedio(String str);
	public List<ScClass> getBetweenStartAndStopVedio(String videoTime);
	public List<ScClass> getSurpassStopVedio(String videoTime);
}