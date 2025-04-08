
package controller.product;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.ProductDTO;

@WebServlet(name = "SortProduct", urlPatterns = {"/product/sort"})
public class SortProduct extends HttpServlet {
    private final String SUCCESS = "../view/product/ListProduct.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession ses = request.getSession();
        Object sesList = ses.getAttribute("listProductConst");
        List<ProductDTO> list = sesList != null ? ((List<ProductDTO>) sesList) : null;
        if (list == null || list.isEmpty()) {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
            return;
        }
        
        String price = request.getParameter("price");
        String sale = request.getParameter("sale");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        
        if (price != null) {
            list.sort((o1, o2) -> {
                int result;
                int priceFinalO1 = o1.getPrice() * (1 - o1.getDiscount() / 100);
                int priceFinalO2 = o2.getPrice() * (1 - o2.getDiscount() / 100);
                if (price.equals("asc")) 
                    result = Integer.compare(priceFinalO1, priceFinalO2);
                else 
                    result = Integer.compare(priceFinalO2, priceFinalO1);
                return result;
            });
            request.setAttribute("price", price);
            ses.setAttribute("listProduct", list);
        } else if (sale != null) {
            List<ProductDTO> listNew;
            if (sale.equals("no")) {
                listNew = list.stream().filter((o) -> {
                    return o.getDiscount() == 0; 
                }).collect(Collectors.toList());
            } else {
                listNew = list.stream().filter((o) -> {
                    return o.getDiscount() != 0;
                }).collect(Collectors.toList());
            }
            request.setAttribute("sale", sale);
            ses.setAttribute("listProduct", listNew);
        } else if (!from.equals("") && !to.equals("")) {
            int fromInt = Integer.parseInt(from);
            int toInt = Integer.parseInt(to);
            List<ProductDTO> listNew;
            listNew = list.stream().filter((o) -> {
                int pricePro = o.getPrice();
                return pricePro >= fromInt && pricePro <= toInt; 
            }).collect(Collectors.toList());
            request.setAttribute("from", from);
            request.setAttribute("to", to);
            ses.setAttribute("listProduct", listNew);
        }
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
