package com.vilicode.bean;
public class User {  
	private Integer uid;
	private String uname;
	private String upwd;
	private String urealname;
	private Integer urole;
	private String umark;
	private String uphone;
	private String uaddress;
	private boolean isadmin=false;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUrealname() {
		return urealname;
	}
	public void setUrealname(String urealname) {
		this.urealname = urealname;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getUrole() {
		return urole;
	}
	public void setUrole(Integer urole) {
		this.urole = urole;
		isadmin=urole==0?true:false;
	}
	public String getUmark() {
		return umark;
	}
	public void setUmark(String umark) {
		this.umark = umark;
	}
	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public boolean isAdmin()
	{
		return urole==0;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", uname='" + uname + '\'' +
				", upwd='" + upwd + '\'' +
				", urealname='" + urealname + '\'' +
				", urole=" + urole +
				", umark='" + umark + '\'' +
				", uphone='" + uphone + '\'' +
				", uaddress='" + uaddress + '\'' +
				'}';
	}
}
