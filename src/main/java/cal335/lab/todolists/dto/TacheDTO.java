package cal335.lab.todolists.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TacheDTO {

    @JsonProperty("nom")
    private String nom;
    @JsonProperty("description")
    private String description;

    @JsonProperty("aFaire")
    private boolean aFaire;

    public TacheDTO() {
    }

    public TacheDTO(String nom, String description, boolean aFaire) {
        this.nom = nom;
        this.description = description;
        this.aFaire = aFaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isaFaire() {
        return aFaire;
    }

    public void setaFaire(boolean aFaire) {
        this.aFaire = aFaire;
    }

    @Override
    public String toString() {
        return "TacheDTO{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", aFaire=" + aFaire +
                '}';
    }
}
