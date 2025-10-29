package ma.fstt.gestionetudiantsejb.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module")
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private Integer idModule;

    @Column(name = "nom_module", nullable = false, length = 100)
    private String nomModule;

    @Column(name = "code_module", nullable = false, unique = true, length = 20)
    private String codeModule;

    @Column(name = "coefficient")
    private Double coefficient;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Suivie> suivies = new ArrayList<>();

    // Constructeurs
    public Module() {
    }

    public Module(String nomModule, String codeModule, Double coefficient) {
        this.nomModule = nomModule;
        this.codeModule = codeModule;
        this.coefficient = coefficient;
    }

    // Getters et Setters
    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public List<Suivie> getSuivies() {
        return suivies;
    }

    public void setSuivies(List<Suivie> suivies) {
        this.suivies = suivies;
    }

    @Override
    public String toString() {
        return "Module{" +
                "idModule=" + idModule +
                ", nomModule='" + nomModule + '\'' +
                ", codeModule='" + codeModule + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}