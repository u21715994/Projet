package projet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class myKeyListener extends KeyAdapter{
	private PacmanView game;
	
	public myKeyListener(PacmanView game) {
		this.game = game;
	}
	
	/*
	 * public void KeyPressed(KeyEvent e)
	 * Permet de déplacer le Pacman
	 * en gérant les événements du clavier 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getExtendedKeyCode()) {
			case KeyEvent.VK_DOWN :
				game.getPacman().setDirection(Direction.BAS);
				game.movePacman();
				break;
			case KeyEvent.VK_UP :
				game.getPacman().setDirection(Direction.HAUT);
				game.movePacman();
				break;
			case KeyEvent.VK_RIGHT : 
				game.getPacman().setDirection(Direction.DROITE);
				game.movePacman();
				break;
			case KeyEvent.VK_LEFT :
				game.getPacman().setDirection(Direction.GAUCHE);
				game.movePacman();
				break;
		}
		game.repaint();
	}
}
