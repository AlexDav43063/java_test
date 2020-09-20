package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.models.Users;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeClass
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] objects) {
        logger.info("Start '" +m.getName() + "' test, with parameters " + Arrays.asList(objects));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] objects) {
        logger.info("Stop '" +m.getName() + "' test ");
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g)->new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }
    public void verifyUserListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Users dbUsers = app.db().users();
            Users uiUsers = app.user().all();
            assertThat(uiUsers, equalTo(dbUsers.stream()
                    .map((g)->new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }

}
