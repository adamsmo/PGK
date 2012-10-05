package presentation;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Cube;
import model.Edge2D;
import model.Edge3D;
import model.Point3D;
import model.Rotation3D;

public class MainView extends Canvas {
  private static final long serialVersionUID = 6265611146451429502L;

  private static List<Edge2D> edges = new ArrayList<Edge2D>();

  public MainView() {
  }

  public void paint(Graphics graphics) {
    for (Edge2D e : edges) {
      graphics.drawLine((int) e.getStart().getX(), (int) e.getStart().getY(), (int) e.getEnd().getX(), (int) e.getEnd()
            .getY());
    }
  }

  public static void main(String[] args) {
    MainView canvas = new MainView(); // We initialize our class here
    JFrame frame = new JFrame();
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(canvas); // Here we add it to the frame
    frame.setVisible(true);

    List<Edge3D> edges3d = new ArrayList<Edge3D>();
    // 1
    edges3d.add(new Edge3D(new Point3D(13, 25, 40), new Point3D(39, 25, 54), null, true));
    // 2
    edges3d.add(new Edge3D(new Point3D(39, 25, 54), new Point3D(54, 15, 54), null, true));
    // 3
    edges3d.add(new Edge3D(new Point3D(54, 15, 54), new Point3D(54, 15, 15), null, true));
    // 4
    edges3d.add(new Edge3D(new Point3D(54, 15, 15), new Point3D(13, 25, 40), null, true));
    // 5
    edges3d.add(new Edge3D(new Point3D(13, 25, 40), new Point3D(15, -54, 15), null, true));
    // 6
    edges3d.add(new Edge3D(new Point3D(39, 25, 54), new Point3D(15, -54, 54), null, true));
    // 7
    edges3d.add(new Edge3D(new Point3D(54, 15, 54), new Point3D(30, -64, 44), null, true));
    // 8
    edges3d.add(new Edge3D(new Point3D(54, 15, 15), new Point3D(54, -54, 15), null, true));
    // 9
    edges3d.add(new Edge3D(new Point3D(15, -54, 15), new Point3D(15, -54, 54), null, true));
    // 10
    edges3d.add(new Edge3D(new Point3D(15, -54, 54), new Point3D(30, -64, 44), null, true));
    // 11
    edges3d.add(new Edge3D(new Point3D(30, -64, 44), new Point3D(54, -54, 15), null, true));
    // 12
    edges3d.add(new Edge3D(new Point3D(54, -54, 15), new Point3D(15, -54, 15), null, true));
    Cube cube = new Cube(edges3d);

    System.out.println(edges);
    while (true) {
      cube.applyRotation(new Rotation3D(0, Math.PI / 90, 0));
      edges = cube.getEdge2DNormalized(30, 400, 400);
      canvas.repaint();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}