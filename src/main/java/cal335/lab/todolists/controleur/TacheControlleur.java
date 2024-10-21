package cal335.lab.todolists.controleur;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cal335.lab.todolists.service.TacheService;
import java.io.IOException;

public class TacheControlleur implements HttpHandler {
    private final TacheService service;

    public TacheControlleur(TacheService service) {
        this.service = service;
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
}
