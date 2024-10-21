package cal335.lab.todolists;
import cal335.lab.todolists.controleur.TacheControlleur;
import cal335.lab.todolists.service.TacheService;
import cal335.lab.todolists.modele.Tache;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationServeurTache {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

            HttpServer serveur = HttpServer.create(new InetSocketAddress(PORT), 0);
            TacheService service = new TacheService();
            serveur.createContext("/taches", new TacheControlleur(service));
            serveur.setExecutor(null);
            serveur.start();
            System.out.println("Serveur est démarré sur le port " +  PORT);

    }
}
