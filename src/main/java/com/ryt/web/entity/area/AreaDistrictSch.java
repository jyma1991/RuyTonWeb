package com.ryt.web.entity.area;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class AreaDistrictSch extends SearchEasyUI{

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
    private String districtNameSch;
    private Integer cityIdSch;

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setDistrictNameSch(String districtNameSch){
        this.districtNameSch = districtNameSch;
    }
    
    @ValueField(column = "districtName")
    public String getDistrictNameSch(){
        return this.districtNameSch;
    }

    public void setCityIdSch(Integer cityIdSch){
        this.cityIdSch = cityIdSch;
    }
    
    @ValueField(column = "cityId")
    public Integer getCityIdSch(){
        return this.cityIdSch;
    }


}