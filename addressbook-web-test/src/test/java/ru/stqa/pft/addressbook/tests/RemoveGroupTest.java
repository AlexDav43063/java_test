package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class RemoveGroupTest extends TestBase {

  @Test
  public void testRemoveGroup() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().removeSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
  }
}
