import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 12345;

        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket(serverHost, serverPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                // Demande à l'utilisateur les informations sur la personne
                System.out.print("Entrez l'âge de la personne : ");
                int age = scanner.nextInt();
                scanner.nextLine(); // consommer le saut de ligne
                System.out.print("Entrez le nom de la personne : ");
                String nom = scanner.nextLine();

                // Création de l'objet Personne et envoi au serveur
                personne personne = new personne(age, nom);
                outputStream.writeObject(personne);
                outputStream.flush();

                // Lecture de l'identificateur du client retourné par le serveur
                int clientId = inputStream.readInt();
                System.out.println("Identificateur du client : " + clientId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
