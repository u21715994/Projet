package projet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * @authors Youssef Oussouf et Kebba Dampha
 * PacmanView représente la vue du Pacman et la partie
 * ayant un Pacman, une liste de Ghost 
 * une liste de Pacgomme
 * et tableau de Type_Case en 2D
 */
public class PacmanView extends JComponent{
	private Pacman pacman;
	private ArrayList<Ghost> ghosts;
	private ArrayList<Pacgomme> pacgommes;
	private Type_Case[][] cases;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int score = 0;
	public static int formerScore = 0;
	public static int labyrinth = 1;
	public static final int WIDTH = 650;
	public static final int HEIGHT = 400;
	public static final int SIZEWIDTH = WIDTH + 1;
	public static final int SIZEHEIGHT = HEIGHT + 1;
	public static long timerPacmanEffect = 0;
	public static int speedPacman = 1;
	public static long timerGhostTouch = 0;
	public static int speedGhosts = 10;
	public static long timerGhostsFollow = 0;
	public static int reboundGhosts = 0;
	
	public PacmanView() {
		//pacman = new Pacman();
		pacman = new PacmanYellow(5*PacmanView.WIDTH/100,7*PacmanView.HEIGHT/13-10,3,Direction.NONE);
		ghosts = new ArrayList<Ghost>(4);
		ghosts.add(new GhostWhite(WIDTH/2,HEIGHT/2));
		//ghosts.get(0).setDirection(Direction.GAUCHE);
		ghosts.add(new GhostWhite(WIDTH/2+Ghost.widthGhost,HEIGHT/2));
		//ghosts.get(1).setDirection(Direction.DROITE);
		ghosts.add(new GhostWhite(WIDTH/2+(Ghost.widthGhost*2),HEIGHT/2));
		//ghosts.get(2).setDirection(Direction.HAUT);
		ghosts.add(new GhostWhite(WIDTH/2+(3*Ghost.widthGhost),HEIGHT/2));
		//ghosts.get(3).setDirection(Direction.DROITE);
		setDirectionGhosts();
		pacgommes = new ArrayList<Pacgomme>();
		createAllPacgommes();
		cases = new Type_Case[SIZEWIDTH][SIZEHEIGHT];
		addKeyListener(new myKeyListener(this));
		setFocusable(true);
		requestFocusInWindow();
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
	
	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public ArrayList<Pacgomme> getPacgommes() {
		return pacgommes;
	}

	public void setPacgommes(ArrayList<Pacgomme> pacgommes) {
		this.pacgommes = pacgommes;
	}

	public Type_Case[][] getCases() {
		return cases;
	}

	public void setCases(Type_Case[][] cases) {
		this.cases = cases;
	}
	
	/*
	 * public void createPacgommeWidth(int x1, int x2, int y, Couleur couleur)
	 * Crée une ligne de pacgomme allant de x1 largeur à x2 largeur,
	 * à la hauteur y et de couleur couleur
	 */
	public void createPacgommeWidthLine(int x1, int x2, int y, Couleur couleur) {
		for(int i = x1; i < x2; i = i+20) {
			pacgommes.add(new Pacgomme(couleur,i,y+5));
		}
	}
	
	/*
	 * public void createPacgommeHeight(int y1, int y2, int x, Couleur couleur)
	 * Crée une ligne de pacgomme allant de y1 hauteur à y2 hauteur,
	 * à la largeur y et de couleur couleur
	 */
	public void createPacgommeHeightLine(int y1, int y2, int x, Couleur couleur) {
		for(int i = y1; i < y2; i = i+20) {
			pacgommes.add(new Pacgomme(couleur,x,i+5));
		}
	}
	
	/*
	 * public void createAllPacgommes()
	 * Permet de créer tous les pacgommes
	 */
	public void createAllPacgommes() {
		createPacgommeWidthLine(Pacman.sizePacman*3,WIDTH/4,7*HEIGHT/13 - 10, Couleur.BLEU);
		createPacgommeWidthLine(75*WIDTH/100,(WIDTH-Pacman.sizePacman*2),7*HEIGHT/13 - 10,Couleur.BLEU);
		createPacgommeHeightLine(HEIGHT/4,(HEIGHT-Pacman.sizePacman),27*WIDTH/100,Couleur.BLEU);
		createPacgommeHeightLine(HEIGHT/4,(75*HEIGHT/100),73*WIDTH/100,Couleur.BLEU);
		createPacgommeWidthLine(WIDTH/20,(WIDTH-Pacman.sizePacman*2),93*HEIGHT/100,Couleur.BLEU);
		createPacgommeWidthLine(WIDTH/20,(WIDTH-Pacman.sizePacman*2),5*HEIGHT/100,Couleur.BLEU);
		createPacgommeHeightLine(37*HEIGHT/100,7*HEIGHT/10,35*WIDTH/100,Couleur.BLEU);
		createPacgommeHeightLine(37*HEIGHT/100,7*HEIGHT/10,65*WIDTH/100,Couleur.BLEU);
		createPacgommeWidthLine(38*WIDTH/100,63*WIDTH/100,37*HEIGHT/100,Couleur.BLEU);
		createPacgommeWidthLine(38*WIDTH/100,63*WIDTH/100,67*HEIGHT/100,Couleur.BLEU);
		createPacgommeWidthLine(WIDTH/20,(WIDTH-Pacman.sizePacman*2),2*HEIGHT/10,Couleur.BLEU);
		createPacgommeWidthLine(WIDTH/20,(WIDTH-Pacman.sizePacman*2),75*HEIGHT/100,Couleur.BLEU);
		pacgommes.add(new Pacgomme(Couleur.VERT,WIDTH/20,HEIGHT/8));
		pacgommes.add(new Pacgomme(Couleur.VIOLET,(WIDTH-WIDTH/11),HEIGHT/8));
		pacgommes.add(new Pacgomme(Couleur.ORANGE,WIDTH/20,85*HEIGHT/100));
		pacgommes.add(new Pacgomme(Couleur.VIOLET,(WIDTH-WIDTH/20),85*HEIGHT/100));
	}
	
	/*
	 * public boolean checkWall(int x, int y)
	 * Vérifie dans le tableau cases
	 * à la position cases[x][y] s'il y a un mur
	 *  revoie vrai dans ce cas sinon faux
	 */
	public boolean checkWall(int x, int y) {
		if(x >= 0 && y >= 0) {
			if(cases[x][y] == Type_Case.MUR)
				return true;
		}
		return false;
	}
	
	/*
	 * public void fillWall(int startX, int startY, int endX, int endY, Graphics g)
	 * dessine un mur à la position (startX,startY) de largeur et hauteur (endX,endY)
	 * remplit également le tableau cases de mur
	 */
	public void fillWall(int startX, int startY, int endX, int endY, Graphics g) {
		g.drawRect(startX, startY, endX, endY);
		g.fillRect(startX, startY, endX, endY);
		if(startX >= Pacman.sizePacman && startY >= Pacman.sizePacman) {
			for(int y = startY-Pacman.sizePacman;y <= (startY+endY); y++) {
				for(int x = startX-Pacman.sizePacman ;x <= (startX+endX); x++) {
					cases[x][y] = Type_Case.MUR;
				}
			}
		}else {
			for(int y = startY-Pacman.sizePacman;y <= (startY+endY); y++) {
				for(int x = startX ;x <= (startX+endX); x++) {
					cases[x][y] = Type_Case.MUR;
				}
			}
		}
	}
	
	/*
	 * public void rebootTimer()
	 * Remet les timers à 0 
	 * et remet la couleur par défaut du Pacman (Jaune)
	 */
	public void rebootTimer() {
		if(System.currentTimeMillis()/1000 - timerPacmanEffect >= 15) {
			//pacman.setCouleur(Couleur.JAUNE);
			pacman = new PacmanYellow(pacman.getX(),pacman.getY(),pacman.getLife(),pacman.getDirection());
			/*for(Ghost gh : ghosts) {
				if(gh.getCouleur().equals(Couleur.BLEU))
					gh.setCouleur(Couleur.BLANC);
			}*/
			for(int i = 0 ;i < ghosts.size(); i++) {
				if(ghosts.get(i).getClass().equals(GhostBlue.class)) {
					ghosts.set(i,new GhostWhite(ghosts.get(i).getX(),ghosts.get(i).getY()));
					setDirectionGhosts();
				}
			}
			timerPacmanEffect = 0;
		}
		if(System.currentTimeMillis()/1000 - timerGhostTouch >= 5) {
			timerGhostTouch = 0;
		}
	}
	
	/*
	 * public void emptyTab()
	 * Vide le tableau
	 */
	public void emptyTab() {
		for(int i = 0; i < SIZEHEIGHT; i++) {
			for(int j = 0; j < SIZEWIDTH; j++) {
				cases[j][i] = null;
			}
		}
	}
	
	/*
	 * public void setGhostsMiddle()
	 * Met les fantômes dans le mur du milieu 
	 */
	public void setGhostsMiddle() {
		ghosts.get(0).setX(WIDTH/2);
		ghosts.get(0).setY(HEIGHT/2);
		ghosts.get(1).setX(WIDTH/2+Ghost.widthGhost);
		ghosts.get(1).setY(HEIGHT/2);
		ghosts.get(2).setX(WIDTH/2+(Ghost.widthGhost*2));
		ghosts.get(2).setY(HEIGHT/2);
		ghosts.get(3).setX(WIDTH/2+(3*Ghost.widthGhost));
		ghosts.get(3).setY(HEIGHT/2);
		ghosts.get(1).setDirection(Direction.DROITE);
		ghosts.get(2).setDirection(Direction.HAUT);
	}
	
	/*
	 * public void paintGhost(Graphics g)
	 * Permet de dessiner le Pacman
	 */
	public void paintPacman(Graphics g) {
		if(pacman.getClass().equals(PacmanPaleYellow.class))
			g.setColor(new Color(240,230,140));
		else if(pacman.getClass().equals(PacmanOrange.class))
			g.setColor(Color.ORANGE);
		else
			g.setColor(Color.YELLOW);
		paintPacmanDirection(g);
		cases[pacman.getX()][pacman.getY()] = Type_Case.PACMAN;
		wraparound();
	}
	
	/*
	 * public void paintPacmanDirection(Graphics g)
	 * Permet de dessiner le Pacman en fonction de sa direction
	 */
	public void paintPacmanDirection(Graphics g) {
		if(pacman.getDirection().equals(Direction.BAS)) {
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -60, 180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -60, 180);
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 60,180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 60,180);
		}else if(pacman.getDirection().equals(Direction.HAUT)) {
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 300, -180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 300, -180);
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -300,-180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -300,-180);
		}else if(pacman.getDirection().equals(Direction.GAUCHE)) {
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -330, -180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -330, -180);
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 330,180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 330,180);
		}else {
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 30, 180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, 30, 180);
			g.drawArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -30,-180);
			g.fillArc(pacman.getX(), pacman.getY(), Pacman.sizePacman, Pacman.sizePacman, -30,-180);
		}
	}
	
	/*
	 * public void paintGhost(Graphics g)
	 * Permet de dessiner les fantômes
	 */
	public void paintGhost(Graphics g) {
		blueGhost();
		for(Ghost gh: ghosts) {
			//if(gh.getCouleur().equals(Couleur.BLEU))
			if(gh.getClass().equals(GhostBlue.class))
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.WHITE);
			g.drawRect(gh.getX(), gh.getY(), Ghost.widthGhost, Ghost.heightGhost);
			g.fillRect(gh.getX(), gh.getY(), Ghost.widthGhost, Ghost.heightGhost);
		}
	}
	
	/*
	 * public void paintPacgomme(Graphics g)
	 * Permet de dessiner les pacogommes 
	 */
	public void paintPacgomme(Graphics g) {
		for(Pacgomme p:pacgommes) {
			if(p.getCouleur().equals(Couleur.VERT))
				g.setColor(Color.GREEN);
			else if(p.getCouleur().equals(Couleur.VIOLET))
				g.setColor(Color.MAGENTA);
			else if(p.getCouleur().equals(Couleur.ORANGE))
				g.setColor(Color.ORANGE);
			else
				g.setColor(Color.BLUE);
			g.drawOval(p.getX(), p.getY(), Pacgomme.sizePacgomme, Pacgomme.sizePacgomme);
			g.fillRect(p.getX(), p.getY(), Pacgomme.sizePacgomme, Pacgomme.sizePacgomme);
			cases[p.getX()][p.getY()] = Type_Case.PACGOMME;
		}
	}
	
	/*
	 * public void paintEat()
	 * Permet de manger les Pacgommes
	 */
	public void paintEat() {
		Pacgomme pac = new Pacgomme();
		for(Pacgomme p:pacgommes) {
			if( (p.getX()-Pacman.sizePacman/2 <= pacman.getX() && (p.getX()+Pacman.sizePacman/2 >= pacman.getX()) 
					&& (p.getY()-Pacman.sizePacman/2 <= pacman.getY() && p.getY()+Pacman.sizePacman/2 >= pacman.getY()) )) {
				pac = p;
				pacman.eat(p.getCouleur());
				switch(p.getCouleur()) {
					case VIOLET:
						pacman = new PacmanPaleYellow(pacman.getX(), pacman.getY(), pacman.getLife(), pacman.getDirection());
						break;
					case ORANGE:
						pacman = new PacmanOrange(pacman.getX(), pacman.getY(), pacman.getLife(), pacman.getDirection());
						break;
					default:
						break;
				}
				cases[pac.getX()][pac.getY()] = null;
			}
		}
		pacgommes.remove(pac);
		game();
	}
	
	/*
	 * public void paintEdge(Graphics g)
	 * Permet de dessiner les coutours
	 * du labyrinthe
	 */
	public void paintEdge(Graphics g) {
		emptyTab();
		//mur contour haut
		g.setColor(Color.RED);
		g.drawRect(0, 0, WIDTH, Pacman.sizePacman/2);
		g.fillRect(0, 0, WIDTH, Pacman.sizePacman/2);
		for(int i = 0; i < SIZEWIDTH; i++) {
			for(int j = 0; j < Pacman.sizePacman/2; j++) {
				cases[i][j] = Type_Case.MUR;
			}
		}
		
		//mur contour gauche haut
		g.drawRect(0, 0, Pacman.sizePacman/2, 47*HEIGHT/100);
		g.fillRect(0, 0, Pacman.sizePacman/2, 47*HEIGHT/100);
		for(int i = 0;i < SIZEHEIGHT/2; i++) {
			for(int j = 0; j < Pacman.sizePacman/2; j++) {
				cases[j][i] = Type_Case.MUR;
			}
		}
		
		//mur contour gauche bas
		g.drawRect(0, 5*HEIGHT/7, Pacman.sizePacman/2, 27*HEIGHT/100);
		g.fillRect(0, 5*HEIGHT/7, Pacman.sizePacman/2, 27*HEIGHT/100);
		for(int i = 4*SIZEHEIGHT/7;i < SIZEHEIGHT; i++) {
			for(int j = 0; j < Pacman.sizePacman/2; j++) {
				cases[j][i] = Type_Case.MUR;
			}
		}
		
		//mur contour bas
		g.drawRect(0, HEIGHT-Pacman.sizePacman/2, WIDTH, Pacman.sizePacman/2);
		g.fillRect(0, HEIGHT-Pacman.sizePacman/2, WIDTH, Pacman.sizePacman/2);
		for(int i = 0;i < SIZEWIDTH; i++) {
			for(int j = SIZEHEIGHT-(int)(Pacman.sizePacman*1.5); j < SIZEHEIGHT; j++) {
				cases[i][j] = Type_Case.MUR;
			}
		}
		
		//mur contour droit haut
		g.drawRect(WIDTH-Pacman.sizePacman/2, 0, Pacman.sizePacman/2, 47*HEIGHT/100);
		g.fillRect(WIDTH-Pacman.sizePacman/2, 0, Pacman.sizePacman/2, 47*HEIGHT/100);
		for(int i = 0;i < SIZEHEIGHT/2; i++) {
			for(int j = SIZEWIDTH-(int)(Pacman.sizePacman*1.5); j < SIZEWIDTH; j++) {
				cases[j][i] = Type_Case.MUR;
			}
		}
		
		//mur contour droit bas
		g.drawRect(WIDTH-Pacman.sizePacman/2, 5*HEIGHT/7, Pacman.sizePacman/2, 27*HEIGHT/100);
		g.fillRect(WIDTH-Pacman.sizePacman/2, 5*HEIGHT/7, Pacman.sizePacman/2, 27*HEIGHT/100);
		for(int i = 4*SIZEHEIGHT/7;i < SIZEHEIGHT; i++) {
			for(int j = SIZEWIDTH-(int)(Pacman.sizePacman*1.5); j < SIZEWIDTH; j++) {
				cases[j][i] = Type_Case.MUR;
			}
		}
		
		//mur du centre
		fillWall(3*WIDTH/7, HEIGHT/2, WIDTH/8, HEIGHT/10,g);
		//mur gauche haut de la moitié
		fillWall(0,HEIGHT/3 ,WIDTH/4 ,HEIGHT/6,g);
		//mur gauche bas de la moitié
		fillWall(0,(int) (HEIGHT/2+Pacman.sizePacman*1.5) ,WIDTH/4 ,HEIGHT/7,g);
		//mur droit haut de la moitié
		fillWall(3*WIDTH/4,HEIGHT/3 ,WIDTH/4 ,HEIGHT/6,g);
		//mur droit bas de la moitié
		fillWall(3*WIDTH/4,(int) (HEIGHT/2+Pacman.sizePacman*1.5) ,WIDTH/4 ,HEIGHT/7,g);
		//mur long à gauche
		fillWall(3*WIDTH/10,4*HEIGHT/10,WIDTH/70,3*HEIGHT/10,g);
		//mur long en haut
		fillWall(35*WIDTH/100,32*HEIGHT/100,3*WIDTH/10,HEIGHT/50,g);
		//mur long à droite
		fillWall(7*WIDTH/10,4*HEIGHT/10,WIDTH/70,3*HEIGHT/10,g);
		//mur long en bas
		fillWall(35*WIDTH/100,72*HEIGHT/100,3*WIDTH/10,HEIGHT/50,g);	
	}
	
	/*
	 * public void paintLabyrinth(Graphics g)
	 * Permet de dessiner le premier labyrinthe
	 */
	public void paintLabyrinth(Graphics g){
		paintEdge(g);
		//Blocs en haut à gauche
		fillWall(WIDTH/10,HEIGHT/10,WIDTH/10,HEIGHT/15,g);
		fillWall(WIDTH/10,HEIGHT/4,WIDTH/10,HEIGHT/60,g);
		//Bloc en haut au milieu
		fillWall(WIDTH/4,HEIGHT/10,4*WIDTH/10,HEIGHT/15,g);
		//Blocs en haut à droite
		fillWall(3*WIDTH/4,HEIGHT/10,WIDTH/10,HEIGHT/15,g);
		fillWall(3*WIDTH/4,HEIGHT/4,WIDTH/10,HEIGHT/60,g);
		//L
		fillWall(WIDTH/10, 9*HEIGHT/10, WIDTH/10, HEIGHT/70,g);
		fillWall(WIDTH/10,8*HEIGHT/10,WIDTH/70,HEIGHT/10,g);
		//Premier O
		fillWall(3*WIDTH/10,8*HEIGHT/10, WIDTH/10,HEIGHT/10,g);
		//Deuxième O
		fillWall(6*WIDTH/10,8*HEIGHT/10, WIDTH/10,HEIGHT/10,g);
		//P
		fillWall(8*WIDTH/10, 8*HEIGHT/10, WIDTH/14, HEIGHT/70,g);
		fillWall(8*WIDTH/10, 85*HEIGHT/100, WIDTH/14, HEIGHT/70,g);
		fillWall(87*WIDTH/100, 8*HEIGHT/10, WIDTH/70, HEIGHT/16,g);
		fillWall(8*WIDTH/10,8*HEIGHT/10,WIDTH/70,HEIGHT/10,g);
	}
	
	/*
	 * public void paintLabyrinth(Graphics g)
	 * Permet de dessiner le deuxième labyrinthe
	 */
	public void paintLabyrinth2(Graphics g){
		paintEdge(g);
		//P
		fillWall(WIDTH/10,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(WIDTH/9,HEIGHT/10,WIDTH/27,HEIGHT/20,g);
		//Premier A
		fillWall(2*WIDTH/10,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(27*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(2*WIDTH/10,HEIGHT/10,WIDTH/13,HEIGHT/90,g);
		fillWall(2*WIDTH/10,HEIGHT/7,WIDTH/13,HEIGHT/90,g);
		//C
		fillWall(35*WIDTH/100,HEIGHT/10,WIDTH/12,HEIGHT/90,g);
		fillWall(35*WIDTH/100,HEIGHT/5,WIDTH/12,HEIGHT/90,g);
		fillWall(35*WIDTH/100,HEIGHT/10,WIDTH/85,HEIGHT/9,g);
		//M
		fillWall(50*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(50*WIDTH/100,HEIGHT/10,WIDTH/12,HEIGHT/90,g);
		fillWall(54*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(58*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		//Deuxième A
		fillWall(65*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(72*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(65*WIDTH/100,HEIGHT/10,WIDTH/13,HEIGHT/90,g);
		fillWall(65*WIDTH/100,HEIGHT/7,WIDTH/13,HEIGHT/90,g);
		//premier N
		fillWall(82*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(82*WIDTH/100,HEIGHT/10,WIDTH/25,HEIGHT/100,g);
		fillWall(86*WIDTH/100,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		fillWall(86*WIDTH/100,20*HEIGHT/100,WIDTH/25,HEIGHT/90,g);
		fillWall(9*WIDTH/10,HEIGHT/10,WIDTH/100,HEIGHT/9,g);
		//L
		fillWall(15*WIDTH/100,81*HEIGHT/100,WIDTH/80,12*HEIGHT/150,g);
		fillWall(15*WIDTH/100,89*HEIGHT/100,WIDTH/12,HEIGHT/90,g);
		//3
		fillWall(3*WIDTH/10,81*HEIGHT/100,WIDTH/12,HEIGHT/90,g);
		fillWall(3*WIDTH/10,85*HEIGHT/100,WIDTH/12,HEIGHT/90,g);
		fillWall(3*WIDTH/10,89*HEIGHT/100,WIDTH/12,HEIGHT/90,g);
		fillWall(38*WIDTH/100,81*HEIGHT/100,WIDTH/100,HEIGHT/11,g);
		//I
		fillWall(WIDTH/2,80*HEIGHT/100,WIDTH/100,HEIGHT/9,g);
		//deuxième N
		fillWall(56*WIDTH/100,80*HEIGHT/100,WIDTH/100,HEIGHT/9,g);
		fillWall(56*WIDTH/100,80*HEIGHT/100,WIDTH/25,HEIGHT/90,g);
		fillWall(60*WIDTH/100,80*HEIGHT/100,WIDTH/100,HEIGHT/9,g);
		fillWall(60*WIDTH/100,90*HEIGHT/100,WIDTH/25,HEIGHT/90,g);
		fillWall(64*WIDTH/100,80*HEIGHT/100,WIDTH/100,HEIGHT/9,g);
		//F
		fillWall(7*WIDTH/10,80*HEIGHT/100,WIDTH/100,HEIGHT/9,g);
		fillWall(7*WIDTH/10,80*HEIGHT/100,WIDTH/15,HEIGHT/100,g);
		fillWall(7*WIDTH/10,87*HEIGHT/100,WIDTH/20,HEIGHT/100,g);
		//O
		fillWall(82*WIDTH/100,79*HEIGHT/100,WIDTH/10,HEIGHT/8,g);
	}
	
	/*
	 * public void movePacman()
	 * Permet au Pacman de se déplacer
	 */
	public void movePacman() {
		if(pacman.getCouleur().equals(Couleur.ORANGE))
			speedPacman = 2;
		else
			speedPacman = 1;
		switch(pacman.getDirection()) {
			case BAS:
				if(!checkWall(pacman.getX(), pacman.getY()+Pacman.sizePacman/10 )) {
					int y = pacman.getY();
					pacman.setY(y+speedPacman);
					repaint();
				}
				break;
			case HAUT:
				if(!checkWall(pacman.getX(), pacman.getY()-Pacman.sizePacman/10 )) {
					int y = pacman.getY();
					pacman.setY(y-speedPacman);
					repaint();
				}
				break;
			case DROITE:
				if( !checkWall(pacman.getX()+Pacman.sizePacman/10, pacman.getY()) ) {
					int x = pacman.getX();
					pacman.setX(x+speedPacman);
					repaint();
				}
				break;
			case GAUCHE:
				if( !checkWall(pacman.getX()-Pacman.sizePacman/10, pacman.getY()) ) {
					int x = pacman.getX();
					pacman.setX(x-speedPacman);
					repaint();
				}
				break;
			default:
				break;
		}
	}
	
	/*
	 * public void setDirectionGhosts()
	 * Permet de modifier la direction de chaque fantôme
	 */
	public void setDirectionGhosts() {
		for(int i = 0;i < ghosts.size(); i++) {
			switch(i) {
				case 0:
					ghosts.get(0).setDirection(Direction.GAUCHE);
				break;
				case 1:
					ghosts.get(1).setDirection(Direction.DROITE);
				break;
				case 2:
					ghosts.get(2).setDirection(Direction.HAUT);
				break;
				case 3:
					ghosts.get(3).setDirection(Direction.DROITE);
				break;
			}
		}
	}
	
	/*
	 * public void blueGhost()
	 * Permet de mettre la couleur de fantômes
	 * en bleu si la couleur de Pacman est orange
	 */
	public void blueGhost() {
		//if(pacman.getCouleur().equals(Couleur.ORANGE)) {
		if(pacman.getClass().equals(PacmanOrange.class)) {
			/*for(Ghost gh : ghosts) {
				gh.setCouleur(Couleur.BLEU);
			}*/
			for(int i = 0;i < ghosts.size(); i++) {
				ghosts.set(i,new GhostBlue(ghosts.get(i).getX(),ghosts.get(i).getY()) );
				setDirectionGhosts();
			}
		}
	}
	
	/*
	 * public void touchGhost()
	 * Permet de gérer la collision
	 * avec les fantômes.
	 * Envoie les fantômes au milieu du labyrinthe
	 * si Pacman est superpacman
	 */
	public void touchGhost() {
		for(Ghost g : ghosts) {
			if( (g.getX()-Pacman.sizePacman/1.5 <= pacman.getX() && (g.getX()+Pacman.sizePacman/1.5 >= pacman.getX())  
					&& (g.getY()-Pacman.sizePacman/2 <= pacman.getY() && g.getY()+Pacman.sizePacman/2 >= pacman.getY())) ) {
				//if(pacman.getCouleur() == Couleur.ORANGE) {
				if(pacman.getClass().equals(PacmanOrange.class)) {
					g.setX(WIDTH/2);
					g.setY(HEIGHT/2);
				}else {
					pacman.setLifeLower();
					pacman.setX(5*PacmanView.WIDTH/100);
					pacman.setY(7*PacmanView.HEIGHT/13-10);
					setGhostsMiddle();
					game();
				}
			}			
		}
	}
	
	/*
	 * public void followsPacman()
	 * Permet aux fantômes de suivre le pacman
	 */
	public void followsPacman() {
		followPacman(ghosts.get(1));
		followPacman(ghosts.get(2));
		followPacman(ghosts.get(3));
		reboundGhosts = 0;
	}
	
	/*
	 * public void moveGhost()
	 * Permet au fantômes de bouger
	 */
	public void moveGhosts() {
		try {
			if(pacman.getClass().equals(PacmanOrange.class)) {
				Thread.sleep(speedGhosts*2);
				avoidPacman();
			}
			else {
				Thread.sleep(speedGhosts);
				moveGhostInWallMiddle();
				followPacman(ghosts.get(0));
				if(reboundGhosts/4 == 200)
					timerGhostsFollow = System.currentTimeMillis()/1000;
				if(timerGhostsFollow != 0 && (System.currentTimeMillis()/1000 - timerGhostsFollow <= 15) ) {
					followsPacman();
				}else {
					timerGhostsFollow = 0;
					bypassPacman(ghosts.get(1));
					bypassPacman2(ghosts.get(2));
					moveRandom(ghosts.get(3));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	/*
	 * public void followPacman(Ghost g)
	 * Permet au fantôme passé en paramètre, 
	 * de suivre Pacman
	 */
	public void followPacman(Ghost g) {
		if(pacman.getClass().equals(PacmanPaleYellow.class))
			moveRandom(g);
		else {
			if(pacman.getX() < g.getX() && !checkWall(g.getX()-Ghost.widthGhost/10,g.getY()))
				g.setX(g.getX()-1);
			else if(pacman.getX() > g.getX() && !checkWall(g.getX()+Ghost.widthGhost/10,g.getY()))
				g.setX(g.getX()+1);
			else if(pacman.getY() < g.getY() && !checkWall(g.getX(),g.getY()-Ghost.heightGhost/10))
				g.setY(g.getY()-1);
			else if(pacman.getY() > g.getY() && !checkWall(g.getX(),g.getY()+Ghost.heightGhost/10))
				g.setY(g.getY()+1);	
		}
	}
	
	/*
	 * public void bypassPacman(Ghost g)
	 * Permet au fantôme passé en paramètre 
	 * de contourner Pacman par le haut ou le bas
	 */
	public void bypassPacman(Ghost g) {
		if( (pacman.getX() <= g.getX()+Ghost.widthGhost && pacman.getX() >= g.getX()-Ghost.widthGhost)
				&& pacman.getY() < g.getY() && !checkWall(g.getX(),g.getY()-Ghost.heightGhost/10))
			g.setY(g.getY()-1);
		else if( (pacman.getX() <= g.getX()+Ghost.widthGhost && pacman.getX() >= g.getX()-Ghost.widthGhost)
				&& pacman.getX() == g.getX() && pacman.getY() > g.getY() && !checkWall(g.getX(),g.getY()+Ghost.heightGhost/10))
			g.setY(g.getY()+1);
		else if(g.getDirection().equals(Direction.DROITE) && !checkWall(g.getX()+Ghost.widthGhost/10,g.getY()))
			g.setX(g.getX()+1);
		else {
			g.setDirection(Direction.GAUCHE);
			reboundGhosts++;
		}
		if(g.getDirection().equals(Direction.GAUCHE) && !checkWall(g.getX()-Ghost.widthGhost/10,g.getY()))
			g.setX(g.getX()-1);
		else {
			g.setDirection(Direction.DROITE);
			reboundGhosts++;
		}
	}
	
	
	/*
	 * public void bypassPacman(Ghost g)
	 * Permet au fantôme passé en paramètre 
	 * de contourner Pacman par les cotés
	 */
	public void bypassPacman2(Ghost g) {
		if( (pacman.getY() <= g.getY()+Ghost.heightGhost && pacman.getY() >= g.getY()-Ghost.heightGhost) && pacman.getX() < g.getX())
			g.setX(g.getX()-1);
		else if( (pacman.getY() <= g.getY()+Ghost.heightGhost && pacman.getY() >= g.getY()-Ghost.heightGhost) && pacman.getX() > g.getX())
			g.setX(g.getX()+1);
		else if(g.getDirection().equals(Direction.HAUT) && !checkWall(g.getX(),g.getY()-Ghost.heightGhost/10))
			g.setY(g.getY()-1);
		else {
			g.setDirection(Direction.BAS);
			reboundGhosts++;
		}
		if(g.getDirection().equals(Direction.BAS) && !checkWall(g.getX(),g.getY()+Ghost.heightGhost/10))
			g.setY(g.getY()+1);
		else {
			g.setDirection(Direction.HAUT);
			reboundGhosts++;
		}
	}
	
	/*
	 * public void moveRandom(Ghost g)
	 * Permet au fantôme passé en paramètre
	 * de bouger de manière aléatoire
	 */
	public void moveRandom(Ghost g) {
		Random rand = new Random();
		int r = rand.nextInt(3) + 1;
		if(checkWall(g.getX()-Ghost.widthGhost/10,g.getY())) {
			reboundGhosts++;
			switch(r) {
				case 1 :
					g.setDirection(Direction.HAUT);
					break;
				case 2 :
					g.setDirection(Direction.DROITE);
					break;
				case 3 :
					g.setDirection(Direction.BAS);
					break;
			}
		}
		else if(checkWall(g.getX()+Ghost.widthGhost/10,g.getY()))  {
			reboundGhosts++;
			switch(r) {
				case 1 : 
					g.setDirection(Direction.HAUT);
					break;
				case 2 : 
					g.setDirection(Direction.GAUCHE);
					break;
				case 3 :
					g.setDirection(Direction.BAS);
					break;
			}
		}
		else if(checkWall(g.getX(),g.getY()-Ghost.heightGhost/10)) {
			reboundGhosts++;
			switch(r) {
			case 1 : 
				g.setDirection(Direction.GAUCHE);
				break;
			case 2 : 
				g.setDirection(Direction.DROITE);
				break;
			case 3 :
				g.setDirection(Direction.BAS);
				break;
			}
		}
		else if(checkWall(g.getX(),g.getY()+Ghost.heightGhost/10)) {
			reboundGhosts++;
			switch(r) {
			case 1 : 
				g.setDirection(Direction.HAUT);
				break;
			case 2 : 
				g.setDirection(Direction.DROITE);
				break;
			case 3 :
				g.setDirection(Direction.GAUCHE);
				break;
			}
		}
		stepGhost(g);
	}
	
	/*
	 * public void stepGhost(Ghost g)
	 * Permet au fantôme passé en paramètre d'avancer
	 */
	public void stepGhost(Ghost g){
		switch(g.getDirection()) {
		case HAUT :
			g.setY(g.getY()-1);
			break;
		case DROITE :
			g.setX(g.getX()+1);
			break;
		case BAS : 
			g.setY(g.getY()+1);
			break;
		case GAUCHE : 
			g.setX(g.getX()-1);
			break;
		default:
			break;
		}
	}
	
	/*
	 * public void moveGhostInWallMiddle()
	 * Permet aux fantômes dans le mur du milieu 
	 * de sortie du mur en se déplaçant vers le haut
	 */
	public void moveGhostInWallMiddle() {
		for(Ghost g : ghosts) {
			if(checkWall(g.getX(),g.getY()))
				g.setY(g.getY()-1);
		}
	}
	
	/*
	 * public void avoidPacman()
	 * Permet au fantôme d'eviter le pacman
	 */
	public void avoidPacman() {
		moveGhostInWallMiddle();
		for(Ghost g : ghosts) {
			if(pacman.getX() < g.getX() && !checkWall(g.getX()+Ghost.widthGhost/10,g.getY()))
				g.setX(g.getX()+1);
			else if(pacman.getX() > g.getX() && !checkWall(g.getX()-Ghost.widthGhost/10,g.getY()))
				g.setX(g.getX()-1);
			else if(pacman.getY() < g.getY() && !checkWall(g.getX(),g.getY()+Ghost.heightGhost/10))
				g.setY(g.getY()+1);
			else if(pacman.getY() > g.getY() && !checkWall(g.getX(),g.getY()-Ghost.heightGhost/10))
				g.setY(g.getY()-1);	
		}
	}
	
	/*
	 * public void wraparound()
	 * Permet de créer les portails 
	 * permettant à Pacman de se téléporter
	 * d'un coté ou de l'autre du labyrinthe
	 */
	public void wraparound () {
		if ( (pacman.getX() >= 0 && pacman.getX() <= 2*WIDTH/100 ) && (pacman.getY() >= 7*HEIGHT/13-15 && pacman.getY() <= 7*HEIGHT/13)) {
			pacman.setX((int) (WIDTH-Pacman.sizePacman*2));
			try {
				Thread.sleep(10);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if((pacman.getX() <= WIDTH-10 && pacman.getX() >= WIDTH-30) && (pacman.getY() >= 7*HEIGHT/13-15 && pacman.getY() <= 7*HEIGHT/13)) {
			pacman.setX(4*WIDTH/100);
			try {
				Thread.sleep(10);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * public void partie()
	 * Vérifie si la partie est finie
	 * (si Pacman n'a plus de vie ou 
	 * s'il n'y a plus de Pacgomme)
	 */
	public void game() {
		if(pacman.getLife() <= 0) {
			JOptionPane.showMessageDialog(this, "Vous n'avez plus de vie.\nGame Over\nScore : " + score);
			System.exit(0);
		}
		if(pacgommes.size() == 0) {
			JOptionPane.showMessageDialog(this, "Il n'y a plus de pacgommes.\nVous avez gagné la partie\nScore : " + score);
			System.exit(0);
		}
	}
	
	/*
	 * public void paint(Graphics g)
	 * Permet de dessiner la partie
	 */
	public void paint(Graphics g) {
		rebootTimer();
		paintEat();
		if(!pacman.getClass().equals(PacmanPaleYellow.class) || System.currentTimeMillis()/1000 - timerGhostTouch == 0 )
			touchGhost();
		paintPacman(g);
		paintGhost(g);
		paintPacgomme(g);
		if(labyrinth%1 == 0 && labyrinth%2 != 0)
			paintLabyrinth(g);
		else if (labyrinth%2 == 0) 
			paintLabyrinth2(g);
		movePacman();
		moveGhosts();
	}

}
