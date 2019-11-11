/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kevin_Nelson;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Kevin Nelson
 */
public class Surface extends JPanel implements MouseListener{
    
    private Puzzle puzzle;
	private int dimension; // le nombre de cases
	private int largeur; // largeur et hauteur de la surface en pixel
	private int taille; // la taille d'une case
	private final int padding = 5;

	public Surface(Puzzle puzzle, int largeur) {
		this.puzzle = puzzle;
		this.dimension = puzzle.getDimension(); // le nombre de cases
		this.largeur = largeur; // largeur et hauteur de la surface en pixel
		this.taille = (largeur - (2 * padding)) / dimension; // la taille d'une															// // case
		this.setSize(largeur, largeur);
		this.addMouseListener(this);
	}

	public void refresh()
	{
		this.dimension = puzzle.getDimension(); // le nombre de cases
		this.taille = (largeur - (2 * padding)) / dimension; // la taille d'u*
		this.repaint();
	}
        
	protected void paintComponent(Graphics g) {

		Font font = new Font("Courier", Font.BOLD, 10);
		g.setColor(Color.BLACK);

		g.fillRect(0, 0, this.largeur, this.largeur);

		g.setColor(Color.WHITE);
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				g.fillRoundRect(this.padding + (taille * i), this.padding
						+ (taille * j), taille, taille, 15, 15);
			}
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				g.drawRoundRect(this.padding + (taille * i), this.padding
						+ (taille * j), taille, taille, 15, 15);
			}
			;
		}

		g.setColor(Color.BLACK);
		g.setFont(font);
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.puzzle.getCase(i, j).getValeur() == '0') {

					g.fillRoundRect(this.padding + (taille * j), this.padding
							+ (taille * i), taille, taille, 15, 15);
				} else {
					g.drawString(
							this.puzzle.getCase(i, j).toString(),
							((taille / 2) + (taille * j)) - padding,
							((taille / 2) + (font.getSize() / 2) + (taille * i))
									- padding);
				}
			}
		}

	}

	public void mousePressed(MouseEvent event) {

		int i = (event.getY() - 5) / taille;
		int j = (event.getX() - 5) / taille;

		if (puzzle.Permutation_Haute()
				&& !(puzzle.Case_zero().getI() == i && puzzle.Case_zero()
						.getJ() == j)) {
			puzzle.Permutation_Basse();
		}
		if (puzzle.Permutation_Basse()
				&& !(puzzle.Case_zero().getI() == i && puzzle.Case_zero()
						.getJ() == j)) {
			puzzle.Permutation_Haute();
		}
		if (puzzle.Permutation_Gauche()
				&& !(puzzle.Case_zero().getI() == i && puzzle.Case_zero()
						.getJ() == j)) {
			puzzle.Permutation_Droite();
		}
		if (puzzle.Permutation_Droite()
				&& !(puzzle.Case_zero().getI() == i && puzzle.Case_zero()
						.getJ() == j)) {
			puzzle.Permutation_Gauche();
		}
		this.repaint();
//		this.refresh();

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
    
}
