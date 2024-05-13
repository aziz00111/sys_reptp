import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Serveur {
    public static void main(String[] args) {
        int port = 12345;
        int Carburant;
        Scanner scanner = new Scanner(System.in);
        try {
            // Création d'une socket serveur
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                // Attente de la connexion d'un client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté");

                // Lecture de l'objet voiture envoyé par le client
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                voiture car = (voiture) inputStream.readObject();
                System.out.println("Voiture reçue : " + car.type +" "+car.model);

                // Fixation de la quantité de carburant
                System.out.print("qte de carburant: ");
                Carburant= scanner.nextInt();
                car.setCarburant(Carburant); // Vous pouvez fixer la quantité de carburant souhaitée ici

                // Fermeture des flux
                inputStream.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
