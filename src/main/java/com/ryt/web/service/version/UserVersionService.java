package com.ryt.web.service.version;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.version.UserVersionDao;
import com.ryt.web.entity.version.UserVersion;
import org.springframework.stereotype.Service;

@Service
public class UserVersionService extends CrudService<UserVersion, UserVersionDao> {

}