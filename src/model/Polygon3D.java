package model;

import java.util.ArrayList;

public class Polygon3D {

  // lista punktów w kolejności w której mają być połączone
  private ArrayList<Point3D> points;

  public ArrayList<Point3D> getPoints() {
    return points;
  }

  public void setPoints(ArrayList<Point3D> points) {
    this.points = points;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((points == null) ? 0 : points.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Polygon3D other = (Polygon3D) obj;
    if (points == null) {
      if (other.points != null)
        return false;
    } else if (!points.equals(other.points))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Polygon [points=" + points + "]";
  }

}
