package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

public class NewUserCreationTest extends TestBase {


  @Test
  public void testNewUserCreation() throws Exception {
    app.getUserHelper().initNewUser();
    app.getUserHelper().fillNewUserForm(new UserData("Name",
            "MName",
            "Last Name",
            "Alexxxx",
            "Company",
            "13 Elm Street",
            "222",
            "Test1"),true);
    app.getUserHelper().submitNewUser();
    app.getNavigationHelper().goToHomePage();
  }
}
