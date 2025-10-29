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
import java.util.List;

@WebServlet("/modules/liste")
public class ListeModulesServlet extends HttpServlet {

    @EJB
    private IModuleRemote moduleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Module> modules = moduleBean.findAllModules();
            request.setAttribute("modules", modules);
            request.getRequestDispatcher("/WEB-INF/views/modules/liste.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des modules: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}