package model;

import java.util.List;

public abstract class Object3D {
  abstract void applyTranslation(Vector3D v);

  abstract void applyRotation(Rotation3D r);

  abstract List<Edge2D> getEdge2D(Point3D observer, Surface viewport);

  protected Point3D translatePoint3d(Point3D p, Vector3D v) {

    long[][] transformMtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { v.getX(), v.getY(), v.getZ(), 1 } };

    return new Point3D(1, 2, 3);
  }

  protected Point3D rotatePoint3d(Point3D p, Rotation3D r) {

    long[][] transformXmtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };
    long[][] transformYmtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };
    long[][] transformZmtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } };

    return new Point3D(1, 2, 3);
  }
}
