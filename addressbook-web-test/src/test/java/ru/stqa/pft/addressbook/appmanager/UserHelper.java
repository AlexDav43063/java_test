package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewUser() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillNewUserForm(UserData userData, boolean creation) {
    String group_name = userData.getGroups().iterator().next().getName();
    type(By.name("firstname"), userData.getName());
    type(By.name("middlename"), userData.getMiddleName());
    type(By.name("lastname"), userData.getLastName());
    type(By.name("nickname"), userData.getNick());
//    typeForFile(By.name("photo"),userData.getPhoto());
    type(By.name("company"), userData.getCompany());
    type(By.name("address"), userData.getStreet());
    type(By.name("home"), userData.getHome());
    type(By.name("work"), userData.getWork());

    if (creation) {
      if(userData.getGroups().size()>0){
//        userData.getGroups();
        Assert.assertTrue(userData.getGroups().size() == 1);
       new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(group_name);
      } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
   }
  }

  public void addUserToGroup(int groupId) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(groupId));
    wd.findElement(By.xpath("//input[@name='add']")).click();
  }

  public void selectGroupForImaging(int groupId){
    new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(groupId));
  }

  public void removeUserFromSelectedGroup(int userId) {
    selectUserById(userId);
    wd.findElement(By.xpath("//input[@name='remove']")).click();
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

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void removeUserForMainPage() {
    click(By.xpath("//div[2]//input[1]"));
  }

  public boolean isThereAUser() {
    return isElementPresent(By.xpath("//tr[2]//td[8]//a[1]"));
  }

  public void selectUserById(int id) {

    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectUserByIdForMod(int id) {

    wd.findElement(By.xpath(".//input[@value='" + id + "']/..//following-sibling::td[7]/a")).click();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  NavigationHelper nav = new NavigationHelper(wd);

  public void create(UserData userData, boolean b) {
    initNewUser();
    fillNewUserForm(userData, b);
    submitNewUser();
    usersCache = null;
    nav.homePage();
  }

  public void modify(UserData userMod) {
    selectUserByIdForMod(userMod.getId());
    fillNewUserForm(userMod, false);
    updateUser();
    usersCache = null;
    nav.homePage();
  }

  public void deleteUserFromMainPage(UserData deletedUser) {
    selectUserById(deletedUser.getId());
    removeUserForMainPage();
    nav.acceptAlert();
    usersCache = null;
    nav.homePage();
  }

  public void deleteUserFromModPage(UserData deletedUser) {
    selectUserByIdForMod(deletedUser.getId());
    removeUser();
    usersCache = null;
    nav.homePage();
  }

  public UserData infoFromEditForm(UserData user) {
    selectUserByIdForMod(user.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withName(firstName).withLastName(lastName).withStreet(address).withHome(home).withMobile(mobile).withWork(work)
            .withEmail(email1).withEmail2(email2).withEmail3(email3);
  }

  public List<UserData> getUserList() {
    List<UserData> users = new ArrayList<>();
    List<WebElement> lastNames = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[3]"));
    List<WebElement> firstNames = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[2]"));
    List<WebElement> ids = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[1]/input"));
    for (int i = 0; i < lastNames.size(); i++) {
      String lname = lastNames.get(i).getText();
      String fname = firstNames.get(i).getText();
      int id = Integer.parseInt(ids.get(i).getAttribute("value"));
      UserData user = new UserData().withId(id).withLastName(lname).withName(fname);
      users.add(user);
    }
    return users;
  }

  private Users usersCache = null;

  public Users all() {
    if (usersCache != null) {
      return new Users(usersCache);
    }
    usersCache = new Users();
    List<WebElement> lastNames = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[3]"));
    List<WebElement> firstNames = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[2]"));
    List<WebElement> phones = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[6]"));
    List<WebElement> emails = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[5]"));
    List<WebElement> addresses = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[4]"));
    List<WebElement> ids = wd.findElements(By.xpath(".//tr[@name=\"entry\"]/td[1]/input"));
    for (int i = 0; i < lastNames.size(); i++) {
      String lName = lastNames.get(i).getText();
      String fName = firstNames.get(i).getText();
      String allPhones = phones.get(i).getText();
      String address = addresses.get(i).getText();
      String allEmails = emails.get(i).getText();
      int id = Integer.parseInt(ids.get(i).getAttribute("value"));
      usersCache.add(new UserData().withId(id).withName(lName).withLastName(fName).withAllPhones(allPhones)
      .withStreet(address).withAllEmails(allEmails));
    }
    return new Users(usersCache);
  }
}