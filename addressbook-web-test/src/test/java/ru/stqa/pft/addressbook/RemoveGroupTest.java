package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class RemoveGroupTest extends TestBase {

  @Test
  public void testRemoveGroup() throws Exception {
    goToGroupPage();
    selectGroup();
    removeSelectedGroup();
    goToGroupPage();
  }
}
