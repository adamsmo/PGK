package model;

public class Vector3D {
  private long x;
  private long y;
  private long z;

  public Vector3D(long x, long y, long z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public long getX() {
    return x;
  }

  public void setX(long x) {
    this.x = x;
  }

  public long getY() {
    return y;
  }

  public void setY(long y) {
    this.y = y;
  }

  public long getZ() {
    return z;
  }

  public void setZ(long z) {
    this.z = z;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (x ^ (x >>> 32));
    result = prime * result + (int) (y ^ (y >>> 32));
    result = prime * result + (int) (z ^ (z >>> 32));
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
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    if (z != other.z)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}
