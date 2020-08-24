package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTest extends TestBase {

 @BeforeMethod
 public void ensurePrecondition() {
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
 }

  @Test
  public void testUserModification() {

    UserData userMod = new UserData("Name23",
            "MName",
            "Last Name214",
            "Alexxxx",
            "Company",
            "13 Elm Street",
            "222",
            null);

    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initModUser();
    app.getUserHelper().fillNewUserForm(userMod, false);
    app.getUserHelper().updateUser();
    app.goTo().goToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(0);
    before.add(userMod);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
