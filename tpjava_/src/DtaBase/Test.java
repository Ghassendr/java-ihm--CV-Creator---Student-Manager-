package DtaBase;
import java.sql.ResultSet;
public class Test {
    public static void main(String[] args) {
        EtudiantManager manager = new EtudiantManager();
        String requette = "select * from etudiant";
        ResultSet rs = manager.selectEtudiant(requette);
        manager.AfficheResult(rs);
        //manager.supprimerEtudiant(1);
        // rs = manager.selectEtudiant(requette);
       manager.AfficheResult(rs);
        int updated = manager.modifierEtudiant(111, "wael", "aaa", 16.8);
        if (updated > 0) {
        System.out.println("Mise à jour réussie !");
        requette = "select * from etudiant";
        rs = manager.selectEtudiant(requette);
        manager.AfficheResult(rs);
        } else {
        System.out.println("Échec de la mise à jour.");
        }
    }
}
