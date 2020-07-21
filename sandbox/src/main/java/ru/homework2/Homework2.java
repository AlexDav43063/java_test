package ru.homework2;

public class Homework2 {
  public static void main(String[] args) {
    Point p1 = new Point(3, 6);
    Point p2 = new Point(5, 3);
    /*Вычисление расстояния между точками при помощи метода в классе Point*/
    System.out.println("Раccтояние между двумя точками c координатами (" + p1.x + "," + p1.y + ")" + " и (" + p2.x + "," + p2.y + ") равно =" + p2.distance(p1));
    /*Вычисление расстояния между точками при помощи функции distance */
    System.out.println("Раccтояние между двумя точками c координатами (" + p1.x + "," + p1.y + ")" + " и (" + p2.x + "," + p2.y + ") равно =" + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    double difX = p2.x - p1.x;
    double difY = p2.y - p1.y;
    double result = Math.sqrt(difX * difX + difY * difY);
    return result;
  }
}
