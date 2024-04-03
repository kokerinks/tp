package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.item.Item;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class SeedDataCommandTest {

    private static final Person[] samplePersons = SampleDataUtil.getSamplePersons();
    private static final Item[] sampleCatalogue = SampleDataUtil.getSampleCatalogue();

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();

        Model expectedModel = new ModelManager();
        for (Person person: samplePersons) {
            expectedModel.addPerson(person);
        }
        for (Item item : sampleCatalogue) {
            expectedModel.addItem(item);
        }

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithSomeContacts_success() {
        Model model = new ModelManager();
        model.addPerson(samplePersons[0]);
        model.addPerson(samplePersons[1]);
        model.addPerson(samplePersons[3]);
        model.addItem(sampleCatalogue[0]);
        model.addItem(sampleCatalogue[1]);
        model.addItem(sampleCatalogue[3]);

        Model expectedModel = new ModelManager();

        // pre-existing contacts
        expectedModel.addPerson(samplePersons[0]);
        expectedModel.addPerson(samplePersons[1]);
        expectedModel.addPerson(samplePersons[3]);

        // pre-existing items
        expectedModel.addItem(sampleCatalogue[0]);
        expectedModel.addItem(sampleCatalogue[1]);
        expectedModel.addItem(sampleCatalogue[3]);

        // new contacts added
        for (int i = 0; i < samplePersons.length; i++) {
            if (i != 0 && i != 1 && i != 3) {
                expectedModel.addPerson(samplePersons[i]);
            }
        }

        // new items added
        for (int i = 0; i < sampleCatalogue.length; i++) {
            if (i != 0 && i != 1 && i != 3) {
                expectedModel.addItem(sampleCatalogue[i]);
            }
        }

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithAllContactsNoCatalogue_success() {
        Model model = new ModelManager();
        for (Person person: samplePersons) {
            model.addPerson(person);
        }

        Model expectedModel = new ModelManager();
        for (Person person: samplePersons) {
            expectedModel.addPerson(person);
        }
        for (Item item : sampleCatalogue) {
            expectedModel.addItem(item);
        }

        //Command should succeed as catalogue has not been added
        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithAllContactsAndCatalogue_failure() {
        Model model = new ModelManager();
        for (Person person: samplePersons) {
            model.addPerson(person);
        }
        for (Item item : sampleCatalogue) {
            model.addItem(item);
        }

        //Command should not succeed as all persons and catalogue exist
        assertCommandFailure(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_FAILURE);
    }

}
