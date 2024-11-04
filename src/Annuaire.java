import java.util.TreeMap;
import java.util.regex.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class Annuaire {
    TreeMap<String, Fiches> Contact = new TreeMap<String, Fiches>();

    public void commande() {
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        String cmd = "";
        boolean er = true;
        do {
            System.out.println("Veuillez choisir une des commandes suivantes :+nom, ?nom, ! ou . :");
            try {
                cmd = s.nextLine();
                er = true;
                switch (cmd.substring(0, 1)) {
                    case "+":
                        if (!Contact.containsKey(cmd.substring(1)))
                            saiseFich(cmd.substring(1));
                        else
                            System.out.println("il Existe deja un contact avec ce nom");
                        break;
                    case "?":
                        if (Contact.containsKey(cmd.substring(1)))
                            affichFich(cmd.substring(1));
                        else
                            System.out.println("il n'existe aucun Contact avec se nom !!");
                        break;
                    case "!":
                        affichContacts();
                        break;
                    case ".":
                        er = false;
                        break;
                    default:
                        System.out.println("La commande incorrect !!");
                        er = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("s'il vous plait veuillez choisir une commande");
            }
        } while (er);
    }

    private void saiseFich(String nom) throws SQLException {
        @SuppressWarnings("resource")
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
        Contact.put(nom, f);
        System.out.println("Le Fiche a été ajoutée avec succès");

    }
    private void affichFich(String nom) {
        Fiches f = Contact.get(nom);
        System.out.println("nom : " + nom + "|  numero : " + f.getNumero() + "|  adresse : " + f.getAdresse());
    }

    private void affichContacts() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("        nom        |     numero           |                     adresse                |");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Fiches f : Contact.values()) {
            System.out.println(String.format("%-19s|", f.getNom()) + String.format("%-16s|", f.getNumero())
                    + String.format("%-50s|", f.getAdresse()));
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

}