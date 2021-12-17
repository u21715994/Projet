package projet;

/**
 * @authors Youssef Oussouf et Kebba Dampha
 * Pacgomme représente un pacgomme héritant de Element (cf Element)
 * ayant une taille
 */
public class Pacgomme extends Element{
	public static int sizePacgomme = 5;
	
	public Pacgomme(Couleur couleur, int x, int y) {
		super(x,y,couleur,Type_Case.PACGOMME);
	}
	
	public Pacgomme() {
		super();
	}
}
