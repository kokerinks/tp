package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.item.Item;

public class CataloguePanel extends UiPart<Region> {

    private static final String FXML = "CataloguePanel.fxml";
    @FXML
    private VBox container;
    @FXML
    private Label catalogueTitle;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Integer> pointsColumn;

    /**
     * Creates a {@code CataloguePanel} with the given {@code ObservableList}.
     */
    public CataloguePanel(ObservableList<Item> itemList) {
        super(FXML);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("points"));
        itemTableView.setItems(itemList);
        itemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
