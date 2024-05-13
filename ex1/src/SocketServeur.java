import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);
        //Saisit du port
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }
        try {
            // Création du ServerSocket
            ServerSocket serverSocket = new ServerSocket(port);
            // Attente d'une connexion entrante
            Socket socket = serverSocket.accept();
            // Ouverture des flux de sortie et d'entrée
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Lecture de l'objet reçu
            String chaine = (String) input.readObject();
            System.out.println(" recu : " + chaine);
            // Affichage des informations sur le client
            System.out.println(" ca vient de : " + socket.getInetAddress() + ":" + socket.getPort());
            // Envoi d'une réponse au client
            output.writeObject(new String("bien recu"));
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
