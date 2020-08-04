package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

public class UserRemoveFromMainPageTest extends TestBase {
  @Test
  public void testUserRemoveFromMainPage() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name",
              "MName",
              "Last Name",
              "Alexxxx",
              "Company",
              "13 Elm Street",
              "222", "Test1"), true);
    }
    app.getUserHelper().selectUser();
    app.getUserHelper().removeUserForMainPage();
    app.getNavigationHelper().acceptAlert();
    app.getNavigationHelper().goToHomePage();
  }
}
