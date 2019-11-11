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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author owner
 */
public class FenetreDetail extends JFrame{
    
    ArrayList<Puzzle> List_successeurs = new ArrayList<Puzzle>();
	ArrayList<Puzzle> Chemin = new ArrayList<Puzzle>();
	Puzzle puzzle;
	IHeuristique heuristique;
	private int jeu = 0;

	JButton btSuivat = new JButton("Suivant");
	JButton btPrecedent = new JButton("Precedent");
	JButton btRsolution = new JButton("Resolution");
	JLabel lblHeuristique = new JLabel("Heuristique : ");
	JLabel lblCout = new JLabel("Le Cout : ");
	JLabel lblMouvement = new JLabel("Mouvement N°: ");
	JLabel lblTotalDeplacement = new JLabel("Total des deplacement : ");

	public FenetreDetail(IHeuristique h, Puzzle p) {
		puzzle = p;
		heuristique = h;
		puzzle.setheuristique(h);
		this.init();
		this.setVisible(true);
	}

	public void init() {
		// fenetre ************************************************************

		this.setSize(401, 250);
		this.setTitle("Essai D'implémentation d'une AI");
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// Panel d'information *************************************************

		JPanel pan_info = new JPanel();
		pan_info.setBorder(BorderFactory.createTitledBorder("Informations :"));
		this.getContentPane().add(pan_info, BorderLayout.CENTER);

		// Button****************************************************************

		JPanel pan_bt = new JPanel();
		pan_bt.setBorder(BorderFactory.createTitledBorder(""));
		Dimension dim = new Dimension(110, 35);
		btPrecedent.setPreferredSize(dim);
		btSuivat.setPreferredSize(dim);
		pan_bt.setLayout(new BorderLayout());
		pan_bt.add(btSuivat, BorderLayout.EAST);
		pan_bt.add(btRsolution, BorderLayout.CENTER);
		pan_bt.add(btPrecedent, BorderLayout.WEST);
		this.getContentPane().add(pan_bt, BorderLayout.SOUTH);

		// Label ****************************************
		lblHeuristique.setText("Heuristique : " + heuristique.toString());
		Box b1 = Box.createHorizontalBox();
		b1.add(lblHeuristique);
		Box b2 = Box.createHorizontalBox();
		b2.add(lblCout);
		Box b3 = Box.createHorizontalBox();
		b3.add(lblMouvement);
		Box b4 = Box.createHorizontalBox();
		b4.add(lblTotalDeplacement);
		Box b = Box.createVerticalBox();
		b.add(b1);
		b.add(b4);
		b.add(b3);
		b.add(b2);
		pan_info.add(b);
		// Clique sur le bouton Précédent
		// *****************************************

		btPrecedent.addActionListener((ActionEvent e) -> {
                    if (jeu > 0) {
                        jeu--;
                        jouer(jeu);
                    }
                });
		// Clique sur le bouton Suivant
		// *****************************************
		btSuivat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jeu < Chemin.size() - 1) {
					jeu++;
					jouer(jeu);
				}
			}
		});
		// Clique sur le bouton Resolution
		// *****************************************
		btRsolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu = 0;
				Chemin.clear();
				List_successeurs.clear();
				boolean solution_trove = false;
				Puzzle meilleur = null;

				List_successeurs.add(new Puzzle(puzzle));
				while (List_successeurs.size() > 0 && !solution_trove) {
					meilleur = List_successeurs.get(0);
					meilleur.setheuristique(heuristique);
					for (int i = 1; i < List_successeurs.size(); i++) {
						Puzzle p = List_successeurs.get(i);
						p.setheuristique(heuristique);
						if (p.heuristique() < meilleur.heuristique()) {
							meilleur = p;
						}
					}
					if (meilleur.isInit()) {
						solution_trove = true;
						Chemin.add(meilleur);
					} else {
						if (existDansChemin(meilleur) >= 0) {
							List_successeurs.remove(meilleur);
						} else {
							Chemin.add(meilleur);
							List_successeurs.clear();
							List_successeurs = meilleur.Successeurs();
						}
					}
				}
				jouer(jeu);
			}
		});
	}

	private int existDansChemin(Puzzle p) {
		for (int i = 0; i < Chemin.size() - 1; i++) {
			if (p.equals(Chemin.get(i))) {
				return i;
			}
		}
		return -1;
	}

	private void jouer(int i) {
		puzzle.setPuzzle(Chemin.get(jeu));
		Fenetre.surface.refresh();
		lblMouvement.setText("Mouvement N°: " + jeu);
		lblTotalDeplacement.setText("Total des deplacement : " + (Chemin.size()-1));
		lblCout.setText("Le Cout : " + Chemin.get(jeu).heuristique());
	}
    
}
