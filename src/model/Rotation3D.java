package model;

public class Rotation3D {
  private double x_cw;
  private double y_cw;
  private double z_cw;

  public Rotation3D(double x_cw, double y_cw, double z_cw) {
    super();
    this.x_cw = x_cw;
    this.y_cw = y_cw;
    this.z_cw = z_cw;
  }

  public double getX_cw() {
    return x_cw;
  }

  public void setX_cw(double x_cw) {
    this.x_cw = x_cw;
  }

  public double getY_cw() {
    return y_cw;
  }

  public void setY_cw(double y_cw) {
    this.y_cw = y_cw;
  }

  public double getZ_cw() {
    return z_cw;
  }

  public void setZ_cw(double z_cw) {
    this.z_cw = z_cw;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x_cw);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y_cw);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(z_cw);
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
    Rotation3D other = (Rotation3D) obj;
    if (Double.doubleToLongBits(x_cw) != Double.doubleToLongBits(other.x_cw))
      return false;
    if (Double.doubleToLongBits(y_cw) != Double.doubleToLongBits(other.y_cw))
      return false;
    if (Double.doubleToLongBits(z_cw) != Double.doubleToLongBits(other.z_cw))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Rotation3D [x_cw=" + x_cw + ", y_cw=" + y_cw + ", z_cw=" + z_cw + "]";
  }

}
