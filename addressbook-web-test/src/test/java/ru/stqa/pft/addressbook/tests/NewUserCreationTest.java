package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewUserCreationTest extends TestBase {


  @Test
  public void testNewUserCreation() throws Exception {
    Users before = app.user().all();
    UserData user = new UserData().
            withName("Name")
            .withMiddleName("MName")
            .withLastName("Last Name")
            .withNick("Alexxxx")
            .withCompany("Company")
            .withStreet("13 Elm Street")
            .withHome("22").withGroup("Test2");
    app.user().create(user, true);
    Users after = app.user().all();

    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after,equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
}
