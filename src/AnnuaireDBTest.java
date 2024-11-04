
    import java.util.Scanner;

    public class AnnuaireDBTest {

        public static void main(String[] args) {
            AnnuaireDB annuaire = new AnnuaireDB();
            Scanner scanner = new Scanner(System.in);
            int choix;

            do {
                System.out.println("\n--- Menu Annuaire ---");
                System.out.println("1. Ajouter un contact");
                System.out.println("2. Modifier un contact");
                System.out.println("3. Supprimer un contact");
                System.out.println("4. Afficher tous les contacts");
                System.out.println("5. Quitter");
                System.out.print("Entrez votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine(); // pour consommer le retour à la ligne

                switch (choix) {
                    case 1:
                        System.out.print("Entrez le nom du contact : ");
                        String nomAjout = scanner.nextLine();
                        annuaire.ajouteContact(nomAjout);
                        break;

                    case 2:
                        System.out.print("Entrez le nom du contact à modifier : ");
                        String nomModif = scanner.nextLine();
                        annuaire.modifierContact(nomModif);
                        break;

                    case 3:
                        System.out.print("Entrez le nom du contact à supprimer : ");
                        String nomSupprime = scanner.nextLine();
                        annuaire.supprimerContact(nomSupprime);
                        break;

                    case 4:
                        annuaire.afficherContact();
                        break;

                    case 5:
                        System.out.println("Fermeture de l'application...");
                        break;

                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } while (choix != 5);

            scanner.close();
        }
    }



