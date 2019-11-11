/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kevin_Nelson;

/**
 *
 * @author owner
 */
public class sommeDistances implements IHeuristique{
    
    public int heuristique(Case[][] tab, int dimension) {
		int somme = 0, c = 0;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (tab[i][j].getValeur() != '0') {
					int y = (tab[i][j].getOrdre() - 1) % dimension;
					int x = (tab[i][j].getOrdre() - 1) / dimension;
					c = Math.abs((i - x)) + Math.abs((j - y));
					somme = somme + c;
				}
			}
		}
		return somme;
	}
	public String toString() {
		return "Somme des distances Manhattan";
	}
    
}
