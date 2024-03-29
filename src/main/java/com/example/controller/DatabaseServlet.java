package com.example.controller;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.example.util.CommonFunctions;
import com.example.util.DBConnect;
import com.example.util.UserType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Statement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insertPatient".equals(action)) {
            insertPatient(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        }
        else if ("logout".equals(action)) {
            logoutUser(request, response);
        }
        else if ("insertAppointment".equals(action)) {
        	insertAppointment(request, response);
        }
        else if ("insertDoctor".equals(action)) {
        	insertDoctor(request, response);
        }
        else if ("insertTechnician".equals(action)) {
        	insertTechnician(request, response);
        }
        else if ("sendEmail".equals(action)) {
//        	sendEmail(request, response);
        }
        else if ("updateTestResults".equals(action)) {
        	updateTestResults(request, response);
        }
        else if ("generateAndDownloadReport".equals(action)) {
        	generateAndDownloadReport(request, response);
        }
        else if ("updateAppointment".equals(action)) {
        	updateAppointment(request, response);
        }
        else if ("deleteAppointment".equals(action)) {
        	deleteAppointment(request, response);
        }
        else if ("updatePatient".equals(action)) {
        	updatePatient(request, response);
        }
        else if ("deletePatient".equals(action)) {
        	deletePatient(request, response);
        }
        else if ("updateDoctor".equals(action)) {
        	updateDoctor(request, response);
        }
        else if ("deleteDoctor".equals(action)) {
        	deleteDoctor(request, response);
        }
        else if ("updateTechnician".equals(action)) {
        	updateTechnician(request, response);
        }
        else if ("deleteTechnician".equals(action)) {
        	deleteTechnician(request, response);
        }
        
        // Add more conditions for other actions as needed
    }

    public void insertPatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNumber = request.getParameter("contactNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String nic = request.getParameter("nic");
        String dateOfBirth = request.getParameter("dateOfBirth");
        
     // Accessing the singleton instance
        CommonFunctions commonFunctions = CommonFunctions.getInstance();

        // Now you can access the methods using the singleton instance
        String uniquePassword = commonFunctions.generateRandomPassword(4);
        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        
//        CommonFunctions commonFunctions = new CommonFunctions();
//        String uniquePassword = commonFunctions.generateRandomPassword(4);
//        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        // Perform database insertion logic here
        try (Connection conn = DBConnect.getConnection();
        	     PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients (first_name, last_name, contact_number, email, address, gender, nic, date_of_birth, password, registered_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
        	    stmt.setString(1, firstName);
        	    stmt.setString(2, lastName);
        	    stmt.setString(3, contactNumber);
        	    stmt.setString(4, email);
        	    stmt.setString(5, address);
        	    stmt.setString(6, gender);
        	    stmt.setString(7, nic);
        	    stmt.setString(8, dateOfBirth);
        	    stmt.setString(9, uniquePassword);
        	    stmt.setString(10, currentDateAndTime);
        	    int ra = stmt.executeUpdate();
        	    if (ra > 0) {
        	        ResultSet generatedKeys = stmt.getGeneratedKeys();
        	        if (generatedKeys.next()) {
        	            // Retrieve the generated ID
        	            int generatedId = generatedKeys.getInt(1);
        	            System.out.println("Inserted record ID: " + generatedId);
        	            // Redirect to a success page after insertion
        	            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
//        	            TODO : uncomment before submit
        	            sendEmail(request, response, firstName, lastName, "Patient", generatedId, uniquePassword, email, "You are welcome to the ABC Laborotary System. With this system you can view your appoitments, view test results and download medical reports.");
        	        } else {
        	            System.out.println("Failed to retrieve inserted record ID");
        	        }
        	    } else {
        	        response.sendRedirect("patients_registration.jsp?error=1");
        	        // Handle insertion failure
        	    }
        	} catch (SQLException e) {
        	    // Handle database error
        	    e.printStackTrace();
        	}

    }
    
    private void insertDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNumber = request.getParameter("contactNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String nic = request.getParameter("nic");
        String medicalCategory = request.getParameter("medicalCategory");
        String approvalCode = request.getParameter("approvalCode");
        
     // Accessing the singleton instance
        CommonFunctions commonFunctions = CommonFunctions.getInstance();

        // Now you can access the methods using the singleton instance
        String uniquePassword = commonFunctions.generateRandomPassword(4);
        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        
//        CommonFunctions commonFunctions = new CommonFunctions();
//        String uniquePassword = commonFunctions.generateRandomPassword(4);
//        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();
        
        if("1111".equals(approvalCode)) {
        	
        	 // Perform database insertion logic here
            try (Connection conn = DBConnect.getConnection();
            	     PreparedStatement stmt = conn.prepareStatement("INSERT INTO doctors (first_name, last_name, contact_number, email, address, gender, nic, medical_category, password, registered_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            	    stmt.setString(1, firstName);
            	    stmt.setString(2, lastName);
            	    stmt.setString(3, contactNumber);
            	    stmt.setString(4, email);
            	    stmt.setString(5, address);
            	    stmt.setString(6, gender);
            	    stmt.setString(7, nic);
            	    stmt.setString(8, medicalCategory);
            	    stmt.setString(9, uniquePassword);
            	    stmt.setString(10, currentDateAndTime);
            	    int ra = stmt.executeUpdate();
            	    if (ra > 0) {
            	        ResultSet generatedKeys = stmt.getGeneratedKeys();
            	        if (generatedKeys.next()) {
            	            // Retrieve the generated ID
            	            int generatedId = generatedKeys.getInt(1);
            	            System.out.println("Inserted record ID: " + generatedId);
            	            // Redirect to a success page after insertion
            	            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
//            	            TODO : uncomment before submit
            	            sendEmail(request, response, firstName, lastName, "Doctor", generatedId, uniquePassword, email, "With your account you can view allocated appointments and add feedback. You are responsible for the assigned patients' directions. Enjoy your dream job with us!");
            	        } else {
            	            System.out.println("Failed to retrieve inserted record ID");
            	        }
            	    } else {
            	        response.sendRedirect("doctors_registration.jsp?error=1");
            	        // Handle insertion failure
            	    }
            	} catch (SQLException e) {
            	    // Handle database error
            	    e.printStackTrace();
            	}
        	
        }else {
        	System.out.println("Approval code is not valid, please contact your top admin.");
        }

       

    }
    
    private void insertTechnician(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNumber = request.getParameter("contactNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String nic = request.getParameter("nic");
        String approvalCode = request.getParameter("approvalCode");
        
     // Accessing the singleton instance
        CommonFunctions commonFunctions = CommonFunctions.getInstance();

        // Now you can access the methods using the singleton instance
        String uniquePassword = commonFunctions.generateRandomPassword(4);
        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        
//        CommonFunctions commonFunctions = new CommonFunctions();
//        String uniquePassword = commonFunctions.generateRandomPassword(4);
//        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();
        
        if("1111".equals(approvalCode)) {
        	
        	 // Perform database insertion logic here
            try (Connection conn = DBConnect.getConnection();
            	     PreparedStatement stmt = conn.prepareStatement("INSERT INTO technicians (first_name, last_name, contact_number, email, address, gender, nic, password, registered_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            	    stmt.setString(1, firstName);
            	    stmt.setString(2, lastName);
            	    stmt.setString(3, contactNumber);
            	    stmt.setString(4, email);
            	    stmt.setString(5, address);
            	    stmt.setString(6, gender);
            	    stmt.setString(7, nic);
            	    stmt.setString(8, uniquePassword);
            	    stmt.setString(9, currentDateAndTime);
            	    int ra = stmt.executeUpdate();
            	    if (ra > 0) {
            	        ResultSet generatedKeys = stmt.getGeneratedKeys();
            	        if (generatedKeys.next()) {
            	            // Retrieve the generated ID
            	            int generatedId = generatedKeys.getInt(1);
            	            System.out.println("Inserted record ID: " + generatedId);
            	            // Redirect to a success page after insertion
            	            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
//            	            TODO : uncomment before submit
            	            sendEmail(request, response, firstName, lastName, "Lab Technician", generatedId, uniquePassword, email, "With your account you can view allocated appointments and you are responsible to provide valid test results. Enjoy your dream job with us!");
            	        } else {
            	            System.out.println("Failed to retrieve inserted record ID");
            	        }
            	    } else {
            	        response.sendRedirect("technicians_registration.jsp?error=1");
            	        // Handle insertion failure
            	    }
            	} catch (SQLException e) {
            	    // Handle database error
            	    e.printStackTrace();
            	}
        	
        }else {
        	System.out.println("Approval code is not valid, please contact your top admin.");
        }

       

    }

 // Assuming you have imported necessary classes
    private void loginUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String userType = request.getParameter("userType");
        int username = Integer.parseInt(request.getParameter("username"));
        String password = request.getParameter("password");

        try (Connection conn = DBConnect.getConnection()) {
            String query = ""; // Initialize query string

            if ("lab_receptionist".equals(userType)) {
                query = "SELECT * FROM lab_receptionists WHERE id = ? AND password = ?";
            } else if ("patient".equals(userType)) {
                query = "SELECT * FROM patients WHERE id = ? AND password = ?";
            } else if ("lab_technician".equals(userType)) {
                query = "SELECT * FROM technicians WHERE id = ? AND password = ?";
            } else if ("doctor".equals(userType)) {
                query = "SELECT * FROM doctors WHERE id = ? AND password = ?";
            } else {
                // Handle invalid userType
                response.sendRedirect("login.jsp?error=1");
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Successful login
                    // Retrieve user data
                    int idDB = rs.getInt("id");
                    String firstNameDB = rs.getString("first_name");
                    String lastNameDB = rs.getString("last_name");
                    String contactNumberDB = rs.getString("contact_number");
                    String emailDB = rs.getString("email");
                    String addressDB = rs.getString("address");
                    String genderDB = rs.getString("gender");
                    String nicDB = rs.getString("nic");
                    String registeredDateTimeDB = rs.getString("registered_date_time");
                    String passwordDB = rs.getString("password");
                    String dateOfBirthDB = "";
                    String medicalCategory = "";
                    
                    // Construct user object based on userType
                    HttpSession session = request.getSession();
                    if ("patient".equals(userType)) {
                        dateOfBirthDB = rs.getString("date_of_birth");
                        Patient patient = new Patient(idDB, firstNameDB, lastNameDB, contactNumberDB, emailDB, addressDB, genderDB, nicDB, dateOfBirthDB, registeredDateTimeDB, passwordDB);
                        session.setAttribute("loggedInUserType", userType); 
                        session.setAttribute("loggedInUser", patient); // Set the loggedInUser attribute
                        response.sendRedirect(request.getContextPath() + "/app/patient_dashboard");
                    } else if ("lab_receptionist".equals(userType)) {
                        // Construct LabReceptionist object if needed
                        LabReceptionist labReceptionist = new LabReceptionist(idDB, firstNameDB, lastNameDB, contactNumberDB, emailDB, addressDB, genderDB, nicDB, registeredDateTimeDB, passwordDB);
                        session.setAttribute("loggedInUserType", userType); 
                        session.setAttribute("loggedInUser", labReceptionist); // Set the loggedInUser attribute
                        response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
                    } else if ("doctor".equals(userType)) {
                    	System.out.println("=====================yes");
                        // Construct LabReceptionist object if needed
                    	medicalCategory = rs.getString("medical_category");
                        Doctor doctor = new Doctor(idDB, firstNameDB, lastNameDB, contactNumberDB, emailDB, addressDB, genderDB, nicDB, medicalCategory, registeredDateTimeDB, passwordDB);
                        session.setAttribute("loggedInUserType", userType); 
                        session.setAttribute("loggedInUser", doctor); // Set the loggedInUser attribute
                        response.sendRedirect(request.getContextPath() + "/app/doctor_dashboard");
                    }
                    else if ("lab_technician".equals(userType)) {
                    	System.out.println("=====================yes");
                        // Construct LabReceptionist object if needed
                        Technician technician = new Technician(idDB, firstNameDB, lastNameDB, contactNumberDB, emailDB, addressDB, genderDB, nicDB, registeredDateTimeDB, passwordDB);
                        session.setAttribute("loggedInUserType", userType); 
                        session.setAttribute("loggedInUser", technician); // Set the loggedInUser attribute
                        response.sendRedirect(request.getContextPath() + "/app/lab_technician_dashboard");
                    }
                } else {
                    // Invalid login
//                    response.sendRedirect("login.jsp?error=1");
                	 // Invalid login
                    request.setAttribute("errorMessage", "Invalid username or password");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/app/login");
                    dispatcher.forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Map<String, String>> convertToObjectArray(String dataString) {
        List<Map<String, String>> jsonArray = new ArrayList<>();
        
        // Remove the leading and trailing brackets from the string
        String trimmedString = dataString.substring(1, dataString.length() - 1);

        // Split the string into individual entries by comma
        String[] entries = trimmedString.split("}, \\{");

        // Regular expression to match the hints
        Pattern hintPattern = Pattern.compile("hint=([^,]+)");

        // Iterate over each entry
        for (String entry : entries) {
            // Remove any remaining curly brackets
            entry = entry.replace("{", "").replace("}", "");

            // Split the entry into key-value pairs
            String[] keyValuePairs = entry.split(", ");

            // Initialize a map to store the key-value pairs
            Map<String, String> jsonObject = new HashMap<>();

            // Iterate over each key-value pair
            for (String pair : keyValuePairs) {
                // Split the pair into key and value
                String[] parts = pair.split("=");

                // Extract key and value
                String key = parts[0].trim();
                String value = parts[1].trim();

                // If the key is 'hint', extract the hint using regular expression
                if (key.equals("hint")) {
                    Matcher hintMatcher = hintPattern.matcher(pair);
                    if (hintMatcher.find()) {
                        jsonObject.put(key, hintMatcher.group(1).trim());
                    }
                } else {
                    jsonObject.put(key, value);
                }
            }

            // Push the constructed JSON object to the list
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    
 // Helper method to create table header cell
    private static PdfPCell createHeaderCell(String text) {
        PdfPCell headerCell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE)));
        headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerCell.setBackgroundColor(new BaseColor(2, 117, 216));
        headerCell.setBorderColor(new BaseColor(2, 117, 216)); // Border color (black)
        headerCell.setPaddingBottom(8); // Adjust line height
        headerCell.setPaddingTop(8); // Adjust line height
        return headerCell;
    }

    // Helper method to create table cell
    private static PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPaddingBottom(8); // Adjust line height
        cell.setPaddingTop(8); // Adjust line height
        return cell;
    }


    protected void generateAndDownloadReport(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	Patient loggedInUser = (Patient) request.getSession().getAttribute("loggedInUser");
        String jsonData = request.getParameter("jsonData");
        System.out.println("Received JSON data: " + jsonData);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        String results = jsonNode.get("test_results").asText();
        String test_type = jsonNode.get("appointment_type").asText();
        String issued_date_time = jsonNode.get("issued_date_time").asText();
        List<Map<String, String>> jsonObjectArrayResults = convertToObjectArray(results);
        System.out.println(jsonObjectArrayResults);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=medical_report.pdf");

        String fileName = "medical_report_"+loggedInUser.getFirstName()+"_"+loggedInUser.getLastName()+".pdf";
        String customerName = loggedInUser.getFirstName() + " " + loggedInUser.getLastName();
        String address = loggedInUser.getAddress();
        String contact = loggedInUser.getContactNumber();
        String email = loggedInUser.getEmail();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Invoice header
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font fontSubheader = FontFactory.getFont(FontFactory.HELVETICA, 14);
            Font fontSmall = FontFactory.getFont(FontFactory.HELVETICA, 10);

            Paragraph header = new Paragraph("Medical Report", fontHeader);
            header.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(header);

            Paragraph subheader = new Paragraph("ABC Laboratory, Sri Lanka", fontSubheader);
            subheader.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(subheader);
            
            // Line separator
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineColor(new BaseColor(0, 0, 0)); // Black color
            lineSeparator.setLineWidth(1); // 1 point width
            lineSeparator.setPercentage(100); // 80% of page width

            document.add(new Chunk(lineSeparator));
            // Add blank line
            document.add(new Paragraph("\n"));
            // Add blank line
            document.add(new Paragraph("\n"));

            Paragraph sellerInfo = new Paragraph("ABC Laboratory\nAddress: 456 Market Street, Kurunegala, Sri Lanka\nContact: +94 77 011 0948\nEmail: abclaboratory0@gmail.com", fontSmall);
            sellerInfo.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(sellerInfo);

            // Add blank line
            document.add(new Paragraph("\n"));

            // Customer details
            Paragraph customerDetails = new Paragraph();
            customerDetails.add(new Chunk("Patient Name: ", fontSmall));
            customerDetails.add(new Chunk(customerName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
            customerDetails.add(new Chunk("Address: ", fontSmall));
            customerDetails.add(new Chunk(address + "\n", fontSmall));
            customerDetails.add(new Chunk("Contact: ", fontSmall));
            customerDetails.add(new Chunk(contact + "\n", fontSmall));
            customerDetails.add(new Chunk("Email: ", fontSmall));
            customerDetails.add(new Chunk(email + "\n", fontSmall));
            document.add(customerDetails);

            // Add blank line
            document.add(new Paragraph("\n"));

            // Description
            Paragraph description = new Paragraph("The patient underwent a comprehensive medical examination including blood tests, urine analysis, and imaging studies. The results indicate normal levels of blood glucose, cholesterol, and blood pressure within the healthy range. Additionally, the urine analysis revealed no abnormalities. The imaging studies, including X-rays and ultrasound, showed no signs of any underlying abnormalities or pathological conditions. Overall, the patient's medical evaluation suggests good health with no immediate concerns.", fontSmall);
            document.add(description);

            // Add blank line
            document.add(new Paragraph("\n"));
            
            // Customer details
            Paragraph testType = new Paragraph();
            
            testType.add(new Chunk("Test Name: ", fontSubheader));
            testType.add(new Chunk(test_type + "\n", fontSubheader));
            document.add(testType);
            
            
         // Invoice table
            PdfPTable table = new PdfPTable(3); // 3 columns
            table.setWidthPercentage(100); // Table width to 100% of the page
            table.setSpacingBefore(10f); // Space before table

            // Table headers
            PdfPCell header1 = createHeaderCell("Feature");
            PdfPCell header2 = createHeaderCell("Value");
            PdfPCell header3 = createHeaderCell("Range");

            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);

            // Iterate over the JSON array and add data to the table
            for (Map<String, String> jsonObject : jsonObjectArrayResults) {
                table.addCell(createCell(jsonObject.get("ingradientName")));
                table.addCell(createCell(jsonObject.get("value")));
                table.addCell(createCell(jsonObject.get("hint")));
            }
            

            // Add the table to the document
            document.add(table);

            // Add blank line
            document.add(new Paragraph("\n"));
         // Add blank line
            document.add(new Paragraph("\n"));
            
         // Description
            Paragraph trustDescription = new Paragraph("Our laboratory, ABC Laboratory, adheres to stringent quality control measures and follows standardized protocols to ensure accurate and reliable test results. We are committed to providing high-quality diagnostic services with utmost professionalism and integrity. Your trust in our services is our highest priority. For any inquiries or assistance, please contact us at abclab@gmail.com or +94770110948.", fontSmall);
            document.add(trustDescription);
            
         // Add blank line
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("\n"));
            

            // Footer
            Paragraph footer = new Paragraph("Results Issued At: " + issued_date_time, fontSmall);
            footer.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(footer);

            document.close();
            System.out.println("Invoice PDF generated and downloaded successfully.");
        } catch (DocumentException | IOException e) {
//        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    protected void logoutUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
       System.out.println("Logging out user...");

       // Invalidate the session (clear user session data)
       request.getSession().invalidate();

       // Redirect to the login page
		/* response.sendRedirect("login.jsp"); */
       response.sendRedirect(request.getContextPath() + "/app/login");
    	
    }
    
    private void insertAppointment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String patientUsername = request.getParameter("patientUsername");
        String appointmentType = request.getParameter("appointmentType");
        String appointmentDescription = request.getParameter("appointmentDescription");
        String checkupDate = request.getParameter("checkupDate");
        String checkupTime = request.getParameter("selectedTime");
        int selectDoctor = Integer.parseInt(request.getParameter("selectDoctor"));
        int selectTechnician = Integer.parseInt(request.getParameter("selectTechnician"));
        String testResults = "[]";
        String payment = request.getParameter("payment");
        
     // Accessing the singleton instance
        CommonFunctions commonFunctions = CommonFunctions.getInstance();

        // Now you can access the methods using the singleton instance
        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        
//        CommonFunctions commonFunctions = new CommonFunctions();
//        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();
        String status = "Pending";
        String cost = "LKR 0.00";
        if ("paid".equals(payment)) {
            status = "Ongoing";
        } else {
            status = "Pending";
        }
        
        if("Complete Blood Count (CBC)".equals(appointmentType)) {
        	cost = "LKR 1500.00";
        }else if("Lipid Panel".equals(appointmentType)) {
        	cost = "LKR 2500.00";
        }else if("Alanine Aminotransferase (ALT)".equals(appointmentType)) {
        	cost = "LKR 3500.00";
        }else if("Kidney Function Tests".equals(appointmentType)) {
        	cost = "LKR 4000.00";
        }else {
        	cost = "LKR 0.00";
        }
        
        

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointments (patient_username, appointment_type, appointment_description, checkup_date, checkup_time, doctor_id, technician_id, payment, created_date_time, status, test_results) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, patientUsername);
            stmt.setString(2, appointmentType);
            stmt.setString(3, appointmentDescription);
            stmt.setString(4, checkupDate);
            stmt.setString(5, checkupTime);
            stmt.setInt(6, selectDoctor);
            stmt.setInt(7, selectTechnician);
            stmt.setString(8, payment);
            stmt.setString(9, currentDateAndTime);
            stmt.setString(10, status);
            stmt.setString(11, testResults);
            int ra = stmt.executeUpdate();
            if (ra > 0) {
                // Redirect to a success page after insertion
                
                ResultSet generatedKeys = stmt.getGeneratedKeys();
    	        if (generatedKeys.next()) {
    	        		int generatedId = generatedKeys.getInt(1);
    	        		int yourNumber =  fetchAppointmentsLengthInThisTime(request, response, checkupDate, checkupTime) + 1;
    	                Patient patient = fetchPatientById(request, response, Integer.parseInt(patientUsername));
    	                String firstName = patient.getFirstName();
    	                String email = patient.getEmail();
    	                
    	                if ("paid".equals(payment)) {
//    	                	TODO : uncomment before submit
    	                	sendEmailForAppointmentsPaid(request, response, firstName, Integer.parseInt(patientUsername), generatedId, yourNumber, email, checkupDate, checkupTime, appointmentType, cost);
    	                    
    	                } else {
//    	                	TODO : uncomment before submit
    	                	sendEmailForAppointmentsUnpaid(request, response, firstName, Integer.parseInt(patientUsername), generatedId, yourNumber, email, checkupDate, checkupTime);
    	                }
    	                
    	                
    	        	
    	        }else {
    	        	System.out.println("Failed to retrieve inserted record id");
    	        }
                
               
    	        response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");

            } else {
                response.sendRedirect("patients_registration.jsp?error=1");
                // Handle insertion failure
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
        }

    }
    
    void fetchAppointments(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointments")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String patientUsername = rs.getString("patient_username");
                String appointmentType = rs.getString("appointment_type");
                String appointmentDescription = rs.getString("appointment_description");
                int doctorId = rs.getInt("doctor_id");
                int technicianId = rs.getInt("technician_id");
                String createdDateTime = rs.getString("created_date_time");
                String checkupDate = rs.getString("checkup_date");
                String checkupTime = rs.getString("checkup_time");
                String status = rs.getString("status");
                String doctorFeedback = rs.getString("doctor_feedback");
                String testResults = rs.getString("test_results");
                String testResultsJson = rs.getString("test_results");
                String issuedDateTime = rs.getString("issued_date_time");
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, String>> testResultsList = objectMapper.readValue(testResultsJson, new TypeReference<List<Map<String, String>>>(){});

                String payment = rs.getString("payment");

                // Create Appointment object
                Appointment appointment = new Appointment(id, patientUsername, appointmentType, appointmentDescription, doctorId, technicianId, createdDateTime, checkupDate, checkupTime, status, doctorFeedback, testResultsList, payment, issuedDateTime);
                
                // Add appointment to the list
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set appointments as request attribute
        request.setAttribute("appointments", appointments);

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    void fetchAppointmentsById(HttpServletRequest request, HttpServletResponse response, int username, UserType userType) 
            throws ServletException, IOException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "";
        try (Connection conn = DBConnect.getConnection()) {
            if (userType.equals(UserType.PATIENT)) {
                query = "SELECT * FROM appointments WHERE patient_username = ?";
            } else if (userType.equals(UserType.DOCTOR)) {
                query = "SELECT * FROM appointments WHERE doctor_id = ?";
            } else if (userType.equals(UserType.LAB_TECHNICIAN)) {
                query = "SELECT * FROM appointments WHERE technician_id = ?";
            }else if (userType.equals(UserType.SINGLE_ITEM)) {
                query = "SELECT * FROM appointments WHERE id = ?";
            }else {
                query = "SELECT * FROM appointments";
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // Retrieve appointment data from ResultSet
                        int id = rs.getInt("id");
                        String patientUsername = rs.getString("patient_username");
                        String appointmentType = rs.getString("appointment_type");
                        String appointmentDescription = rs.getString("appointment_description");
                        int doctorId = rs.getInt("doctor_id");
                        int technicianId = rs.getInt("technician_id");
                        String createdDateTime = rs.getString("created_date_time");
                        String checkupDate = rs.getString("checkup_date");
                        String checkupTime = rs.getString("checkup_time");
                        String status = rs.getString("status");
                        String doctorFeedback = rs.getString("doctor_feedback");
                        
                        // Parse test results from JSON string
                        String testResultsJson = rs.getString("test_results");
                        ObjectMapper objectMapper = new ObjectMapper();
                        List<Map<String, String>> testResultsList = objectMapper.readValue(testResultsJson, new TypeReference<List<Map<String, String>>>(){});

                        String payment = rs.getString("payment");
                        String issuedDateTime = rs.getString("issued_date_time");
                        
                        System.out.println(testResultsJson);

                        // Create Appointment object
                        Appointment appointment = new Appointment(id, patientUsername, appointmentType, appointmentDescription, doctorId, technicianId, createdDateTime, checkupDate, checkupTime, status, doctorFeedback, testResultsList, payment, issuedDateTime);

                        // Add appointment to the list
                        appointments.add(appointment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set appointments as request attribute
        request.setAttribute("appointmentsById", appointments);
    

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    int fetchAppointmentsLengthInThisTime(HttpServletRequest request, HttpServletResponse response, String date, String time) 
            throws ServletException, IOException {
        int appointmentCount = 0;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM appointments WHERE checkup_date = ? AND checkup_time = ?")) {
            stmt.setString(1, date);
            stmt.setString(2, time);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                appointmentCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the count of appointments
        return appointmentCount;
    }

    
    void fetchPatients(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Patient> patients = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String dateOfBirth = rs.getString("date_of_birth");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                Patient patient = new Patient(id, firstName, lastName, contactNumber, email, address, gender, nic, dateOfBirth, registeredDateTime, password);
                
                // Add patient to the list
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set patients as request attribute
        request.setAttribute("patients", patients);

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    Patient fetchPatientById(HttpServletRequest request, HttpServletResponse response, int Id) 
            throws ServletException, IOException {
        Patient patient = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients WHERE id = ?")) {
            stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retrieve patient data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String dateOfBirth = rs.getString("date_of_birth");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                patient = new Patient(id, firstName, lastName, contactNumber, email, address, gender, nic, dateOfBirth, registeredDateTime, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the patient object
        request.setAttribute("patientById", patient);
        return patient;
    }

    
    void fetchDoctors(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM doctors")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String medicalCategory = rs.getString("medical_category");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                Doctor doctor = new Doctor(id, firstName, lastName, contactNumber, email, address, gender, nic, medicalCategory, registeredDateTime, password);
                
                // Add patient to the list
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set patients as request attribute
        request.setAttribute("doctors", doctors);

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    Doctor fetchDoctorById(HttpServletRequest request, HttpServletResponse response, int Id) 
            throws ServletException, IOException {
    	Doctor doctor = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM doctors WHERE id = ?")) {
        	stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String medicalCategory = rs.getString("medical_category");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                doctor = new Doctor(id, firstName, lastName, contactNumber, email, address, gender, nic, medicalCategory, registeredDateTime, password);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set patients as request attribute
        request.setAttribute("doctorById", doctor);
        return doctor;

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    void fetchTechnicians(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Technician> technicians = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM technicians")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                Technician technician = new Technician(id, firstName, lastName, contactNumber, email, address, gender, nic, registeredDateTime, password);
                
                // Add patient to the list
                technicians.add(technician);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set patients as request attribute
        request.setAttribute("technicians", technicians);

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    Technician fetchTechnicianById(HttpServletRequest request, HttpServletResponse response, int Id) 
            throws ServletException, IOException {
        Technician technician = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM technicians WHERE id = ?")) {
        	stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve appointment data from ResultSet
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String nic = rs.getString("nic");
                String registeredDateTime = rs.getString("registered_date_time");
                String password = rs.getString("password");

                // Create Patient object
                technician = new Technician(id, firstName, lastName, contactNumber, email, address, gender, nic, registeredDateTime, password);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set patients as request attribute
        request.setAttribute("technicianById", technician);
        return technician;

//        // Forward to appointments view page
//        request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
    }
    
    void sendEmail(HttpServletRequest request, HttpServletResponse response, String firstName, String lastName, String userType, int userId, String password, String email, String description) 
            throws ServletException, IOException {
    	String senderEmail = "abclaboratory0@gmail.com";
        String senderPassword = "tlki nula jfem iqmk";

        // Recipient's email address
        String recipientEmail = email;

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Login Details - ABC Laboratory");

            // Set the actual message
            // Set the actual message body
            String body = "Hi " + firstName + ",\n\n" +
                    "You have successfully registered to ABC Laboratory System. Please use these credentials to login to your account.\n\n" +
                    "UserType: " + userType + "\n" +
                    "Username: " + userId + "\n" +
                    "Password: " + password + "\n\n" +
                    "Login here - http://localhost:8090/MyLabTest1/app/login \n\n" +
                    ""+description+"\n\n" +
                    "Best regards,\n" +
                    "ABC Laboratory System";

            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    void sendEmailForAppointmentsUnpaid(HttpServletRequest request, HttpServletResponse response, String firstName, int userId, int appointmentId, int yourNumber, String email, String date, String time) 
            throws ServletException, IOException {
    	String senderEmail = "abclaboratory0@gmail.com";
        String senderPassword = "tlki nula jfem iqmk";

        // Recipient's email address
        String recipientEmail = email;

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Appointment Details - ABC Laboratory");

            // Set the actual message
            // Set the actual message body
            String body = "Hi " + firstName + ",\n\n" +
                    "Your appointment was successfully added. Find below details:\n\n" +
                    "Date: " + date + "\n" +
                    "Time: " + time + "\n" +
                    "Your Number: " + yourNumber + "\n" +
                    "Ref. Number: " + appointmentId + "\n\n" +
                    "Payment Status: Unpaid \n\n" +
                    "Please visit and make the payment at least 30 minutes before the appointment time. \n\n" +
                    "Best regards,\n" +
                    "ABC Laboratory System";

            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    void sendEmailForAppointmentsPaid(HttpServletRequest request, HttpServletResponse response, String firstName, int userId, int appointmentId, int yourNumber, String email, String date, String time, String testCategory, String cost) 
            throws ServletException, IOException {
    	String senderEmail = "abclaboratory0@gmail.com";
        String senderPassword = "tlki nula jfem iqmk";

        // Recipient's email address
        String recipientEmail = email;

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Appointment Details - ABC Laboratory");

            // Set the actual message
            // Set the actual message body
            String body = "Hi " + firstName + ",\n\n" +
                    "Your appointment was successfully added. Find below details:\n\n" +
                    "Date: " + date + "\n" +
                    "Time: " + time + "\n" +
                    "Your Number: " + yourNumber + "\n" +
                    "Ref. Number: " + appointmentId + "\n\n" +
                    "Payment Status: Paid \n\n" +
                    "Test Category: " + testCategory + "\n" +
                    "Paid amount: " + cost + "\n\n" +
                    "Best regards,\n" +
                    "ABC Laboratory System";

            message.setText(body);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
//    void updateTestResults(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//    String jsonData = request.getParameter("jsonData");
//    // Process JSON data as needed
//    System.out.println("Received JSON data: " + jsonData);
//    // You can parse the JSON data and update the test results in your database
//    
////    {"appointmentId":"12","results":[{"White Blood Cells (WBC)":"5","Red Blood Cells (RBC)":"5","Hemoglobin (Hgb)":"14","Hematocrit (Hct)":"39","Platelets":"320"}]}
//    
//    
//    
//}
    
    private void updateTestResults(HttpServletRequest request, HttpServletResponse response) 

            throws ServletException, IOException {

        String jsonData = request.getParameter("jsonData");

        System.out.println("Received JSON data: " + jsonData);
        
     // Accessing the singleton instance
        CommonFunctions commonFunctions = CommonFunctions.getInstance();

        // Now you can access the methods using the singleton instance
        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();

        
//        CommonFunctions commonFunctions = new CommonFunctions();
//        String currentDateAndTime = commonFunctions.getCurrentDateAndTime();



        try {

            // Parse the JSON data

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(jsonData);



            // Extract appointment ID and test results

            String appointmentId = jsonNode.get("appointmentId").asText();
            JsonNode resultsNode = jsonNode.get("results");



            // Convert JSON test results to a list of maps

            List<Map<String, String>> testResultsList = new ArrayList<>();

            for (JsonNode resultNode : resultsNode) {

                Map<String, String> testResult = new HashMap<>();

                testResult.put("ingradientName", resultNode.get("ingradientName").asText());

                testResult.put("value", resultNode.get("value").asText());

                testResult.put("hint", resultNode.get("hint").asText());

                testResultsList.add(testResult);

            }



            // Construct the SQL query
            String sql = "UPDATE appointments SET test_results = ?, status = ?, issued_date_time = ? WHERE id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Set the test_results JSON string
                String testResultsJson = objectMapper.writeValueAsString(testResultsList);
                // Set the parameter values for the prepared statement
                stmt.setString(1, testResultsJson);
                stmt.setString(2, "Completed");
                stmt.setString(3, currentDateAndTime);
                stmt.setInt(4, Integer.parseInt(appointmentId));
                // Execute the update query

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Update successful
                    // Send response indicating success
                    response.sendRedirect(request.getContextPath() + "/app/lab_technician_dashboard");
                } else {
                    // Update failed
                    // Send response indicating failure
                }
            } catch (SQLException e) {
                // Handle database error
                e.printStackTrace();
                // Send response indicating failure
                response.getWriter().write("Failed to update test results due to a database error!");
            }
        } catch (IOException e) {
            // Handle JSON parsing error
            e.printStackTrace();
            // Send response indicating failure
            response.getWriter().write("Failed to update test results due to a JSON parsing error!");
        }
    }
    
    private void updateAppointment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	 int appointmentId = Integer.parseInt(request.getParameter("id"));
    String appointmentDescription = request.getParameter("appointmentDescription");
    String checkupDate = request.getParameter("checkupDate");
    String checkupTime = request.getParameter("selectedTime");
    int selectDoctor = Integer.parseInt(request.getParameter("selectDoctor"));
    int selectTechnician = Integer.parseInt(request.getParameter("selectTechnician"));
    String payment = request.getParameter("payment");
    String status = "Pending";
    String cost = "LKR 0.00";
    if ("paid".equals(payment)) {
        status = "Ongoing";
    } else {
        status = "Pending";
    }
    
    // Assuming testResults, currentDateAndTime, status, and cost are declared and initialized as in the provided code

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE appointments SET appointment_description = ?, checkup_date = ?, checkup_time = ?, doctor_id = ?, technician_id = ?, payment = ?, status = ? WHERE id = ?")) {
        stmt.setString(1, appointmentDescription);
        stmt.setString(2, checkupDate);
        stmt.setString(3, checkupTime);
        stmt.setInt(4, selectDoctor);
        stmt.setInt(5, selectTechnician);
        stmt.setString(6, payment);
        stmt.setString(7, status);
        stmt.setInt(8, appointmentId);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Handle successful update
            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
        } else {
            // Handle no rows updated (e.g., if the appointment doesn't exist)
            response.sendRedirect("update_appointment.jsp?error=1");
        }
    } catch (SQLException e) {
        // Handle database error
        e.printStackTrace();
    }
}
    
    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        System.out.println("///////////////////////////////ok");

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM appointments WHERE id = ?")) {
            stmt.setInt(1, appointmentId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Handle successful deletion
                response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
            } else {
                // Handle no rows deleted (e.g., if the appointment doesn't exist)
                response.sendRedirect("update_appointment.jsp?error=1");
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
        }
    }
    
    private void updatePatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	 int patientId = Integer.parseInt(request.getParameter("id"));
    	 String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String contactNumber = request.getParameter("contactNumber");
         String email = request.getParameter("email");
         String address = request.getParameter("address");
         String gender = request.getParameter("gender");
         String nic = request.getParameter("nic");
         String dateOfBirth = request.getParameter("dateOfBirth");
    
    // Assuming testResults, currentDateAndTime, status, and cost are declared and initialized as in the provided code

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE patients SET first_name = ?, last_name = ?, contact_number = ?, email = ?, address = ?, gender = ?, nic = ?, date_of_birth = ? WHERE id = ?")) {
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, contactNumber);
        stmt.setString(4, email);
        stmt.setString(5, address);
        stmt.setString(6, gender);
        stmt.setString(7, nic);
        stmt.setString(8, dateOfBirth);
        stmt.setInt(9, patientId);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Handle successful update
            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
        } else {
            // Handle no rows updated (e.g., if the appointment doesn't exist)
            response.sendRedirect("update_appointment.jsp?error=1");
        }
    } catch (SQLException e) {
        // Handle database error
        e.printStackTrace();
    }
}
    
    private void deletePatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int patientId = Integer.parseInt(request.getParameter("id"));
        System.out.println("///////////////////////////////ok");

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM patients WHERE id = ?")) {
            stmt.setInt(1, patientId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Handle successful deletion
                response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
            } else {
                // Handle no rows deleted (e.g., if the appointment doesn't exist)
                response.sendRedirect("update_appointment.jsp?error=1");
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
        }
    }
    
    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	 int doctorId = Integer.parseInt(request.getParameter("id"));
    	 String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String contactNumber = request.getParameter("contactNumber");
         String email = request.getParameter("email");
         String address = request.getParameter("address");
         String gender = request.getParameter("gender");
         String nic = request.getParameter("nic");
         String medicalCategory = request.getParameter("medicalCategory");
    
    // Assuming testResults, currentDateAndTime, status, and cost are declared and initialized as in the provided code

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE doctors SET first_name = ?, last_name = ?, contact_number = ?, email = ?, address = ?, gender = ?, nic = ?, medical_category = ? WHERE id = ?")) {
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, contactNumber);
        stmt.setString(4, email);
        stmt.setString(5, address);
        stmt.setString(6, gender);
        stmt.setString(7, nic);
        stmt.setString(8, medicalCategory);
        stmt.setInt(9, doctorId);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Handle successful update
            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
        } else {
            // Handle no rows updated (e.g., if the appointment doesn't exist)
            response.sendRedirect("update_appointment.jsp?error=1");
        }
    } catch (SQLException e) {
        // Handle database error
        e.printStackTrace();
    }
}
    
    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("id"));
        System.out.println("///////////////////////////////ok");

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM doctors WHERE id = ?")) {
            stmt.setInt(1, doctorId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Handle successful deletion
                response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
            } else {
                // Handle no rows deleted (e.g., if the appointment doesn't exist)
                response.sendRedirect("update_appointment.jsp?error=1");
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
        }
    }
    
    private void updateTechnician(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	 int technicianId = Integer.parseInt(request.getParameter("id"));
    	 String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String contactNumber = request.getParameter("contactNumber");
         String email = request.getParameter("email");
         String address = request.getParameter("address");
         String gender = request.getParameter("gender");
         String nic = request.getParameter("nic");
    
    // Assuming testResults, currentDateAndTime, status, and cost are declared and initialized as in the provided code

    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE technicians SET first_name = ?, last_name = ?, contact_number = ?, email = ?, address = ?, gender = ?, nic = ? WHERE id = ?")) {
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, contactNumber);
        stmt.setString(4, email);
        stmt.setString(5, address);
        stmt.setString(6, gender);
        stmt.setString(7, nic);
        stmt.setInt(8, technicianId);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            // Handle successful update
            response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
        } else {
            // Handle no rows updated (e.g., if the appointment doesn't exist)
            response.sendRedirect("update_appointment.jsp?error=1");
        }
    } catch (SQLException e) {
        // Handle database error
        e.printStackTrace();
    }
}
    
    private void deleteTechnician(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int technicianId = Integer.parseInt(request.getParameter("id"));
        System.out.println("///////////////////////////////ok");

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM technicians WHERE id = ?")) {
            stmt.setInt(1, technicianId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Handle successful deletion
                response.sendRedirect(request.getContextPath() + "/app/lab_receptionist_dashboard");
            } else {
                // Handle no rows deleted (e.g., if the appointment doesn't exist)
                response.sendRedirect("update_appointment.jsp?error=1");
            }
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
        }
    }


}




