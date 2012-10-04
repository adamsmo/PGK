package model;

import java.util.ArrayList;
import java.util.List;

public class Sceen {
  private List<Object3D> cubes;

  List<Object3D> getFrame() {
    return new ArrayList<Object3D>();
    // TODO implement
  }

  void applayTranslation(Vector3D v) {
    for (Object3D c : cubes) {
      c.applyTranslation(v);
    }
  }

  void applayRotation(Vector3D v) {
    for (Object3D c : cubes) {
      c.applyRotation(v);
    }
  }

  void applyZoom() {

  }

  public Sceen(List<Object3D> cubes) {
    super();
    this.cubes = cubes;
  }

  public List<Object3D> getCubes() {
    return cubes;
  }

  public void setCubes(List<Object3D> cubes) {
    this.cubes = cubes;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cubes == null) ? 0 : cubes.hashCode());
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
    Sceen other = (Sceen) obj;
    if (cubes == null) {
      if (other.cubes != null)
        return false;
    } else if (!cubes.equals(other.cubes))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Sceen [cubes=" + cubes + "]";
  }

}
