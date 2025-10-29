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

@WebServlet("/etudiants/modifier")
public class ModifierEtudiantServlet extends HttpServlet {

    @EJB
    private IEtudiantRemote etudiantBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Etudiant etudiant = etudiantBean.findEtudiantById(id);
            request.setAttribute("etudiant", etudiant);
            request.getRequestDispatcher("/WEB-INF/views/etudiants/modifier.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/etudiants/liste");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String cne = request.getParameter("cne");
            String adresse = request.getParameter("adresse");
            String niveau = request.getParameter("niveau");

            Etudiant etudiant = etudiantBean.findEtudiantById(id);
            if (etudiant != null) {
                etudiant.setNom(nom);
                etudiant.setPrenom(prenom);
                etudiant.setCne(cne);
                etudiant.setAdresse(adresse);
                etudiant.setNiveau(niveau);
                etudiantBean.updateEtudiant(etudiant);
            }

            response.sendRedirect(request.getContextPath() + "/etudiants/liste");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la modification: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/etudiants/modifier.jsp").forward(request, response);
        }
    }
}