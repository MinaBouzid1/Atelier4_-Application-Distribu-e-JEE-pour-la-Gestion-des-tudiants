package ma.fstt.gestionetudiantsweb.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.ISuivieRemote;
import ma.fstt.gestionetudiantsejb.entities.Suivie;

import java.io.IOException;
import java.util.List;

@WebServlet("/notes/liste")
public class ListeNotesServlet extends HttpServlet {

    @EJB
    private ISuivieRemote suivieBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Suivie> notes = suivieBean.findAllSuivies();
            request.setAttribute("notes", notes);
            request.getRequestDispatcher("/WEB-INF/views/notes/liste.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des notes: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}