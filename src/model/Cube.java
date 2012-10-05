package model;

import java.util.ArrayList;
import java.util.List;

public class Cube extends Object3D {
  private List<Edge3D> edges;

  @Override
  public void applyTranslation(Vector3D v) {
    for (Edge3D e3D : edges) {
      e3D.setStart(translatePoint3d(e3D.getStart(), v));
      e3D.setEnd(translatePoint3d(e3D.getEnd(), v));
    }
  }

  @Override
  public void applyRotation(Rotation3D r) {
    for (Edge3D e3D : edges) {
      e3D.setStart(rotatePoint3d(e3D.getStart(), r));
      e3D.setEnd(rotatePoint3d(e3D.getEnd(), r));
    }
  }

  @Override
  public List<Edge2D> getEdge2D(double z) {
    ArrayList<Edge2D> edges2d = new ArrayList<Edge2D>();
    cutProtrudingEdges(z);
    for (Edge3D e : edges) {
      if (e.isDrawable() == false) {
        continue;
      }
      if (e.getMiddleCut() == null) {
        Point3D startProjection = calculateCutPoint(new Edge3D(e.getStart(), new Point3D(0, 0, 0), null, false), z);
        Point3D endProjection = calculateCutPoint(new Edge3D(e.getEnd(), new Point3D(0, 0, 0), null, false), z);
        edges2d.add(new Edge2D(new Point2D(startProjection.getX(), startProjection.getY()), new Point2D(endProjection
              .getX(), endProjection.getY())));
        continue;
      }
      if (e.getStart().getZ() < 0) {
        Point3D endProjection = calculateCutPoint(new Edge3D(e.getEnd(), new Point3D(0, 0, 0), null, false), z);
        edges2d.add(new Edge2D(new Point2D(e.getMiddleCut().getX(), e.getMiddleCut().getY()), new Point2D(endProjection
              .getX(), endProjection.getY())));
        continue;
      } else if (e.getEnd().getZ() < 0) {
        Point3D startProjection = calculateCutPoint(new Edge3D(e.getStart(), new Point3D(0, 0, 0), null, false), z);
        edges2d.add(new Edge2D(new Point2D(startProjection.getX(), startProjection.getY()), new Point2D(e
              .getMiddleCut().getX(), e.getMiddleCut().getY())));
        continue;
      }

      throw new RuntimeException("nie powinno nigdy tu wejsc!!!!!");
    }

    return edges2d;
  }

  public List<Edge2D> getEdge2DNormalized(double z, int resX, int resY) {
    List<Edge2D> edges = getEdge2D(z);
    List<Edge2D> normalizedEdges = new ArrayList<Edge2D>();
    for (Edge2D e : edges) {
      e.getStart().setX(e.getStart().getX() + (resX / 2));
      e.getEnd().setX(e.getEnd().getX() + (resX / 2));
      e.getStart().setY(-e.getStart().getY() + (resY / 2));
      e.getEnd().setY(-e.getEnd().getY() + (resY / 2));
      normalizedEdges.add(e);
    }
    return normalizedEdges;
  }

  private void cutProtrudingEdges(double z) {
    for (Edge3D e3D : edges) {
      if (e3D.getStart().getZ() > 0 && e3D.getEnd().getZ() > 0) {
        e3D.setMiddleCut(null);
        e3D.setDrawable(true);
        continue;
      }
      if (e3D.getStart().getZ() <= 0 && e3D.getEnd().getZ() <= 0) {
        e3D.setMiddleCut(null);
        e3D.setDrawable(false);
        continue;
      }
      if (e3D.getStart().getZ() <= 0 || e3D.getEnd().getZ() <= 0) {
        e3D.setMiddleCut(calculateCutPoint(e3D, z));
        e3D.setDrawable(true);
        continue;
      }
    }
  }

  public Cube(Point3D center, double height, double width, double depth) {
    this.edges = new ArrayList<Edge3D>();
    Point3D point3d1 = new Point3D(center.getX() - width / 2, center.getY() + height, center.getZ() - depth / 2);
    Point3D point3d2 = new Point3D(center.getX() - width / 2, center.getY() + height, center.getZ() + depth / 2);
    Point3D point3d3 = new Point3D(center.getX() + width / 2, center.getY() + height, center.getZ() + depth / 2);
    Point3D point3d4 = new Point3D(center.getX() + width / 2, center.getY() + height, center.getZ() - depth / 2);
    Point3D point3d5 = new Point3D(center.getX() - width / 2, center.getY(), center.getZ() - depth / 2);
    Point3D point3d6 = new Point3D(center.getX() - width / 2, center.getY(), center.getZ() + depth / 2);
    Point3D point3d7 = new Point3D(center.getX() + width / 2, center.getY(), center.getZ() + depth / 2);
    Point3D point3d8 = new Point3D(center.getX() + width / 2, center.getY(), center.getZ() - depth / 2);
    // 1
    edges.add(new Edge3D(point3d1, point3d2, null, true));
    // 2
    edges.add(new Edge3D(point3d2, point3d3, null, true));
    // 3
    edges.add(new Edge3D(point3d3, point3d4, null, true));
    // 4
    edges.add(new Edge3D(point3d4, point3d1, null, true));
    // 5
    edges.add(new Edge3D(point3d1, point3d5, null, true));
    // 6
    edges.add(new Edge3D(point3d2, point3d6, null, true));
    // 7
    edges.add(new Edge3D(point3d3, point3d7, null, true));
    // 8
    edges.add(new Edge3D(point3d4, point3d8, null, true));
    // 9
    edges.add(new Edge3D(point3d5, point3d6, null, true));
    // 10
    edges.add(new Edge3D(point3d6, point3d7, null, true));
    // 11
    edges.add(new Edge3D(point3d7, point3d8, null, true));
    // 12
    edges.add(new Edge3D(point3d8, point3d5, null, true));
  }

  public Cube(List<Edge3D> edges) {
    super();
    this.edges = edges;
  }

  public List<Edge3D> getEdges() {
    return edges;
  }

  public void setEdges(List<Edge3D> edges) {
    this.edges = edges;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((edges == null) ? 0 : edges.hashCode());
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
    if (edges == null) {
      if (other.edges != null)
        return false;
    } else if (!edges.equals(other.edges))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Cube [edges=" + edges + "]";
  }

}
