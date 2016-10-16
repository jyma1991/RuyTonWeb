package com.ryt.web.entity.area;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class AreaCitySch extends SearchEasyUI{

	//根据时间进行搜索的部分
	private Date createdStartSch;
	private Date createdEndSch;

	@ValueField(column = "createdTime", equal = ">=")
	public Date getCreatedStartSch() {
		return createdStartSch;
	}

	public void setCreatedStartSch(Date createdStartSch) {
		this.createdStartSch = createdStartSch;
	}

	@ValueField(column = "createdTime", equal = "<")
	public Date getCreatedEndSch() {
		if (createdEndSch != null) {
			return DateUtil.getDateAfterDay(createdEndSch, 1);
		}
		return createdEndSch;
	}

	public void setCreatedEndSch(Date createdEndSch) {
		this.createdEndSch = createdEndSch;
	}


    private Integer idSch;
    private String cityNameSch;
    private Integer provinceIdSch;
    private Integer districtIdSch;

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setCityNameSch(String cityNameSch){
        this.cityNameSch = cityNameSch;
    }
    
    @ValueField(column = "cityName")
    public String getCityNameSch(){
        return this.cityNameSch;
    }

    public void setProvinceIdSch(Integer provinceIdSch){
        this.provinceIdSch = provinceIdSch;
    }
    
    @ValueField(column = "provinceId")
    public Integer getProvinceIdSch(){
        return this.provinceIdSch;
    }

    public void setDistrictIdSch(Integer districtIdSch){
        this.districtIdSch = districtIdSch;
    }
    
    @ValueField(column = "districtId")
    public Integer getDistrictIdSch(){
        return this.districtIdSch;
    }


}