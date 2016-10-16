package com.ryt.web.entity.area;

import java.util.Date;

public class AreaCity {
	private Integer id;
	private String cityName;
	private Integer provinceId;
	private Integer districtId;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return this.cityName;
	}

	public void setProvinceId(Integer provinceId){
		this.provinceId = provinceId;
	}

	public Integer getProvinceId(){
		return this.provinceId;
	}

	public void setDistrictId(Integer districtId){
		this.districtId = districtId;
	}

	public Integer getDistrictId(){
		return this.districtId;
	}

}