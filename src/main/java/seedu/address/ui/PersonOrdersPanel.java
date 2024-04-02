package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.item.Item;
import seedu.address.model.person.Person;
import seedu.address.model.person.orders.Order;

public class PersonOrdersPanel extends UiPart<Region> {
    private static final String FXML = "PersonOrdersPanel.fxml";

    private Person person;

    @FXML
    private VBox container;
    @FXML
    private Label orderTitle;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableColumn<Order, String> dateTimeColumn;
    @FXML
    private TableColumn<Order, String> itemNameColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;
    @FXML
    private TableColumn<Order, Integer> pointsColumn;

    /**
     * Creates a {@code PersonOrdersPanel} with the given {@code Person}.
     */
    public PersonOrdersPanel() {
        super(FXML);
    }

    public void updatePersonDetails(Person selectedPerson) {
        orderTitle.setText(selectedPerson.getName().toString() + " Orders");
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("itemPoints"));
        orderTableView.setItems(FXCollections.observableArrayList(selectedPerson.getOrders()));
        orderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
