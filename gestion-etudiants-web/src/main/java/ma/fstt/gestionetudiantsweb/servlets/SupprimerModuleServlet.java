package ma.fstt.gestionetudiantsweb.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.IModuleRemote;

import java.io.IOException;

@WebServlet("/modules/supprimer")
public class SupprimerModuleServlet extends HttpServlet {

    @EJB
    private IModuleRemote moduleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            moduleBean.deleteModule(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/modules/liste");
    }
}