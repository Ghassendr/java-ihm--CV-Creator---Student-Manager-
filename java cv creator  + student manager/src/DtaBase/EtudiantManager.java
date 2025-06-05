package DtaBase;

import java.sql.*;

public class EtudiantManager  {

    Connection conn = null;
    Statement st = null;

    EtudiantManager() {
        try {
            Class.forName(Config.NomDriver);
            System.out.println("driver ok ...");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur pas de driver : " + e.getMessage());
        }

        // Connection à la base
        try {
            conn = DriverManager.getConnection(Config.UrlDataBase, Config.UserName, Config.Password);
            System.out.println("connected !!");
            st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur de connection : " + e.getMessage());
        }
    }

    public int ajoutEtudiant(int cin, String nom, String prenom, double moyenne) {
        int a = 0;
        String req = "insert into etudiant values (?,?,?,?)";
        try {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(req);
                // Les indices en Java SQL commencent toujours à 1
                ps.setInt(1, cin);
                ps.setString(2, nom);
                ps.setString(3, prenom);
                ps.setDouble(4, moyenne);

                a = ps.executeUpdate();
                System.out.println("added");
            }
        } catch (SQLException e) {
            System.out.println("erreur insertion : " + e.getMessage());
        }
        return a;
    }

    ResultSet selectEtudiant(String requetteselection) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(requetteselection);
        } catch (SQLException e) {
            System.out.println("erreur de selection : " + e.getMessage());
        }
        return rs;
    }

    void AfficheResult(ResultSet rs) {
        try {
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int nbcol = rsmd.getColumnCount();

                for (int i = 1; i <= nbcol; i++) {
                    System.out.print(rsmd.getColumnName(i) + "\t");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= nbcol; i++) {
                        System.out.print(rs.getObject(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("erreur de selection : " + e.getMessage());
        }
    }

   public int supprimerEtudiant(int cin) {
        int a = 0;
        String req = "DELETE FROM etudiant WHERE cin = ?";
        try {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setInt(1, cin);

                a = ps.executeUpdate();
                System.out.println("Étudiant supprimé avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
        return a;
    }

    public int modifierEtudiant(int cin, String nom, String prenom, double moyenne) {
        int a = 0;
        String req = "UPDATE etudiant SET nom = ?, prenom = ?, moyenne = ? WHERE cin = ?";
        try {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(req);
                ps.setString(1, nom);       // Correct : nom est le 1er paramètre dans la requête
                ps.setString(2, prenom);    // Correct : prenom est le 2ème paramètre
                ps.setDouble(3, moyenne);   // Correct : moyenne est le 3ème paramètre
                ps.setInt(4, cin);          // Correct : cin est le critère WHERE donc 4ème

                a = ps.executeUpdate();
                System.out.println("Étudiant modifié avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification : " + e.getMessage());
        }
        return a;
    }



}
