package cal335.lab.todolists.service;
import cal335.lab.todolists.modele.Tache;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class TacheService {
    private List<Tache> listeTaches;
    private ObjectMapper objectMapper = new ObjectMapper();

    public TacheService() {
        this.listeTaches = new ArrayList<>();
    }

//    public void ajouterTache(HttpExchange echange) throws IOException {
//        byte[] requestCorps = echange.getRequestBody().readAllBytes();
//        String corps = new String(requestCorps);
//        String[] parties = corps.split(",");
//
//        if(parties.length == 3){
//            String nom = parties[0];
//            String description = parties[1];
//            Boolean estFait = Boolean.parseBoolean(parties[2]);
//
//            Tache tache = new Tache(nom, description, estFait);
//            listeTaches.add(tache);
//
//            String reponse = "Tache ajoutée: " + tache;
//            echange.sendResponseHeaders(201, reponse.getBytes().length);
//
//            try(OutputStream os = echange.getResponseBody()){
//                os.write(reponse.getBytes());
//            }
//        }
//        else{
//            String reponse = "Invalide format. Utilise: Nom, Description, aFaire";
//            echange.sendResponseHeaders(400, reponse.getBytes().length);
//            try(OutputStream os = echange.getResponseBody()){
//                os.write(reponse.getBytes());
//            }
//        }
//    }

    public void rechercherTousLesTaches(HttpExchange echange) throws IOException {
        String reponse = listeTaches.toString();
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, reponse.getBytes().length);

        try(OutputStream os = echange.getResponseBody()){
            os.write(reponse.getBytes());
        }
    }
    public void ajouterTache(HttpExchange echange) throws IOException{
        byte[] requestCorps = echange.getRequestBody().readAllBytes();
        String corps = new String(requestCorps);
        Tache tache = objectMapper.readValue(corps, Tache.class);

        listeTaches.add(tache);
        String reponse = objectMapper.writeValueAsString(tache);
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(201, reponse.getBytes().length);

        try(OutputStream os = echange.getResponseBody()){
            os.write(reponse.getBytes());
        }
        catch(Exception e){
            String reponseErreur = "Invalide format. Espere Json";
            echange.sendResponseHeaders(400, reponseErreur.getBytes().length);
            try(OutputStream os = echange.getResponseBody()){
                os.write(reponseErreur.getBytes());
            }
        }
    }
}
