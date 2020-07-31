package ru.newTestPackage.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTest {
  @Test
  public void tes0(){
    Equation e = new Equation(1,1,1);
    Assert.assertEquals(e.rootNumber(),0);
  }
  @Test
  public void tes1(){
    Equation e = new Equation(1,2,1);
    Assert.assertEquals(e.rootNumber(),1);
  }
  @Test
  public void tes2(){
    Equation e = new Equation(1,5,6);
    Assert.assertEquals(e.rootNumber(),2);
  }
  @Test
  public void testLinear(){
    Equation e = new Equation(0,1,1);
    Assert.assertEquals(e.rootNumber(),1);
  }
  @Test
  public void testConstant(){
    Equation e = new Equation(0,0,6);
    Assert.assertEquals(e.rootNumber(),0);
  }
  @Test
  public void testInf(){
    Equation e = new Equation(0,0,0);
    Assert.assertEquals(e.rootNumber(),-1);
  }
}
