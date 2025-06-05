package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MAF extends JFrame implements MouseListener {
    JButton b1, b2;
    JLabel l1, l2;

    public MAF() {
        //ihm
        //Evenement
        //b1 ,b2 source devenemnt
        b1.addActionListener(new Ecouteur());
        b2.addActionListener(new Ecouteur());
        l1.addMouseListener(new Ecouteur());
        l2.addMouseListener(new Ecouteur());
        b1.addMouseListener(this); //
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public class Ecouteur implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                l1.setBackground(Color.red);
            }
            if (e.getSource() == b2) {
                l1.setBackground(Color.blue);
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == l1) {
                l1.setBackground(Color.yellow);
            }
            if (e.getSource() == l2) {
                l2.setBackground(Color.green);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
