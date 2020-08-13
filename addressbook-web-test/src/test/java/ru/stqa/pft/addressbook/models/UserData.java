package ru.stqa.pft.addressbook.models;

public class UserData {
  private int id;
  private final String name;
  private final String middleName;
  private final String lastName;
  private final String nick;
  private final String company;
  private final String street;
  private final String home;
  private String group;

  public UserData(String name, String MiddleName, String lastName, String nick, String company, String street, String home, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.middleName = MiddleName;
    this.lastName = lastName;
    this.nick = nick;
    this.company = company;
    this.street = street;
    this.home = home;
    this.group = group;
  }

  public UserData(int id, String name, String MiddleName, String lastName, String nick, String company, String street, String home, String group) {
    this.id = id;
    this.name = name;
    this.middleName = MiddleName;
    this.lastName = lastName;
    this.nick = nick;
    this.company = company;
    this.street = street;
    this.home = home;
    this.group = group;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
    return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
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

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }
}
