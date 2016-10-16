package com.ryt.web.service.auth;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.auth.AuthDataTypeResourceDao;
import com.ryt.web.entity.auth.AuthDataTypeResource;
import org.springframework.stereotype.Service;

@Service
public class AuthDataTypeResourceService extends CrudService<AuthDataTypeResource, AuthDataTypeResourceDao> {

}