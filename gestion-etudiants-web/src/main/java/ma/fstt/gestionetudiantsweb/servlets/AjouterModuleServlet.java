package ma.fstt.gestionetudiantsweb.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.gestionetudiantsejb.ejb.IModuleRemote;
import ma.fstt.gestionetudiantsejb.entities.Module;

import java.io.IOException;

@WebServlet("/modules/ajouter")
public class AjouterModuleServlet extends HttpServlet {

    @EJB
    private IModuleRemote moduleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/modules/ajouter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nomModule = request.getParameter("nomModule");
            String codeModule = request.getParameter("codeModule");
            Double coefficient = Double.parseDouble(request.getParameter("coefficient"));

            Module module = new Module(nomModule, codeModule, coefficient);
            moduleBean.addModule(module);

            response.sendRedirect(request.getContextPath() + "/modules/liste");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'ajout du module: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/modules/ajouter.jsp").forward(request, response);
        }
    }
}