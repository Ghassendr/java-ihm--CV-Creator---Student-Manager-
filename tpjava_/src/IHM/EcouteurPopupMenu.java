package IHM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurPopupMenu implements ActionListener {

GestioProfile gp;
    public EcouteurPopupMenu(GestioProfile gestioProfile) {
        this.gp=gestioProfile;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int index = gp.jl.getSelectedIndex();

        if (e.getSource() == gp.item_modifier) {

            if (index >= 0 && index < Data.List_profil.size()) {
                Profil profil = Data.List_profil.get(index);


                JTextField tfNewNom = new JTextField(profil.getNom());
                JTextField tfNewPrenom = new JTextField(profil.getPrenom());

                Object[] message = {
                        "Nom:", tfNewNom,
                        "Prénom:", tfNewPrenom
                };

                int option = JOptionPane.showConfirmDialog(
                        gp, message, "Modifier le profil", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {

                    profil.setNom(tfNewNom.getText());
                    profil.setPrenom(tfNewPrenom.getText());


                    if (index < gp.listpanne.size()) {
                        FormPannel panel = gp.listpanne.get(index);
                        panel.updateFields(profil);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(gp, "Aucun profil sélectionné ou la liste est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == gp.item_supprimer) {
            if (index >= 0 && index < Data.List_profil.size()) {
                Data.List_profil.remove(index);
                gp.model.remove(index);
                if (index < gp.jtp.getTabCount()) {
                    gp.jtp.removeTabAt(index);
                }
            } else {
                JOptionPane.showMessageDialog(gp, "Aucun profil sélectionné pour suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == gp.item_suprimertous) {

            int confirm = JOptionPane.showConfirmDialog(gp, "Voulez-vous vraiment supprimer tous les profils ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Data.List_profil.clear();
                gp.model.removeAllElements();
                gp.jtp.removeAll();
            }
        }
    }


}
