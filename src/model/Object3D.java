package model;

import java.util.List;

import Jama.Matrix;

public abstract class Object3D {
  abstract void applyTranslation(Vector3D v);

  abstract void applyRotation(Rotation3D r);

  abstract List<Edge2D> getEdge2D(Point3D observer, Surface viewport);

  public static Point3D translatePoint3d(Point3D p, Vector3D v) {

    double[][] transformMtx = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { v.getX(), v.getY(), v.getZ(), 1 } };
    Matrix transformMatrix = new Matrix(transformMtx);
    
    double[][] pointMtx = {{p.getX(),p.getY(),p.getZ(),1.0}};
    Matrix pMtx = new Matrix(pointMtx);
    
    pMtx = pMtx.times(transformMatrix);
    
    double d = pMtx.get(0, 3);
    d = 1/d;
    
    pMtx.times(d);
    
    return new Point3D(pMtx.get(0, 0), pMtx.get(0, 1), pMtx.get(0, 1));
  }

  public static Point3D rotatePoint3d(Point3D p, Rotation3D r) {

    double x = r.getX_cw();
    double y = r.getY_cw();
    double z = r.getZ_cw();
    
    double[][] transformXmtx = { { 1, 0, 0, 0 }, 
                                  { 0, Math.cos(x), -Math.sin(x), 0 }, 
                                  { 0, Math.sin(x), Math.cos(x), 0 }, 
                                  { 0, 0, 0, 1 } };
    
    double[][] transformYmtx = { { Math.cos(y), 0, Math.sin(y), 0 }, 
                                  { 0, 1, 0, 0 }, 
                                  { -Math.sin(y), 0, Math.cos(y), 0 }, 
                                  { 0, 0, 0, 1 } };
    
    double[][] transformZmtx = { { Math.cos(z), -Math.sin(z), 0, 0 }, 
                                  { Math.sin(z), Math.cos(z), 0, 0 }, 
                                  { 0, 0, 1, 0 }, 
                                  { 0, 0, 0, 1 } };
    
    Matrix tXmtx = new Matrix(transformXmtx);
    Matrix tYmtx = new Matrix(transformYmtx);
    Matrix tZmtx = new Matrix(transformZmtx);
    
    Matrix fullRotateMatrix = tXmtx.times(tYmtx);
    fullRotateMatrix = fullRotateMatrix.times(tZmtx);
    
    double[][] pointMtx = {{p.getX(),p.getY(),p.getZ(),1.0}};
    Matrix pMtx = new Matrix(pointMtx);
    
    pMtx = pMtx.times(fullRotateMatrix);
    
    double d = pMtx.get(0, 3);
    d = Math.abs(1/d);
    
    pMtx = pMtx.times(d);
    
    return new Point3D(pMtx.get(0, 0), pMtx.get(0, 1), pMtx.get(0, 2));
  }
}
