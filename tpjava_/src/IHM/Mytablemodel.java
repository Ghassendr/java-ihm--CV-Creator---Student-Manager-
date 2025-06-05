package IHM;

import DtaBase.EtudiantManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mytablemodel extends AbstractTableModel {
    ArrayList<Object []> data = new  ArrayList<Object []>() ;
    ResultSet  rs;
    EtudiantManager manager;
    ResultSetMetaData  rsmd;

    public Mytablemodel(ResultSet  rs, EtudiantManager manager)  {
        this.manager=manager;
        this.rs=rs;
        try {
            rsmd=rs.getMetaData();

            while (rs.next()){
            Object[] ligne= new Object[rsmd.getColumnCount()];
              for (int i=0 ; i <ligne.length;i++){
ligne[i] =rs.getObject(i+1);
              }
              data.add(ligne);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return  data.size();
    }

    @Override
    //ren,voi le nombre de colonne

    public int getColumnCount() {

        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return data.get(rowIndex)[columnIndex] ;
    }


    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      if (getColumnName(columnIndex).equalsIgnoreCase("moyenne") ){
        return true;}
      else return false;
    }

    int NameColumnToIndex(String nameColumn){
        for (int i=0 ;i<getColumnCount();i++){
            if (getColumnName(i).equalsIgnoreCase(nameColumn)){
                return i;
            }
        }return -1;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int cin =(int) getValueAt(rowIndex,NameColumnToIndex("cin"));
        String nom =(String) getValueAt(rowIndex,NameColumnToIndex("nom"));
        String prenom = (String) getValueAt(rowIndex,NameColumnToIndex("prenom"));
        double moyenne =Double.parseDouble(aValue+"") ;


        int a =manager.modifierEtudiant(cin , nom , prenom,moyenne);
if (a>0){
     data.get(rowIndex)[columnIndex]=aValue;}

    }
    public void AjouterEtudiant (int cin ,String nom, String prenom, double moyenne){
        int a= manager.ajoutEtudiant(cin,  nom,  prenom,  moyenne);
   if (a>0){


        data.add(new Object[]{cin , nom , prenom,moyenne});
        fireTableDataChanged();}
   else {
       JOptionPane.showMessageDialog(null,"erreur lors de linsertion");
   }
    }

    public void supprimerEtudiant(int cin) {
        int a = manager.supprimerEtudiant(cin); // Supprimer de la base
        if (a <= 0) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'étudiant avec CIN: " + cin);
        }
    }

    public void removeRow(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < data.size()) {
            data.remove(selectedRow); // Supprimer de la mémoire
            fireTableDataChanged();   // Rafraîchir la vue
        }
    }}


