import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Serveur {
    public static void main(String[] args) {
        int port = 12345;
        AtomicInteger clientIdCounter = new AtomicInteger(0);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur démarré.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouveau client connecté.");

                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                while (true) {
                    // Lecture de l'objet Personne envoyé par le client
                    personne personne = (personne) inputStream.readObject();
                    if (personne == null) {
                        break; // Fin de la transmission des données
                    }
                    System.out.println("Personne reçue : " + personne.getAge() + " ans, " + personne.getNom());

                    // Génération de l'identificateur du client et envoi au client
                    int clientId = clientIdCounter.incrementAndGet();
                    outputStream.writeInt(clientId);
                    outputStream.flush();
                }

                // Fermeture des flux et de la connexion
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
