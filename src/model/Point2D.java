package model;

public class Point2D {
  private double x;
  private double y;
  private double depth;

  public Point2D(double x, double y, double depth) {
    super();
    this.x = x;
    this.y = y;
    this.depth = depth;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getDepth() {
    return depth;
  }

  public void setDepth(double depth) {
    this.depth = depth;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(depth);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    Point2D other = (Point2D) obj;
    if (Double.doubleToLongBits(depth) != Double.doubleToLongBits(other.depth))
      return false;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
      return false;
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Point2D [x=" + x + ", y=" + y + ", depth=" + depth + "]";
  }

}
