package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Object3DTest {

  @Test
  public void testTranslatePoint3d() {
    Point3D translatedPoint3d = Object3D.translatePoint3d(new Point3D(1, 1, 1), new Vector3D(1, 1, 1));
    assertEquals(2.0, translatedPoint3d.getX(),0.0001);
    assertEquals(2.0, translatedPoint3d.getY(),0.0001);
    assertEquals(2.0, translatedPoint3d.getZ(),0.0001);
  }

  @Test
  public void testRotatePoint3d() {
    Point3D rotatedPoint3d = Object3D.rotatePoint3d(new Point3D(1, 1, 1), new Rotation3D(0, Math.PI, 0));
    assertEquals(-1.0, rotatedPoint3d.getX(),0.0001);
    assertEquals(1.0, rotatedPoint3d.getY(),0.0001);
    assertEquals(-1.0, rotatedPoint3d.getZ(),0.0001);
  }

}
