package model;

import java.util.List;

import math.geom3d.line.StraightLine3D;
import math.geom3d.plane.Plane3D;

import Jama.Matrix;

public abstract class Object3D {
  abstract void applayTranslation(Vector3D v);

  abstract void applayRotation(Rotation3D r);

  abstract List<Edge2D> getEdge2D(double z);

  public static Point3D translatePoint3d(Point3D p, Vector3D v) {

    double[][] transformMtx = { { 1, 0, 0, 0 }, 
                                 { 0, 1, 0, 0 }, 
                                 { 0, 0, 1, 0 }, 
                                 { v.getX(), v.getY(), v.getZ(), 1 } };
    Matrix transformMatrix = new Matrix(transformMtx);

    double[][] pointMtx = { { p.getX(), p.getY(), p.getZ(), 1.0 } };
    Matrix pMtx = new Matrix(pointMtx);

    pMtx = pMtx.times(transformMatrix);

    double d = pMtx.get(0, 3);
    d = 1 / d;

    pMtx.times(d);

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

    Matrix fullRotateMatrix = tXmtx.times(tYmtx);
    fullRotateMatrix = fullRotateMatrix.times(tZmtx);

    double[][] pointMtx = { { p.getX(), p.getY(), p.getZ(), 1.0 } };
    Matrix pMtx = new Matrix(pointMtx);

    pMtx = pMtx.times(fullRotateMatrix);

    double d = pMtx.get(0, 3);
    d = Math.abs(1 / d);

    pMtx = pMtx.times(d);

    return new Point3D(pMtx.get(0, 0), pMtx.get(0, 1), pMtx.get(0, 2));
  }

  public static Point3D calculateCutPoint(Edge3D e3d, double z) {
    // uzycie biblioteki do policzenia gdzie znajduje sie punkt przeciecia
    // odcinaka który wychodzi "z ekranu" czyli przecinający plaszczyzne
    // rownolegla do osi OX OY przechodzaca przez punkt 0,0,0
    StraightLine3D line3d = new StraightLine3D(new math.geom3d.Point3D(e3d.getStart().getX(), e3d.getStart().getY(),
          e3d.getStart().getZ()),
          new math.geom3d.Point3D(e3d.getEnd().getX(), e3d.getEnd().getY(), e3d.getEnd().getZ()));
    Plane3D plane3d = new Plane3D(new math.geom3d.Point3D(0, 0, z), new math.geom3d.Vector3D(1, 1, 0),
          new math.geom3d.Vector3D(1, 2, 0));

    math.geom3d.Point3D intersection = plane3d.lineIntersection(line3d);
    // ------------------------------------------------------------------
    return new Point3D(intersection.getX(), intersection.getY(), intersection.getZ());
  }
}
