package presentation;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Cube;
import model.Edge2D;
import model.Point3D;
import model.Rotation3D;
import model.Vector3D;

public class MainView extends Canvas {
  private static final long serialVersionUID = 6265611146451429502L;

  private static List<Edge2D> edges = new ArrayList<Edge2D>();

  public MainView() {
  }

  public void paint(Graphics graphics) {
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    for (Edge2D e : edges) {
      graphics.drawLine((int) e.getStart().getX(), (int) e.getStart().getY(), (int) e.getEnd().getX(), (int) e.getEnd()
            .getY());
    }
  }

  public static void main(String[] args) {
    final MainView canvas = new MainView(); // We initialize our class here
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(canvas); // Here we add it to the frame
    frame.setVisible(true);

    final Cube cube = new Cube(new Point3D(20, 50, 100), 40, 40, 40);
    edges = cube.getEdge2DNormalized(30, 400, 400);

    canvas.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {

        System.out.println("keyTyped " + e.getKeyChar());
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("keyReleased");

      }

      @Override
      public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
          System.out.println("keyTyped");
          cube.applyTranslation(new Vector3D(0, 0, -1));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
          System.out.println("keyTyped");
          cube.applyTranslation(new Vector3D(0, 0, 1));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
          System.out.println("keyTyped");
          cube.applyTranslation(new Vector3D(-1, 0, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
          System.out.println("keyTyped");
          cube.applyTranslation(new Vector3D(1, 0, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          System.out.println("keyTyped");
          cube.applyRotation(new Rotation3D(0, Math.PI / 90, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          System.out.println("keyTyped");
          cube.applyRotation(new Rotation3D(0, -Math.PI / 90, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          System.out.println("keyTyped");
          cube.applyRotation(new Rotation3D(Math.PI / 90, 0, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          System.out.println("keyTyped");
          cube.applyRotation(new Rotation3D(-Math.PI / 90, 0, 0));
          edges = cube.getEdge2DNormalized(30, 400, 400);
          canvas.repaint();
        }

        System.out.println("keyReleased");
      }
    });

    // while (true) {
    // cube.applyRotation(new Rotation3D(0, Math.PI / 45, 0));
    // edges = cube.getEdge2DNormalized(30, 400, 400);
    // System.out.println(edges);
    // canvas.repaint();
    // try {
    // Thread.sleep(100);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
  }
}