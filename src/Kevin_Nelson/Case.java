/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kevin_Nelson;

/**
 *
 * @author Kevin Nelson
 */
public class Case {

    private int valeur;
    private int ordre;
    public static int compteur = 0;

    public Case() {
        compteur++;
        this.valeur = compteur;
        this.ordre = compteur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(char valeur) {
        this.valeur = valeur;
    }

    public int getOrdre() {
        return ordre;
    }

  
    public String toString() {
        return String.valueOf(this.valeur);
    }
}
