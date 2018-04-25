package com.weilai.user.account.model;

import java.io.Serializable;

public class Carbox implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer boxLength;
    private java.util.Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBoxLength() {
		return boxLength;
	}
	public void setBoxLength(Integer boxLength) {
		this.boxLength = boxLength;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Carbox [id=" + id + ", boxLength=" + boxLength + ", createTime=" + createTime + "]";
	}
    
}
