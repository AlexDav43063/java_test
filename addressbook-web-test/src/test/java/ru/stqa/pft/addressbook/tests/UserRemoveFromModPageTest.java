package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class UserRemoveFromModPageTest extends TestBase {
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
                .withWork("21124");
        if (app.db().users().size() == 0) {
            app.user().create(user, true);
        }
    }

    @Test
    public void testUserRemove() {
        Users before = app.db().users();
        UserData deletedUser = before.iterator().next();
        app.user().deleteUserFromModPage(deletedUser);
        assertEquals(app.user().count(), before.size() - 1);
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(deletedUser)));
    }
}
