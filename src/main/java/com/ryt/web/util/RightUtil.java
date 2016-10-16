package com.ryt.web.util;

import java.util.List;

import com.ryt.web.common.RMSContext;
import com.ryt.web.entity.auth.AuthSystemFunction;
import org.springframework.util.StringUtils;

/**
 * 权限检查工具类
 *
 */
public class RightUtil {

    /**
     * 根据资源ID和操作代码检查是否具有权限
     * 
     * @param srId SysRec表主键
     * @param operateCode SysOperate表主键
     * @return 返回true权限存在
     */
    public static boolean check(String systemResourceId, String operateCode) {
    	//systemResourceId ="44";
        if (StringUtils.isEmpty(systemResourceId) || StringUtils.isEmpty(operateCode)) {
            return false;
        }

        int srid = Integer.valueOf(systemResourceId);

        List<AuthSystemFunction> userSysFunctions = RMSContext.getInstance().getCurrentUseAuthSystemFunction();
        if(userSysFunctions==null || userSysFunctions.size()<=0){
        	return false;
        }
        for (AuthSystemFunction sysFun : userSysFunctions) {
            if (sysFun.getSystemResourceId() == srid && operateCode.equals(sysFun.getOperateCode())) {
                return true;
            }
        }

        return false;
    }
}
