<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Technician Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS styles */
        .container {
            text-align: center;
            margin-top: 50px;
        }
        .section {
            display: none;
        }
        .selected-card {
            border: 2px solid blue;
        }
        .table-scrollable {
            max-height: 300px;
            overflow-y: auto;
        }
        /* Sticky table header */
        thead th {
            position: sticky;
            top: 0;
            background-color: #ffffff;
        }
        .custom-card-lab-receptionist-dashboard {
            background: linear-gradient(to right,  #DEF4FF, #EFFAFF );
            border: none;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .ingredient-label {
            font-weight: bold;
        }
        /* Custom styles for ingredient fields */
        .ingredient-field {
            display: flex;
            margin-bottom: 10px;
        }
        .ingredient-name {
            flex-basis: 30%;
            text-align: left;
            font-weight: bold;
        }
        .ingredient-input {
            flex-basis: 40%;
            margin-left: 10px;
            width: calc(100% - 20px);
        }
        .ingredient-hint {
            flex-basis: 30%;
            font-size: 0.8rem;
            color: #6c757d;
            margin-left: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<div class="container">

<%-- Check if user is logged in --%>
<% 
    String loggedInUserType = (String) session.getAttribute("loggedInUserType");
    Technician loggedInUser = (Technician) session.getAttribute("loggedInUser");
    if ("lab_technician".equals(loggedInUserType)) { %>
       
       
<div class="row justify-content-center">
    <div class="col-md-12">
        <div class="card custom-card-lab-receptionist-dashboard mb-4">
            <div class="card-body">
                <h3>Welcome to the Technician Dashboard</h3>
                <p class="card-text" style="color: rgba(0, 0, 0, 0.5);">Welcome to the Patient Dashboard! Your gateway to efficient patient management and seamless appointment scheduling.</p>
                <div class="row">
                    <div class="col-md-6">
                        <!-- Display welcome message -->
                        <p class="lead">Welcome, <%= loggedInUser.getFirstName() %>!</p> 
                    </div>
                    <div class="col-md-6 mx-auto">
                        <!-- Logout button -->
                        <form action="action" method="POST">
                            <input type="hidden" name="action" value="logout">
                            <button type="submit" class="btn btn-outline-primary btn-sm">Logout</button>
                        </form>
                        <%-- <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger">Logout</a> --%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--  <h3>Welcome to the Lab Receptionist Dashboard</h3> -->
      
<!-- Appointments section content goes here -->
<div class="row">
    <div class="col-md-4">
        <div class="d-flex justify-content-start">
            <h3>Appointments Section</h3>
        </div>
    </div>
    <div class="col-md-4">
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="searchInputAppointments" placeholder="Search by ID" aria-label="Search" aria-describedby="basic-addon2">
        </div>
    </div>
</div>
       
<div class="table-responsive table-scrollable">
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Patient ID</th>
                <th>Appointment Type</th>
                <th>Checkup Date</th>
                <th>Checkup Time</th>
                <th>Doctor ID</th>
                <th>Payment</th>
                <th>Status</th>
                <th>Action</th> <!-- New column header for action button -->
            </tr>
        </thead>
        <tbody id="searchResultsAppointments">
            <% for (Appointment appointment : (List<Appointment>) request.getAttribute("appointmentsById")) { %>
                <tr id="<%= appointment.getId() %>">
                    <td><%= appointment.getId() %></td>
                    <td><%= appointment.getPatientUsername() %></td>
                    <td><%= appointment.getAppointmentType() %></td>
                    <td><%= appointment.getCheckupDate() %></td>
                    <td><%= appointment.getCheckupTime() %></td>
                    <td><%= appointment.getDoctorId() %></td>
                    <td><%= appointment.getPayment() %></td>
                    <td><%= appointment.getStatus() %></td>
                    <td> <!-- New column for action button -->
                        <button type="button" class="btn btn-primary view-details-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" data-appointment-id="<%= appointment.getId() %>" data-patient-username="<%= appointment.getPatientUsername() %>" data-appointment-type="<%= appointment.getAppointmentType() %>">
                            More
                        </button>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Appointment Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Place details content here -->
                <p>Patient Username: <span id="patientUsername"></span></p>
                <p>Appointment Type: <span id="appointmentType"></span></p>
                <%-- Ingredient fields based on appointment type --%>
                <div id="ingredientFields">
                    <!-- Ingredient fields will be added dynamically here -->
                </div>
                <!-- Update button -->
                <button type="button" class="btn btn-primary" id="updateButton">Update Test Results</button>
            </div>
        </div>
    </div>
</div>

<% } else {
    // User is not logged in, redirect to login page
    response.sendRedirect("login");
    /* response.sendRedirect(request.getContextPath() + "/app/login"); */
} %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>

// JavaScript for real-time search

document.getElementById('searchInputAppointments').addEventListener('input', function() {
    var searchId = this.value.trim();
    var rows = document.querySelectorAll('#searchResultsAppointments tr');

    rows.forEach(function(row) {
        var idCell = row.cells[0]; // Assuming the ID is in the first cell
        if (idCell.textContent.includes(searchId)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});

// JavaScript to update modal content based on selected row
var viewDetailsBtns = document.querySelectorAll('.view-details-btn');

viewDetailsBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        var appointmentId = this.getAttribute('data-appointment-id');
        var patientUsername = this.getAttribute('data-patient-username');
        var appointmentType = this.getAttribute('data-appointment-type');

        document.getElementById('patientUsername').textContent = patientUsername;
        document.getElementById('appointmentType').textContent = appointmentType;

        // Clear previous ingredient fields
        var ingredientFields = document.getElementById('ingredientFields');
        ingredientFields.innerHTML = '';

        // Define the default number of fields and ingredient names
        var numFields = 7;
        var ingredientNames = ['White Blood Cells (WBC)', 'Red Blood Cells (RBC)', 'Hemoglobin (Hgb)', 'Hematocrit (Hct)', 'Platelets', 'Field 6', 'Field 7'];
        var hints = ['Normal: 4.0-11.0 x 10^9/L', 'Normal: 4.5-5.9 x 10^12/L', 'Normal: 13.5-17.5 g/dL', 'Normal: 38.3-48.6%', 'Normal: 150-450 x 10^9/L', 'Hint 6', 'Hint 7'];

        // Adjust the number of fields and ingredient names based on the appointment type
        if (appointmentType === 'Complete Blood Count (CBC)') {
            numFields = 5;
            ingredientNames = ['White Blood Cells (WBC)', 'Red Blood Cells (RBC)', 'Hemoglobin (Hgb)', 'Hematocrit (Hct)', 'Platelets'];
            hints = ['Normal: 4.0-11.0 x 10^9/L', 'Normal: 4.5-5.9 x 10^12/L', 'Normal: 13.5-17.5 g/dL', 'Normal: 38.3-48.6%', 'Normal: 150-450 x 10^9/L'];
        } else if (appointmentType === 'Lipid Panel') {
            numFields = 4;
            ingredientNames = ['Total Cholesterol', 'HDL Cholesterol', 'LDL Cholesterol', 'Triglycerides'];
            hints = ['Desirable: <200 mg/dL', 'Low Risk: >60 mg/dL', 'Near Optimal: 100-129 mg/dL', 'Normal: <150 mg/dL'];
        } else if (appointmentType === 'Liver Function Tests') {
            numFields = 4;
            ingredientNames = ['Alanine Aminotransferase (ALT)', 'Aspartate Aminotransferase (AST)', 'Alkaline Phosphatase (ALP):', 'Bilirubin'];
            hints = ['Normal: 7-56 U/L', 'Normal: 8-48 U/L', 'Normal: 40-129 U/L', 'Normal: 0.1-1.2 mg/dL'];
        } else if (appointmentType === 'Kidney Function Tests') {
            numFields = 3;
            ingredientNames = ['Blood Urea Nitrogen (BUN)', 'Creatinine', 'Estimated Glomerular Filtration Rate (eGFR)'];
            hints = ['Normal: 6-20 mg/dL', 'Normal: 0.7-1.3 mg/dL', 'Normal: >60 mL/min/1.73m^2'];
        } // Add more conditions for other appointment types
       
        // Create ingredient fields based on the adjusted number of fields and ingredient names
        for (var i = 0; i < numFields; i++) {
            var inputGroup = document.createElement('div');
            inputGroup.classList.add('ingredient-field');
            var nameLabel = document.createElement('div');
            nameLabel.classList.add('ingredient-name');
            nameLabel.textContent = ingredientNames[i];
            var input = document.createElement('input');
            input.setAttribute('type', 'text');
            input.classList.add('form-control', 'ingredient-input');
            var hintLabel = document.createElement('div');
            hintLabel.classList.add('ingredient-hint');
            hintLabel.textContent = hints[i];
            inputGroup.appendChild(nameLabel);
            inputGroup.appendChild(input);
            inputGroup.appendChild(hintLabel);
            ingredientFields.appendChild(inputGroup);
        }

        // Set appointment ID as a data attribute for the update button
        var updateButton = document.getElementById('updateButton');
        updateButton.setAttribute('data-appointment-id', appointmentId);
        // Set ingredientNames as a global variable
        window.ingredientNames = ingredientNames;
    });
});

// JavaScript to handle update button click
document.getElementById('updateButton').addEventListener('click', function() {

    var appointmentId = this.getAttribute('data-appointment-id');

    var ingredientValues = [];

    var ingredientInputs = document.querySelectorAll('#ingredientFields input');

    

    // Use window.ingredientNames to access the global variable

    window.ingredientNames.forEach(function(ingredientName, index) {

        var ingredientValue = ingredientInputs[index].value;

        var hint = document.querySelectorAll('.ingredient-hint')[index].textContent;

        ingredientValues.push({

            "ingradientName": ingredientName,

            "value": ingredientValue,

            "hint": hint

        });

    });
    // Construct the data object in the desired format
    var data = {
        "appointmentId": appointmentId,
        "results": ingredientValues
    };

    // Send ingredient values to servlet as JSON
    var jsonData = JSON.stringify(data);
    console.log("JSON Data:", jsonData);
    // Create a hidden form
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', 'action/');
    form.setAttribute('id', 'updateForm'); // Add an ID to the form for easy access
    form.style.display = 'none'; // Hide the form
    // Add the action parameter
    var actionInput = document.createElement('input');
    actionInput.setAttribute('type', 'hidden');
    actionInput.setAttribute('name', 'action');
    actionInput.setAttribute('value', 'updateTestResults');
    form.appendChild(actionInput);
    // Add the jsonData parameter
    var jsonDataInput = document.createElement('input');
    jsonDataInput.setAttribute('type', 'hidden');
    jsonDataInput.setAttribute('name', 'jsonData');
    jsonDataInput.setAttribute('value', jsonData);
    form.appendChild(jsonDataInput);
    // Append the form to the document body
    document.body.appendChild(form);
    // Submit the form
    form.submit();
    var modal = document.getElementById('exampleModal');
    var modalInstance = bootstrap.Modal.getInstance(modal);
    modalInstance.hide();
});

</script>
</body>
</html>
