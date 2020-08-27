package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewUserCreationTest extends TestBase {


  @Test
  public void testNewUserCreation() throws Exception {
    Users before = app.user().all();
    File photo = new File("src/test/resources/Снимок экрана 2020-08-27 в 10.15.41.png");
    UserData user = new UserData().
            withName("Name")
            .withMiddleName("MName")
            .withLastName("Last Name")
            .withNick("Alexxxx")
            .withPhoto(photo)
            .withCompany("Company")
            .withStreet("13 Elm Street")
            .withHome("22").withWork("12345").withGroup("Test2");
    app.user().create(user, true);
    assertThat(app.user().count(), equalTo(before.size()+1));
    Users after = app.user().all();
    assertThat(after,equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
}
