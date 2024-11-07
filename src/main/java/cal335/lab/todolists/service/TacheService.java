package cal335.lab.todolists.service;
import cal335.lab.todolists.dto.TacheDTO;
import cal335.lab.todolists.mapper.TacheMapper;
import cal335.lab.todolists.modele.Tache;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class TacheService {

    private Map<Boolean, List<Tache>> listeTaches;



    public TacheService() {
        this.listeTaches = new HashMap<>();
        this.listeTaches.put(true, new ArrayList<>());
        this.listeTaches.put(false, new ArrayList<>());
    }

    public List<TacheDTO> rechercherTousLesTaches() {
        List<TacheDTO> tacheDtoListe = new ArrayList<>();

        for(Boolean status: listeTaches.keySet()){
            for (Tache tache: listeTaches.get(status)){
                TacheDTO tacheDTO = TacheMapper.toDTO(tache);
                tacheDtoListe.add(tacheDTO);
            }
        }
        return tacheDtoListe;
    }
    public Tache ajouterTacheDeDTO(TacheDTO tachedto){
        Tache tache = TacheMapper.toEntite(tachedto);

        List<Tache> tacheList = listeTaches.get(tache.getAFaire());
        tacheList.add(tache);
        return tache;
    }
}
