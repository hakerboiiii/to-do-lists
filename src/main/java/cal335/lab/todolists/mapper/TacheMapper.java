package cal335.lab.todolists.mapper;
import cal335.lab.todolists.dto.TacheDTO;
import cal335.lab.todolists.modele.Tache;

public class TacheMapper {
    public static TacheDTO toDTO(Tache tache) {
        if(tache == null) {
            return null;
        }

        return new TacheDTO(tache.getNom(), tache.getDescription(), tache.getAFaire());
    }

    public static Tache toEntite(TacheDTO tacheDTO) {
        if(tacheDTO == null) {
            return null;
        }
        return new Tache(tacheDTO.getNom(), tacheDTO.getDescription(), tacheDTO.isaFaire());
    }
}
