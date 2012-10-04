package model;

import java.util.List;

public class Cube implements Object3D {
  private List<Edge3D> edges;
  private Point3D position;
  private float arc;

  @Override
  public void applyTranslation(Vector3D v) {
    // TODO Auto-generated method stub

  }

  @Override
  public void applyRotation(Vector3D r) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Edge2D> getEdge2D(Point3D observer, Surface viewport) {
    // TODO Auto-generated method stub
    return null;
  }

  public Cube(List<Edge3D> edges, Point3D position, float arc) {
    super();
    this.edges = edges;
    this.position = position;
    this.arc = arc;
  }

  public List<Edge3D> getEdges() {
    return edges;
  }

  public void setEdges(List<Edge3D> edges) {
    this.edges = edges;
  }

  public Point3D getPosition() {
    return position;
  }

  public void setPosition(Point3D position) {
    this.position = position;
  }

  public float getArc() {
    return arc;
  }

  public void setArc(float arc) {
    this.arc = arc;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(arc);
    result = prime * result + ((edges == null) ? 0 : edges.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
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
    Cube other = (Cube) obj;
    if (Float.floatToIntBits(arc) != Float.floatToIntBits(other.arc))
      return false;
    if (edges == null) {
      if (other.edges != null)
        return false;
    } else if (!edges.equals(other.edges))
      return false;
    if (position == null) {
      if (other.position != null)
        return false;
    } else if (!position.equals(other.position))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Cube [edges=" + edges + ", position=" + position + ", arc=" + arc + "]";
  }

}
