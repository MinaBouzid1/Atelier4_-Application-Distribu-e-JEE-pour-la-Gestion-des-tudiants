package ma.fstt.gestionetudiantsejb.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ma.fstt.gestionetudiantsejb.entities.Etudiant;

import java.util.List;

@Stateless
public class EtudiantBean implements IEtudiantRemote {

    @PersistenceContext(unitName = "cnx")
    private EntityManager entityManager;

    @Override
    public void addEtudiant(Etudiant etudiant) {
        entityManager.persist(etudiant);
    }

    @Override
    public void updateEtudiant(Etudiant etudiant) {
        entityManager.merge(etudiant);
    }

    @Override
    public void deleteEtudiant(Integer id) {
        Etudiant etudiant = entityManager.find(Etudiant.class, id);
        if (etudiant != null) {
            entityManager.remove(etudiant);
        }
    }

    @Override
    public Etudiant findEtudiantById(Integer id) {
        return entityManager.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant> findAllEtudiants() {
        TypedQuery<Etudiant> query = entityManager.createQuery(
                "SELECT e FROM Etudiant e", Etudiant.class);
        return query.getResultList();
    }

    @Override
    public Etudiant findEtudiantByCne(String cne) {
        TypedQuery<Etudiant> query = entityManager.createQuery(
                "SELECT e FROM Etudiant e WHERE e.cne = :cne", Etudiant.class);
        query.setParameter("cne", cne);
        List<Etudiant> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}