package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.orders.Order;

public class PersonOrdersPanel extends UiPart<Region> {
    private static final String FXML = "PersonOrdersPanel.fxml";

    private Person person;

    @FXML
    private Label title;
    @FXML
    private ListView<Order> orderListView;

    /**
     * Creates a {@code PersonOrdersPanel} with the given {@code Person}.
     */
    public PersonOrdersPanel() {
        super(FXML);
    }

    public void updatePersonDetails(Person selectedPerson) {
        title.setText(selectedPerson.getName().toString());
        orderListView.setItems(FXCollections.observableArrayList(selectedPerson.getOrders()));
    }
}
