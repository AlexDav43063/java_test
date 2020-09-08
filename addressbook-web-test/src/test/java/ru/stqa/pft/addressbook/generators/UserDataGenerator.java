package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.appmanager.DbHelper;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;
import ru.stqa.pft.addressbook.models.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {
    @Parameter(names = "-c", description = "User count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("xml")) {
            saveASXml(users, new File(file));
        } else if (format.equals("json")) {
            saveASJson(users, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveASJson(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveASXml(List<UserData> users, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        String xml = xstream.toXML(users);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<UserData>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData().withName(String.format("name %s", i)).withLastName(String.format("lastname %s", i))
                    .withStreet(String.format("address %s", i)).withHome(String.format("33%s", i)).withWork(String.format("33%s", i))
                    .withEmail(String.format("firstTest%s@test.ru", i))
                    .withEmail2(String.format("secondTest%s@test.ru", i)));
//                    .inGroup(new GroupData().withName("Test1").withHeader("Header1").withFooter("footer1")));
        }
        return users;
    }
}
