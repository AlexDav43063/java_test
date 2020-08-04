package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.UserData;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewUser() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillNewUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getName());
    type(By.name("middlename"), userData.getMiddleName());
    type(By.name("lastname"), userData.getLastName());
    type(By.name("nickname"), userData.getNick());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getStreet());
    type(By.name("home"), userData.getHome());

    if (creation) {
      try {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
      } catch (NoSuchElementException e) {
        System.out.println("Group named " + "\'" + userData.getGroup() + "\' was not found, the contact was created without a group");
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public boolean isThereAUser() {
    return isElementPresent(By.xpath("//tr[2]//td[8]//a[1]"));
  }

  NavigationHelper nav = new NavigationHelper(wd);

  public void createUser(UserData userData, boolean b) {
    initNewUser();
    fillNewUserForm(userData, b);
    submitNewUser();
    nav.goToHomePage();
  }
}
