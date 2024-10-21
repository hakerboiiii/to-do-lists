package cal335.lab.todolists;
import cal335.lab.todolists.modele.Tache;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {

//        Serialisation
        Tache tacheSerialise = new Tache("Faire les courses", "Acheter du lait, du pain et des oeufs", true);

        ObjectMapper objectMapperSerialise = new ObjectMapper();

        String jsonSerialise = objectMapperSerialise.writeValueAsString(tacheSerialise);
        System.out.println("Json serialise " + jsonSerialise);


        System.out.println("-------------------------------------------------");
        // Deserialisation
        try {
            String json = "{\"id\":1,\"nom\":\"Task1\",\"description\":\"Description1\",\"aFaire\":true}";;
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert JSON string to Task object
            Tache tache = objectMapper.readValue(json, Tache.class);
            System.out.println("Deserialise tache: " + tache);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
