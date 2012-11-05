package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cube extends Object3D {
  private List<Edge3D> edges;

  @Override
  public void applayTranslation(Vector3D v) {
    for (Edge3D e3D : edges) {
      e3D.setStart(translatePoint3d(e3D.getStart(), v));
      e3D.setEnd(translatePoint3d(e3D.getEnd(), v));
    }
  }

  @Override
  public void applayRotation(Rotation3D r) {
    for (Edge3D e3D : edges) {
      e3D.setStart(rotatePoint3d(e3D.getStart(), r));
      e3D.setEnd(rotatePoint3d(e3D.getEnd(), r));
    }
  }

  public List<Edge2D> getEdge2D(double startViewvingDistance) {
    ArrayList<Edge2D> edges2d = new ArrayList<Edge2D>();
    cutProtrudingEdges(startViewvingDistance);
    for (Edge3D e : edges) {
      if (e.isDrawable() == false) {
        continue;
      }
      if (e.getMiddleCut() == null) {
        Point3D startProjection = calculateCutPoint(new Edge3D(e.getStart(), new Point3D(0, 0, 0), null, false),
              startViewvingDistance);
        Point3D endProjection = calculateCutPoint(new Edge3D(e.getEnd(), new Point3D(0, 0, 0), null, false),
              startViewvingDistance);
        edges2d.add(new Edge2D(new Point2D(startProjection.getX(), startProjection.getY(), e.getStart().getZ()),
              new Point2D(endProjection.getX(), endProjection.getY(), e.getEnd().getZ())));
        continue;
      }
      if (e.getStart().getZ() < startViewvingDistance) {
        Point3D endProjection = calculateCutPoint(new Edge3D(e.getEnd(), new Point3D(0, 0, 0), null, false),
              startViewvingDistance);
        edges2d.add(new Edge2D(new Point2D(e.getMiddleCut().getX(), e.getMiddleCut().getY(), e.getStart().getZ()),
              new Point2D(endProjection.getX(), endProjection.getY(), e.getEnd().getZ())));
        continue;
      } else if (e.getEnd().getZ() < startViewvingDistance) {
        Point3D startProjection = calculateCutPoint(new Edge3D(e.getStart(), new Point3D(0, 0, 0), null, false),
              startViewvingDistance);
        edges2d.add(new Edge2D(new Point2D(startProjection.getX(), startProjection.getY(), e.getStart().getZ()),
              new Point2D(e.getMiddleCut().getX(), e.getMiddleCut().getY(), e.getEnd().getZ())));
        continue;
      }

      throw new RuntimeException("nie powinno nigdy tu wejsc!!!!!");
    }

    return edges2d;
  }

  public List<Edge2D> getEdge2DNormalized(double startViewvingDistance, int resX, int resY) {
    List<Edge2D> edges = getEdge2D(startViewvingDistance);
    List<Edge2D> normalizedEdges = new ArrayList<Edge2D>();

    for (Edge2D e : edges) {
      normalizedEdges.add(normalizeEdge(resX, resY, e));
    }

    return normalizedEdges;
  }

  public List<Edge2D> getEdge2DNormalizedScaled(int startViewvingDistance, int resX, int resY, int zoom) {
    if (zoom == 0) {
      throw new RuntimeException("nieprawidłowa");
    }
    List<Edge2D> edges2d = getEdge2D(startViewvingDistance);
    List<Edge2D> scaledEdges2d = new ArrayList<Edge2D>();
    double factor = 1;

    if (zoom < 0) {
      factor = 1.0 / Math.abs(zoom);
    } else {
      factor = 1.0 * Math.abs(zoom);
    }

    for (Edge2D e : edges2d) {
      scaledEdges2d.add(normalizeEdge(resX, resY, scaleEdge(factor, e)));
    }
    return scaledEdges2d;
  }

  private Edge2D normalizeEdge(int resX, int resY, Edge2D e) {
    Edge2D eN = Edge2D.getEmptyEdge();
    eN.getStart().setX(e.getStart().getX() + (resX / 2));
    eN.getEnd().setX(e.getEnd().getX() + (resX / 2));
    eN.getStart().setY(-e.getStart().getY() + (resY / 2));
    eN.getEnd().setY(-e.getEnd().getY() + (resY / 2));
    return eN;
  }

  private Edge2D scaleEdge(double factor, Edge2D e) {
    Edge2D es = Edge2D.getEmptyEdge();
    es.getStart().setX(e.getStart().getX() * factor);
    es.getStart().setY(e.getStart().getY() * factor);
    es.getEnd().setX(e.getEnd().getX() * factor);
    es.getEnd().setY(e.getEnd().getY() * factor);
    return es;
  }

  private void cutProtrudingEdges(double startViewvingDistance) {
    for (Edge3D e3D : edges) {
      if (e3D.getStart().getZ() > startViewvingDistance && e3D.getEnd().getZ() > startViewvingDistance) {
        e3D.setMiddleCut(null);
        e3D.setDrawable(true);
        continue;
      }
      if (e3D.getStart().getZ() <= startViewvingDistance && e3D.getEnd().getZ() <= startViewvingDistance) {
        e3D.setMiddleCut(null);
        e3D.setDrawable(false);
        continue;
      }
      if (e3D.getStart().getZ() <= startViewvingDistance || e3D.getEnd().getZ() <= startViewvingDistance) {
        e3D.setMiddleCut(calculateCutPoint(e3D, startViewvingDistance));
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
