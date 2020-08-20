package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void removeSelectedGroup() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupMod() {
        click(By.name("edit"));
    }

    public void submitGroupMod() {
        click(By.name("update"));
    }

    NavigationHelper nav = new NavigationHelper(wd);

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        nav.goToGroupPage();
    }

    public void modifyGroup(GroupData groupData) {
        selectGroupById(groupData.getId());
        initGroupMod();
        fillGroupForm(groupData);
        submitGroupMod();
        nav.goToGroupPage();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        removeSelectedGroup();
        nav.goToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groupData = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupData.add(new GroupData().withId(id).withName(name));
        }
        return groupData;
    }

    public Set<GroupData> all() {
        Set<GroupData> groupData = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupData.add(new GroupData().withId(id).withName(name));
        }
        return groupData;
    }


}
