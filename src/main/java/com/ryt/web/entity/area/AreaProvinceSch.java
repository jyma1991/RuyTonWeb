package com.ryt.web.entity.area;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class AreaProvinceSch extends SearchEasyUI{

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
    private String provinceNameSch;
    private Integer sorteIdSch;
    private String typeSch;

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setProvinceNameSch(String provinceNameSch){
        this.provinceNameSch = provinceNameSch;
    }
    
    @ValueField(column = "provinceName")
    public String getProvinceNameSch(){
        return this.provinceNameSch;
    }

    public void setSorteIdSch(Integer sorteIdSch){
        this.sorteIdSch = sorteIdSch;
    }
    
    @ValueField(column = "sorteId")
    public Integer getSorteIdSch(){
        return this.sorteIdSch;
    }

    public void setTypeSch(String typeSch){
        this.typeSch = typeSch;
    }
    
    @ValueField(column = "type")
    public String getTypeSch(){
        return this.typeSch;
    }


}