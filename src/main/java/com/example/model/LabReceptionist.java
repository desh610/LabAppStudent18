package com.example.model;

public class LabReceptionist {
	private int id;
	private String first_name;
	private String last_name;
	private String contact_number;
	private String email;
	private String address;
	private String gender;
	private String nic;
	private String registered_date_time;
	private String password;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getNIC() {
		return nic;
	}
	
	public void setNIC(String nic) {
		this.nic = nic;
	}
	
	public String getRegisteredDateTime() {
		return registered_date_time;
	}
	
	public void setRegisteredDateTime(String registered_date_time) {
		this.registered_date_time = registered_date_time;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LabReceptionist(int id, String first_name, String last_name, String contact_number, String email,
			String address, String gender, String nic, String registered_date_time, String password) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_number = contact_number;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.nic = nic;
		this.registered_date_time = registered_date_time;
		this.password = password;
	}

}
