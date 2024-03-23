package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * This interface represents a listener for person selection events.
 * Implementing classes can be notified when a person is selected.
 */
public interface PersonSelectionListener {

    /**
     * Called when a person is selected.
     *
     * @param selectedPerson The selected person
     */
    void onPersonSelected(Person selectedPerson);
}
