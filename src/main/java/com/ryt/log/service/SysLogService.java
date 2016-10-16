package com.ryt.log.service;

import java.util.UUID;

import org.durcframework.core.UserContext;
import org.durcframework.core.service.CrudService;
import com.ryt.log.dao.SysLogDao;
import com.ryt.log.entity.SysLog;
import org.springframework.stereotype.Service;

@Service
public class SysLogService extends CrudService<SysLog, SysLogDao> {
	public void saveLog(SysLog sysLog) {
		sysLog.setUserId(UserContext.getInstance().getUser()==null?0:UserContext.getInstance().getUser().getId());
		sysLog.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		this.getDao().save(sysLog);
	}
}