import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnuaireDBGUI extends JFrame {

    private AnnuaireDB annuaire;

    public AnnuaireDBGUI() {
        annuaire = new AnnuaireDB();
        initComponents();
    }

    private void initComponents() {
        // Configurer la fenêtre principale
        setTitle("Annuaire");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Créer les boutons
        JButton btnAjouter = new JButton("Ajouter Contact");
        JButton btnModifier = new JButton("Modifier Contact");
        JButton btnSupprimer = new JButton("Supprimer Contact");
        JButton btnAfficher = new JButton("Afficher Contacts");

        // Créer un panneau pour les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(btnAfficher);

        // Ajouter le panneau au centre de la fenêtre
        add(panel, BorderLayout.CENTER);

        // Action pour le bouton Ajouter
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Entrez le nom du contact :");
                if (nom != null && !nom.isEmpty()) {
                    annuaire.ajouteContact(nom);
                }
            }
        });

        // Action pour le bouton Modifier
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Entrez le nom du contact à modifier :");
                if (nom != null && !nom.isEmpty()) {
                    annuaire.modifierContact(nom);
                }
            }
        });

        // Action pour le bouton Supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Entrez le nom du contact à supprimer :");
                if (nom != null && !nom.isEmpty()) {
                    annuaire.supprimerContact(nom);
                }
            }
        });

        // Action pour le bouton Afficher
        btnAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                StringBuilder contacts = new StringBuilder();

                // Récupérer tous les contacts
                annuaire.afficherContact();
                // Ajoutez votre méthode pour afficher ici

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(450, 300));
                JOptionPane.showMessageDialog(null, scrollPane, "Liste des Contacts", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnnuaireDBGUI().setVisible(true);
            }
        });
    }
}
