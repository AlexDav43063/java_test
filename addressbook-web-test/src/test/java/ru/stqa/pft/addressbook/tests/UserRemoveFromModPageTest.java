package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

public class UserRemoveFromModPageTest extends TestBase {
  @Test
  public void testUserRemove() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name",
              "MName",
              "Last Name",
              "Alexxxx",
              "Company",
              "13 Elm Street",
              "222", "Test1"), true);
    }
    app.getUserHelper().initModUser();
    app.getUserHelper().removeUser();
    app.getNavigationHelper().goToHomePage();
  }
}
