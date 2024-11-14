package cal335.lab.todolists.controleur;

import cal335.lab.todolists.dto.TacheDTO;
import cal335.lab.todolists.mapper.TacheMapper;
import cal335.lab.todolists.modele.Tache;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cal335.lab.todolists.service.TacheService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
            this.rechercherTaches(echange);
        }
        else if("POST".equalsIgnoreCase(methode) && "/taches".equals(chemin)){
            this.ajouterTache(echange);
        }
        else{
            echange.sendResponseHeaders(405, -1);
        }
    }

    public void rechercherTaches(HttpExchange echange) throws IOException {
        List<TacheDTO> tacheDtolist = service.rechercherTousLesTaches();
        String reponse = objectMapper.writeValueAsString(tacheDtolist);

        sendResponse(echange, reponse, 200);

    }
    public void ajouterTache(HttpExchange echange) throws IOException{
        byte[] requestBody = echange.getRequestBody().readAllBytes();
        String corps = new String(requestBody);

        try{
            TacheDTO tacheDTO = objectMapper.readValue(corps, TacheDTO.class);
            Tache tache = service.ajouterTacheDeDTO(tacheDTO);

            TacheDTO tacheDTOAjoute = TacheMapper.toDTO(tache);
            String reponse = objectMapper.writeValueAsString(tacheDTOAjoute);
            sendResponse(echange, reponse, 201);
        }
        catch(Exception e){
            String reponse = "{\"message\": \"Erreur lors de la création de la tâche\"}";
            sendResponse(echange, reponse, 400);
        }
    }

    private void sendResponse(HttpExchange echange, String reponse, int statusCode) throws IOException{
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(statusCode, reponse.getBytes().length);

        try(OutputStream os = echange.getResponseBody()){
            os.write(reponse.getBytes());
        }
    }
}
