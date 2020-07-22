package ru.newTestPackage.sandbox.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.homework2.Point;

public class TestPoint {
  @Test
  public void testDistanceNull(){
    Point p1 = new Point(10,10);
    Point p2 = new Point(10,10);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
  @Test
  public void testDistance(){
    Point p1 = new Point(5,4);
    Point p2 = new Point(1,1);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
  @Test
  public void testDistanceScalar(){
    Point p1 = new Point(5,4);
    Point p2 = new Point(1,1);
    Assert.assertEquals(p1.distance(p2),p2.distance(p1));
  }
}
