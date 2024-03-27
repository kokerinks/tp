package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.orders.Order;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label membership;
    @FXML
    private FlowPane tags;
    @FXML
    private Label points;
    @FXML
    private Label orders;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Contact no.: \t" + person.getPhone().value);
        address.setText("Home Addr.: \t" + person.getAddress().value);
        email.setText("Email Addr.: \t" + person.getEmail().value);
        membership.setText("Membership Tier: \t" + person.getMembershipPoints().toString());
        orders.setText(buildOrderString(person.getOrders()));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        points.setText("Redeem Pts: \t" + String.valueOf(person.getPoints().value));
    }

    private static String buildOrderString(ArrayList<Order> orders) {
        StringBuilder orderStringBuilder = new StringBuilder("Orders:\n");
        for (Order order : orders) {
            orderStringBuilder.append(order.toString());
            orderStringBuilder.append("\n");
        }
        return orderStringBuilder.toString();
    }
}
