package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class RemoveGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testRemoveGroup() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size()-1);
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
  }
}
