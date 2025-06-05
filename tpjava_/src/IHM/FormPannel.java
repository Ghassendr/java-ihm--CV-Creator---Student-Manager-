package IHM;

import javax.swing.*;

public class FormPannel extends JPanel {
    JLabel lb_bien ,lb_nom,lb_prenom;
    JButton btn_val;
    FormPannel (Profil p){
        lb_bien=new JLabel("Bien Venue");
        lb_nom=new JLabel(p.nom);
        lb_prenom=new JLabel(p.prenom);
        btn_val=new JButton("valider");



        this.add(lb_bien);
        this.add(lb_nom);
        this.add(lb_prenom);


    }
    public void updateFields(Profil p) {
        this.lb_nom.setText(p.getNom());
        this.lb_prenom.setText(p.getPrenom());
    }

}
