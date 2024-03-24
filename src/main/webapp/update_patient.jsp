<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MyLabTest1</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="styles.css" rel="stylesheet">  
</head>
<body>

<% 
    String loggedInUserType = (String) session.getAttribute("loggedInUserType");
    if ("lab_receptionist".equals(loggedInUserType)) { 
        // Retrieve the Patient object from request attributes
        Patient patient = (Patient) request.getAttribute("patientById");
%>
<div class="container mt-5">
    <h2 class="text-start mb-4">Customize Patient</h2>
    <form action="action/" method="POST" id="updateOrDeleteForm">
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="<%= patient.getFirstName() %>" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="<%= patient.getLastName() %>" required>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="contactNumber" class="form-label">Contact Number</label>
                    <input type="text" class="form-control" id="contactNumber" name="contactNumber" value="<%= patient.getContactNumber() %>" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= patient.getEmail() %>" required>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <textarea class="form-control" id="address" name="address" required><%= patient.getAddress() %></textarea>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="gender" class="form-label">Gender</label>
                    <select class="form-select" id="gender" name="gender" required>
                        <option value="male" <%= "male".equals(patient.getGender()) ? "selected" : "" %>>Male</option>
                        <option value="female" <%= "female".equals(patient.getGender()) ? "selected" : "" %>>Female</option>
                        <option value="other" <%= "other".equals(patient.getGender()) ? "selected" : "" %>>Other</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="nic" class="form-label">NIC</label>
                    <input type="text" class="form-control" id="nic" name="nic" value="<%= patient.getNic() %>" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="dateOfBirth" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="<%= patient.getDateOfBirth() %>" required>
                </div>
            </div>
        </div> 
        <input type="hidden" name="action" id="action" value="updatePatient">
    
    <!-- Hidden input field to pass the action for delete -->
    <input type="hidden" name="deleteAction" id="deleteAction" value="deletePatient">
    
    <!-- Hidden input field to pass the appointment ID for both update and delete -->
    <input type="hidden" name="id" value="<%= patient.getId() %>">
    
    <div class="container mt-4">
        <div class="row justify-content-end">
            <div class="col-md-6 text-end">
               <button type="submit" form="updateOrDeleteForm" class="btn btn-primary me-2">Update</button>
            
            <!-- Delete button with onclick event to confirm deletion -->
            <button type="button" class="btn btn-danger me-2" onclick="confirmDelete()">Delete</button>


            
            <!-- Cancel button -->
            <a href="lab_receptionist_dashboard" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </div>
    </form>
</div>
<% 
    } else {
        // User is not logged in, redirect to login page
        response.sendRedirect("login");
    } 
%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
function confirmDelete() {
	 // Set the action to deleteAppointment and submit the form
   document.getElementById("action").value = "deletePatient";
   document.getElementById("updateOrDeleteForm").submit();
}
</script>
</body>
</html>
