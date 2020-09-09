package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTest extends TestBase {
    UserData userMod;

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
                .withWork("21124").inGroup(new GroupData().withName("test1"));
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().homePage();
        }
        if (app.db().users().size() == 0) {
            app.user().create(user, true);
        }
        userMod = app.db().users().iterator().next();
        if (app.db().groups().size() == userMod.getGroups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewTest1"));
            app.goTo().homePage();
        }
        app.user().selectUserById(userMod.getId());
    }

    @Test
    public void testAddUserToGroup() {
        int idGroupForAdd = getNameForAdd(userMod).iterator().next().getId();
        Users before = usersInGroups(idGroupForAdd);
        app.user().addUserToGroup(idGroupForAdd);
        Users after = usersInGroups(idGroupForAdd);;

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(userMod.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    public Groups getNameForAdd(UserData userMod) {
        Groups groups = app.db().groups();
        Groups userGroups = userMod.getGroups();
        groups.removeAll(userGroups);
        return groups;
    }

    private Users usersInGroups(int groupID){
        Users result = new Users();
        app.db().groups().forEach((b) -> {
            if (b.getId() == groupID) {
                if (b.getUsers().size() > 0) {
                    result.addAll(b.getUsers());
                }
            }
        });
        return result;
    }
}
