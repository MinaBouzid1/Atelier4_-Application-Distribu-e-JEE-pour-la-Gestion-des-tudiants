package ma.fstt.gestionetudiantsejb.ejb;

import jakarta.ejb.Remote;
import ma.fstt.gestionetudiantsejb.entities.Module;
import java.util.List;

@Remote
public interface IModuleRemote {
    void addModule(Module module);
    void updateModule(Module module);
    void deleteModule(Integer id);
    Module findModuleById(Integer id);
    List<Module> findAllModules();
    Module findModuleByCode(String code);
}