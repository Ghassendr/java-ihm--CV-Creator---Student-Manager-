package IHM;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EcouteurFocus extends FocusAdapter {
    private GestioProfile gp;
    private JTextField textField;
    private String placeholder;

    public EcouteurFocus(GestioProfile gp, JTextField textField, String placeholder) {
        this.gp = gp;
        this.textField = textField;
        this.placeholder = placeholder;
        textField.setText(placeholder);
        textField.setForeground(java.awt.Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(java.awt.Color.BLACK);
        }
        gp.lb_help.setText(" votre " + placeholder.toLowerCase() +" <10  caractere et ne contien pas des chiffres");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().trim().isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(java.awt.Color.GRAY);
        }
        gp.lb_help.setText("Help: ");
    }
}
