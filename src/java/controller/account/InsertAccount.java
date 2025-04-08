
package controller.account;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.AccountDAO;
import model.dto.AccountDTO;

@WebServlet(name = "InsertAccount", urlPatterns = {"/account/insert"})
public class InsertAccount extends HttpServlet {
    private final String ROUTER = "../view/account/InsertAccount.jsp";
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
        request.getRequestDispatcher(ROUTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String account = request.getParameter("account").trim();
        String pass = request.getParameter("pass").trim();
        String lastName = request.getParameter("lastName").trim();
        String firstName = request.getParameter("firstName").trim();
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        boolean gender = request.getParameter("gender").equals("male");
        String phone = request.getParameter("phone").trim();
        boolean isUse = request.getParameter("isUse")!=null;
        int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));
        AccountDTO acc = new AccountDTO(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
        int result = new AccountDAO(request.getServletContext()).insert(acc);
        if (result > 0) 
            response.sendRedirect(SUCCESS);
        else
            response.sendRedirect(FAIL);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
