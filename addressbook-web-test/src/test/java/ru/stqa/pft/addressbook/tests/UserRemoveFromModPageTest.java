package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

import java.util.List;

public class UserRemoveFromModPageTest extends TestBase {
  @Test
  public void testUserRemove() {
    UserData user = new UserData("Name",
            "MName",
            "Last Name",
            "Alexxxx",
            "Company",
            "13 Elm Street",
            "222",
            "Test2");
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(user, true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initModUser();
    app.getUserHelper().removeUser();
    app.getNavigationHelper().goToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(0);
    Assert.assertEquals(before, after);
  }
}
