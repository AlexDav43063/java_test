package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData groupData = new GroupData().withName("Test2");
    app.getGroupHelper().createGroup(groupData);
    Set<GroupData> after = app.getGroupHelper().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    groupData.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(groupData);
    Assert.assertEquals(after, before);
  }

}
