package phong;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import model.Point3D;

public class PhongPresentation {

  private static BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
  private static PhongLightModel lm = new PhongLightModel(new Point3D(100, 0, 100));
  private static ImageDrawingComponent drawingComponent = new ImageDrawingComponent(image);

  private static BufferedImage repaint(BufferedImage image, PhongLightModel lm) {
    int[][] picture = normalizePicture(lm.getPicture());
    for (int x = 0; x < picture.length; x++) {
      for (int y = 0; y < picture[x].length; y++) {
        Color c;
        if (picture[x][y] == 0) {
          c = new Color(0, 0, 200);
        } else {
          c = new Color(picture[x][y], picture[x][y], picture[x][y]);
        }
        image.setRGB(x, y, c.getRGB());
      }
    }
    return image;
  }

  private static int[][] normalizePicture(double[][] picture) {
    double factor = 255.0 / 250.0;
    int[][] result = new int[picture.length][picture[0].length];

    for (int x = 0; x < picture.length; x++) {
      for (int y = 0; y < picture[x].length; y++) {
        result[x][y] = (int) (picture[x][y] * factor);
        if (result[x][y] > 0) {
        }
        if (result[x][y] > 255) {
          result[x][y] = 255;
        } else if (result[x][y] < 0) {
          result[x][y] = 0;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    image = repaint(image, lm);
    JFrame window = new JFrame();
    window.setSize(500, 500);

    final String[] materialType = { "półmatowy", "błyszczący", "matowy" };
    final SpinnerListModel materialTypeModel = new SpinnerListModel(materialType);
    final String[] positions = { "side", "top", "corner" };
    final SpinnerListModel positionModel = new SpinnerListModel(positions);

    Button b = new Button("Rysuj");
    b.addMouseListener(new MouseListener() {

      @Override
      public void mouseReleased(MouseEvent arg0) {
      }

      @Override
      public void mousePressed(MouseEvent arg0) {
      }

      @Override
      public void mouseExited(MouseEvent arg0) {
      }

      @Override
      public void mouseEntered(MouseEvent arg0) {
      }

      @Override
      public void mouseClicked(MouseEvent arg0) {
        String materialType = (String) materialTypeModel.getValue();
        String position = (String) positionModel.getValue();

        double c = 0;
        double ks = 0;
        double kd = 0;

        double cX = 0;
        double cY = 0;
        double cZ = 0;

        if ("błyszczący".equals(materialType)) {
          c = 32;
          ks = 0.45;
          kd = 0.9;
        }
        if ("półmatowy".equals(materialType)) {
          c = 50;
          ks = 0.3;
          kd = 0.5;
        }
        if ("matowy".equals(materialType)) {
          c = 32;
          ks = 0.1;
          kd = 0.9;
        }
        if ("top".equals(position)) {
          cX = 0;
          cY = -100;
          cZ = 100;
        }
        if ("corner".equals(position)) {
          cX = 100;
          cY = 100;
          cZ = 100;
        }
        if ("side".equals(position)) {
          cX = 100;
          cY = 0;
          cZ = 100;
        }
        PhongLightModel ltm = new PhongLightModel(new Point3D(cX, cY, cZ), c, ks, kd);
        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        image = repaint(image, ltm);
        drawingComponent.bi = image;
        drawingComponent.repaint();
      }
    });

    JSpinner positionsSpinner = new JSpinner(positionModel);
    JSpinner materialTypeSpinner = new JSpinner(materialTypeModel);

    JPanel p = new JPanel();
    p.setBounds(20, 270, 60, 40);
    p.add(b);
    positionsSpinner.setBounds(20, 310, 100, 40);
    materialTypeSpinner.setBounds(20, 350, 100, 40);

    window.add(positionsSpinner);
    window.add(materialTypeSpinner);
    window.add(p);
    window.add(drawingComponent);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
