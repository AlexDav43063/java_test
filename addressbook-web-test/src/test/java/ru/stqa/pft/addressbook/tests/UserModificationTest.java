package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTest extends TestBase {

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
    public void testUserModification() {
        Users before = app.user().all();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData().withId(modifiedUser.getId())
                .withName("Name")
                .withMiddleName("MName")
                .withLastName("Last Name")
                .withNick("Alexxxx")
                .withCompany("Company")
                .withStreet("13 Elm Street")
                .withHome("22")
                .withGroup("Test2");
        app.user().modify(user);
        assertThat(app.group().count(), equalTo(before.size()));
        Users after = app.user().all();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }
}
