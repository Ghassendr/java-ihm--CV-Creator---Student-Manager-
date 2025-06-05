package DtaBase;

import IHM.Mytablemodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class GestionEtudiant extends JFrame {

    JTable Jt;
    Mytablemodel model;
    EtudiantManager manager;

    GestionEtudiant() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 600);
        this.setTitle("Gestion étudiant");

        // Champs de saisie
        JLabel labelNom = new JLabel("Nom:");
        JTextField fieldNom = new JTextField(15);

        JLabel labelPrenom = new JLabel("Prénom:");
        JTextField fieldPrenom = new JTextField(15);

        JLabel labelCIN = new JLabel("CIN:");
        JTextField fieldCIN = new JTextField(15);

        JLabel labelMoyenne = new JLabel("Moyenne:");
        JTextField fieldMoyenne = new JTextField(15);

        JButton submitButton = new JButton("Enregistrer");

        JPanel pn = new JPanel();
        pn.add(labelNom);
        pn.add(fieldNom);
        pn.add(labelPrenom);
        pn.add(fieldPrenom);
        pn.add(labelCIN);
        pn.add(fieldCIN);
        pn.add(labelMoyenne);
        pn.add(fieldMoyenne);
        pn.add(submitButton);

        this.add(pn, BorderLayout.NORTH);

        // Connexion et chargement initial des données
        String req = "SELECT * FROM etudiant";
        manager = new EtudiantManager();
        ResultSet rs = manager.selectEtudiant(req);
        model = new Mytablemodel(rs, manager);
        Jt = new JTable();
        Jt.setModel(model);
        this.add(new JScrollPane(Jt), BorderLayout.CENTER);

        // 📌 MENU CONTEXTUEL (clic droit)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem supprimerItem = new JMenuItem("Supprimer l'étudiant");
        popupMenu.add(supprimerItem);

        // Appliquer le menu à la table
        Jt.setComponentPopupMenu(popupMenu);
        Jt.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) showPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                int row = Jt.rowAtPoint(e.getPoint());
                if (row >= 0 && row < Jt.getRowCount()) {
                    Jt.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // ➕ Ajouter étudiant
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = fieldNom.getText();
                    String prenom = fieldPrenom.getText();
                    int cin = Integer.parseInt(fieldCIN.getText());
                    double moyenne = Double.parseDouble(fieldMoyenne.getText());
                    model.AjouterEtudiant(cin, nom, prenom, moyenne);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "CIN ou Moyenne invalide !");
                }
            }
        });

        // 🗑️ Supprimer via clic droit
        supprimerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Jt.getSelectedRow();
                if (selectedRow != -1) {
                    int cin = (int) model.getValueAt(selectedRow, 0); // CIN en 1ère colonne
                    model.supprimerEtudiant(cin);                    // Supprimer BDD
                    model.removeRow(selectedRow);                    // Supprimer UI
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new GestionEtudiant().setVisible(true);
    }
}
