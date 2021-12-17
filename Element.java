package projet;

/**
 * @authors Youssef Oussouf et Kebba Dampha
 * Element représente un element
 * ayant une position x, une position y
 * une couleur, un Type_Case et une direction 
 */
public abstract class Element {
	protected int x;
	protected int y;
	protected Couleur couleur;
	protected Type_Case cell;
	protected Direction direction;
	
	public Element(int x, int y, Couleur couleur,Type_Case cell) {
		this.x = x;
		this.y = y;
		this.couleur = couleur;
		this.cell = cell;
	}
	
	public Element() {}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
