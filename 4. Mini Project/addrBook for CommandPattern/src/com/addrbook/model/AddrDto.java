package com.addrbook.model;

public class AddrDto {
	private int id;
	private String name;
	private String email;
	private String comdept;
	private String birth;
	private String tel;
	private String memo;
	
	public AddrDto() {}

	public AddrDto(int id, String name, String email, String comdept,  String birth, String tel, String memo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.comdept = comdept;
		this.birth = birth;
		this.tel = tel;
		this.memo = memo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComdept() {
		return comdept;
	}

	public void setComdept(String comdept) {
		this.comdept = comdept;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}



}
