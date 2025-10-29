package ma.fstt.gestionetudiantsejb.ejb;



import jakarta.ejb.Remote;
import ma.fstt.gestionetudiantsejb.entities.Etudiant;
import java.util.List;

@Remote
public interface IEtudiantRemote {
    void addEtudiant(Etudiant etudiant);
    void updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(Integer id);
    Etudiant findEtudiantById(Integer id);
    List<Etudiant> findAllEtudiants();
    Etudiant findEtudiantByCne(String cne);
}