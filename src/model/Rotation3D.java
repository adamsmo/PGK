package model;

public class Rotation3D {
  private long x_cw;
  private long y_cw;
  private long z_cw;

  public Rotation3D(long x_cw, long y_cw, long z_cw) {
    super();
    this.x_cw = x_cw;
    this.y_cw = y_cw;
    this.z_cw = z_cw;
  }

  public long getX_cw() {
    return x_cw;
  }

  public void setX_cw(long x_cw) {
    this.x_cw = x_cw;
  }

  public long getY_cw() {
    return y_cw;
  }

  public void setY_cw(long y_cw) {
    this.y_cw = y_cw;
  }

  public long getZ_cw() {
    return z_cw;
  }

  public void setZ_cw(long z_cw) {
    this.z_cw = z_cw;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (x_cw ^ (x_cw >>> 32));
    result = prime * result + (int) (y_cw ^ (y_cw >>> 32));
    result = prime * result + (int) (z_cw ^ (z_cw >>> 32));
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
    Rotation3D other = (Rotation3D) obj;
    if (x_cw != other.x_cw)
      return false;
    if (y_cw != other.y_cw)
      return false;
    if (z_cw != other.z_cw)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Rotation3D [x_cw=" + x_cw + ", y_cw=" + y_cw + ", z_cw=" + z_cw + "]";
  }

}
