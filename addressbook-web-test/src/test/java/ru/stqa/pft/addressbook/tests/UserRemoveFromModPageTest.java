package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserRemoveFromModPageTest extends TestBase {
  @Test
  public void testUserRemove() {
    app.getUserHelper().initModUser();
    app.getUserHelper().removeUser();
    app.getNavigationHelper().goToHomePage();
  }
}
