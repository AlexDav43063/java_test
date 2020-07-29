package ru.stqa.pft.addressbook.models;

public class UserData {
  private final String name;
  private final String middleName;
  private final String lastName;
  private final String nick;
  private final String company;
  private final String street;
  private final String home;

  public UserData(String name, String MiddleName, String lastName, String nick, String company, String street, String home) {
    this.name = name;
    middleName = MiddleName;
    this.lastName = lastName;
    this.nick = nick;
    this.company = company;
    this.street = street;
    this.home = home;
  }

  public String getName() {
    return name;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNick() {
    return nick;
  }

  public String getCompany() {
    return company;
  }

  public String getStreet() {
    return street;
  }

  public String getHome() {
    return home;
  }
}
