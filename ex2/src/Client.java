import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";
        //String type,model;
        int serverPort = 12345;

        try {
            // Création d'une nouvelle voiture
            Scanner scanner = new Scanner(System.in);
            System.out.print("Type de voiture : ");
            String type = scanner.nextLine();
            System.out.print("Modèle de voiture : ");
            String model = scanner.nextLine();
            voiture car = new voiture(type,model);

            // Connexion au serveur
            Socket socket = new Socket(serverHost, serverPort);

            // Envoi de la voiture au serveur
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(car);

            // Fermeture des flux et de la connexion
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
