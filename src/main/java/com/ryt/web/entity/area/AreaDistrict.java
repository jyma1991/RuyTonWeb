package com.ryt.web.entity.area;

import java.util.Date;

public class AreaDistrict {
	private Integer id;
	private String districtName;
	private Integer cityId;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setDistrictName(String districtName){
		this.districtName = districtName;
	}

	public String getDistrictName(){
		return this.districtName;
	}

	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}

	public Integer getCityId(){
		return this.cityId;
	}

}