
import java.util.Scanner;

    public class AnnuaireFicheTest {

        public static void main(String[] args) {
            AnnuaireFiche annuaire = new AnnuaireFiche();
            Scanner scanner = new Scanner(System.in);
            int choix;

            do {
                System.out.println("\n--- Menu Annuaire ---");
                System.out.println("1. Ajouter un contact");
                System.out.println("2. Modifier un contact");
                System.out.println("3. Supprimer un contact");
                System.out.println("4. Afficher un contact");
                System.out.println("5. Afficher tous les contacts");
                System.out.println("6. Quitter");
                System.out.print("Entrez votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine(); // Consomme le retour à la ligne

                switch (choix) {
                    case 1:
                        System.out.print("Entrez le nom du contact à ajouter : ");
                        String nomAjout = scanner.nextLine();
                        annuaire.saiseFich(nomAjout);
                        break;

                    case 2:
                        System.out.print("Entrez le nom du contact à modifier : ");
                        String nomModif = scanner.nextLine();
                        annuaire.modifierFich(nomModif);
                        break;

                    case 3:
                        System.out.print("Entrez le nom du contact à supprimer : ");
                        String nomSupprime = scanner.nextLine();
                        annuaire.supprimerFich(nomSupprime);
                        break;

                    case 4:
                        System.out.print("Entrez le nom du contact à afficher : ");
                        String nomAffiche = scanner.nextLine();
                        annuaire.afficheFiche(nomAffiche);
                        break;

                    case 5:
                        annuaire.afficheContacts();
                        break;

                    case 6:
                        System.out.println("Fermeture de l'annuaire...");
                        break;

                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (choix != 6);

            scanner.close();
        }
    }

