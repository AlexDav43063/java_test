package ru.newTestPackage.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {
  @Test
  public void testPrimesTrue(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }
  @Test(enabled = false)
  public void testPrimesTrueLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testPrimesFalse(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }
}
