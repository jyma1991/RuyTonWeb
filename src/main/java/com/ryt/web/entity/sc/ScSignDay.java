package com.ryt.web.entity.sc;

import java.util.HashMap;
import java.util.Map;
/**
 * 用于接收APP调用后台获取签到信息的类
 * signDate 为签到日期
 * signDetail key 为签到日期  value 为签到图片链接
 * @author Jyma
 *
 */
public class ScSignDay {
	private String signDate;
	private Map<String, Object> signDetail = new HashMap<String, Object>();
	
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public Map<String, Object> getSignDetail() {
		return signDetail;
	}
	public void setSignDetail(Map<String, Object> signDetail) {
		this.signDetail = signDetail;
	}
}
