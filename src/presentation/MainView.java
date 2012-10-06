package presentation;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Cube;
import model.Edge2D;
import model.Point3D;
import model.Rotation3D;
import model.Sceen;
import model.Vector3D;

public class MainView extends Canvas {
  private static final long serialVersionUID = 6265611146451429502L;

  private List<Edge2D> edges = new ArrayList<Edge2D>();
  private int zoom = 4;

  private Sceen sceen = new Sceen(new ArrayList<Cube>());

  public Sceen getScene() {
    return sceen;
  }

  public MainView() {
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
          sceen.applayTranslation(new Vector3D(0, 0, -1));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
          sceen.applayTranslation(new Vector3D(0, 0, 1));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
          sceen.applayTranslation(new Vector3D(-1, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
          sceen.applayTranslation(new Vector3D(1, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          sceen.applayRotation(new Rotation3D(0, Math.PI / 90, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          sceen.applayRotation(new Rotation3D(0, -Math.PI / 90, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          sceen.applayRotation(new Rotation3D(Math.PI / 90, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          sceen.applayRotation(new Rotation3D(-Math.PI / 90, 0, 0));
          refresh(MainView.this);
        }
      }
    });

    this.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        int delta = e.getWheelRotation() * (-1);

        if (zoom + delta == 0) {
          if (delta < 0) {
            zoom = -1;
          } else if (delta > 0) {
            zoom = 1;
          }
        } else {
          zoom += delta;
        }
        refresh(MainView.this);
      }
    });
  }

  public void paint(Graphics graphics) {
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    for (Edge2D e : edges) {
      graphics.drawLine((int) e.getStart().getX(), (int) e.getStart().getY(), (int) e.getEnd().getX(), (int) e.getEnd()
            .getY());
    }
  }

  private void refresh(Canvas canvas) {
    List<Cube> cubes = sceen.getCubes();
    edges.clear();
    for (Cube c : cubes) {
      edges.addAll(c.getEdge2DNormalizedScaled(50, 400, 400, zoom));
    }
    System.out.println(edges);
    canvas.repaint();
  }

  public static void main(String[] args) {
    final MainView canvas = new MainView(); // We initialize our class here
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(canvas); // Here we add it to the frame
    frame.setVisible(true);

    Cube cube = new Cube(new Point3D(20, -10, 100), 80, 80, 80);
    List<Cube> cubes = new ArrayList<Cube>();
    cubes.add(cube);
    canvas.getScene().setCubes(cubes);
  }
}