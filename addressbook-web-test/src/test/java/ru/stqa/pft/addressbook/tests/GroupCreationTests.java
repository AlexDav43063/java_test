package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData groupData = new GroupData("Test1", "Test1", null);
    app.getGroupHelper().createGroup(groupData);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);


    int max = 0;
    for(GroupData g : after){
      if (g.getId()>max){
        max = g.getId();
      }
    }
    groupData.setId(max);
    before.add(groupData);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
