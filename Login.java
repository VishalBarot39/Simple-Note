//127.0.0.1:8080/Login
//A simple note app created by using login framework, html, css, js

package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
    @Override
    public void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            request.getRequestDispatcher("Welcome").forward(request, response);
        }
        String redirect = (String) request.getAttribute("redirect");
        if (redirect != null)
            session.setAttribute("redirect", redirect);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Simple Note</title>");
            out.println("</head>");
            out.println("<body style='background-color: #f0f0f0; font-family: Arial, sans-serif;'>");
            out.println("<header style='text-align: center; padding: 20px; background-color: #0074d9;'>");
            out.println("<h1 style='color: #fff;'>Login to your Simple Note account</h1>");
            out.println("</header>");
            out.println("<div style='text-align: center;'>");
            out.println("<form action='Authenticator'>");
            out.println("<label for='login' style='color: #333; font-size: 18px;'>Username:</label>");
            out.println(
                    "<input type='text' id='login' name='username' style='margin: 10px; padding: 5px; border: 1px solid #ccc;'><br><br>");
            out.println("<label for='password' style='color: #333; font-size: 18px;'>Password:</label>");
            out.println(
                    "<input type='password' id='password' name='password' style='margin: 10px; padding: 5px; border: 1px solid #ccc;'><br><br>");
            out.println(
                    "<input type='submit' value='Submit' style='margin: 10px; padding: 10px 20px; background-color: #0074d9; color: #fff; border: none; cursor: pointer;'>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
