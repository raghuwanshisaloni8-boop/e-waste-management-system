package myshop;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // DEBUG
        System.out.println("USER: " + user);
        System.out.println("PASS: " + pass);

        // Empty check
        if(user == null || pass == null ||
           user.trim().isEmpty() || pass.trim().isEmpty()) {

            System.out.println("EMPTY LOGIN BLOCKED");
            response.sendRedirect("login.html");
            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("LOGIN SUCCESS");

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect("home.html");

            } else {

                System.out.println("LOGIN FAILED");

                out.println("<html><body style='text-align:center;font-family:Arial;margin-top:50px;'>");
                out.println("<h2 style='color:red;'>Invalid Username or Password</h2>");
                out.println("<a href='login.html'>Try Again</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<h3 style='color:red;'>Server Error</h3>");
        }
    }
}