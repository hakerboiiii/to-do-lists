package cal335.lab.todolists.modele;

public class Tache {

    private static long compteur = 0;
    private long id;
    private String nom, description;
    private boolean aFaire;


    public Tache(){}; // Constructeur vide pour Jackson


    public Tache(String nom, String description, boolean estFait) {
        this.id = ++compteur;
        this.nom = nom;
        this.description = description;
        this.aFaire = estFait;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getAFaire() {
        return this.aFaire;
    }

    public long getId() {
        return id;
    }


    public static void setId(long compteur) {
        Tache.compteur = compteur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setaFaire(boolean aFaire) {
        this.aFaire = aFaire;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", aFaire=" + aFaire +
                '}';
    }


}
