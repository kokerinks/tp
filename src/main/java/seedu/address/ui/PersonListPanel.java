package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private PersonSelectionListener personSelectionListener;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());

        // Add event handler to handle clicks on PersonCards
        personListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Get the selected item from the ListView
                Person selectedPerson = personListView.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                    handlePersonCardClick(selectedPerson);
                }
            }
        });
    }

    /**
     * Sets the {@code PersonSelectionListener} whom will listen to when
     * a {@code PersonCard} is clicked in this {@code personListPanel}
     */
    public void setPersonSelectionListener(PersonSelectionListener listener) {
        this.personSelectionListener = listener;
    }

    /**
     * Inform the {@code personSelectionListener} that a {@code Person} has been selected
     *
     * @param selectedPerson {@code Person} that has been selected
     */
    private void handlePersonCardClick(Person selectedPerson) {
        // Notify the listener that a person is selected
        if (personSelectionListener != null) {
            personSelectionListener.onPersonSelected(selectedPerson);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
