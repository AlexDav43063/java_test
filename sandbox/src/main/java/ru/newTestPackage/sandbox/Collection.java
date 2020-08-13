package ru.newTestPackage.sandbox;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Collection {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> lang = new ArrayList<String>();
    lang.add("Java");
    lang.add("C#");
    lang.add("Python");
    lang.add("C++");

    for(String l : lang){
      System.out.println("Я хочу выучить " + l);
    }
  }
}