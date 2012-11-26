package model;

public class Vector3D {
  private double x;
  private double y;
  private double z;

  public Vector3D(Point3D start, Point3D end) {
    x = end.getX() - start.getX();
    y = end.getY() - start.getY();
    z = end.getZ() - start.getZ();
  }

  public Vector3D(double x, double y, double z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
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

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(z);
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
    Vector3D other = (Vector3D) obj;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
      return false;
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
      return false;
    if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
      return false;
    return true;
  }

  public double dotProduct(Vector3D v) {
    return x * v.getX() + y * v.getY() + z * v.getZ();
  }

  @Override
  public String toString() {
    return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}
