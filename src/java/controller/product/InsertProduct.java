/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.AccountDAO;
import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.dto.AccountDTO;
import model.dto.CategoryDTO;
import model.dto.ProductDTO;

/**
 *
 * @author quoch
 */
@WebServlet(name = "InsertProduct", urlPatterns = {"/product/insert"})
public class InsertProduct extends HttpServlet {
    private final String ROUTER = "../view/product/InsertProduct.jsp";
    private final String SUCCESS = "list?insert=success";
    private final String FAIL = "list?insert=fail";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        List<CategoryDTO> listCategory = new CategoryDAO(getServletContext()).listAll();
        List<AccountDTO> listAccount = new AccountDAO(getServletContext()).listAll();
        LocalDate dateNow = LocalDate.now();
        request.setAttribute("dateNow", dateNow);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listAccount", listAccount);
        request.getRequestDispatcher(ROUTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String productId = request.getParameter("productId").trim();
        String productName = request.getParameter("productName").trim();
        String productImage = request.getParameter("productImage");
        String brief = request.getParameter("brief").trim();
        Date postedDate = Date.valueOf(request.getParameter("postedDate"));
        CategoryDTO cat = new CategoryDAO(getServletContext()).getObjectById(request.getParameter("typeId"));
        AccountDTO acc = new AccountDAO(getServletContext()).getObjectById(request.getParameter("account"));
        String unit = request.getParameter("unit").trim();
        int price = Integer.parseInt(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        //-- Call business logic
        ProductDTO pro = new ProductDTO(productId, productName, productImage, brief, postedDate, cat, acc, unit, price, discount);
        int result = new ProductDAO(getServletContext()).insert(pro);
        if (result > 0) 
            response.sendRedirect(SUCCESS);
        else
            response.sendRedirect(FAIL);
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
