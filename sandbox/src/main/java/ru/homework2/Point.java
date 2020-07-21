package ru.homework2;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    double difX = this.x - p.x;
    double difY = this.y - p.y;
    double result = Math.sqrt(difX * difX + difY * difY);
    return result;
  }
}

