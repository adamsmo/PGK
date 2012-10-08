package model;

public class Matrix {
  private double[][] a;

  public Matrix(double[][] a) {
    this.a = a;
  }

  public Matrix multiply(Matrix m) {
    if (m.a == null || m.a.length == 0 || m.a[0].length == 0 || this.a.length != m.a[0].length) {
      throw new RuntimeException("nie jest dobrze, nie da się pomnozyc");
    }

    double[][] p = new double[this.a.length][m.a[0].length];

    for (int i = 0; i < p.length; i++) {
      for (int j = 0; j < p[i].length; j++) {
        // p[i][j] =
        for (int k = 0; k < m.a.length; k++) {
          
        }
      }
    }

    return new Matrix(p);
  }
}
