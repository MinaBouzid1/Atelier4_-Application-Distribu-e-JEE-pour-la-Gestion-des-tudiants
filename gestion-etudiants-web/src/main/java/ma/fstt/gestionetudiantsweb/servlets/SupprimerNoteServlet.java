package ma.fstt.gestionetudiantsweb.servlets;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.ISuivieRemote;

import java.io.IOException;

@WebServlet("/notes/supprimer")
public class SupprimerNoteServlet extends HttpServlet {

    @EJB
    private ISuivieRemote suivieBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            suivieBean.deleteSuivie(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/notes/liste");
    }
}