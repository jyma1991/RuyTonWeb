package com.ryt.web.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sys.SysClass;


public interface SysClassDao extends BaseDao<SysClass> {

	List<SysClass> getSysClassByTypeId(SysClass sysClass);

	List<SysClass> getChildSysClassesListByParentId(SysClass sysClass);

	List<SysClass> getAllRelativeName(@Param("sysClassTypeId")Integer sysClassTypeId);
}