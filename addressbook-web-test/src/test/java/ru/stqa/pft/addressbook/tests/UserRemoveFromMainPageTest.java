package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class UserRemoveFromMainPageTest extends TestBase {
  @BeforeMethod
  public void ensurePrecondition() {
    UserData user = new UserData().
            withName("Name")
            .withMiddleName("MName")
            .withLastName("Last Name")
            .withNick("Alexxxx")
            .withCompany("Company")
            .withStreet("13 Elm Street")
            .withHome("22")
            .withGroup("Test2");
    if (!app.user().isThereAUser()) {
      app.user().create(user, true);
    }
  }

  @Test
  public void testUserRemoveFromMainPage() {
    Users before = app.user().all();
    UserData deletedUser = before.iterator().next();
    app.user().deleteUserFromMainPage(deletedUser);
    Users after = app.user().all();

    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedUser)));
  }
}
