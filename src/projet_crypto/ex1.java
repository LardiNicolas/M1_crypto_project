package projet_crypto;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

public class ex1 {

    public static final String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String mess = "Lattaqueestpourdemain";
        String cle = "FUSREBJFYDzMPHYDALDIU";
        mess = mess.toUpperCase();
        cle = cle.toUpperCase();

        // int[] messint = convert(mess);
        // int[] cleint = convert(cle);
        // System.out.println(crypt(messint, cleint));
        ArrayList<Carte> listeCarte = genkey();
        /*
         * for (Carte c : listeCarte) { System.out.print(c.toString()); }
         */
        affiche(listeCarte);
    }

    private static void affiche(ArrayList<Carte> listeCarte) {
        for (int i = 0; i <=4; i++) {
            final int j = i;
            List<Carte> result = listeCarte.stream().filter(carte -> carte.getSigne() == j).collect(Collectors.toList());
            System.out.println("Signe:" + i);
            for (Carte c : result) {
                System.out.print("[" + c.valeur + "] ");
            }
            System.out.println("");

        }
    }

    private static String crypt(int[] m, int[] c) {
        String res = "";
        for (int i = 0; i < m.length; i++) {
            res += ex1.alphabet.charAt((m[i] + c[i]) % 26);
        }
        return res;
    }

    private static int[] convert(String s) {
        int res[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res[i] = ex1.alphabet.indexOf(c);
            // System.out.println(res[i]);
        }
        return res;
    }

    private static ArrayList<Carte> genkey() {
        ArrayList<Carte> res = new ArrayList<Carte>();
        for (int i = 0; i < 52; i++) { // 54 car jeu de 52 cartes + 2 jokers
            Carte c = new Carte();
            final s=c.getSigne();
            final v=c.valeur;
            List<Carte> tmp=res.stream().filter(carte -> carte.getSigne() == j).collect(Collectors.toList());
            if (res.size()>0) {
                res.add(c);
            }
        }
        res.add((int) Math.floor(Math.random() * 53), new Carte(1, 4));
        res.add((int) Math.floor(Math.random() * 53), new Carte(2, 4));
        return res;
    }
}
