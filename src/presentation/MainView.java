package presentation;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;

public class MainView extends Canvas {
  private static final long serialVersionUID = 6265611146451429502L;

  static private int x1 = 2;
  static private int x2 = 4;
  static private int y1 = 40;
  static private int y2 = 30;
  
  
  public MainView() {
	}

	public void paint(Graphics graphics) {
	  graphics.drawLine(x1, x2, y1, y2);
	}

	public static void main(String[] args) {
		MainView canvas = new MainView(); // We initialize our class here
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(canvas); // Here we add it to the frame
		frame.setVisible(true);
		
		Random r =  new Random(new Date().getTime());
		while(true){
		  y2 = r.nextInt(300);
		  canvas.repaint(1000/60);
		}
	}
}