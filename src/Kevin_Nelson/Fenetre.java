/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kevin_Nelson;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Future;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.tools.DiagnosticCollector;

/**
 *
 * @author Kevin Nelson
 */
class Fenetre extends JFrame {

//    static Object Surface;

    private Puzzle puzzle; // Objet de la classe Puzzle
    static Surface surface;

    private JMenuBar menu;  // La barre de Menu
// Les éléments du Menu en tant que tel
    private JMenu menuJeu;
    private JMenu menuNouveau;
    private JSeparator separateur; // Séparateur entre les éléments du menu
    JMenu menuHeristique = new JMenu("AI Heuristique");
    JMenu menuAction = new JMenu("Disposition");
    private JMenu menuAide;  // Contient les Instructions et des infos complémentaires sur le Jeu
    private JButton pause;  // Bouton pause
    private JPanel testpanel;
// Differentes possibilités de configuration de la fenetre - du plus facil au plus difficile
    private JMenuItem mi3;
    private JMenuItem mi4;
    private JMenuItem mi5;
    private JMenuItem mi6;
    private JMenuItem mi7;
    private JMenuItem mi8;
    private JMenuItem menuInstructions;
    private JMenuItem menuApropos;
    private JMenuItem menuRedemarrer; // Bouton pour recommencer une nouvelle partie
    private JMenuItem menuQuitter; // Bouton pour Quitter le Jeu
    private JMenuItem menuScoreEleve;
    private JMenuItem mi9;
    private JMenuItem mi10;
// Fin de l'enumération des différentes possiblités

    JMenuItem mih1 = new JMenuItem("h1 : Case male placee");
    JMenuItem mih2 = new JMenuItem("h2 : Case bien placee");
    JMenuItem mih3 = new JMenuItem("h3 : Somme des distances ");
    JMenuItem miInitatisation = new JMenuItem("Initialisation");
    JMenuItem miDesordonnee = new JMenuItem("Desordonnee");
   
    
    

    public Fenetre() {
        this.init();
//         pause = new JButton("PAUSE");
//        add((pause),BorderLayout.SOUTH);
        this.pack();
//        this.revalidate();
        this.setVisible(true);
    }

    private void init() {

        // JFrame-----------------------------------------------------------------------
        this.setPreferredSize(new Dimension(405, 451));
        this.setSize(405, 451);
        this.setTitle("TP Version 3.0"); // Titre de la fenetre principale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
         testpanel = new JPanel();
        menu = new JMenuBar();
        pause = new JButton("PAUSE");
        menuJeu = new JMenu("Jeu");
        menuRedemarrer = new JMenuItem("Redemarrer");
        menuScoreEleve = new JMenuItem("Score élevé");
        menuNouveau = new JMenu("Niveau");
        menuAide = new JMenu("Aide");  // Option aide
        menuInstructions = new JMenuItem("Instructions");  // Instanciation Instructions
        menuApropos = new JMenuItem(" À propos "); // Instanciation à propos
        menuQuitter = new JMenuItem("Quitter"); // Quitter la partie
        separateur = new JSeparator(); // Inistanciation du séparateur (On va le configurer de manière verticale)
        separateur.setOrientation(SwingConstants.VERTICAL); // Séparateur vertical

        // Items
        mi3 = new JMenuItem("3 x 3");
        mi4 = new JMenuItem("4 x 4");
        mi5 = new JMenuItem("5 x 5");
        mi6 = new JMenuItem("6 x 6");
        mi7 = new JMenuItem("7 x 7");
        mi8 = new JMenuItem("8 x 8");
        mi9 = new JMenuItem("9 x 9");
        mi10 = new JMenuItem("10 x 10");
        // Fin Items

        // JMenu--------------------------------------------------------------------------
        menu.add(menuJeu);
        menu.add(menuAide);
//        menu.add(separateur);
      testpanel.add((pause),BorderLayout.SOUTH);
      add((pause),BorderLayout.SOUTH);
        menuAide.add(menuInstructions);
        menuAide.add(menuApropos);
        menuJeu.add(menuRedemarrer);
        menuJeu.add(menuNouveau);
        menuJeu.add(menuAction);
        menu.add(menuHeristique);
        menuJeu.add(menuScoreEleve);
        menuJeu.add(menuQuitter);

        menuNouveau.add(mi3);
        menuNouveau.add(mi4);
        menuNouveau.add(mi5);
        menuNouveau.add(mi6);
        menuNouveau.add(mi7);
        menuNouveau.add(mi8);
        menuNouveau.add(mi9);
        menuNouveau.add(mi10);
        menuHeristique.add(mih1);
        menuHeristique.add(mih2);
        menuHeristique.add(mih3);
        menuAction.add(miInitatisation);
        menuAction.add(miDesordonnee);
        add(testpanel);
        this.setJMenuBar(menu);
        // Evenement
        // JMenu---------------------------------------------------------------

        menuRedemarrer.addActionListener((ActionEvent e) -> {
//            puzzle = new Puzzle(4);
            puzzle.Desordonner();
        });
        
        

        // Actions à faire en fonction du champ sélectioné
        mi3.addActionListener((ActionEvent e) -> {
            initPuzzle(3);
        });
        mi4.addActionListener((ActionEvent e) -> {
            initPuzzle(4);
        });
        mi5.addActionListener((ActionEvent e) -> {
            initPuzzle(5);
        });

        mi6.addActionListener((ActionEvent e) -> {
            initPuzzle(6);
        });

        mi7.addActionListener((ActionEvent e) -> {
            initPuzzle(7);
        });

        mi8.addActionListener((ActionEvent e) -> {
            initPuzzle(8);
        });

        mi9.addActionListener((ActionEvent e) -> {
            initPuzzle(9);
        });

        mi10.addActionListener((ActionEvent e) -> {
            initPuzzle(10);
        });
        // Fin des actions effectuées en fonction du niveau sélectionné

        miInitatisation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initPuzzle(puzzle.getDimension());
            }
        });
        miDesordonnee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                puzzle.Desordonner();
                surface.refresh();
            }
        });
        menuQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mih1.addActionListener((ActionEvent e) -> {
            FenetreDetail f = new FenetreDetail(new CasesNonPlace(), puzzle);
        });
        mih2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FenetreDetail f = new FenetreDetail(new CaseBienPlacee(), puzzle);
            }
        });
        mih3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FenetreDetail f = new FenetreDetail(new sommeDistances(), puzzle);
            }
        });

        // ------------------------------------------------------------------------------
        this.puzzle = new Puzzle(4);
        this.puzzle.Desordonner();
        surface = new Surface(puzzle, 390);
        this.getContentPane().add(surface);

    }

    public void initPuzzle(int dimension) {
        this.puzzle.setDimension(dimension);
        char test = '0';
        int x = Character.getNumericValue(test);
        surface.refresh();
    }

}
