package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData groupData = new GroupData()
            .withId(modifiedGroup.getId()).withName("Test1").withHeader("Test2").withFooter("Test3");
    app.getGroupHelper().modifyGroup(groupData);
    Set<GroupData> after = app.getGroupHelper().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(groupData);
    Assert.assertEquals(before, after);
  }
}
