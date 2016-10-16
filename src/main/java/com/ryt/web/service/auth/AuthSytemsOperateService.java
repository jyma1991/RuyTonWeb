package com.ryt.web.service.auth;


import org.durcframework.core.DurcException;
import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.auth.AuthSystemFunctionDao;
import com.ryt.web.dao.auth.AuthSytemsOperateDao;
import com.ryt.web.entity.auth.AuthSystemFunction;
import com.ryt.web.entity.auth.AuthSystemResource;
import com.ryt.web.entity.auth.AuthSytemsOperate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthSytemsOperateService extends CrudService<AuthSytemsOperate, AuthSytemsOperateDao> {
	@Autowired
    private AuthSystemFunctionDao functionDao;
    /**
     * 添加操作权限 1. 添加功能
     * @param res 资源
     * @param operate 操作类型
     */
    public void add(AuthSystemResource res, AuthSytemsOperate operate) {
        this.addFunction(res, operate);
    }
    
    // 添加系统功能,返回保存后的主键值
    private int addFunction(AuthSystemResource res, AuthSytemsOperate operate) {
        AuthSystemFunction function = new AuthSystemFunction();

        function.setOperateCode(operate.getOperateCode());
        function.setSystemResourceId(res.getId());

        function.setFunctionName(operate.getOperateName() + "(" + operate.getOperateCode() + ")");


        AuthSystemFunction storeFun = functionDao.get(function);

        if (storeFun != null) {
            throw new DurcException("添加失败 - [" + function.getFunctionName() + "]记录已存在.");
        }

        functionDao.save(function);

        return function.getId();
    }
	/**
	 * 根据操作码查询操作对象
	 * @param operateCode
	 * @return
	 */
    public AuthSytemsOperate getByOperateCode(String operateCode) {
    	return this.getDao().getByOperateCode(operateCode);
    }
}