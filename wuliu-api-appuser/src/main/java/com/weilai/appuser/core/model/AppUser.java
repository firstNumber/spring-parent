package com.weilai.appuser.core.model;

import java.io.Serializable;

public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userId;
	private String phone;
	private String nickName;
	private String token;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", userId=" + userId + ", phone=" + phone + ", nickName=" + nickName + ", token="
				+ token + "]";
	}

}
