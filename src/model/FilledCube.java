package model;

import java.util.ArrayList;
import java.util.List;

public class FilledCube extends Cube {

  public FilledCube(Point3D center, double height, double width, double depth) {
    super(center, height, width, depth);
  }

  @Override
  public List<Polygon2D> getPolygons2dNormalizedScaled(int startViewvingDistance, int resX, int resY, int zoom) {
    List<Edge2D> edges2d = getEdge2DNormalizedScaled(50, 400, 400, zoom);
    List<Polygon2D> polygons2d = new ArrayList<Polygon2D>();

    polygons2d.add(new Polygon2D(
    edges2d.get(1 - 1).getStart(),
    edges2d.get(2 - 1).getStart(),
    edges2d.get(3 - 1).getStart(),
    edges2d.get(4 - 1).getStart()));

    polygons2d.add(new Polygon2D(
    edges2d.get(1 - 1).getStart(),
    edges2d.get(5 - 1).getStart(),
    edges2d.get(9 - 1).getStart(),
    edges2d.get(6 - 1).getStart()));

    polygons2d.add(new Polygon2D(
    edges2d.get(4 - 1).getStart(),
    edges2d.get(5 - 1).getStart(),
    edges2d.get(12 - 1).getStart(),
    edges2d.get(8 - 1).getStart()));

    polygons2d.add(new Polygon2D(
    edges2d.get(9 - 1).getStart(),
    edges2d.get(10 - 1).getStart(),
    edges2d.get(11 - 1).getStart(),
    edges2d.get(12 - 1).getStart()));

    polygons2d.add(new Polygon2D(
    edges2d.get(2 - 1).getStart(),
    edges2d.get(7 - 1).getStart(),
    edges2d.get(10 - 1).getStart(),
    edges2d.get(6 - 1).getStart()));

    polygons2d.add(new Polygon2D(
    edges2d.get(3 - 1).getStart(),
    edges2d.get(7 - 1).getStart(),
    edges2d.get(11 - 1).getStart(),
    edges2d.get(8 - 1).getStart()));

    return polygons2d;
  }
}
