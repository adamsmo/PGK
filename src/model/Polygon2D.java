package model;

import java.util.ArrayList;

public class Polygon2D {

  // lista punktów w kolejności w której mają być połączone
  private ArrayList<Point2D> points;

  public Polygon2D(Point2D... pts) {
    points = new ArrayList<Point2D>();
    for (Point2D p : pts) {
      points.add(p);
    }
  }

  public ArrayList<Point2D> getPoints() {
    return points;
  }

  public void setPoints(ArrayList<Point2D> points) {
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
    Polygon2D other = (Polygon2D) obj;
    if (points == null) {
      if (other.points != null)
        return false;
    } else if (!points.equals(other.points))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Polygon2D [points=" + points + "]";
  }

}
