package IHM;

import javax.swing.*;
import java.awt.*;

public class Bureau extends JFrame {
    JMenuBar menuBar;
    JMenu menuTpString;
    JMenu menuTPSBase;
    JMenuItem TP1,TP2;
    JDesktopPane disctop;
    public Bureau(){
        this.setTitle("TP JAVA");
        this.setSize(1200,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar=new JMenuBar() ;
        menuTpString=new JMenu("Tpstring");
        menuTPSBase=new JMenu("TPBASE");
        menuBar.add(menuTpString);
        menuBar.add(menuTPSBase);
        TP1=new JMenuItem("TP 1");
        TP2=new JMenuItem("TP 2");
        menuTpString.add(TP1);  menuTpString.add(TP2);


        this.add(menuBar);
        disctop=new JDesktopPane();
        this.add(disctop);
        GestioProfile G=new GestioProfile();
        disctop.add(G);




    }
}
