package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.models.UserData;

public class NewUserCreationTest extends TestBase {


  @Test
  public void testNewUserCreation() throws Exception {
    app.getUserHelper().initNewUser();
    app.getUserHelper().fillNewUserForm(new UserData("Name", "MName", "Last Name", "Alexxxx", "Company", "13 Elm Street", "222"));
    app.getUserHelper().submitNewUser();
    app.getNavigationHelper().goToHomePage();
  }
}
