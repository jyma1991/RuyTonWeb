package com.ryt.web.util;

import org.durcframework.core.DurcException;
import com.ryt.web.common.Md5Encrypt;

public class PasswordUtil {

	/**
	 * 生成保存在数据库中的密码
	 * @param password 明文密码
	 * @return
	 */
	public static String createStorePswd(String password){
		password = Md5Encrypt.encrypt(password);
		return createHash(password);
	}
	
	public static String createHash(String password){
		try {
			return PasswordHash.createHash(password);
		} catch (Exception e) {
			throw new DurcException("密码加密出错");
		} 
	}
	
	public static boolean validatePassword(String password, String correctHash){
		try {
			return PasswordHash.validatePassword(password, correctHash);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public static void main(String[] args) {
		System.out.println(createHash("202cb962ac59075b964b07152d234b70"));
	}
	
}
