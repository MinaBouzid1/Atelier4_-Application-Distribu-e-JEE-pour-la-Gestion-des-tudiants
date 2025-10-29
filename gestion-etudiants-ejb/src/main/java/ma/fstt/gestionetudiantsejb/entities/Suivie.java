package ma.fstt.gestionetudiantsejb.entities;



import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "suivie")
public class Suivie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suivie")
    private Integer idSuivie;

    @Column(name = "note", nullable = false)
    private Double note;

    @Column(name = "date_note", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_module", nullable = false)
    private Module module;

    // Constructeurs
    public Suivie() {
    }

    public Suivie(Double note, Date dateNote, Etudiant etudiant, Module module) {
        this.note = note;
        this.dateNote = dateNote;
        this.etudiant = etudiant;
        this.module = module;
    }

    // Getters et Setters
    public Integer getIdSuivie() {
        return idSuivie;
    }

    public void setIdSuivie(Integer idSuivie) {
        this.idSuivie = idSuivie;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Suivie{" +
                "idSuivie=" + idSuivie +
                ", note=" + note +
                ", dateNote=" + dateNote +
                '}';
    }
}