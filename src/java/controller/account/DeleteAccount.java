
package controller.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.AccountDAO;
import model.dto.AccountDTO;

@WebServlet(name = "DeleteAccount", urlPatterns = {"/account/delete"})
public class DeleteAccount extends HttpServlet {
    private final String SUCCESS = "list?delete=success";
    private final String FAIL = "list?delete=fail";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        AccountDAO daoAcc = new AccountDAO(getServletContext());
        AccountDTO acc = daoAcc.getObjectById(account);
        int result = daoAcc.delete(acc);
        
        HttpSession ses = request.getSession();
        AccountDTO accMain = (AccountDTO) ses.getAttribute("acc");
        if (account.equals(accMain.getAccount())) 
            accMain = null;
        ses.setAttribute("acc", accMain);
        
        if (result > 0) 
            response.sendRedirect(SUCCESS);
        else 
            response.sendRedirect(FAIL);
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
