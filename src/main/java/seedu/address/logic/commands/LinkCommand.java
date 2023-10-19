package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Links a contact to a specific event.
 */
public class LinkCommand extends Command {
    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a contact to a specific event. "
            + "Parameters: "
            + PREFIX_EVENT + "EVENT NAME "
            + PREFIX_CONTACT + "CONTACT NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT + "NUS Career Fair 2023 "
            + PREFIX_CONTACT + "John Doe";

    public static final String MESSAGE_SUCCESS = "Linked contact: %1$s to event: %2$s";
    public static final String MESSAGE_NO_SUCH_EVENT = "This event does not exist in the event list. "
            + "Please add it in first: %1$s";
    public static final String MESSAGE_NO_SUCH_CONTACT = "This person does not exist in JobFestGo. "
            + "Please add it in first: %1$s";
    public static final String MESSAGE_LINKED_CONTACT = "The contact: %1$s is already linked to the event: %2$s";

    private final EventName eventNameToLink;
    private final Name contactNameToLink;

    /**
     * Creates a LinkCommand to link the person specified by the name to the event specified by the name.
     */
    public LinkCommand(EventName eventNameToLink, Name contactNameToLink) {
        this.eventNameToLink = eventNameToLink;
        this.contactNameToLink = contactNameToLink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Event eventToLink = model.getEvent(eventNameToLink);
            Person contactToLink = model.getPerson(contactNameToLink);

            if (eventToLink.isLinkedToContact(contactToLink)) {
                throw new CommandException(String.format(MESSAGE_LINKED_CONTACT, contactNameToLink, eventToLink));
            }

            eventToLink.linkContact(contactToLink);

            return new CommandResult(String.format(MESSAGE_SUCCESS, contactNameToLink, eventToLink));
        } catch (EventNotFoundException enfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_EVENT, eventNameToLink));
        } catch (PersonNotFoundException pnfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_CONTACT, contactNameToLink));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCommand)) {
            return false;
        }

        LinkCommand otherLinkCommand = (LinkCommand) other;
        return eventNameToLink.equals(otherLinkCommand.eventNameToLink)
                && contactNameToLink.equals(otherLinkCommand.contactNameToLink);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventToLink", eventNameToLink)
                .add("contactToLink", contactNameToLink)
                .toString();
    }
}
