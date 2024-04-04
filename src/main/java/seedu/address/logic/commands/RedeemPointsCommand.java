package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Points;

/**
 * Redeems points from an existing person in the loyalty program.
 */
public class RedeemPointsCommand extends Command {
    public static final String COMMAND_WORD = "redeempts";
    public static final String MESSAGE_CONSTRAINTS = "Points to redeem should be greater than 0.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redeems points from the person identified. \n"
            + "Parameters: " + PREFIX_NAME + "NAME " + PREFIX_POINTS + "POINTS \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_POINTS + "30";

    public static final String MESSAGE_REDEEMPOINTS_SUCCESS =
            "Redeemed %1$s points from %2$s";
    public static final String MESSAGE_INSUFFICIENT_POINTS =
            "%s has insufficient points (%s pts) to redeem.";

    private final Name name;
    private final Points pointsToRedeem;

    /**
     * Constructs a RedeemPointsCommand to redeem the specified {@code Points}
     * from the person identified by {@code Name}.
     *
     * @param name of the person in the list to redeem the points from
     * @param pointsToRedeem to be subtracted from the person's current points
     */
    public RedeemPointsCommand(Name name, Points pointsToRedeem) {
        requireAllNonNull(name, pointsToRedeem);
        this.name = name;
        this.pointsToRedeem = pointsToRedeem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> personOptional = lastShownList.stream()
                .filter(person -> person.getName().fullName.toLowerCase().contains(this.name.fullName.toLowerCase()))
                .findFirst();

        if (personOptional.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = personOptional.get();

        int currentPointsValue = personToEdit.getPoints().getValue();
        int pointsToRedeemValue = this.pointsToRedeem.getValue();

        if (currentPointsValue < pointsToRedeemValue) {
            throw new CommandException(String.format(MESSAGE_INSUFFICIENT_POINTS, personToEdit.getName(), currentPointsValue));
        }

        Points newPoints = new Points(Integer.toString(currentPointsValue - pointsToRedeemValue));

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(),
                personToEdit.getMembershipPoints(), personToEdit.getAllergens(),
                newPoints, personToEdit.getOrders());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson, pointsToRedeemValue));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RedeemPointsCommand)) {
            return false;
        }

        RedeemPointsCommand e = (RedeemPointsCommand) other;
        return name.equals(e.name) && pointsToRedeem.equals(e.pointsToRedeem);
    }

    private String generateSuccessMessage(Person person, int pointsToRedeem) {
        return String.format(MESSAGE_REDEEMPOINTS_SUCCESS, pointsToRedeem, person.getName());
    }
}
