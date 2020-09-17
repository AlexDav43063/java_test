package ru.stqa.pft.rest;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class restTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(274);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test_Dav").withDescription("New test Davydov");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
