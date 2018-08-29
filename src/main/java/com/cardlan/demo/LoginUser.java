package com.cardlan.demo;

public class LoginUser {
	private int id;
	private String username;
	private String password;
	private String expected;
	private String url;
	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", username=" + username + ", password=" + password + ", expected=" + expected
				+ ", url=" + url + "]";
	}
	public String getUrl() {
		return url;
	}
}
