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
if ("lab_receptionist".equals(loggedInUserType)) { %>
<div class="container mt-4">
    <h2 class="my-4">Add Doctor</h2>
    <form action="action/" method="POST">
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="contactNumber" class="form-label">Contact Number</label>
                    <input type="tel" class="form-control" id="contactNumber" name="contactNumber" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="gender" class="form-label">Gender</label>
                    <select class="form-select" id="gender" name="gender" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="nic" class="form-label">NIC</label>
                    <input type="text" class="form-control" id="nic" name="nic" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="medicalCategory" class="form-label">Medical Category</label>
                    <select class="form-select" id="medicalCategory" name="medicalCategory" required>
                        <option value="cardiology">Cardiology</option>
                        <option value="dermatology">Dermatology</option>
                        <!-- Add more medical categories here -->
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label for="approvalCode" class="form-label">Top Admin Approval Code</label>
                    <input type="text" class="form-control" id="approvalCode" name="approvalCode" required>
                </div>
            </div>
        </div>
        <input type="hidden" name="action" value="insertDoctor">
        <div class="d-flex justify-content-start mt-2">
            <button type="submit" class="btn btn-primary me-2">Register</button>   
            <a href="lab_receptionist_dashboard" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
<% } else {
    // User is not logged in, redirect to login page
    response.sendRedirect("login");
} %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
