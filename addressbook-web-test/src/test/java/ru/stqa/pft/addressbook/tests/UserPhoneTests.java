package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserPhoneTests extends TestBase {

    @Test
    public void testUserPhone(){
        app.goTo().homePage();
        UserData contact = app.user().all().iterator().next();
        UserData contactInfoFromEditForm = app.user().infoFromEditForm(contact);

        assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }

    public String cleaned(String phones){
        return phones.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
