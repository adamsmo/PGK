package model;

public class Edge3D {
  private Point3D start;
  private Point3D end;
  private Point3D middleCut;
  private boolean drawable;

  public Edge3D(Point3D start, Point3D end, Point3D middleCut, boolean drawable) {
    super();
    this.start = start;
    this.end = end;
    this.middleCut = middleCut;
    this.drawable = drawable;
  }

  public Point3D getStart() {
    return start;
  }

  public void setStart(Point3D start) {
    this.start = start;
  }

  public Point3D getEnd() {
    return end;
  }

  public void setEnd(Point3D end) {
    this.end = end;
  }

  public Point3D getMiddleCut() {
    return middleCut;
  }

  public void setMiddleCut(Point3D middleCut) {
    this.middleCut = middleCut;
  }

  public boolean isDrawable() {
    return drawable;
  }

  public void setDrawable(boolean drawable) {
    this.drawable = drawable;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (drawable ? 1231 : 1237);
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((middleCut == null) ? 0 : middleCut.hashCode());
    result = prime * result + ((start == null) ? 0 : start.hashCode());
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
    Edge3D other = (Edge3D) obj;
    if (drawable != other.drawable)
      return false;
    if (end == null) {
      if (other.end != null)
        return false;
    } else if (!end.equals(other.end))
      return false;
    if (middleCut == null) {
      if (other.middleCut != null)
        return false;
    } else if (!middleCut.equals(other.middleCut))
      return false;
    if (start == null) {
      if (other.start != null)
        return false;
    } else if (!start.equals(other.start))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Edge3D [start=" + start + ", end=" + end + ", middleCut=" + middleCut + ", drawable=" + drawable + "]";
  }

}
