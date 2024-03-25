<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Appointment" %>
<%@ page import="com.example.model.Patient" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Dashboard</title>
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
    </style>
</head>
<body>

<div class="container">

<%-- Check if user is logged in --%>
<% 
    String loggedInUserType = (String) session.getAttribute("loggedInUserType");
    Patient loggedInUser = (Patient) session.getAttribute("loggedInUser");
    if ("patient".equals(loggedInUserType)) { %>
       
<div class="row justify-content-center">
    <div class="col-md-12">
        <div class="card custom-card-lab-receptionist-dashboard mb-4">
            <div class="card-body">
                <h3>Welcome to the Patient Dashboard</h3>
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
      
<!-- Appointments section content goes here -->
<div class="row">
    <div class="col-md-4">
        <div class="d-flex justify-content-start">
            <h3>Appointments Section</h3>
        </div>
    </div>
    <div class="col-md-4">
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="searchInput" placeholder="Search by ID" aria-label="Search" aria-describedby="basic-addon2">
        </div>
    </div>
</div>
       
<div class="table-responsive table-scrollable">
    <table class="table table-striped">
    <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Appointment Type</th>
            <th>Checkup Date</th>
            <th>Checkup Time</th>
            <th>Doctor ID</th>
            <th>Technician ID</th>
            <th>Payment</th>
            <th>Status</th>
            <th>Action</th> <!-- New column for action -->
        </tr>
    </thead>
    <tbody id="searchResults">
       <% for (Appointment appointment : (List<Appointment>) request.getAttribute("appointmentsById")) { %>
    <tr id="<%= appointment.getId() %>">
        <td><%= appointment.getId() %></td>
        <td><%= appointment.getAppointmentType() %></td>
        <td><%= appointment.getCheckupDate() %></td>
        <td><%= appointment.getCheckupTime() %></td>
        <td><%= appointment.getDoctorId() %></td>
        <td><%= appointment.getTechnicianId() %></td>
        <td><%= appointment.getPayment() %></td>
        <td><%= appointment.getStatus() %></td>
        <td>
            <button class="btn btn-primary btn-sm more-button" 
            data-status="<%= appointment.getStatus() %>"  
            data-type="<%= appointment.getAppointmentType() %>" 
            data-results="<%= appointment.getTestResults() %>"
            data-patient-username="<%= appointment.getPatientUsername() %>"
            data-issued-date-time="<%= appointment.getissuedDateTime() %>">More</button>
        </td>
    </tr>
<% } %>
    </tbody>
</table>
</div>

<div class="modal fade" id="moreModal" tabindex="-1" role="dialog" aria-labelledby="moreModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="moreModalLabel">More Information</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="modalBodyContent">
                <!-- Content of the modal goes here -->
            </div>
            <div class="modal-footer" id="modalFooterContent">
			    <!-- Download button will be added here -->
			    
			</div>
        </div>
    </div>
</div>

<% } else {
    // User is not logged in, redirect to login page
    response.sendRedirect("login");
} %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>

