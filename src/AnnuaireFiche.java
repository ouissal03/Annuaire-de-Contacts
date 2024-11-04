import java.io.FileInputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class AnnuaireFiche implements Serializable {

    TreeMap<String, Fiches> Contact = new TreeMap<String, Fiches>();

    @SuppressWarnings("unchecked")
    public AnnuaireFiche() {
        super();
        try {
            FileInputStream fi = new FileInputStream("annuaire.obj");
            ObjectInputStream in = new ObjectInputStream(fi);
            Contact = (TreeMap<String, Fiches>) in.readObject();
            in.close();
            fi.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saiseFich(String nom) {
        if (!Contact.containsKey(nom)) {
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
            this.saveFiche();



        } else
            System.out.println("il Exist deja un contact avec ce nom");
    }

    public void modifierFich(String nom) {
        if (Contact.containsKey(nom)) {
            @SuppressWarnings("resource")
            Scanner s = new Scanner(System.in);
            String numero = "";
            do {
                System.out.println("Veuillez entrer le nouveaux numero de Contact :¤" + nom + "¤");
                numero = s.nextLine();
            } while (!Pattern.matches("^([+]2126|06)[0-9]{8}", numero));
            String adresse = "";
            do {
                System.out.println("Veuillez entrer le nouveaux adresse de Contact :¤" + nom + "¤");
                adresse = s.nextLine();
            } while (adresse.equals("") || String.valueOf(adresse.charAt(0)).equals("\n"));
            Fiches f = new Fiches(nom, numero, adresse);
            Contact.put(nom, f);
            this.saveFiche();


        } else
            System.out.println("il n'existe aucun contact avec ce nom");
    }

    public void supprimerFich(String nom) {
        if (Contact.containsKey(nom)) {
            @SuppressWarnings("resource")
            Scanner s = new Scanner(System.in);
            System.out.println("Voulez-vous supprimer ce contact?(Enter 'oui' pour confirmation)");
            if (s.nextLine().toLowerCase().equals("oui")) {
                Contact.remove(nom);
                System.out.println("Le contact a été supprimer avec succèes");
                this.saveFiche();


            }
        } else
            System.out.println("Il n'existe aucun contact avec ce nom");
    }

    public void saveFiche() {

        try {

            // if(!Contact.containsKey(f.getNom())) {
            FileOutputStream fo = new FileOutputStream("annuaire.obj");
            ObjectOutputStream out = new ObjectOutputStream(fo);
//			fo.write(f.getNom().getBytes());
//			fo.write(",".getBytes());
//			fo.write(f.getNumero().getBytes());
//			fo.write(",".getBytes());
//			fo.write(f.getAdresse().getBytes());
//			fo.write('\n');
            out.writeObject(Contact);
            out.close();
            fo.close();
            System.out.println("Le Contact a été Enregistrer avec succèes");
//			}
//			else
//				System.out.println("il exist deja un contact evec ce nom");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficheFiche(String nom) {
        if (Contact.containsKey(nom)) {
            System.out.println("Nom : " + Contact.get(nom).getNom() + "|  Numero : " + Contact.get(nom).getNumero()
                    + "|  Adresse : " + Contact.get(nom).getAdresse());
        } else
            System.out.println("Ce fiche n'existe pas dans la base de données");
//		try {
//			FileOutputStream fo = new FileOutputStream(nomFile);
//			fo.write(f.getNom().getBytes());
//			fo.write(",".getBytes());
//			fo.write(f.getNumero().getBytes());
//			fo.write(",".getBytes());
//			fo.write(f.getAdresse().getBytes());
//			fo.write('\n');
//			fo.close();
//			System.out.println("Le Fiche a été ajouter avec succèes");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }

    public void afficheContacts() {
        if (!Contact.isEmpty()) {
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            System.out.println(
                    "        nom        |     numero     |                     adresse                      |");
            System.out.println(
                    "----------------------------------------------------------------------------------------");
            for (Fiches f : Contact.values()) {
                System.out.println(String.format("%-19s|", f.getNom()) + String.format("%-16s|", f.getNumero())
                        + String.format("%-50s|", f.getAdresse()));
            }
            System.out.println(
                    "----------------------------------------------------------------------------------------");
        } else
            System.out.println("Pas De Contacts");
    }

}
