package projet;

/**
 * @authors Youssef Oussouf et Kebba Dampha
 * Pacman représente un pacman héritant de Element(cf Element)
 * ayant des vies et une taille
 */
public class Pacman extends Element{
	private int life;
	public static int sizePacman = 20;

	public Pacman() {
		super(5*PacmanView.WIDTH/100,7*PacmanView.HEIGHT/13-10,Couleur.JAUNE,Type_Case.PACMAN);
		this.life = 3;
		this.direction = Direction.NONE;
	}
	
	public Pacman(int x, int y, int life, Couleur couleur, Direction direction, Type_Case cell) {
		super(x,y,couleur,cell);
		this.life = life;
		this.direction = direction;
	}
	
	public int getLife() {
		return life;
	}
	
	/*
	 * public void setLifeLower()
	 * Diminue les vies de Pacman de 1
	 * et affiche les vies 
	 */
	public void setLifeLower() {
		if(PacmanView.timerGhostTouch == 0) {
			life--;
			PacmanView.timerGhostTouch = System.currentTimeMillis()/1000;
			System.out.println("Vie restante : " + life);
		}
	}
	
	/*
	 * public void setLifeIncrease()
	 * Augmente les vies de Pacman de 1
	 * et affiche les vies 
	 */
	public void setLifeIncrease() {
		life++;
		System.out.println("Vie : " + life);
	}

	/*
	 * public void setScore(Couleur couleur)
	 * Permet la modification du score
	 * en fonction de la couleur passé en paramètre
	 */
	public void setScore(Couleur couleur) {
		switch(couleur) {
			case BLEU:
				PacmanView.score+=100;
				System.out.println("Score : " + PacmanView.score);
				break;
			case VIOLET:
				PacmanView.score+=300;
				System.out.println("Score : " + PacmanView.score);
				break;
			case ORANGE:
				PacmanView.score+=500;
				System.out.println("Score : " + PacmanView.score);
				break;
			case VERT:
				PacmanView.score+=1000;
				System.out.println("Score : " + PacmanView.score);
				break;
			default:
				break;
		}
	}
	
	/*
	 * public void eat (Couleur couleur)
	 * Modifie le Pacman en fonction de la couleur
	 * et met à jour le score
	 */
	public void eat(Couleur couleur) {
		if(couleur == Couleur.BLEU) {
			setScore(couleur);
		}else if(couleur == Couleur.VIOLET) {
			setScore(couleur);
			//this.couleur = Couleur.JAUNE_PALE;
			PacmanView.timerPacmanEffect = System.currentTimeMillis()/1000;
		}
		else if(couleur == Couleur.ORANGE) {
			//this.couleur = Couleur.ORANGE;
			setScore(couleur);
			PacmanView.timerPacmanEffect = System.currentTimeMillis()/1000;
		}else if(couleur == Couleur.VERT) {
			setScore(couleur);
			PacmanView.labyrinth++;
		}
		if(PacmanView.score >= 5000 && PacmanView.formerScore == 0) {
			setLifeIncrease();
			PacmanView.formerScore++;
		}
	}
}