<script>
    document.getElementById('searchInput').addEventListener('input', function() {
        var searchId = this.value.trim().toLowerCase();
        var rows = document.querySelectorAll('#searchResults tr');

        rows.forEach(function(row) {
            var idCell = row.cells[0];
            var rowId = row.id.toLowerCase();
            if (rowId.includes(searchId)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
    
    function convertToObjectArray(dataString) {
        // Remove the leading and trailing brackets from the string
        var trimmedString = dataString.substring(1, dataString.length - 1);

        // Split the string into individual entries by comma
        var entries = trimmedString.split("}, {");

        // Initialize an empty array to store the JSON objects
        var jsonArray = [];

        // Regular expression to match the hints
        var hintRegex = /hint=([^,]+)/;

        // Iterate over each entry
        entries.forEach(function(entry) {
            // Remove any remaining curly brackets
            entry = entry.replace("{", "").replace("}", "");

            // Split the entry into key-value pairs
            var keyValuePairs = entry.split(", ");

            // Initialize an object to store the key-value pairs
            var jsonObject = {};

            // Iterate over each key-value pair
            keyValuePairs.forEach(function(pair) {
                // Split the pair into key and value
                var parts = pair.split("=");

                // Extract key and value
                var key = parts[0].trim();
                var value = parts[1].trim();

                // If the key is 'hint', extract the hint using regular expression
                if (key === "hint") {
                    var hintMatch = pair.match(hintRegex);
                    if (hintMatch) {
                        jsonObject[key] = hintMatch[1].trim();
                    }
                } else {
                    jsonObject[key] = value;
                }
            });

            // Push the constructed JSON object to the array
            jsonArray.push(jsonObject);
        });

        return jsonArray;
    }



        
    document.addEventListener("DOMContentLoaded", function() {
        var moreButtons = document.querySelectorAll('.more-button');

        moreButtons.forEach(function(button) {
            button.addEventListener('click', function() {
                var status = button.getAttribute('data-status');
                var type = button.getAttribute('data-type');
                var results = button.getAttribute('data-results');
                var patient_username = button.getAttribute('data-patient-username');
                var issued_date_time = button.getAttribute('data-issued-date-time');

                var modalBody = document.getElementById('modalBodyContent');
                modalBody.innerHTML = "<h1>" + type + "</h1>";

                if (status === "Completed") {
                    var jsonObjectArray = convertToObjectArray(results);
                    var tableHTML = "<h2>Test Results</h2><table class='table' border='1'><thead><tr><th>Ingradient Name</th><th>Value</th><th>Hint</th></tr></thead><tbody>";

                    jsonObjectArray.forEach(function(r) {
                        tableHTML += "<tr><td>" + r.ingradientName + "</td><td>" + r.value.replace(/\}/g, '') + "</td><td>" + r.hint + "</td></tr>";
                    });

                    tableHTML += "</tbody></table>";

                    modalBody.innerHTML += tableHTML;

                    // Add Download button to modal footer
                    var modalFooter = document.querySelector('.modal-footer');
                    modalFooter.innerHTML = "<button type='button' class='btn btn-primary' onclick='downloadResults(\"" + patient_username + "\", \"" + type + "\", \"" + results + "\", \"" + issued_date_time + "\")'>Download</button>";
                } else {
                    modalBody.innerHTML += "<h2>No Test Results Available</h2>";
                    // Remove any existing buttons from modal footer
                    var modalFooter = document.querySelector('.modal-footer');
                    modalFooter.innerHTML = "";
                }

                var modal = new bootstrap.Modal(document.getElementById('moreModal'));
                modal.show();
            });
        });
    });

 function downloadResults(patient_username, type, results, issued_date_time) {
	 
	    console.log("patient username: ", patient_username);
	    console.log("appointment type: ", type);
	    console.log("test results: ", results);
	    console.log("test results: ", issued_date_time);
	    
	    var data = {
	            "patient_username": patient_username,
	            "appointment_type": type,
	            "test_results": results,
	            "issued_date_time": issued_date_time
	        };
	    var jsonData = JSON.stringify(data);
	    console.log("JSON Data:", jsonData);
	    
	 	// Create a hidden form
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', 'action/');
        form.setAttribute('id', 'generateAndDownloadReportForm'); // Add an ID to the form for easy access
        form.style.display = 'none'; // Hide the form
        // Add the action parameter
        var actionInput = document.createElement('input');
        actionInput.setAttribute('type', 'hidden');
        actionInput.setAttribute('name', 'action');
        actionInput.setAttribute('value', 'generateAndDownloadReport');
        form.appendChild(actionInput);
        
        var jsonDataInput = document.createElement('input');
        jsonDataInput.setAttribute('type', 'hidden');
        jsonDataInput.setAttribute('name', 'jsonData');
        jsonDataInput.setAttribute('value', jsonData);
        form.appendChild(jsonDataInput);
        
        document.body.appendChild(form); // Append the form to the document body
        form.submit(); // Submit the form
        var modal = document.getElementById('moreModal');
        var modalInstance = bootstrap.Modal.getInstance(modal);
        modalInstance.hide();
	
    	
    }

</script>
</body>
</html>
