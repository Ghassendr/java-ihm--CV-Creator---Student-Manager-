package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GestioProfile extends JInternalFrame {

    JLabel lb_nom, lb_prenom, lb_pseudo, lb_help;
    JTextField tf_nom, tf_prenom, tf_pseudo;
    JButton btnEnregister;
    JPanel pn;
    JSplitPane jsp;
    JTabbedPane jtp;
    DefaultListModel<String> model;
    JList<String> jl;
//global bech naccedilhom ml ecouteur popup
    JMenuItem item_modifier=new JMenuItem("Modifier");

    JMenuItem item_supprimer=new JMenuItem("supprimer");

    JMenuItem item_suprimertous=new JMenuItem("Supprimer tous");

ArrayList<FormPannel> listpanne=new ArrayList<>();
    public GestioProfile() {
        this.setTitle("Gestion Profile");
        this.setSize(1000, 700);
        this.setLocation(20, 20);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Création des composants
        lb_nom = new JLabel("Nom:");
        lb_prenom = new JLabel("Prénom:");
        lb_pseudo = new JLabel("Pseudo:");
        lb_help = new JLabel("Help"); // Nouveau label pour éviter la confusion

        tf_nom = new JTextField(15);
        tf_prenom = new JTextField(15);
        tf_pseudo = new JTextField(15);

        btnEnregister = new JButton("Enregistrer");

        // Panel avec FlowLayout
        pn = new JPanel(new FlowLayout());
        pn.add(lb_nom);
        pn.add(tf_nom);
        pn.add(lb_prenom);
        pn.add(tf_prenom);
        pn.add(lb_pseudo);
        pn.add(tf_pseudo);
        pn.add(btnEnregister);

        // Initialisation de la liste et du modèle
        model = new DefaultListModel<>();
        jl = new JList<>(model);
        jl.setPreferredSize(new Dimension(120, 30));

        // Création du SplitPane
        jsp = new JSplitPane();
        jsp.setLeftComponent(new JScrollPane(jl)); // Ajout d'un JScrollPane pour éviter les problèmes d'affichage

        // Création des onglets
        jtp = new JTabbedPane();
       // jtp.addTab("Tab 1", new JPanel());
        //jtp.addTab("Tab 2", new JPanel());
        jsp.setRightComponent(jtp);


        this.add(pn, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        this.add(lb_help, BorderLayout.SOUTH);


        btnEnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = tf_pseudo.getText().trim();


                boolean pseudoExiste = false;
                for (Profil profil : Data.List_profil) {
                    if (profil.getPseudo().equalsIgnoreCase(pseudo)) {
                        pseudoExiste = true;
                        break;
                    }
                }

                if (pseudoExiste) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ce pseudo est déjà utilisé. Veuillez en choisir un autre.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else if (pseudo.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Le champ pseudo ne peut pas être vide.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {

                    model.addElement(pseudo);
                    Profil p = new Profil(tf_nom.getText(), tf_prenom.getText(), pseudo);
                    Data.List_profil.add(p);


                    tf_nom.setText("");
                    tf_prenom.setText("");
                    tf_pseudo.setText("");
                }
            }
        });



        lb_nom.addMouseListener(new EcouteurLabel());
        lb_prenom.addMouseListener(new EcouteurLabel());
        lb_pseudo.addMouseListener(new EcouteurLabel());



        tf_nom.addFocusListener(new EcouteurFocus(this, tf_nom, "Nom"));
        tf_prenom.addFocusListener(new EcouteurFocus(this, tf_prenom, "Prénom"));
        tf_pseudo.addFocusListener(new EcouteurFocus(this, tf_pseudo, "Pseudo"));

   //mouse listner pour tt
   jl.addMouseListener(new MouseAdapter() {
       @Override
       public void mouseClicked(MouseEvent e) {
           super.mouseClicked(e);
           // this -> pour la class anonime adapter
           jl.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   super.mouseClicked(e);

                   if (e.getClickCount() == 2) {
                       String pseudo = jl.getSelectedValue();

                       if (pseudo != null) {

                           boolean existeDeja = false;
                           for (int i = 0; i < jtp.getTabCount(); i++) {
                               if (jtp.getTitleAt(i).equals(pseudo)) {
                                   jtp.setSelectedIndex(i);
                                   existeDeja = true;
                                   break;
                               }
                           }

                           if (!existeDeja) {

                               FormPannel pn = new FormPannel(Data.rechercheselonpseudo(pseudo));
                               listpanne.add(pn);
                               jtp.addTab(pseudo, pn);
                               jtp.setSelectedComponent(pn);
                           }
                       }
                   }
               }
           });


           if (e.getButton()==MouseEvent.BUTTON3 ){
               //click droite

               JPopupMenu popupMenu=new JPopupMenu();

               popupMenu.add(item_modifier);
               popupMenu.add(item_supprimer);
               popupMenu.add(item_suprimertous);
               popupMenu.show(jl,e.getX(),e.getY());
               item_modifier.addActionListener(new EcouteurPopupMenu(GestioProfile.this));

               item_supprimer.addActionListener(new EcouteurPopupMenu(GestioProfile.this));
               item_suprimertous.addActionListener(new EcouteurPopupMenu(GestioProfile.this));
               // bech njibha taht l element selectionner toul
               // dupricated show bare khter bech ynhiwha fel version jeya de java



           }



       }
   });

    }

}
