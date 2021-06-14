package com.ch.ebusiness.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class BUser {
	private Integer id;
	@NotBlank(message="用户名必须输入！")
	private String login_name;
	@NotBlank(message="密码必须输入！")
	@Length(min=6, max=20, message="密码长度在6到20之间！")
	private String bpwd;
	private String rebpwd;
	private String code;
	private Integer gender;
	private String credit_card;
	@Email(message="邮箱格式不正确！")
	private String email;
	@Length(min=11, max=11, message="电话长度应为11位！")
	private String mobile;
	private String user_name;
	private Integer wallet;
	private Integer score;

	public Integer getScore() {
		return score;
	}
	public Integer getWallet() {
		return wallet;
	}
	public String getCredit_card() {
		return credit_card;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getBpwd() {
		return bpwd;
	}
	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRebpwd() {
		return rebpwd;
	}
	public void setRebpwd(String rebpwd) {
		this.rebpwd = rebpwd;
	}
}
