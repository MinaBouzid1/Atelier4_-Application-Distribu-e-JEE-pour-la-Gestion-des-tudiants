package ma.fstt.gestionetudiantsweb.servlets;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.IEtudiantRemote;
import ma.fstt.gestionetudiantsejb.ejb.IModuleRemote;
import ma.fstt.gestionetudiantsejb.ejb.ISuivieRemote;
import ma.fstt.gestionetudiantsejb.entities.Etudiant;
import ma.fstt.gestionetudiantsejb.entities.Module;
import ma.fstt.gestionetudiantsejb.entities.Suivie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/notes/modifier")
public class ModifierNoteServlet extends HttpServlet {

    @EJB
    private ISuivieRemote suivieBean;

    @EJB
    private IEtudiantRemote etudiantBean;

    @EJB
    private IModuleRemote moduleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Suivie suivie = suivieBean.findSuivieById(id);
            List<Etudiant> etudiants = etudiantBean.findAllEtudiants();
            List<Module> modules = moduleBean.findAllModules();

            request.setAttribute("suivie", suivie);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("modules", modules);
            request.getRequestDispatcher("/WEB-INF/views/notes/modifier.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/notes/liste");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
            Integer idModule = Integer.parseInt(request.getParameter("idModule"));
            Double note = Double.parseDouble(request.getParameter("note"));
            String dateStr = request.getParameter("dateNote");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNote = sdf.parse(dateStr);

            Suivie suivie = suivieBean.findSuivieById(id);
            if (suivie != null) {
                Etudiant etudiant = etudiantBean.findEtudiantById(idEtudiant);
                Module module = moduleBean.findModuleById(idModule);

                suivie.setNote(note);
                suivie.setDateNote(dateNote);
                suivie.setEtudiant(etudiant);
                suivie.setModule(module);
                suivieBean.updateSuivie(suivie);
            }

            response.sendRedirect(request.getContextPath() + "/notes/liste");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la modification: " + e.getMessage());
            doGet(request, response);
        }
    }
}