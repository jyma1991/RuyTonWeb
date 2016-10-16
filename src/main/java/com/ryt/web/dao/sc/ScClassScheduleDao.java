package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sc.ScClassSchedule;

public interface ScClassScheduleDao extends BaseDao<ScClassSchedule> {
	public List<ScClassSchedule> findClassScheduleByClassName(@Param("className") String className,@Param("beginDate") String beginDate,@Param("endDate") String endDate,@Param("schoolId") int schoolId);

	public List<ScClassSchedule> getScClassScheduleByWeekDay(Integer classId);
}