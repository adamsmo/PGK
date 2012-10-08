package model;

public class Matrix {
  private double[][] a;

  public Matrix(double[][] a) {
    this.a = a;
  }

  public Matrix multiply(Matrix m) {
    if (m.a == null || m.a.length == 0 || m.a[0].length == 0 || this.a[0].length != m.a.length) {
      throw new RuntimeException("nie jest dobrze, nie da się pomnozyc");
    }

    double[][] p = new double[this.a.length][m.a[0].length];

    for (int i = 0; i < p.length; i++) {
      for (int j = 0; j < p[i].length; j++) {
        p[i][j] = 0;
        for (int k = 0; k < m.a.length; k++) {
          p[i][j] += this.a[i][k] * m.a[k][j];
        }
      }
    }

    return new Matrix(p);
  }

  public double get(int x, int y) {
    return a[x][y];
  }

  public Matrix multiply(double d) {
    double[][] p = new double[a.length][a[0].length];

    for (int i = 0; i < p.length; i++) {
      for (int j = 0; j < p[i].length; j++) {
        p[i][j] = a[i][j] * d;
      }
    }

    return new Matrix(p);
  }
}
