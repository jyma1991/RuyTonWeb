package com.ryt.app.commom;

public class RuyTonAppContants {
	// 手机系统类别
	public static final String PLATFORM_IOS = "2";
	// 用户类别
	public static final int USER_TYPE_SCHOOL = 4;
	public static final int USER_TYPE_TEACHER = 2;
	public static final int USER_TYPE_PARENT = 1;
	// 通知公告类别
	public static final int NOTIFY_TYPE = 12;
	public static final int NOTIFY_TYPE_CLASS = 154;
	public static final int NOTIFY_TYPE_SCHOOL = 153;

	// 是否删除 1:删除 0:未删除
	public static final byte ARTICLE_DELETE_TRUE = 1;
	public static final byte ARTICLE_DELETE_FALSE = 0;

	public static final String DO_FAIL = "操作失败";
	// 教室类别
	public static final int CLASS_TYPE_CLASSROOM = 69;
	public static final int CLASS_TYPE_PUBLIC= 70;

	public static final String JPUSH_TYPE_SIGN = "1";
	public static final String JPUSH_TYPE_NOTIFY = "2";
	
	//一天毫秒数
	public static final long WIKI_MEMCAHE_TIME = 1 * 1000 * 60 * 60 * 24;
	//1天毫秒数
	public static final long COURSE_MEMCAHE_TIME = 1 * 1000 * 60 * 60 * 24;
	//8个小时
	public static final long COOK_MEMCAHE_TIME = 1000 * 60 * 60 * 8;
	//一小时的毫秒数
	public static final long VIDEO_MEMCAHE_TIME = 1 * 1000 * 60 * 60 ;
	//家长用户身份
	public static final boolean MEMBER_FLAG_TRUE = true;
	public static final boolean MEMBER_FLAG_FALSE = false;
	
	public static final int PRODUCT_PAY_STATUS_PAY_FAIL = 0;
	public static final int PRODUCT_PAY_STATUS_PAY_SUCCESS = 1;
	public static final int PRODUCT_PAY_STATUS_PAY_CANCEL = 2;
	
	//支付宝卖家id
	public static final String SELLER_ID = "2088021159844523";
	public static final String PAY_SIGN = "afum915o5e89tdvwai69b0vxhe7h4dvw";
	public static final int  PRODUCT_ONE_YEAR = 1;
	public static final int  PRODUCT_TWO_YEAR = 2;
	public static final int  PRODUCT_THREE_YEAR = 3;
	
	//论坛
	public static final int  SYSCLASSTYPEID_FORUM = 17;
	
	//聊天
	public static final String CHAT_DIRECTION_FROM = "from";
	public static final String CHAT_DIRECTION_TO = "to";
	public static final String CHAT_TYPE_PRIVATE = "privateChat";
	public static final String CHAT_TYPE_GROUP = "groupChat";
	
	
	//0:女 1:男
	public static final int  USER_SEX_MAN = 1;
	public static final int  USER_SEX_WOMAN = 0;
	public static final String DEFAULT_MAN_HEAD_URL = "img/head/6.png";
	public static final String DEFAULT_WOMAN_HEAD_URL = "img/head/13.png";
	
}
