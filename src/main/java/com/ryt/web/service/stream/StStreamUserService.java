package com.ryt.web.service.stream;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.stream.StStreamUserDao;
import com.ryt.web.entity.stream.StStreamUser;
import org.springframework.stereotype.Service;

@Service
public class StStreamUserService extends CrudService<StStreamUser, StStreamUserDao> {

}