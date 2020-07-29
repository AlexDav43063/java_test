package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.models.UserData;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewUser() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillNewUserForm(UserData userData) {
    type(By.name("firstname"), userData.getName());
    type(By.name("middlename"), userData.getMiddleName());
    type(By.name("lastname"), userData.getLastName());
    type(By.name("nickname"), userData.getNick());
    type(By.name("company"),userData.getCompany());
    type(By.name("address"),userData.getStreet());
    type(By.name("home"),userData.getHome());
  }

  public void initNewUser() {
    click(By.linkText("add new"));
  }

  public void initModUser() {
    click(By.xpath("//tr[2]//td[8]//a[1]"));
  }

  public void updateUser() {
    click(By.name("update"));
  }

  public void removeUser() {
    click(By.xpath("//form[2]//input[2]"));
  }

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void removeUserForMainPage() {
    click(By.xpath("//div[2]//input[1]"));
  }
}
