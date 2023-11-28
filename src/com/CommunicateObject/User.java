package com.CommunicateObject;

import java.io.Serializable;

public class User extends ObjectMsgDecorator implements Serializable{
	private Integer id;
	private Integer pw;
	//나중에 업데이트 사항 private String nick;
	public User(ObjectMsg obj,Integer id, Integer pw){
		super(obj);
		this.obj = obj;
		this.id = id;
		this.pw = pw;
	}
	public int getId(){return this.id;}
	public int getPw(){return this.pw;}
	public boolean IsPw(User u) {return this.pw.equals(u.getPw());} //패스워드가 맞는지
	public void setId(Integer id){this.id=id;}
	public void setPw(Integer pw){this.pw=pw;}
	@Override
	public boolean equals(Object obj) {
		User temp = (User)obj;
		return temp.getId() == this.id && temp.getPw() == this.pw;
	}

	@Override
	public String toString() {
		return "현재 ID: "+ id + "PW : " + pw +"입니다.";
	}
}
