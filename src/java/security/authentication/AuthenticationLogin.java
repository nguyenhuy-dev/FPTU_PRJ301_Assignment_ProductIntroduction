
package security.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.AccountDAO;
import model.dto.AccountDTO;

@WebServlet(name = "AuthenticationLogin", urlPatterns = {"/login"})
public class AuthenticationLogin extends HttpServlet {
    private final String ROUTER = "/view/Login.jsp";
    private final String SUCCESS = "home";
    private final String FAIL = "login?login=fail";
    private final String END = "/view/LoginError.html"; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession ses = request.getSession();
        AccountDTO acc = (AccountDTO) ses.getAttribute("acc");
        Object objError = ses.getAttribute("countError");
        int countError = (objError!=null)?(Integer)objError:0;
        ses.setAttribute("countError", countError);
        
        if (acc != null && acc.isIsUse()) {
            response.sendRedirect(SUCCESS);
        } else if (countError <= 3) {
            request.getRequestDispatcher(ROUTER).forward(request, response);
        } else {
            request.getRequestDispatcher(END).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String account = request.getParameter("account").trim();
        String pass = request.getParameter("pass");
        AccountDTO acc = new AccountDAO(getServletContext()).getUserByLogin(account, pass);
        HttpSession ses = request.getSession();
        //-- Giữ lại info như update
        ses.setAttribute("acc", acc);
        ses.setAttribute("account", account);
        ses.setAttribute("pass", pass);
//        if (acc != null && acc.isIsUse()) {
//            ses.setAttribute("countError", 0);
//            ses.setAttribute("infoLogin", acc);
//            response.sendRedirect(SUCCESS);
//        } else {
//            Object objError = ses.getAttribute("countError");
//            int countError = objError!=null?(Integer)objError:0;
//            countError++;
//            ses.setAttribute("countError", countError);
//            ses.setAttribute("errorLogin", "Invalid username or password!");
//            ses.setAttribute("errorLogin1", "If you enter the wrong login more than 3 times, you will be banned from logging in.");
//            response.sendRedirect(FAIL);
//        } 
        
        int countError = (Integer) ses.getAttribute("countError");
        if (countError > 3) {
            request.getRequestDispatcher(END);
        } else if (acc != null && acc.isIsUse()) {
            ses.setAttribute("errorLogin", null);
            ses.setAttribute("countError", 0);
            response.sendRedirect(SUCCESS);
        } else {
            countError++;
            ses.setAttribute("countError", countError);
            ses.setAttribute("errorLogin", "Invalid username or password!");
            ses.setAttribute("errorLogin1", "If you enter the wrong login more than 3 times, you will be banned from logging in.");
            response.sendRedirect(FAIL);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
