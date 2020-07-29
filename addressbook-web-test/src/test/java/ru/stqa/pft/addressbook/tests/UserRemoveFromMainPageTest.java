package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserRemoveFromMainPageTest extends TestBase {
  @Test
  public void testUserRemoveFromMainPage() {
    app.getUserHelper().selectUser();
    app.getUserHelper().removeUserForMainPage();
    app.getNavigationHelper().acceptAlert();
    app.getNavigationHelper().goToHomePage();
  }
}
