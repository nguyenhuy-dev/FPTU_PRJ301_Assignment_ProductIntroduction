
package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ProductDAO;
import model.dto.ProductDTO;

@WebServlet(name = "DetailsProduct", urlPatterns = {"/product/details"})
public class DetailsProduct extends HttpServlet {
    private final String SUCCESS = "../view/product/DetailsProduct.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String productId = request.getParameter("productId");
        ProductDTO pro = new ProductDAO(getServletContext()).getObjectById(productId);
        request.setAttribute("pro", pro);
        
        //-- Check present cookie
        Cookie[] cookies = request.getCookies();
        String viewed = "";
        if (cookies != null) 
            for (Cookie cookie : cookies) 
                if (cookie.getName().equals("viewed")) 
                    viewed = cookie.getValue();
        //-- Add productId into viewed
        if (!viewed.contains(productId)) 
            viewed += productId + "@";
        //-- Rewrite cookie
        Cookie cookie = new Cookie("viewed", viewed);
        cookie.setMaxAge(60 * 5);
        response.addCookie(cookie);

        request.getRequestDispatcher(SUCCESS).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
