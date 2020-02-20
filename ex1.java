import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.*;

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
        ArrayList<Carte> deck = genDeck(); //Deck de carte de base
        ArrayList<Carte> deckRandom = genkey(deck); //Deck de carte de base organisé aléatoirement

        //afficheCle(deckRandom);
        operation(deckRandom);
        //afficheCle(deckRandom);
        //affichageDeck(deck);
        //afficheCle(deckRandom);
        //affichageDeck(deckRandom);
    }

    /*
     *Fonction permettant de l'affichage du deck de carte par famille de signe
     */
    private static void affichageDeck(ArrayList<Carte> listeCarte) {
        System.out.println("DECK DE CARTE : ");
        for (int i = 0; i <= 4; i++) {
            final int j = i;
            List<Carte> result = listeCarte.stream().filter(carte -> carte.getSigne() == j).collect(Collectors.toList());
            System.out.println("Signe:" + i);
            for (Carte c : result) {
                System.out.print("[" + c.valeur + "] ");
            }
            System.out.println("");
        }
    }

    /*
     *Fonction permettant de l'affichage de la clé de chiffrement
     */
    private static void afficheCle(ArrayList<Carte> listeCarte) {
        int cpt = 0;
        System.out.println("CLÉ DE CHIFFREMENT : ");
        for (Carte c : listeCarte) {
            if (cpt < 10) {
                System.out.print("[" + c.getValeur() + "," + c.getSigne() + "]");
                cpt++;
            } else {
                cpt = 0;
                System.out.print("\n");
                System.out.print("[" + c.getValeur() + "," + c.getSigne() + "]");
            }
        }
        System.out.print("\n");
    }

    private static void operation(ArrayList<Carte> listeCarte) {
        //OPERATION 1 : Recul du joker noir d'une position, si le joker noir est en dernière position il passe derrière la carte du
        //dessus (donc, en deuxième position)
        boolean bool = false;
        for (Carte c : listeCarte) {
            int signeCarte = c.getSigne();
            int valeurCarte = c.getValeur();
            if (signeCarte == 4 && valeurCarte == 2 && bool == false) {
                int index = listeCarte.indexOf(c);
                if (index != listeCarte.size() - 1) {
                    System.out.println("SWAP DU JOKER NOIR: [2,4] de la position " + index + " à la position " + (index + 1));
                    Collections.swap(listeCarte, index, index + 1);
                    bool = true;
                } else if (index == listeCarte.size() - 1) {
                    System.out.println("SWAP DU JOKER NOIR: [2,4] de la position " + index + " à la position " + 1);
                    Collections.swap(listeCarte, index, 1);
                    bool = true;
                }
            }
        }
        //OPERATION 2 : Recul du joker rouge de deux positions : Vous faites reculer le joker rouge de deux cartes. S’il était en
        //dernière position, il passe en troisième position; s’il était en avant dernière position il passe en deuxième.

        int index = 0;
        bool = false;
        for (Carte c : listeCarte) {
            int signeCarte = c.getSigne();
            int valeurCarte = c.getValeur();
            if (signeCarte == 4 && valeurCarte == 1) {
                index = listeCarte.indexOf(c);
            }
        }

        //TESTER MODULO pour plus tard (autre méthode)
        if (bool == false) {
            if (index != listeCarte.size() - 2) {
                System.out.println("DEPLACEMENT DU JOKER ROUGE: [1,4] de la position " + index + " à la position " + (index + 2));
                move(listeCarte, index, index + 2);
                bool = true;
            } else if (index == listeCarte.size() - 2) {
                System.out.println("DEPLACEMENT DU JOKER ROUGE: [1,4] de la position " + index + " à la position " + 1);
                move(listeCarte, index, 1);
                bool = true;
            } else if (index == listeCarte.size() - 1) {
                System.out.println("DEPLACEMENT DU JOKER ROUGE: [1,4] de la position " + index + " à la position " + 2);
                move(listeCarte, index, 2);
                bool = true;
            }
        }

        // OPERATION 3 : Double coupe par rapport aux jokers. Vouz repérez les deux jokers et vous intervertissez le paquet des
        //cartes situées au-dessus du joker qui est en premier avec le paquet de cartes qui est au-dessous du joker
        //qui est en second. Dans cette opération la couleur des jokers est sans importance.

        //etape 1:
        int indexJoker1=0;
        int indexJoker2=0;
        bool = false;
        for (Carte c : listeCarte) {
            int signeCarte = c.getSigne();
            int valeurCarte = c.getValeur();
            //reperage du premier joker puis du second
            if (signeCarte == 4 && valeurCarte == 1 && bool == false || signeCarte == 4 && valeurCarte == 2 && bool == false) {
                indexJoker1 = listeCarte.indexOf(c);
                bool = true;
            }else if (signeCarte == 4 && valeurCarte == 1 || signeCarte == 4 && valeurCarte == 2) {
                indexJoker2 = listeCarte.indexOf(c);
            }
        }

        ArrayList<Carte> listTemp1 = new ArrayList<Carte>();
        ArrayList<Carte> listTemp2 = new ArrayList<Carte>();

        for(int i = 0; i < indexJoker1;i++){
            Carte element = listeCarte.get(0);
            listeCarte.remove(0);
            listTemp1.add(element);
        }
        System.out.println("indexJoker1 = "+ indexJoker1+"\nlisteTemp1 = \n"+ listTemp1);



        //OPERTION 4 : Coupe simple déterminée par la dernière carte : vous regardez la dernière carte et vous évaluez son
        //numéro selon l’ordre du Bridge : trèfle-carreau-cœur-pique et dans chaque couleur as, 2, 3, 4, 5, 6, 7, 8,
        //9, 10, valet, dame et roi (l’as de trèfle a ainsi le numéro 1, le roi de pique a le numéro 52). Les jokers on
        //par convention le numéro 53. Si le numéro de la dernière carte est n vous prenez les n premières cartes
        //du dessus du paquet et les placez derrière les autres cartes à l’exception de la dernière carte qui reste la
        //dernière.
    }

    public static void move(ArrayList<Carte> listeCarte, int depart, int destination) {
        int delta = destination-depart;
        if (depart < destination) {
            //System.out.println("(+)swap avec écart de : "+delta);
            Collections.swap(listeCarte, depart, depart + 1);
            move(listeCarte,depart+1,destination);
        } else if(depart > destination){
            //System.out.println("(-)swap avec écart de : "+delta);
            Collections.swap(listeCarte, depart, depart - 1);
            move(listeCarte,depart-1,destination);
        }else{
            //System.out.println("(=)destination atteinte");
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

    private static ArrayList<Carte> genDeck() {
        ArrayList<Carte> res = new ArrayList<Carte>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                Carte c = new Carte(i, j);
                res.add(c);
            }
        }
        Carte joker1 = new Carte(1, 4); //joker rouge
        Carte joker2 = new Carte(2, 4); //joker noir
        res.add(joker1);
        res.add(joker2);
        return res;
    }


    private static ArrayList<Carte> genkey(ArrayList<Carte> listeCarte) {
        ArrayList<Carte> res = new ArrayList<Carte>();
        for (Carte c : listeCarte) {
            res.add(c);
        }
        Collections.shuffle(res);
        return res;
    }
}
