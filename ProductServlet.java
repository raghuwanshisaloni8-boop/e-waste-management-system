package myshop;

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/products")

public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myshop",
                    "root",
                    "1234"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM products");

            out.println("<html><head><title>MyShop</title>");

            // CSS
            out.println("<style>");

            out.println("body{font-family:Segoe UI;margin:0;background:linear-gradient(135deg,#74ebd5,#ACB6E5);} ");

            out.println(".header{background:#131921;color:white;padding:15px;display:flex;justify-content:space-between;align-items:center;}");

            out.println(".title{font-size:24px;font-weight:bold;}");

            out.println(".logout{background:#ff9900;padding:8px 15px;border-radius:8px;color:black;text-decoration:none;font-weight:bold;}");

            out.println(".search-box{text-align:center;margin:20px;}");

            out.println(".search{padding:12px;width:300px;border:none;border-radius:10px;font-size:16px;}");

            out.println(".container{display:flex;flex-wrap:wrap;justify-content:center;}");

            out.println(".product{background:white;width:220px;margin:15px;padding:15px;border-radius:15px;box-shadow:0 5px 15px rgba(0,0,0,0.2);text-align:center;transition:0.3s;}");

            out.println(".product:hover{transform:scale(1.05);} ");

            out.println(".img{font-size:60px;}");

            out.println(".price{color:green;font-size:20px;font-weight:bold;}");

            out.println(".btn{background:#ff9900;border:none;padding:10px;width:100%;border-radius:8px;font-weight:bold;cursor:pointer;}");

            out.println(".cart-box{background:white;width:80%;margin:20px auto;padding:20px;border-radius:12px;box-shadow:0 5px 15px rgba(0,0,0,0.2);} ");

            out.println("</style>");

            out.println("</head><body>");

            // HEADER
            out.println("<div class='header'>");

            out.println("<div class='title'>🛒 MyShop</div>");

            out.println("<a class='logout' href='logout'>Logout</a>");

            out.println("</div>");

            // SEARCH
            out.println("<div class='search-box'>");

            out.println("<input type='text' id='search' class='search' placeholder='Search Products...' onkeyup='searchProducts()'>");

            out.println("</div>");

            // PRODUCTS
            out.println("<div class='container' id='products'>");

            while (rs.next()) {

                String name = rs.getString("name");

                String emoji = "💻";

                if (name.toLowerCase().contains("phone"))
                    emoji = "📱";
                else if (name.toLowerCase().contains("watch"))
                    emoji = "⌚";
                else if (name.toLowerCase().contains("head"))
                    emoji = "🎧";

                out.println("<div class='product'>");

                out.println("<div class='img'>" + emoji + "</div>");

                out.println("<h3>" + name + "</h3>");

                out.println("<div class='price'>₹" +
                        rs.getDouble("price") +
                        "</div>");

                out.println("<p>Stock: " +
                        rs.getInt("stock") +
                        "</p>");

                out.println("<button class='btn' onclick=\"addToCart('" +
                        name + "'," +
                        rs.getDouble("price") +
                        ")\">Add to Cart</button>");

                out.println("</div>");
            }

            out.println("</div>");

            // CART
            out.println("<div class='cart-box'>");

            out.println("<h2>🛒 Cart</h2>");

            out.println("<ul id='cart'></ul>");

            out.println("<h3 id='total'>Total: ₹0</h3>");

            out.println("</div>");

            // JS
            out.println("<script>");

            out.println("let cart = JSON.parse(localStorage.getItem('cart')) || [];");

            out.println("function saveCart(){ localStorage.setItem('cart', JSON.stringify(cart)); }");

            out.println("function addToCart(name,price){ cart.push({name,price}); saveCart(); showCart(); }");

            out.println("function removeItem(i){ cart.splice(i,1); saveCart(); showCart(); }");

            out.println("function showCart(){");

            out.println("let list=document.getElementById('cart');");

            out.println("list.innerHTML='';");

            out.println("let total=0;");

            out.println("cart.forEach((item,i)=>{");

            out.println("total+=item.price;");

            out.println("let li=document.createElement('li');");

            out.println("li.innerHTML=item.name+' - ₹'+item.price+' <button onclick=\"removeItem('+i+')\">Remove</button>';");

            out.println("list.appendChild(li);");

            out.println("});");

            out.println("document.getElementById('total').innerText='Total: ₹'+total;");

            out.println("}");

            out.println("function searchProducts(){");

            out.println("let input=document.getElementById('search').value.toLowerCase();");

            out.println("let products=document.getElementsByClassName('product');");

            out.println("for(let i=0;i<products.length;i++){");

            out.println("let text=products[i].innerText.toLowerCase();");

            out.println("products[i].style.display=text.includes(input)?'block':'none';");

            out.println("}");

            out.println("}");

            out.println("showCart();");

            out.println("</script>");

            out.println("</body></html>");

            con.close();

        } catch (Exception e) {

            out.println("<h2>Error:</h2>");

            out.println(e.getMessage());
        }
    }
}