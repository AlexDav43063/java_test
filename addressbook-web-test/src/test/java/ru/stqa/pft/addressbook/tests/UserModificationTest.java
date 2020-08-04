package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.UserData;

public class UserModificationTest extends TestBase {
  @Test
  public void testUserModification() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name",
              "MName",
              "Last Name",
              "Alexxxx",
              "Company",
              "13 Elm Street",
              "222", "Test2"), true);
    }
    app.getUserHelper().initModUser();
    app.getUserHelper().fillNewUserForm(new UserData("Name",
            "MName",
            "Last Name",
            "Alexxxx",
            "Company",
            "13 Elm Street",
            "222",
            null), false);
    app.getUserHelper().updateUser();
    app.getNavigationHelper().goToHomePage();
  }
}
