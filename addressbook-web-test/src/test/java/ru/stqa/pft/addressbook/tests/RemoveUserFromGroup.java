package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.models.UserData;
import ru.stqa.pft.addressbook.models.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveUserFromGroup extends TestBase {
    private UserData userForRem;
    private GroupData groupForRemoveUser;
    @BeforeMethod
    public void ensurePrecondition() {
        groupForRemoveUser = app.db().groups().stream().iterator().next();
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

        userForRem = app.db().users().iterator().next();
        if (userForRem.getGroups().size() == 0) {
            app.user().selectUserById(userForRem.getId());
            groupForRemoveUser = app.db().groups().stream().iterator().next();
            app.user().addUserToGroup(groupForRemoveUser.getId());
            app.goTo().homePage();
        } else if(app.db().groups().size() == userForRem.getGroups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewTest2"));
            app.goTo().homePage();
            groupForRemoveUser = getNameForRemove(userForRem).stream().iterator().next();
            app.user().selectUserById(userForRem.getId());
            app.user().addUserToGroup(groupForRemoveUser.getId());
            app.goTo().homePage();
        } else {
            groupForRemoveUser = getNameForRemove(userForRem).stream().iterator().next();
            app.user().selectUserById(userForRem.getId());
            app.user().addUserToGroup(groupForRemoveUser.getId());
            app.goTo().homePage();
        }
    }

    @Test
    public void testRemoveUserFromGroup() {
        app.goTo().homePage();
        app.user().selectGroupForImaging(groupForRemoveUser.getId());
        Users before = usersInGroups(groupForRemoveUser.getId());
        System.out.println(before);
        app.user().removeUserFromSelectedGroup(userForRem.getId());
        Users after = usersInGroups(groupForRemoveUser.getId());

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(userForRem)));
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

    public Groups getNameForRemove(UserData user) {
        Groups groups = app.db().groups();
        Groups userGroups = user.getGroups();
        groups.removeAll(userGroups);
        return groups;
    }
}
