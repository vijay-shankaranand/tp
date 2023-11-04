package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Adds a contact to JobFestGo.
 */
public class AddContactCommand extends Command {
    public static final String COMMAND_WORD = "add_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to JobFestGo. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "vendors "
            + PREFIX_TAG + "clients";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact's name/phone number is already in "
            + "JobFestGo";
    public static final String MESSAGE_INVALID_TAG = " is missing in JobFestGo. "
            + "Please use tags already added to JobFestGo.";
    private final Contact toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Contact}
     */
    public AddContactCommand(Contact contact) {
        requireNonNull(contact);
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        for (Tag tag : toAdd.getTags()) {
            if (!model.hasTag(tag)) {
                throw new CommandException("Tag: " + tag + MESSAGE_INVALID_TAG);
            }
        }

        model.addContact(toAdd);

        // Update the respective filtered lists to show the relevant components
        // Flow of adding contact should result in it returning to main dashboard
        model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddContactCommand)) {
            return false;
        }

        AddContactCommand otherAddCommand = (AddContactCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
