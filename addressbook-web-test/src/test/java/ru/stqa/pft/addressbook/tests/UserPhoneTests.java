package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserPhoneTests extends TestBase {

    @Test
    public void testUserPhone(){
        app.goTo().homePage();
        UserData contact = app.user().all().iterator().next();
        UserData contactInfoFromEditForm = app.user().infoFromEditForm(contact);

        assertThat(contact.getStreet(), equalTo(contactInfoFromEditForm.getStreet()));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergePhones(UserData contact) {
       return Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWork())
                .stream()
                .filter((s)-> !s.equals(""))
                .map(UserPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    private String mergeEmails(UserData contact) {
       return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream()
                .filter((s)-> !s.equals(""))
                .map(UserPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phones){
        return phones.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
