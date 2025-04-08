package controller.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.dto.ProductDTO;

@WebServlet(name = "ListProduct", urlPatterns = {"/product/list"})
public class ListProduct extends HttpServlet {
    private final String SUCCESS = "../view/product/ListProduct.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String typeId = request.getParameter("typeId");
        String productName = request.getParameter("productName");
        productName = productName != null ? productName.trim() : null;
        
        ProductDAO proDAO = new ProductDAO(getServletContext());
        List<ProductDTO> list;
        HttpSession ses = request.getSession();
        if (typeId != null) {
            list = proDAO.listProductsByCategory(typeId);
            ses.setAttribute("report", new CategoryDAO(getServletContext()).getObjectById(typeId).getCategoryName());
        } else if (productName != null) {
            list = proDAO.listProductsByBox(productName);
            ses.setAttribute("report", "Search for '" + productName + "'");
        } else {
            list = proDAO.listAll();
            ses.setAttribute("report", "List All");
        }
        ses.setAttribute("listProductConst", list);
        ses.setAttribute("listProduct", list);
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
