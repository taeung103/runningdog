package com.kh.runningdog.volunteer.model.vo;

import java.sql.Date;

public class Vreply implements java.io.Serializable{
	private static final long serialVersionUID = 8882L;
	
	private int vreply_no;
	private int volno;
	private String nickname;
	private java.sql.Date vreply_date;
	private String vreply_content;
	private int parant_reply;
	private int vreply_level;
	
	public Vreply() {}
	
	public Vreply(int vreply_no, int volno, String nickname, Date vreply_date, String vreply_content, int parant_reply,
			int vreply_level) {
		super();
		this.vreply_no = vreply_no;
		this.volno = volno;
		this.nickname = nickname;
		this.vreply_date = vreply_date;
		this.vreply_content = vreply_content;
		this.parant_reply = parant_reply;
		this.vreply_level = vreply_level;
	}

	public int getVreply_no() {
		return vreply_no;
	}

	public void setVreply_no(int vreply_no) {
		this.vreply_no = vreply_no;
	}

	public int getVolno() {
		return volno;
	}

	public void setVolno(int volno) {
		this.volno = volno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public java.sql.Date getVreply_date() {
		return vreply_date;
	}

	public void setVreply_date(java.sql.Date vreply_date) {
		this.vreply_date = vreply_date;
	}

	public String getVreply_content() {
		return vreply_content;
	}

	public void setVreply_content(String vreply_content) {
		this.vreply_content = vreply_content;
	}

	public int getParant_reply() {
		return parant_reply;
	}

	public void setParant_reply(int parant_reply) {
		this.parant_reply = parant_reply;
	}

	public int getVreply_level() {
		return vreply_level;
	}

	public void setVreply_level(int vreply_level) {
		this.vreply_level = vreply_level;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Vreply [vreply_no=" + vreply_no + ", volno=" + volno + ", nickname=" + nickname + ", vreply_date="
				+ vreply_date + ", vreply_content=" + vreply_content + ", parant_reply=" + parant_reply
				+ ", vreply_level=" + vreply_level + "]";
	}
	
	
	
	

}