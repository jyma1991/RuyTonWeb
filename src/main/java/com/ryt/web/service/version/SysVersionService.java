package com.ryt.web.service.version;

import java.util.List;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.version.SysVersionDao;
import com.ryt.web.entity.version.SysVersion;
import org.springframework.stereotype.Service;

@Service
public class SysVersionService extends CrudService<SysVersion, SysVersionDao> {

	public List<SysVersion> getVersion() {
		return getDao().getVersion();
	}

}