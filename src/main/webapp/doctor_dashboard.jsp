<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Dashboard</title>
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
    Doctor loggedInUser = (Doctor) session.getAttribute("loggedInUser");
    if ("doctor".equals(loggedInUserType)) { %>
    
    <!-- Doctor Dashboard content -->
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card custom-card-lab-receptionist-dashboard mb-4">
                <div class="card-body">
                    <h3>Welcome to the Doctor Dashboard</h3>
                    <p class="card-text" style="color: rgba(0, 0, 0, 0.5);">Welcome, <%= loggedInUser.getFirstName() %>!</p>
                    <div class="row">
                        <div class="col-md-6">
                            <!-- Display welcome message -->
                            <p class="lead">Welcome, <%= loggedInUser.getFirstName() %>!</p>
                        </div>
                        <div class="col-md-6 mx-auto">
                            <!-- Logout button -->
                            <form action="logout" method="POST">
                                <button type="submit" class="btn btn-outline-primary btn-sm">Logout</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Search form -->
    <div class="row">
    <div class="col-md-4">
            <h3>Appointments Section</h3>
        </div>
        <div class="col-md-4">
            <div class="input-group mb-3">
                <input type="text" class="form-control" id="searchInput" placeholder="Search by ID" aria-label="Search" aria-describedby="basic-addon2">
            </div>
        </div>
    </div>

    <!-- Display search results -->
    <div class="table-responsive table-scrollable">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Patient Username</th>
                    <th>Appointment Type</th>
                    <th>Description</th>
                    <th>Checkup Date</th>
                    <th>Checkup Time</th>
                    <th>Doctor ID</th>
                    <th>Technician ID</th>
                    <th>Payment</th>
                    <th>Created At</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody id="searchResults">
                <% 
                List<Appointment> appointmentsById = (List<Appointment>) request.getAttribute("appointmentsById");
                if (appointmentsById != null) {
                    for (Appointment appointment : appointmentsById) { %>
                        <tr>
                            <td><%= appointment.getId() %></td>
                            <td><%= appointment.getPatientUsername() %></td>
                            <td><%= appointment.getAppointmentType() %></td>
                            <td><%= appointment.getAppointmentDescription() %></td>
                            <td><%= appointment.getCheckupDate() %></td>
                            <td><%= appointment.getCheckupTime() %></td>
                            <td><%= appointment.getDoctorId() %></td>
                            <td><%= appointment.getTechnicianId() %></td>
                            <td><%= appointment.getPayment() %></td>
                            <td><%= appointment.getCreatedDateTime() %></td>
                            <td><%= appointment.getStatus() %></td>
                        </tr>
                    <% }
                } %>
            </tbody>
        </table>
    </div>

<% } else {
    // User is not logged in, redirect to login page
    response.sendRedirect("login");
} %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // JavaScript for real-time search
    document.getElementById('searchInput').addEventListener('input', function() {
        var searchId = this.value.trim();
        var rows = document.querySelectorAll('#searchResults tr');

        rows.forEach(function(row) {
            var idCell = row.cells[0]; // Assuming the ID is in the first cell
            if (idCell.textContent.includes(searchId)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
</script>
</body>
</html>
