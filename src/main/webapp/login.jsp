<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ABC Laboratory</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <!-- <link href="styles.css" rel="stylesheet"> -->
<style type="text/css">
.login-form {
  max-width: 400px;
  margin: 0 auto;
  margin-top: 100px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
}
.login-form h2 {
  text-align: center;
  margin-bottom: 20px;
}
.form-control {
  margin-bottom: 20px;
}
.btn-login {
  width: 100%;
}
</style>
</head>
<body>

<div class="container">
  <div class="login-form">
    <h2>Login</h2>
    <form action="action/" method="POST">
      <div class="form-group">
        <select class="form-control" name="userType" required>
          <option value="">Select User Type</option>
          <option value="patient">Patient</option>
          <option value="lab_receptionist">Lab Receptionist</option>
          <option value="lab_technician">Lab Technician</option>
          <option value="doctor">Doctor</option>
         <!--  <option value="lab_top_manager">Laboratory Top Manager</option> -->
        </select>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Username" name="username" id="username" required>
      </div>
      <div class="form-group">
        <input type="password" class="form-control" placeholder="Password" name="password" id="password" required>
      </div>
      
      <% if (request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-danger" role="alert">
 <%= request.getAttribute("errorMessage") %>
</div>
   <%--   --%>
<% } %>

      <input type="hidden" name="action" value="login">
      <button type="submit" class="btn btn-primary btn-login" id="loginButton">Login</button>
    </form>
     <div class="mt-2">

        <a href="https://docs.google.com/document/d/1Yb1674azFovsw7czuErR9MVjnedAkoQzIgmqx7jC0iw/edit?usp=sharing" target="_blank">Tap here to get test credentials</a>

      </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
