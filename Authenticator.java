package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Authenticator", urlPatterns = { "/Authenticator" })
public class Authenticator extends HttpServlet {

    private boolean isUserValid(String username, String password) {
        // Load user credentials from the file
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\visha\\Desktop\\Todo\\demo\\src\\main\\resources\\static\\users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];

                    // Check if the username and password match
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // User not found or password doesn't match
    }

    @Override
    public void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") != null) {
            request.getRequestDispatcher("Welcome").forward(request, response);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (isUserValid(username, password)) {
                session.setAttribute("username", username);
                String redirect = (String) session.getAttribute("redirect");
                if (redirect != null) {
                    session.removeAttribute("redirect");
                    request.getRequestDispatcher(redirect).forward(request, response);
                } else {
                    response.sendRedirect("Notes.html");
                    // request.getRequestDispatcher("Welcome").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("Login").forward(request, response);
            }
        }
    }
}

