package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

public class GroupModificationTest extends TestBase {
  @Test
  public  void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupMod();
    app.getGroupHelper().fillGroupForm(new GroupData("testa1", "testa2","testa3"));
    app.getGroupHelper().submitGroupMod();
    app.getNavigationHelper().goToGroupPage();
  }
}
