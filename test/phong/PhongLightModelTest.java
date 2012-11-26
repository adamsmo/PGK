package phong;

import java.util.ArrayList;

import model.Point3D;
import model.Vector3D;

import org.junit.Assert;
import org.junit.Test;

public class PhongLightModelTest {

  private PhongLightModel lm = new PhongLightModel(new Point3D(40, 40, 40));

  @Test
  public void intersection() {
    ArrayList<Point3D> expected = new ArrayList<Point3D>();
    expected.add(new Point3D(0, 0, -3));
    expected.add(new Point3D(0, 0, 3));
    Assert.assertEquals(expected,
          lm.getInterSectionPoints(new Point3D(0, 0, 0), 3, new Point3D(0, 0, -3), new Point3D(0, 0, 3)));
  }

  @Test
  public void mirror() {
    Vector3D l = new Vector3D(-1, 0, 2);
    Vector3D n = new Vector3D(-0.9, 0, -0.9);
    System.out.println(lm.mirror(l, n));
  }
}
