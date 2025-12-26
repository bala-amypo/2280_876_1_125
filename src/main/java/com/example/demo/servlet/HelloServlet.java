package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        // 🎉 EASTER EGG: Try ?secret=chef in your browser!
        String secret = request.getParameter("secret");
        if ("chef".equals(secret)) {
            out.write("👨‍🍳 Secret Chef Mode: The best ingredient is clean code! Happy testing! 🚀");
        } else {
            out.write("Hello from servlet"); // Required by test
        }
        out.flush();
    }
}