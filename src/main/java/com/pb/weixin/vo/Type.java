package com.pb.weixin.vo;

import java.io.Serializable;

public class Type implements Serializable{

	private Integer typeId;     //id
	private String typeName;    //类型名称
    private Integer fatherTypeId;   //父类类型ID
    private String fatherTypeName;   //父类类型名称
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getFatherTypeId() {
		return fatherTypeId;
	}
	public void setFatherTypeId(Integer fatherTypeId) {
		this.fatherTypeId = fatherTypeId;
	}
	public String getFatherTypeName() {
		return fatherTypeName;
	}
	public void setFatherTypeName(String fatherTypeName) {
		this.fatherTypeName = fatherTypeName;
	}
	
	
	
}
