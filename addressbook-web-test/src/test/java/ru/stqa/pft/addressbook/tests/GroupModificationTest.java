package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupModificationTest extends TestBase {
  @Test
  public  void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test1", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupMod();
    app.getGroupHelper().fillGroupForm(new GroupData("Test1", "Test2","Test3"));
    app.getGroupHelper().submitGroupMod();
    app.getNavigationHelper().goToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before);
  }
}
