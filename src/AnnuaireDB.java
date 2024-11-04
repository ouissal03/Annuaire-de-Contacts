import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;


public class AnnuaireDB {

    private DB_Connection conn = new DB_Connection();

    public AnnuaireDB() {
        super();
    }

    public boolean existContact(String nom) {
        try {
            conn.Connecter();
            Statement st = conn.con.createStatement();
            ResultSet res = st.executeQuery("SELECT count(*) FROM Fiche where nom='" + nom + "'");

            if (res.next() && !res.getString(1).equals("0")) {
                res.close();
                st.close();
                return true;
            }
            res.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void ajouteContact(String nom) {
        try {
            conn.Connecter();
            if (!existContact(nom)) {
                Scanner s = new Scanner(System.in);
                String numero = "";
                do {
                    System.out.println("Veuillez entrer le numero de Contact :¤" + nom + "¤");
                    numero = s.nextLine();
                } while (!Pattern.matches("^([+]2126|06)[0-9]{8}", numero));
                String adresse = "";
                do {
                    System.out.println("Veuillez entrer l'adresse de Contact :¤" + nom + "¤");
                    adresse = s.nextLine();
                } while (adresse.equals("") || String.valueOf(adresse.charAt(0)).equals("\n"));
                Fiches f = new Fiches(nom, numero, adresse);
                Statement st = conn.con.createStatement();
                st.execute("insert into Fiche values('" + f.getNom() + "','" + f.getNumero() + "','" + f.getAdresse()
                        + "');");
                st.close();
                System.out.println("Le Contact a été ajouter avec succèes");
            } else
                System.out.println("Le Contact est deja existe");
            conn.Deconnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierContact(String nom) {
        try {
            conn.Connecter();
            if (existContact(nom)) {
                Statement st = conn.con.createStatement();
                Scanner s = new Scanner(System.in);
                String numero = "";
                do {
                    System.out.println("Veuillez entrer le numero de Contact :¤" + nom + "¤");
                    numero = s.nextLine();
                } while (!Pattern.matches("^([+]2126|06)[0-9]{8}", numero));
                String adresse = "";
                do {
                    System.out.println("Veuillez entrer l'adresse de Contact :¤" + nom + "¤");
                    adresse = s.nextLine();
                } while (adresse.equals("") || String.valueOf(adresse.charAt(0)).equals("\n"));
                Fiches f = new Fiches(nom, numero, adresse);
                st.executeUpdate(
                        "update Fiche set numero='" + f.getNumero() + "', adresse='" + f.getAdresse() + "' where nom='" + nom + "'");
                st.close();
                System.out.println("Le contact a été modifier avec succèes");
            } else
                System.out.println("ce nom de contact n'existe pas dans la base");
            conn.Deconnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerContact(String nom) {
        try {
            conn.Connecter();
            if (existContact(nom)) {
                Statement st = conn.con.createStatement();
                st.execute("delete from Fiche where nom='" + nom + "'");
                st.close();
                System.out.println("Le Contact a été supprimer avec succèes");
            } else
                System.out.println("ce nom de contact n'existe pas dans la base");
            conn.Deconnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherContact() {
        conn.Connecter();
        String nom = null, numero = null, adresse = null;

        try {
            Statement st = conn.con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Fiche");
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            System.out.println(
                    "        nom        |     numero     |                     adresse                      |");
            System.out.println(
                    "----------------------------------------------------------------------------------------");

            while (res.next()) {
                nom = res.getString("nom");
                numero =  res.getString("numero");
                adresse = res.getString("adresse");
                System.out.println(String.format("%-19s|", nom) + String.format("%-16s|", numero)
                        + String.format("%-50s|", adresse));
            }
            System.out.println(
                    "----------------------------------------------------------------------------------------");

            res.close();
            st.close();
            conn.Deconnecter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}