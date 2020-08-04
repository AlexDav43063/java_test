package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class RemoveGroupTest extends TestBase {

  @Test
  public void testRemoveGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test1", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().removeSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
  }
}
