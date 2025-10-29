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

@WebServlet("/etudiants/ajouter")
public class AjouterEtudiantServlet extends HttpServlet {

    @EJB
    private IEtudiantRemote etudiantBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/etudiants/ajouter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String cne = request.getParameter("cne");
            String adresse = request.getParameter("adresse");
            String niveau = request.getParameter("niveau");

            Etudiant etudiant = new Etudiant(nom, prenom, cne, adresse, niveau);
            etudiantBean.addEtudiant(etudiant);

            response.sendRedirect(request.getContextPath() + "/etudiants/liste");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'ajout de l'étudiant: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/etudiants/ajouter.jsp").forward(request, response);
        }
    }
}