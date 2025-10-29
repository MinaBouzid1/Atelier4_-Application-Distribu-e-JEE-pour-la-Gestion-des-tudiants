package ma.fstt.gestionetudiantsejb.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ma.fstt.gestionetudiantsejb.entities.Suivie;

import java.util.List;

@Stateless
public class SuivieBean implements ISuivieRemote {

    @PersistenceContext(unitName = "cnx")
    private EntityManager entityManager;

    @Override
    public void addSuivie(Suivie suivie) {
        entityManager.persist(suivie);
    }

    @Override
    public void updateSuivie(Suivie suivie) {
        entityManager.merge(suivie);
    }

    @Override
    public void deleteSuivie(Integer id) {
        Suivie suivie = entityManager.find(Suivie.class, id);
        if (suivie != null) {
            entityManager.remove(suivie);
        }
    }

    @Override
    public Suivie findSuivieById(Integer id) {
        // Utiliser JOIN FETCH pour charger les associations
        TypedQuery<Suivie> query = entityManager.createQuery(
                "SELECT s FROM Suivie s JOIN FETCH s.etudiant JOIN FETCH s.module WHERE s.idSuivie = :id",
                Suivie.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Suivie> findAllSuivies() {
        // MODIFICATION IMPORTANTE : Charger les associations avec JOIN FETCH
        TypedQuery<Suivie> query = entityManager.createQuery(
                "SELECT s FROM Suivie s JOIN FETCH s.etudiant JOIN FETCH s.module",
                Suivie.class);
        return query.getResultList();
    }

    @Override
    public List<Suivie> findSuiviesByEtudiant(Integer idEtudiant) {
        // Charger aussi les associations dans cette méthode
        TypedQuery<Suivie> query = entityManager.createQuery(
                "SELECT s FROM Suivie s JOIN FETCH s.etudiant JOIN FETCH s.module WHERE s.etudiant.idEtudiant = :idEtudiant",
                Suivie.class);
        query.setParameter("idEtudiant", idEtudiant);
        return query.getResultList();
    }

    @Override
    public List<Suivie> findSuiviesByModule(Integer idModule) {
        // Charger aussi les associations dans cette méthode
        TypedQuery<Suivie> query = entityManager.createQuery(
                "SELECT s FROM Suivie s JOIN FETCH s.etudiant JOIN FETCH s.module WHERE s.module.idModule = :idModule",
                Suivie.class);
        query.setParameter("idModule", idModule);
        return query.getResultList();
    }

    @Override
    public Double getMoyenneEtudiant(Integer idEtudiant) {
        TypedQuery<Double> query = entityManager.createQuery(
                "SELECT AVG(s.note) FROM Suivie s WHERE s.etudiant.idEtudiant = :idEtudiant",
                Double.class);
        query.setParameter("idEtudiant", idEtudiant);
        Double moyenne = query.getSingleResult();
        return moyenne != null ? moyenne : 0.0;
    }
}