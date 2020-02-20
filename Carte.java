public class Carte {

    int valeur;
    int signe; // 1pique 2trefle 3carreau 4coeur

    public int getSigne() {
        return signe;
    }
    public int getValeur() { return valeur; }

    public Carte() {
        valeur = 1 + (int) Math.floor(Math.random() * 13);
        signe = (int) Math.floor(Math.random() * 4);
    }

    public Carte(int v, int s) {
        valeur = v;
        signe = s;
    }

    @Override
    public String toString() {
        switch(signe){
            case 0:
                return valeur + " de Pique(" + signe + ")\n";
            case 1:
                return valeur + " de Carreau(" + signe + ")\n";
            case 2:
                return valeur + " de Trefle(" + signe + ")\n";
            case 3:
                return valeur + " de Coeur(" + signe + ")\n";
            case 4:
                return valeur + " d'AS(" + signe + ")\n";
        }
        return "error toString, Carte :"+valeur+"signe :"+signe+ ")\n";
    }

}
