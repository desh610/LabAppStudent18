package com.example.model;

import java.util.List;
import java.util.Map;

public class Appointment {
	
	private int id;
	private String patientUsername;
	private String appointmentType;
	private String appointmentDescription;
	private int doctorId;
	private int technicianId;
	private String createdDateTime;
	private String checkupDate;
	private String checkupTime;
	private String status;
	private String doctorFeedback;
	private List<Map<String, String>> testResults; // Changed data type to List<Map<String, String>>
	private String payment;
	private String issuedDateTime;
	
	public Appointment(int id, String patientUsername, String appointmentType, String appointmentDescription,
			int doctorId, int technicianId, String createdDateTime, String checkupDate, String checkupTime, String status,
			String doctorFeedback, List<Map<String, String>> testResults, String payment, String issuedDateTime) { // Changed constructor parameter type
		super();
		this.id = id;
		this.patientUsername = patientUsername;
		this.appointmentType = appointmentType;
		this.appointmentDescription = appointmentDescription;
		this.doctorId = doctorId;
		this.technicianId = technicianId;
		this.createdDateTime = createdDateTime;
		this.checkupDate = checkupDate;
		this.checkupTime = checkupTime;
		this.status = status;
		this.doctorFeedback = doctorFeedback;
		this.testResults = testResults;
		this.payment = payment;
		this.issuedDateTime = issuedDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getAppointmentDescription() {
		return appointmentDescription;
	}

	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(int technicianId) {
		this.technicianId = technicianId;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCheckupDate() {
		return checkupDate;
	}

	public void setCheckupDate(String checkupDate) {
		this.checkupDate = checkupDate;
	}

	public String getCheckupTime() {
		return checkupTime;
	}

	public void setCheckupTime(String checkupTime) {
		this.checkupTime = checkupTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctorFeedback() {
		return doctorFeedback;
	}

	public void setDoctorFeedback(String doctorFeedback) {
		this.doctorFeedback = doctorFeedback;
	}

	public List<Map<String, String>> getTestResults() {
		return testResults;
	}

	public void setTestResults(List<Map<String, String>> testResults) {
		this.testResults = testResults;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getissuedDateTime() {
		return issuedDateTime;
	}

	public void setissuedDateTime(String issuedDateTime) {
		this.issuedDateTime = issuedDateTime;
	}

	
	
	
	
	
}
