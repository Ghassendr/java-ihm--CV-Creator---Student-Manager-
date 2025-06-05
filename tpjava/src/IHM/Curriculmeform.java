package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Curriculmeform extends JFrame {
    JButton btnquitter ,btnval ;
    JLabel labeltite ,lb_nom;
    JTextField tf_nom ;
    Curriculmeform (){


        this.setTitle("curriculme vetae");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new FlowLayout());
        ///
        labeltite=new JLabel("CV");
        lb_nom=new JLabel("nom");
        labeltite.setForeground(Color.WHITE);
        labeltite.setFont(new Font(Font.SANS_SERIF , Font.ITALIC| Font.BOLD, 32));
        labeltite.setOpaque(true );
        labeltite.setBackground(Color.green);
        labeltite.setPreferredSize(new Dimension(800 ,100));

        tf_nom=new JTextField((15));



        this.add(labeltite);
        this.add(lb_nom);
this.add(tf_nom);



         btnval=new JButton("valider") ;
         this.add(btnval);
        btnquitter=new JButton("quitter") ;
        this.add(btnquitter);
// event
        btnquitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });


        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ecri ficher
                String nom=tf_nom.getText() ;
                File f=new File("cv.html");
                try {
                    FileWriter fw=new FileWriter(f ,false);
                    fw.write("<html> <title> cv </title> <body> <h1>"+ nom +" </h1> </body> <html>" );
                    fw.close();
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }


}
