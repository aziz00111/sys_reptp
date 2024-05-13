import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        //Saisit de Nom et Port du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        // communication avec le serveur
        try {
            // Résolution du nom d'hôte en une adresse IP
            InetAddress adr = InetAddress.getByName(host);
            // Création d'une socket et connexion au serveur
            Socket socket = new Socket(adr, port);
            // Ouverture des flux de sortie et d'entrée
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Envoi d'un objet de type String au serveur
            output.writeObject(new String("ma première socket"));
            // Lecture de l'objet reçu du serveur
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
