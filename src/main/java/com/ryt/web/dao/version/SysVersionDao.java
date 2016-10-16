package com.ryt.web.dao.version;

import java.util.List;

import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.version.SysVersion;

public interface SysVersionDao extends BaseDao<SysVersion> {
	
	/**
	 * 获取版本信息
	 * @return
	 */
	List<SysVersion> getVersion();
}