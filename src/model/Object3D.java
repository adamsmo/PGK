package model;

import java.util.List;

public abstract class Object3D {
  public abstract void applayTranslation(Vector3D v);

  public abstract void applayRotation(Rotation3D r);

  public abstract List<Polygon2D> getPolygons2dNormalizedScaled(int startViewvingDistance, int resX, int resY, int zoom);

  public static Point3D translatePoint3d(Point3D p, Vector3D v) {

    double[][] transformMtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { v.getX(), v.getY(), v.getZ(), 1 } };
    Matrix transformMatrix = new Matrix(transformMtx);

    double[][] pointMtx = { { p.getX(), p.getY(), p.getZ(), 1.0 } };
    Matrix pMtx = new Matrix(pointMtx);

    pMtx = pMtx.multiply(transformMatrix);

    double d = pMtx.get(0, 3);
    d = 1 / d;

    pMtx.multiply(d);

    return new Point3D(pMtx.get(0, 0), pMtx.get(0, 1), pMtx.get(0, 2));
  }

  public static Point3D rotatePoint3d(Point3D p, Rotation3D r) {

    double x = r.getX_cw();
    double y = r.getY_cw();
    double z = r.getZ_cw();

    double[][] transformXmtx = { { 1, 0, 0, 0 }, { 0, Math.cos(x), -Math.sin(x), 0 },
          { 0, Math.sin(x), Math.cos(x), 0 }, { 0, 0, 0, 1 } };

    double[][] transformYmtx = { { Math.cos(y), 0, Math.sin(y), 0 }, { 0, 1, 0, 0 },
          { -Math.sin(y), 0, Math.cos(y), 0 }, { 0, 0, 0, 1 } };

    double[][] transformZmtx = { { Math.cos(z), -Math.sin(z), 0, 0 }, { Math.sin(z), Math.cos(z), 0, 0 },
          { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

    Matrix tXmtx = new Matrix(transformXmtx);
    Matrix tYmtx = new Matrix(transformYmtx);
    Matrix tZmtx = new Matrix(transformZmtx);

    Matrix fullRotateMatrix = tXmtx.multiply(tYmtx);
    fullRotateMatrix = fullRotateMatrix.multiply(tZmtx);

    double[][] pointMtx = { { p.getX(), p.getY(), p.getZ(), 1.0 } };
    Matrix pMtx = new Matrix(pointMtx);

    pMtx = pMtx.multiply(fullRotateMatrix);

    double d = pMtx.get(0, 3);
    d = Math.abs(1 / d);

    pMtx = pMtx.multiply(d);

    return new Point3D(pMtx.get(0, 0), pMtx.get(0, 1), pMtx.get(0, 2));
  }

  public static Point3D calculateCutPoint(Edge3D e3d, double startViewvingDistance) {

    Point3D V0 = new Point3D(0, 0, startViewvingDistance);
    Vector3D n = new Vector3D(0, 0, 1);

    Point3D P0 = e3d.getStart();
    Point3D P1 = e3d.getEnd();

    // v0 - p0
    Vector3D w = new Vector3D(V0.getX() - P0.getX(), V0.getY() - P0.getY(), V0.getZ() - P0.getZ());
    // p1 - p0
    Vector3D u = new Vector3D(P1.getX() - P0.getX(), P1.getY() - P0.getY(), P1.getZ() - P0.getZ());

    double s = (vectorMultiply(n, w) / vectorMultiply(n, u));

    // punkt P0 - s * u

    double x = P0.getX() + s * u.getX();
    double y = P0.getY() + s * u.getY();
    double z = P0.getZ() + s * u.getZ();

    return new Point3D(x, y, z);
  }

  private static double vectorMultiply(Vector3D v1, Vector3D v2) {
    return (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ());
  }
}
