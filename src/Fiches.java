import java.io.Serializable;

@SuppressWarnings("serial")
public class Fiches implements Serializable {

    private String nom;
    private String numero;
    private String adresse;


    public String getNom() {
        return nom;
    }
    public String getNumero() {
        return numero;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public Fiches(String fnom, String fnum, String fadresse) {
        super();
        this.nom = fnom;
        this.numero = fnum;
        this.adresse = fadresse;
    }
}