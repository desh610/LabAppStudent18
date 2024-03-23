package com.example.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NavigationManager {

    public static void navigateTo(HttpServletRequest request, HttpServletResponse response, String destination) throws ServletException, IOException {
  
        request.getRequestDispatcher(destination).forward(request, response);
    }
}

