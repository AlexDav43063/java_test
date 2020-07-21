package ru.newTestPackage.sandbox;

public class MyFirstClass {

  public static void main(String[] args) {
    System.out.println("Hello!!! World!!!");
    Square s = new Square(5);
    System.out.println("Площадь квадрата cо cтороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5,7);
    System.out.println("Площадь прямоугольника cо cторонами " + r.a + " и " + r.b + " = " + r.area());

  }

}