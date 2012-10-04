package model;

public class Surface {
  private int a;
  private int b;
  private int c;

  public Surface(int a, int b, int c) {
    super();
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  public int getC() {
    return c;
  }

  public void setC(int c) {
    this.c = c;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + a;
    result = prime * result + b;
    result = prime * result + c;
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
    Surface other = (Surface) obj;
    if (a != other.a)
      return false;
    if (b != other.b)
      return false;
    if (c != other.c)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Surface [a=" + a + ", b=" + b + ", c=" + c + "]";
  }

}
