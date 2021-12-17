package projet;

import java.awt.Color;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new PacmanView());
		jf.setSize(PacmanView.WIDTH+100, PacmanView.HEIGHT+100);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Pacman");
		jf.setVisible(true);
	}

}
