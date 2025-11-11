package Etudiants;

public class Etudiant {
    // Attributs (encapsulés)
    private final String id;
    private  String nom;
    private double moyenne;
    private int age;
    // Attributs partager entre toutes les instance
    private static int compteur = 0;  // attribut static : compteur d'instances

    // Constructeurs
    //constructeur principale
    public Etudiant(String nom, double moyenne, int age) {
        this.id = "E-" + compteur++;
        this.nom = nom; // "this" référence à l'attribut de l'objet courant
        this.moyenne = moyenne;
        this.age = age;
        compteur++;  // incrementer le compteur static pour chaque nouvel objet
    }
    // constructeur secondaire
    public Etudiant(String nom) {  // constructeur surcharger : valeur par défaut
        this(nom, 0.0, 18); // appel au contructeur principal via "this(...)"
    }

    //Getters et Setters (encapsulation)
    public String getId() {
        return id;
    }
    public String getNom(){  // getter public : lecture du nom
        return nom;
    }
    public void setNom(String nom) {  // setter public : modification du nom
        if (nom != null && !nom.isBlank()) { // validation simple
            this.nom = nom;
        }
    }
    public double getMoyenne(){  // getter pour moyenne
        return moyenne;
    }
    // Setter pour la moyenne avec vérification
    public void setMoyenne(double moyenne) { // methode void : ne retourne rien
        if (moyenne >= 0.0 && moyenne <= 20.0) {
            this.moyenne = moyenne;
        }else  {
            throw new IllegalArgumentException("La moyenne doit être entre 0 e 20");
        }
    }
    public int getAge(){
        return age;
    }

    // Méthodes utilitaires
    public boolean aReussi(){ // methode qui retourne true or false
        return this.moyenne >= 10.0;
    }
    public void afficherInfos(){
        System.out.println("Etudiant : " + nom + ", age : " + age + ", moyenne : " + moyenne);
    }
    // methodes static : liée a la classe, pas a l'instance
    public static int getCompteur() {  //  méthode static pour lire le compteur
        return compteur;
    }
    // méthode protected (accessible dans la package et sous-classe)
    protected void methodeProtegee(){
        System.out.println("MethodeProtegee, visible aux sous-classe et au package");
    }
    @Override
    public String toString(){
        return "Etudiant[id=" + id + ", nom=" + nom + ", age=" + age + ", moyenne=" + moyenne + "]";
    }
}
