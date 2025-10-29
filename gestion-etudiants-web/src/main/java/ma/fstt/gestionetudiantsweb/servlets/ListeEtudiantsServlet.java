package ma.fstt.gestionetudiantsweb.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.IEtudiantRemote;
import ma.fstt.gestionetudiantsejb.entities.Etudiant;

import java.io.IOException;
import java.util.List;

@WebServlet("/etudiants/liste")
public class ListeEtudiantsServlet extends HttpServlet {

    @EJB
    private IEtudiantRemote etudiantBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Etudiant> etudiants = etudiantBean.findAllEtudiants();
            request.setAttribute("etudiants", etudiants);
            request.getRequestDispatcher("/WEB-INF/views/etudiants/liste.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des étudiants: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}