package com.ryt.app.commom;

/**
 * Openfire 常量配置
 * 
 * @author gushenjie
 *
 */
public class OpenfireConstants {
	// 服务器地址
	public static final String SERVER_IP = "139.196.169.23";
	public static final String ROOM_JID = "conference." + SERVER_IP;
	public static final String BASE_GET_ONLINE_USERS = "http://" + SERVER_IP + ":9090/plugins/presence/status";
}
