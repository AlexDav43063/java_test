package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().homePage();
        }
        UserData user = new UserData().
                withName("Name")
                .withMiddleName("MName")
                .withLastName("Last Name")
                .withNick("Alexxxx")
                .withCompany("Company")
                .withStreet("13 Elm Street")
                .withHome("22")
                .withWork("21124").inGroup(new GroupData().withName("test1"));
        if (app.db().users().size() == 0) {
            app.user().create(user, true);
        }
    }

    @Test
    public void testUserModification() {
        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData().withId(modifiedUser.getId())
                .withName("Name")
                .withMiddleName("MName")
                .withLastName("Last Name")
                .withNick("Alexxxx")
                .withCompany("Company")
                .withStreet("13 Elm Street")
                .withHome("22")
                .withWork("211248989");
        app.user().modify(user);
        assertThat(app.group().count(), equalTo(before.size()));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
        verifyUserListInUI();
    }
}
