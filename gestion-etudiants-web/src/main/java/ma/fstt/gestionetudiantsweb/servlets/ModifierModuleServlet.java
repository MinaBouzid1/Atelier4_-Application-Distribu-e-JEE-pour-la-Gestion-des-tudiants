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

@WebServlet("/modules/modifier")
public class ModifierModuleServlet extends HttpServlet {

    @EJB
    private IModuleRemote moduleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Module module = moduleBean.findModuleById(id);
            request.setAttribute("module", module);
            request.getRequestDispatcher("/WEB-INF/views/modules/modifier.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/modules/liste");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nomModule = request.getParameter("nomModule");
            String codeModule = request.getParameter("codeModule");
            Double coefficient = Double.parseDouble(request.getParameter("coefficient"));

            Module module = moduleBean.findModuleById(id);
            if (module != null) {
                module.setNomModule(nomModule);
                module.setCodeModule(codeModule);
                module.setCoefficient(coefficient);
                moduleBean.updateModule(module);
            }

            response.sendRedirect(request.getContextPath() + "/modules/liste");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la modification: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/modules/modifier.jsp").forward(request, response);
        }
    }
}