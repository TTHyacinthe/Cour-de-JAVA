package GestionDuPersonnel.Personnel;

import GestionDuPersonnel.Interfaces.IAugmentation;
import GestionDuPersonnel.Interfaces.ICalculSalire;
import GestionDuPersonnel.Interfaces.IGestionConges;
import GestionDuPersonnel.Contrat.Contrat;

import java.time.LocalDate;


public abstract class Personnels implements ICalculSalire, IGestionConges, IAugmentation {

    protected int id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected LocalDate dateEntree;
    private Contrat contrat;

    public Personnels(int id,
                      String matricule,
                      String nom,
                      String prenom,
                      LocalDate dateEntree,
                      Contrat contrat) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateEntree = dateEntree;
        this.contrat = contrat;
    }

    public int getId() {
        return id;
    }
    public String getMatricule() {
        return matricule;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public LocalDate getDateEntree() {
        return dateEntree;
    }
    public Contrat getContrat() {
        return contrat;
    }
}
