package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String name;
  private String middleName;
  private String lastName;
  private String nick;
  private String company;
  private String street;
  private String home;
  private String mobile;
  private String work;
  private String group;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(name, userData.name) &&
            Objects.equals(lastName, userData.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastName);
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", name='" + name + '\'' +
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

  public String getWork() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getMobile() {
    return mobile;
  }

  public UserData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }

  public UserData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public UserData withNick(String nick) {
    this.nick = nick;
    return this;
  }

  public UserData withCompany(String company) {
    this.company = company;
    return this;
  }

  public UserData withStreet(String street) {
    this.street = street;
    return this;
  }

  public UserData withHome(String home) {
    this.home = home;
    return this;
  }

  public UserData withWork(String work) {
    this.work = work;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }
}
