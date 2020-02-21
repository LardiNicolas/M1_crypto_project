public class Carte {

    int valeur;
    int signe; // 1pique 2trefle 3carreau 4coeur

    public int getSigne() {
        return signe;
    }

    public int getValeur() {
        return valeur;
    }

    public Carte() {
        valeur = 1 + (int) Math.floor(Math.random() * 13);
        signe = (int) Math.floor(Math.random() * 4);
    }

    public Carte(int Valeur, int Signe) {
        valeur = Valeur;
        signe = Signe;
    }

    public int evalBridge() {
        if (this.signe == 4) return 53;
        return this.signe * 13 + valeur;
    }

    @Override
    public String toString() {
        switch (signe) {
            case 0:
                return valeur + " \t de Trefle \t(" + signe + ")\n";
            case 1:
                return valeur + " \t de Carreau\t(" + signe + ")\n";
            case 2:
                return valeur + " \t de Coeur \t(" + signe + ")\n";
            case 3:
                return valeur + " \t de Pique \t(" + signe + ")\n";
            case 4:
                return valeur + " \t Joker   \t(" + signe + ")\n";
        }
        return "error toString, Carte :" + valeur + "signe :" + signe + ")\n";
    }

}
