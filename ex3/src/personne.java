import java.io.Serializable;

public class personne implements Serializable {
    private int age;
    private String nom;

    public personne(int age, String nom) {
        this.age = age;
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }
}
