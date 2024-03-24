package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.DatabaseServlet;
import com.example.model.*;
import com.example.util.*;

@WebServlet("/NavigationServlet")
public class NavigationServlet extends HttpServlet {
	
	private DatabaseServlet databaseServlet = new DatabaseServlet(); 
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/app")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/index")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/login")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/app/login")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/patients_registration")) {
            request.getRequestDispatcher("/patients_registration.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/doctors_registration")) {
            request.getRequestDispatcher("/doctors_registration.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/technicians_registration")) {
            request.getRequestDispatcher("/technicians_registration.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/add_appointment")) {
        	databaseServlet.fetchDoctors(request, response);
        	databaseServlet.fetchTechnicians(request, response);
            request.getRequestDispatcher("/add_appointment.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/update_appointment")) {
            // Assuming you have the appointment ID stored in the id parameter
            String appointmentId = request.getParameter("id");
            
            // Fetch doctors, technicians, and appointment details by ID
            databaseServlet.fetchDoctors(request, response);
            databaseServlet.fetchTechnicians(request, response);
            databaseServlet.fetchAppointmentsById(request, response, Integer.parseInt(appointmentId), UserType.SINGLE_ITEM);
            
            // Forward the request to the update_appointment.jsp page
            request.getRequestDispatcher("/update_appointment.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/update_patient")) {
            // Assuming you have the appointment ID stored in the id parameter
            String patientId = request.getParameter("id");
            
            // Fetch doctors, technicians, and appointment details by ID
            databaseServlet.fetchPatientById(request, response, Integer.parseInt(patientId));
            
            // Forward the request to the update_appointment.jsp page
            request.getRequestDispatcher("/update_patient.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/update_doctor")) {
            // Assuming you have the appointment ID stored in the id parameter
            String doctorId = request.getParameter("id");
            
            // Fetch doctors, technicians, and appointment details by ID
            databaseServlet.fetchDoctorById(request, response, Integer.parseInt(doctorId));
            
            // Forward the request to the update_appointment.jsp page
            request.getRequestDispatcher("/update_doctor.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/update_technician")) {
            // Assuming you have the appointment ID stored in the id parameter
            String technicianId = request.getParameter("id");
            
            // Fetch doctors, technicians, and appointment details by ID
            databaseServlet.fetchTechnicianById(request, response, Integer.parseInt(technicianId));
            
            // Forward the request to the update_appointment.jsp page
            request.getRequestDispatcher("/update_technician.jsp").forward(request, response);
        }
        else if (pathInfo.equals("/lab_receptionist_dashboard")) {
        	databaseServlet.fetchAppointments(request, response);
        	databaseServlet.fetchPatients(request, response);
        	databaseServlet.fetchDoctors(request, response);
        	databaseServlet.fetchTechnicians(request, response);
            // Assuming receptionist_dashboard.jsp is the dashboard for receptionists
            request.getRequestDispatcher("/lab_receptionist_dashboard.jsp").forward(request, response);
        } else if (pathInfo.equals("/app/lab_receptionist_dashboard")) {
        	databaseServlet.fetchAppointments(request, response);
        	databaseServlet.fetchPatients(request, response);
        	databaseServlet.fetchDoctors(request, response);
        	databaseServlet.fetchTechnicians(request, response);
            // Assuming receptionist_dashboard.jsp is the dashboard for receptionists
            request.getRequestDispatcher("/lab_receptionist_dashboard.jsp").forward(request, response);
        } 
        else if (pathInfo.equals("/patient_dashboard")) {
        	Patient loggedInUser = (Patient) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.PATIENT);
        	    request.getRequestDispatcher("/patient_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        	
        }
        else if (pathInfo.equals("/app/patient_dashboard")) {
        	Patient loggedInUser = (Patient) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.PATIENT);
        	    request.getRequestDispatcher("/patient_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        }
        
        else if (pathInfo.equals("/doctor_dashboard")) {
        	Doctor loggedInUser = (Doctor) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.DOCTOR);
        	    request.getRequestDispatcher("/doctor_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        	
        }
        else if (pathInfo.equals("/app/doctor_dashboard")) {
        	Doctor loggedInUser = (Doctor) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.DOCTOR);
        	    request.getRequestDispatcher("/doctor_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        }
        else if (pathInfo.equals("/lab_technician_dashboard")) {
        	Technician loggedInUser = (Technician) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.LAB_TECHNICIAN);
        	    request.getRequestDispatcher("/lab_technician_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        	
        }
        else if (pathInfo.equals("/app/lab_technician_dashboard")) {
        	Technician loggedInUser = (Technician) request.getSession().getAttribute("loggedInUser");
        	if (loggedInUser != null) {
        	    System.out.println("===================== " + loggedInUser.getId());
        	    databaseServlet.fetchAppointmentsById(request, response, loggedInUser.getId(), UserType.LAB_TECHNICIAN);
        	    request.getRequestDispatcher("/lab_technician_dashboard.jsp").forward(request, response);
        	} else {
        		response.sendRedirect("login");
        	}

        }

        else {
            // Unknown path, return 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

