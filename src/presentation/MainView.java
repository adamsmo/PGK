package presentation;

import java.awt.Canvas;
import java.awt.Color;
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

  private final double TRANSLATION_SPEED = 5;
  private final double ROTATION_SPEED = Math.PI / 90;

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
          sceen.applayTranslation(new Vector3D(0, 0, -TRANSLATION_SPEED));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
          sceen.applayTranslation(new Vector3D(0, 0, TRANSLATION_SPEED));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
          sceen.applayTranslation(new Vector3D(-TRANSLATION_SPEED, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
          sceen.applayTranslation(new Vector3D(TRANSLATION_SPEED, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
          sceen.applayTranslation(new Vector3D(0, -TRANSLATION_SPEED, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
          sceen.applayTranslation(new Vector3D(0, TRANSLATION_SPEED, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          sceen.applayRotation(new Rotation3D(0, -ROTATION_SPEED, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          sceen.applayRotation(new Rotation3D(0, ROTATION_SPEED, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          sceen.applayRotation(new Rotation3D(-ROTATION_SPEED, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          sceen.applayRotation(new Rotation3D(ROTATION_SPEED, 0, 0));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
          sceen.applayRotation(new Rotation3D(0, 0, ROTATION_SPEED));
          refresh(MainView.this);
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
          sceen.applayRotation(new Rotation3D(0, 0, -ROTATION_SPEED));
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
    graphics.setColor(new Color(0, 0, 0));
    graphics.drawRect(40, 40, 40, 40);
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
    final MainView canvas = new MainView();
    JFrame window = new JFrame();
    window.setSize(500, 500);
    window.getContentPane().add(canvas);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Cube c1 = new Cube(new Point3D(100, -30, 100), 80, 80, 80);
    Cube c2 = new Cube(new Point3D(-100, -30, 100), 80, 80, 80);
    Cube c3 = new Cube(new Point3D(100, -30, -100), 80, 80, 80);
    Cube c4 = new Cube(new Point3D(-100, -30, -100), 80, 80, 80);
    Cube r = new Cube(new Point3D(0, -30, 0), 1, 40, 280);

    List<Cube> cubes = new ArrayList<Cube>();
    cubes.add(c1);
    cubes.add(c2);
    cubes.add(c3);
    cubes.add(c4);
    cubes.add(r);
    canvas.getScene().setCubes(cubes);
  }
}