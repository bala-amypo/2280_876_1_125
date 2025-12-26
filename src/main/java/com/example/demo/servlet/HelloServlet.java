package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(urlPatterns = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    
    private static final String[] CHEF_QUOTES = {
        "The secret ingredient is... more profit margin! 👨‍🍳",
        "I'm not just a servlet, I'm a recipe for success! 📊",
        "Cooking the books? No, we're cooking REAL food here! 🍔",
        "This servlet is well-done, not medium-rare! ✅",
        "JWT tokens are like salt - a little goes a long way! 🔐"
    };
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        // Easter egg: Secret menu based on query parameter
        String secret = request.getParameter("secret");
        
        if ("menu".equals(secret)) {
            out.write(getSecretMenu());
        } else if ("profit".equals(secret)) {
            out.write(getProfitJoke());
        } else if ("chef".equals(secret)) {
            out.write(getRandomChefQuote());
        } else {
            // Standard response for tests
            out.write("Hello from servlet");
        }
        
        out.flush();
    }
    
    private String getSecretMenu() {
        return """
                🍽️  SECRET DEVELOPER MENU 🍽️
                ================================
                1. NullPointerException Soup - $404.00
                2. Stack Overflow Sandwich - $500.00
                3. Recursive Pizza (contains itself) - $∞
                4. Caffeine-Driven Development Coffee - $FREE
                5. Spaghetti Code Pasta - $Legacy
                
                ⚠️  Warning: May cause unexpected behavior!
                """;
    }
    
    private String getProfitJoke() {
        return """
                💰 PROFIT CALCULATION EASTER EGG 💰
                ===================================
                
                Q: Why did the MenuItem go to therapy?
                A: It had too many emotional ingredients!
                
                Q: What's a restaurant's favorite data structure?
                A: A Hash Brown Table! 🥔
                
                Q: Why don't programmers like cooking?
                A: Too many EXCEPTIONS in the kitchen! 🔥
                
                Your actual profit margin: [REDACTED] 😉
                """;
    }
    
    private String getRandomChefQuote() {
        int index = (int) (Math.random() * CHEF_QUOTES.length);
        LocalTime now = LocalTime.now();
        
        return String.format("""
                👨‍🍳 CHEF'S WISDOM OF THE MOMENT 👨‍🍳
                =====================================
                Time: %02d:%02d:%02d
                
                "%s"
                
                P.S. - Try ?secret=menu for more surprises!
                """, now.getHour(), now.getMinute(), now.getSecond(), 
                CHEF_QUOTES[index]);
    }
}