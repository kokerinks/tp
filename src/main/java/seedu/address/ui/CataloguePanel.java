package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;

public class CataloguePanel extends UiPart<Region> {

    private static final String FXML = "CataloguePanel.fxml";
    @FXML
    private Label title;
    @FXML
    private ListView<Item> itemListView;

    /**
     * Creates a {@code CataloguePanel} with the given {@code ObservableList}.
     */
    public CataloguePanel(ObservableList<Item> itemList) {
        super(FXML);
        itemListView.setItems(itemList);
    }
}
