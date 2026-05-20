package myshop;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");

        try {

            // 🔐 NULL CHECK (IMPORTANT - prevents NULL rows)
            if(user == null || pass == null || email == null ||
               user.trim().isEmpty() || pass.trim().isEmpty() || email.trim().isEmpty()) {

                response.sendRedirect("register.html");
                return;
            }

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password,email) VALUES (?,?,?)"
            );

            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);

            ps.executeUpdate();

            // ✔ success → login page
            response.sendRedirect("login.html");

        } catch(Exception e) {
            e.printStackTrace();

            PrintWriter out = response.getWriter();
            out.println("<h3 style='color:red;'>Registration Failed</h3>");
        }
    }
}