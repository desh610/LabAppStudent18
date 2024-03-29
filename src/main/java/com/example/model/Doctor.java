package com.example.model;

public class Doctor {
	
	private int id;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String address;
	private String gender;
	private String nic;
	private String medicalCategory;
	private String registeredDateTime;
	private String password;
	
	
	public Doctor(int id, String firstName, String lastName, String contactNumber, String email, String address,
			String gender, String nic, String medicalCategory, String registeredDateTime, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.nic = nic;
		this.medicalCategory = medicalCategory;
		this.registeredDateTime = registeredDateTime;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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


	public String getNic() {
		return nic;
	}


	public void setNic(String nic) {
		this.nic = nic;
	}


	public String getMedicalCategory() {
		return medicalCategory;
	}


	public void setMedicalCategory(String medicalCategory) {
		this.medicalCategory = medicalCategory;
	}


	public String getRegisteredDateTime() {
		return registeredDateTime;
	}


	public void setRegisteredDateTime(String registeredDateTime) {
		this.registeredDateTime = registeredDateTime;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
