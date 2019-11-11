/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kevin_Nelson;


import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Kevin Nelson
 */
public class Puzzle {

    IHeuristique heuristique;
    private int dimension;
    private Case tab[][];

    public Puzzle(int dimension) {

        this.setDimension(dimension);

    }

    public Puzzle(Puzzle p) {
        this.dimension = p.getDimension();
        tab = new Case[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tab[i][j] = p.tab[i][j];
            }
        }
    }

    public void setPuzzle(Puzzle p) {
        this.dimension = p.getDimension();
        tab = new Case[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tab[i][j] = p.tab[i][j];
            }
        }
    }
    
//    public void scramble() {
//        boolean[] used = new boolean[16];
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                int val = (int) (16 * Math.random());
//                while (used[val]) {
//                    val = (int) (16 * Math.random());
//                }
//                used[val] = true;
//                if (val != 0) {
//                    sq[i][j].setText("" + val);
//                } else {
//                    sq[i][j].setBackground(Color.gray);
//                    m = i;
//                    n = j;
//                }
//            }
//        }
//    }
    

    public Case getCase(int i, int j) {
        return this.tab[i][j];
    }

    public void init() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Case c = new Case();

                if (i == dimension - 1 && j == dimension - 1) {
                    if(c.getValeur() != 48){
                    c.setValeur('0');
                    }
                }

                tab[i][j] = c;
            }
        }
        
    }

    public boolean isInit() {
        int compt = 65;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tab[i][j].getValeur() != (char) compt) {
                    if (i == dimension - 1 && j == dimension - 1
                            && tab[i][j].getValeur() == '0') {
                        return true;
                    } else {
                        return false;
                    }
                }
                compt++;
            }
        }
        return true;
//     int compt = 1;
//        for (int i = 0; i < dimension; i++) {
//            for (int j = 0; j < dimension; j++) {
//                if (tab[i][j].getValeur() != 1) {
//                    if (i == dimension - 1 && j == dimension - 1
//                            && tab[i][j].getValeur() == '0') {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//                compt++;
//            }
//        }
//        return true;
    }

    public int heuristique() {
        return heuristique.heuristique(this.tab, this.dimension);
    }

    public void setheuristique(IHeuristique h) {
        this.heuristique = h;
    }

    public ij Case_zero() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tab[i][j].getValeur() == '0') {
                    return new ij(i, j);
                }
            }
        }
        return null;
    }

    public ArrayList<Puzzle> Successeurs() {

        ArrayList<Puzzle> list = new ArrayList<Puzzle>();

        if (this.Permutation_Haute()) {
            list.add(new Puzzle(this));
            this.Permutation_Basse();
        }
        if (this.Permutation_Basse()) {
            list.add(new Puzzle(this));
            this.Permutation_Haute();
        }
        if (this.Permutation_Gauche()) {
            list.add(new Puzzle(this));
            this.Permutation_Droite();
        }
        if (this.Permutation_Droite()) {
            list.add(new Puzzle(this));
            this.Permutation_Gauche();
        }

        return list;
    }

    public boolean Permutation_Haute() {
        Case c;
        int i = Case_zero().getI();
        int j = Case_zero().getJ();

        if (i > 0) {
            c = tab[i][j];
            tab[i][j] = tab[i - 1][j];
            tab[i - 1][j] = c;
            return true;
        }
        return false;
    }

    public boolean Permutation_Basse() {
        Case c;
        int i = Case_zero().getI();
        int j = Case_zero().getJ();
        ;

        if (i < dimension - 1) {
            c = tab[i][j];
            tab[i][j] = tab[i + 1][j];
            tab[i + 1][j] = c;
            return true;
        }
        return false;
    }

    public boolean Permutation_Gauche() {
        Case c;
        int i = Case_zero().getI();
        int j = Case_zero().getJ();

        if (j > 0) {
            c = tab[i][j];
            tab[i][j] = tab[i][j - 1];
            tab[i][j - 1] = c;
            return true;
        }
        return false;
    }

    public boolean Permutation_Droite() {
        Case c;
        int i = Case_zero().getI();
        int j = Case_zero().getJ();

        if (j < dimension - 1) {
            c = tab[i][j];
            tab[i][j] = tab[i][j + 1];
            tab[i][j + 1] = c;
            return true;
        }
        return false;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        tab = new Case[dimension][dimension];
        Case.compteur = 0;
        this.init();
    }

    public void Desordonner() {
        Random r = new Random();
        int valeur;
        for (int i = 0; i < 30; i++) {
            valeur = 1 + r.nextInt(4);
            switch (valeur) {
                case 1:
                    this.Permutation_Haute();
                    break;
                case 2:
                    this.Permutation_Gauche();
                    break;
                case 3:
                    this.Permutation_Basse();
                    break;
                case 4:
                    this.Permutation_Droite();
                    break;

            }
        }
    }

    public boolean equals(Puzzle obj) {
        if (this.toString().equals(obj.toString())) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                str += tab[i][j];
            }
        }
        return str;
    }

    public class ij {

        private int i;
        private int j;

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public ij(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }

}
