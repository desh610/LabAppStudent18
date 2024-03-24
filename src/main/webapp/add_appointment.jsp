<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Appointment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .chip-selector {
            display: flex;
            flex-wrap: wrap;
        }

        .chip {
            background-color: #ffffff;
            border: 1px solid #2196F3;
            border-radius: 16px;
            padding: 8px 16px;
            margin: 4px;
            cursor: pointer;
            color: #2196F3;
        }

        .selected {
            background-color: #2196F3;
            color: #ffffff;
        }
    </style>
</head>
<body>

<% 
    String loggedInUserType = (String) session.getAttribute("loggedInUserType");
    if ("lab_receptionist".equals(loggedInUserType)) { %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Add New Appointment</h2>
    <form action="action/" method="POST">
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="patientUsername" class="form-label">Patient Username (Unique ID)</label>
                    <input type="text" class="form-control" id="patientUsername" name="patientUsername" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="appointmentType" class="form-label">Appointment Type</label>
                    <select class="form-select" id="appointmentType" name="appointmentType" required>
                        <option value="">Select Appointment Type</option>
                        <option value="Complete Blood Count (CBC)">Complete Blood Count (CBC)</option>
                        <option value="Lipid Panel">Lipid Panel</option>
                        <option value="Alanine Aminotransferase (ALT)">Alanine Aminotransferase (ALT)</option>
                        <option value="Kidney Function Tests">Kidney Function Tests</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="appointmentDescription" class="form-label">Appointment Description</label>
                    <textarea class="form-control" id="appointmentDescription" name="appointmentDescription" required></textarea>
                </div>
            </div>
             <div class="col-md-6">
                <div class="mb-3">
                    <label for="selectTechnician" class="form-label">Select Technician (Optional)</label>
                    <select class="form-select" id="selectTechnician" name="selectTechnician">
				    <option value="">Select a technician</option>
				    <% 
				        List<Technician> technicians = (List<Technician>) request.getAttribute("technicians");
				        for (Technician technician : technicians) {
				    %>
				        <option value="<%= technician.getId() %>"><%= technician.getFirstName() %> <%= technician.getLastName() %></option>
				    <% } %>
				</select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="selectDoctor" class="form-label">Select Doctor (Optional)</label>
                    <select class="form-select" id="selectDoctor" name="selectDoctor">
				    <option value="">Select a doctor</option>
				    <% 
				        List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
				        for (Doctor doctor : doctors) {
				    %>
				        <option value="<%= doctor.getId() %>"><%= doctor.getFirstName() %> <%= doctor.getLastName() %> (<%= doctor.getMedicalCategory() %>)</option>
				    <% } %>
				</select>
                </div>
            </div>
           
           <div class="col-md-6">
                <div class="mb-3">
                    <label for="checkupDate" class="form-label">Checkup Date</label>
                    <input type="date" class="form-control" id="checkupDate" name="checkupDate" required>
                </div>
            </div>
           
           
           
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="payment" class="form-label">Payment</label>
                    <select class="form-select" id="payment" name="payment" required>
                        <option value="">Payment</option>
                        <option value="paid">Paid</option>
                        <option value="unpaid">Unpaid</option>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="checkupTime" class="form-label">Checkup Time</label>
                    <div class="chip-selector" id="checkupTime">
                        <div class="chip" onclick="selectTime('09:00 AM')">9:00 AM</div>
                        <div class="chip" onclick="selectTime('10:30 AM')">10:30 AM</div>
                        <div class="chip" onclick="selectTime('12:30 PM')">12:30 PM</div>
                        <div class="chip" onclick="selectTime('14:30 PM')">2:30 PM</div>
                        <div class="chip" onclick="selectTime('16:30 PM')">4:30 PM</div>
                    </div>
                    <input type="hidden" id="selectedTime" name="selectedTime">
                </div>
            </div>
        </div>
        <!-- Hidden field to specify the action -->
        <input type="hidden" name="action" value="insertAppointment">
        <div class="d-flex justify-content-start mt-4">
            <button type="submit" class="btn btn-primary me-2">Add Appointment</button>
            <a href="lab_receptionist_dashboard" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<% } else {
    // User is not logged in, redirect to login page
    response.sendRedirect("login");
} %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function selectTime(time) {
        // Remove 'selected' class from all chips
        var chips = document.querySelectorAll('.chip');
        chips.forEach(chip => chip.classList.remove('selected'));
        
        // Add 'selected' class to the clicked chip
        var selectedChip = event.target;
        selectedChip.classList.add('selected');

        // Set the selected time to the hidden input field
        document.getElementById('selectedTime').value = time;
    }
</script>
</body>
</html>
