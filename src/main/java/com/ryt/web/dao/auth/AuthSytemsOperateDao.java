package com.ryt.web.dao.auth;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.auth.AuthSystemFunction;
import com.ryt.web.entity.auth.AuthSytemsOperate;

public interface AuthSytemsOperateDao extends BaseDao<AuthSytemsOperate> {
	/**
	 * 根据操作码查询操作对象 modify by wyp
	 * @param operateCode
	 * @return
	 */
	AuthSytemsOperate getByOperateCode(@Param("operateCode") String operateCode);
}