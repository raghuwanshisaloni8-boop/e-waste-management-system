package myshop;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // 🔐 STEP: session get karo
        HttpSession session = request.getSession(false);

        // 🔐 STEP: session destroy karo
        if(session != null){
            session.invalidate();
        }

        // 🔐 STEP: cache disable (important for back button security)
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires", 0);

        // 🔐 STEP: redirect to login page
        response.sendRedirect("login.html");
    }
}