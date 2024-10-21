package cal335.lab.todolists.controleur;

import cal335.lab.todolists.dto.TacheDTO;
import cal335.lab.todolists.mapper.TacheMapper;
import cal335.lab.todolists.modele.Tache;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cal335.lab.todolists.service.TacheService;
import java.io.IOException;

public class TacheControlleur implements HttpHandler {
    private final TacheService service;
    private final ObjectMapper objectMapper;

    public TacheControlleur(TacheService service) {
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }



    @Override
    public void handle(HttpExchange echange) throws IOException {
        String methode = echange.getRequestMethod();
        String chemin = echange.getRequestURI().getPath();

        if("GET".equalsIgnoreCase(methode) && "/taches".equals(chemin)){
            service.rechercherTousLesTaches(echange);
        }
        else if("POST".equalsIgnoreCase(methode) && "/taches".equals(chemin)){
            service.ajouterTache(echange);
        }
        else{
            echange.sendResponseHeaders(405, -1);
        }
    }

    public void getTaches(HttpExchange echange) throws IOException {
        service.rechercherTousLesTaches(echange);
    }
    public void creerTache(HttpExchange echange) throws IOException{
        TacheDTO tacheDTO = objectMapper.readValue(echange.getRequestBody(), TacheDTO.class);
        Tache tache = service.ajouterTachefromDTO(tacheDTO);

        TacheDTO tacheDTOAjoute = TacheMapper.toDTO(tache);
        String reponse = objectMapper.writeValueAsString(tacheDTOAjoute);
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(201, reponse.getBytes().length);
    }
}
