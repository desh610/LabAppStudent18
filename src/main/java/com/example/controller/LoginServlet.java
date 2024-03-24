/*
 * package com.example.controller;
 * 
 * import com.example.model.LabReceptionist; import com.example.util.DBConnect;
 * 
 * import java.io.IOException; import java.sql.Connection; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * @WebServlet("/LoginServlet") public class LoginServlet extends HttpServlet {
 * private static final long serialVersionUID = 1L;
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * String userType = request.getParameter("userType"); String username =
 * request.getParameter("username"); String password =
 * request.getParameter("password");
 * 
 * try (Connection conn = DBConnect.getConnection()) { String query = ""; //
 * Initialize query string
 * 
 * if ("lab_receptionist".equals(userType)) { query =
 * "SELECT * FROM lab_receptionists WHERE username = ? AND password = ?"; } else
 * if ("doctor".equals(userType)) { query =
 * "SELECT * FROM doctors WHERE username = ? AND password = ?"; } else if
 * ("lab_technician".equals(userType)) { query =
 * "SELECT * FROM lab_technicians WHERE username = ? AND password = ?"; } else {
 * // Handle invalid userType response.sendRedirect("login.jsp?error=1");
 * return; }
 * 
 * try (PreparedStatement stmt = conn.prepareStatement(query)) {
 * stmt.setString(1, username); stmt.setString(2, password); ResultSet rs =
 * stmt.executeQuery(); if (rs.next()) { // Successful login // Retrieve
 * customer data int idDB = rs.getInt("id"); String firstNameDB =
 * rs.getString("first_name"); String lastNameDB = rs.getString("last_name");
 * String contactNumberDB = rs.getString("contact_number"); String emailDB =
 * rs.getString("email"); String addressDB = rs.getString("address"); String
 * genderDB = rs.getString("gender"); String nicDB = rs.getString("nic"); String
 * registeredDateTimeDB = rs.getString("registered_date_time"); String
 * usernameDB = rs.getString("username"); String passwordDB =
 * rs.getString("password");
 * 
 * // Create LabReceptionist object LabReceptionist labReceptionist = new
 * LabReceptionist(idDB, firstNameDB, lastNameDB, contactNumberDB, emailDB,
 * addressDB, genderDB, nicDB, registeredDateTimeDB, usernameDB, passwordDB);
 * 
 * // Set LabReceptionist as request attribute
 * request.setAttribute("labReceptionist", labReceptionist);
 * 
 * // Store user authentication state in session HttpSession session =
 * request.getSession(); session.setAttribute("loggedInUser", username); //
 * Store username
 * 
 * // Print and check the value of loggedInUser
 * System.out.println("Logged in user: " +
 * session.getAttribute("loggedInUser"));
 * 
 * // Forward to lab_receptionist_dashboard.jsp
 * request.getRequestDispatcher("lab_receptionist_dashboard.jsp").forward(
 * request, response); } else { // Invalid login
 * response.sendRedirect("login.jsp?error=1"); } } } catch (SQLException e) {
 * e.printStackTrace(); } }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // If a GET request is made
 * to /login, redirect to another page
 * response.sendRedirect("lab_receptionist_dashboard.jsp"); } }
 * 
 * 
 * 
 * 
 * 
 * //===================================================
 * 
 * //package com.example.controller; // //import com.example.model.Customer;
 * //import com.example.util.DBConnect; // //import java.io.IOException;
 * //import java.sql.Connection; //import java.sql.PreparedStatement; //import
 * java.sql.ResultSet; //import java.sql.SQLException; // //import
 * javax.servlet.RequestDispatcher; //import javax.servlet.ServletException;
 * //import javax.servlet.annotation.WebServlet; //import
 * javax.servlet.http.HttpServlet; //import
 * javax.servlet.http.HttpServletRequest; //import
 * javax.servlet.http.HttpServletResponse; // //@WebServlet("/LoginServlet")
 * //public class LoginServlet extends HttpServlet { // private static final
 * long serialVersionUID = 1L; // // protected void doPost(HttpServletRequest
 * request, HttpServletResponse response) // throws ServletException,
 * IOException { // String username = request.getParameter("username"); //
 * String password = request.getParameter("password"); // // try (Connection
 * conn = DBConnect.getConnection(); // PreparedStatement stmt = conn.
 * prepareStatement("SELECT * FROM customer WHERE username = ? AND password = ?"
 * )) { // stmt.setString(1, username); // stmt.setString(2, password); //
 * ResultSet rs = stmt.executeQuery(); // if (rs.next()) { // // Successful
 * login // // Retrieve customer data // int id = rs.getInt("id"); // String
 * name = rs.getString("name"); // String email = rs.getString("email"); //
 * String phone = rs.getString("phone"); // String user =
 * rs.getString("username"); // String pass = rs.getString("password"); // // //
 * Create Customer object // Customer customer = new Customer(id, name, email,
 * phone, user, pass); // // // Set customer as request attribute //
 * request.setAttribute("customer", customer); //// // Redirect to view.jsp ////
 * response.sendRedirect("view.jsp"); // // // Forward to view.jsp //
 * request.getRequestDispatcher("view.jsp").forward(request, response); // }
 * else { // // Invalid login // response.sendRedirect("login.jsp?error=1"); //
 * } // } catch (SQLException e) { // e.printStackTrace(); // } // } // //
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) // throws ServletException, IOException { // // If a GET request is
 * made to /login, redirect to another page //// TODO HANDEL //
 * response.sendRedirect("login.jsp"); // } //}
 * 
 */