package IHM;

import java.util.ArrayList;

public class Data {

    public static ArrayList<Profil> List_profil=new ArrayList<>();
public static Profil rechercheselonpseudo (String ps) {
    for (int i = 0; i < List_profil.size(); i++)
  if (List_profil.get(i).pseudo.equals(ps))
      return List_profil.get(i);

  return null;
    }
}
