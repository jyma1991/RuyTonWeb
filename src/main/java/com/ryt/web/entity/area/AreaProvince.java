package com.ryt.web.entity.area;

import java.util.Date;

public class AreaProvince {
	private Integer id;
	private String provinceName;
	private Integer sorteId;
	private String type;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}

	public String getProvinceName(){
		return this.provinceName;
	}

	public void setSorteId(Integer sorteId){
		this.sorteId = sorteId;
	}

	public Integer getSorteId(){
		return this.sorteId;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

}