package ma.fstt.gestionetudiantsejb.ejb;

import jakarta.ejb.Remote;
import ma.fstt.gestionetudiantsejb.entities.Suivie;
import java.util.List;

@Remote
public interface ISuivieRemote {
    void addSuivie(Suivie suivie);
    void updateSuivie(Suivie suivie);
    void deleteSuivie(Integer id);
    Suivie findSuivieById(Integer id);
    List<Suivie> findAllSuivies();
    List<Suivie> findSuiviesByEtudiant(Integer idEtudiant);
    List<Suivie> findSuiviesByModule(Integer idModule);
    Double getMoyenneEtudiant(Integer idEtudiant);
}