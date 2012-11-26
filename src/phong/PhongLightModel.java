package phong;

import java.util.ArrayList;

import model.Point3D;
import model.Vector3D;

public class PhongLightModel {
  private Point3D camera = new Point3D(0, 0, 0);
  private Point3D light;
  private Point3D sphere = new Point3D(0, 0, 100);

  private static final int X = 250;
  private static final int Y = 250;
  private static final int SPHERE_R = 30;

  private static final int DARKNESS = 1;

  private double e = 2;
  private double a = 30;
  private double b = 50;
  private double c = 50;
  private double ks = 0.3;
  private double kd = 0.5;
  private double ka = 0.3;

  public PhongLightModel(Point3D light) {
    this.light = light;
  }

  public PhongLightModel(Point3D point3d, double c, double ks, double kd) {
    this.c = c;
    this.ks = ks;
    this.kd = kd;
  }

  public double[][] getPicture() {
    double[][] picture = new double[X][Y];
    for (int x = 0; x < picture.length; x++) {
      for (int y = 0; y < picture[x].length; y++) {
        picture[x][y] = calculatePhong(x, y);
      }
    }

    return picture;
  }

  private double calculatePhong(int x, int y) {
    int normalizedX = x - X / 2;
    int normalizedY = y - Y / 2;
    int normalizedZ = 200;

    Point3D viewingPoint = new Point3D(normalizedX, normalizedY, normalizedZ);

    ArrayList<Point3D> intersections = getInterSectionPoints(sphere, SPHERE_R, camera, viewingPoint);
    if (intersections.size() == 0) {
      return 0;
    }
    System.out.println("przecieło się dla  x = " + normalizedX + " y = " + normalizedY);
    Point3D surfaceCameraTouchPoint;
    if (intersections.size() == 1) {
      surfaceCameraTouchPoint = intersections.get(0);
    } else {
      double distance0 = distance(viewingPoint, intersections.get(0));
      double distance1 = distance(viewingPoint, intersections.get(1));
      if (distance0 < distance1) {
        surfaceCameraTouchPoint = intersections.get(0);
      } else {
        surfaceCameraTouchPoint = intersections.get(1);
      }
    }

    Vector3D n = new Vector3D(sphere, surfaceCameraTouchPoint);
    n = normalize(n);
    Vector3D l = new Vector3D(surfaceCameraTouchPoint, light);
    Vector3D r = mirror(l, n);
    Vector3D v = new Vector3D(surfaceCameraTouchPoint, camera);

    double L = 3;

    ArrayList<Point3D> lightIntersections = getInterSectionPoints(sphere, SPHERE_R, light, surfaceCameraTouchPoint);
    Point3D surfaceLightTouchPoint;
    if (lightIntersections.size() == 1) {
      surfaceLightTouchPoint = lightIntersections.get(0);
    } else {
      double distance0 = distance(light, lightIntersections.get(0));
      double distance1 = distance(light, lightIntersections.get(1));
      if (distance0 < distance1) {
        surfaceLightTouchPoint = lightIntersections.get(0);
      } else {
        surfaceLightTouchPoint = lightIntersections.get(1);
      }
    }
    if (distance(surfaceCameraTouchPoint, surfaceLightTouchPoint) > 0.001) {
      return DARKNESS;
    }

    double d = distance(sphere, light);

    double I = (1.0 / (a + b * d + c * d * d))
          * (L * kd * n.dotProduct(l) + L * ks * Math.pow((r.dotProduct(v)), e) + L * ka);

    return I < DARKNESS ? DARKNESS : I;
  }

  public Vector3D mirror(Vector3D v, Vector3D n) {
    // v-2n(v.dotProduct(v));
    Vector3D r = new Vector3D(0, 0, 0);
    r.setX(v.getX() - 2 * n.getX() * (v.dotProduct(n)));
    r.setY(v.getY() - 2 * n.getY() * (v.dotProduct(n)));
    r.setZ(v.getZ() - 2 * n.getZ() * (v.dotProduct(n)));
    return r;
  }

  private Vector3D normalize(Vector3D n) {
    double length = Math.sqrt((n.getX() * n.getX()) + (n.getY() * n.getY()) + (n.getZ() * n.getZ()));
    return new Vector3D(n.getX() / length, n.getY() / length, n.getZ() / length);
  }

  private double distance(Point3D p1, Point3D p2) {
    double dx = p1.getX() - p2.getX();
    double dy = p1.getY() - p2.getY();
    double dz = p1.getZ() - p2.getZ();
    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }

  public ArrayList<Point3D> getInterSectionPoints(Point3D sphereCenter, double r, Point3D lineStart, Point3D lineEnd) {
    double xA = lineStart.getX();
    double yA = lineStart.getY();
    double zA = lineStart.getZ();

    double xB = lineEnd.getX();
    double yB = lineEnd.getY();
    double zB = lineEnd.getZ();

    double xC = sphereCenter.getX();
    double yC = sphereCenter.getY();
    double zC = sphereCenter.getZ();

    double delta_a = (xB - xA) * (xB - xA) + (yB - yA) * (yB - yA) + (zB - zA) * (zB - zA);
    double delta_b = 2 * ((xB - xA) * (xA - xC) + (yB - yA) * (yA - yC) + (zB - zA) * (zA - zC));
    double delta_c = (xA - xC) * (xA - xC) + (yA - yC) * (yA - yC) + (zA - zC) * (zA - zC) - r * r;

    double delta = delta_b * delta_b - 4 * delta_a * delta_c;

    if (delta < 0) {
      return new ArrayList<Point3D>();
    }
    ArrayList<Point3D> points = new ArrayList<Point3D>();

    // pierwszy punkt
    double d = (-delta_b - Math.sqrt(delta)) / (2 * delta_a);
    double x = xA + d * (xB - xA);
    double y = yA + d * (yB - yA);
    double z = zA + d * (zB - zA);
    points.add(new Point3D(x, y, z));

    // drugi punkt
    d = (-delta_b + Math.sqrt(delta)) / (2 * delta_a);
    x = xA + d * (xB - xA);
    y = yA + d * (yB - yA);
    z = zA + d * (zB - zA);
    points.add(new Point3D(x, y, z));

    return points;
  }
}