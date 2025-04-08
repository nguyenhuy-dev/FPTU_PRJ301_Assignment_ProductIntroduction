/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.category;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.CategoryDAO;
import model.dto.CategoryDTO;

/**
 *
 * @author quoch
 */
@WebServlet(name = "UpdateCategory", urlPatterns = {"/category/update"})
public class UpdateCategory extends HttpServlet {
    private final String ROUTER = "../view/category/UpdateCategory.jsp";
    private final String SUCCESS = "list?update=success";
    private final String FAIL = "list?update=fail";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String typeId = request.getParameter("typeId");
        CategoryDTO cat = new CategoryDAO(request.getServletContext()).getObjectById(typeId);
        request.setAttribute("cat", cat);
        request.getRequestDispatcher(ROUTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String categoryName = request.getParameter("categoryName").trim();
        String memo = request.getParameter("memo").trim();
        CategoryDTO cat = new CategoryDTO(typeId, categoryName, memo);
        int result = new CategoryDAO(request.getServletContext()).update(cat);
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
