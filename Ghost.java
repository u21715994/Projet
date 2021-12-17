package projet;

/**
 * @authors Youssef Oussouf et Kebba Dampha
 * Ghost représente un fantôme heritant de Element(cf Element)
 * ayant une largeur et une hauteur 
 */
public class Ghost extends Element{
	public static int widthGhost = 10;
	public static int heightGhost = 20;
	
	public Ghost(int x, int y) {
		super(x,y,Couleur.BLANC,Type_Case.FANTOME);
	}
	
	public int getWidth() {
		return widthGhost;
	}

	public int getHeight() {
		return heightGhost;
	}
}
