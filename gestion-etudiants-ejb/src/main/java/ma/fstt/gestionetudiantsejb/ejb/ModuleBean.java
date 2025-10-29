package ma.fstt.gestionetudiantsejb.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ma.fstt.gestionetudiantsejb.entities.Module;

import java.util.List;

@Stateless
public class ModuleBean implements IModuleRemote {

    @PersistenceContext(unitName = "cnx")
    private EntityManager entityManager;

    @Override
    public void addModule(Module module) {
        entityManager.persist(module);
    }

    @Override
    public void updateModule(Module module) {
        entityManager.merge(module);
    }

    @Override
    public void deleteModule(Integer id) {
        Module module = entityManager.find(Module.class, id);
        if (module != null) {
            entityManager.remove(module);
        }
    }

    @Override
    public Module findModuleById(Integer id) {
        return entityManager.find(Module.class, id);
    }

    @Override
    public List<Module> findAllModules() {
        TypedQuery<Module> query = entityManager.createQuery(
                "SELECT m FROM Module m", Module.class);
        return query.getResultList();
    }

    @Override
    public Module findModuleByCode(String code) {
        TypedQuery<Module> query = entityManager.createQuery(
                "SELECT m FROM Module m WHERE m.codeModule = :code", Module.class);
        query.setParameter("code", code);
        List<Module> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}