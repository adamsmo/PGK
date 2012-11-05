package model;

import java.util.ArrayList;
import java.util.List;

public class FilledCube extends Cube {

  public FilledCube(Point3D center, double height, double width, double depth) {
    super(center, height, width, depth);
  }

  @Override
  List<Polygon2D> getPolygons2d(double z) {
    List<Edge2D> edges2d = getEdge2D(z);
    List<Polygon2D> polygons2d = new ArrayList<Polygon2D>();

    edges2d.get(1).getStart();
    edges2d.get(2).getStart();
    edges2d.get(3).getStart();
    edges2d.get(4).getStart();

    edges2d.get(1).getStart();
    edges2d.get(5).getStart();
    edges2d.get(9).getStart();
    edges2d.get(6).getStart();

    edges2d.get(4).getStart();
    edges2d.get(5).getStart();
    edges2d.get(12).getStart();
    edges2d.get(8).getStart();

    edges2d.get(9).getStart();
    edges2d.get(10).getStart();
    edges2d.get(11).getStart();
    edges2d.get(12).getStart();
    
    edges2d.get(2).getStart();
    edges2d.get(7).getStart();
    edges2d.get(10).getStart();
    edges2d.get(6).getStart();
    
    edges2d.get(3).getStart();
    edges2d.get(7).getStart();
    edges2d.get(11).getStart();
    edges2d.get(8).getStart();
    
    return polygons2d;
  }
}
