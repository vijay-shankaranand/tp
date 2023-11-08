package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactIsInEventPredicate;
import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventIsAlreadyLinkedToContactException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskIsInEventPredicate;

/**
 * Links a contact to a specific event.
 */
public class LinkCommand extends Command {
    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a contact to a specific event. "
            + "Parameters: "
            + PREFIX_EVENT + "EVENT_NAME "
            + PREFIX_CONTACT + "CONTACT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT + "NUS Career Fair 2023 "
            + PREFIX_CONTACT + "John Doe";

    public static final String MESSAGE_SUCCESS = "Linked contact(s): %1$s to event: %2$s";
    public static final String MESSAGE_NO_SUCH_EVENT = "The event: %1$s does not exist in the event list. "
            + "Please add it in first.";
    public static final String MESSAGE_NO_SUCH_CONTACT = "The contact: %1$s does not exist in JobFestGo. "
            + "Please add it in first.";
    public static final String MESSAGE_LINKED_CONTACT = "The contact: %1$s is already linked to the event: %2$s";

    private final Name eventNameToLink;
    private final Set<Name> contactNameListToLink;

    /**
     * Creates a LinkCommand to link the contact specified by the name to the event specified by the name.
     */
    public LinkCommand(Name eventNameToLink, Set<Name> contactNameListToLink) {
        this.eventNameToLink = eventNameToLink;
        this.contactNameListToLink = contactNameListToLink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Event eventToLink = model.getEvent(eventNameToLink);
            Set<Contact> contactsToLink = new HashSet<>();

            for (Name contactName : contactNameListToLink) {
                Contact contactToLink = model.getContact(contactName);
                if (model.isContactLinkedToEvent(contactToLink, eventToLink)) {
                    throw new CommandException(
                            String.format(MESSAGE_LINKED_CONTACT, contactName, eventNameToLink));
                }
                contactsToLink.add(contactToLink);
            }

            for (Contact contact : contactsToLink) {
                eventToLink = model.getEvent(eventNameToLink);
                model.linkContactToEvent(contact, eventToLink);
            }

            // Get the event after linking contact.
            eventToLink = model.getEvent(eventNameToLink);

            // Update the respective filtered lists to show the components within the event
            // Flow of command should be after linking a contact to an event,
            // it goes to the selected event.
            model.updateFilteredContactList(new ContactIsInEventPredicate(eventToLink));
            model.updateFilteredTaskList(new TaskIsInEventPredicate(eventToLink));

            return new CommandResult(String.format(MESSAGE_SUCCESS, contactNameListToLink, eventNameToLink),
                    model.getEvent(eventNameToLink), false);
        } catch (EventNotFoundException enfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_EVENT, eventNameToLink));
        } catch (ContactNotFoundException cnfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_CONTACT, cnfe.getName()));
        } catch (EventIsAlreadyLinkedToContactException eialte) {
            throw new CommandException(
                    String.format(MESSAGE_LINKED_CONTACT, eialte.getContact().getName(), eventNameToLink));
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
                && contactNameListToLink.equals(otherLinkCommand.contactNameListToLink);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventToLink", eventNameToLink)
                .add("contactToLink", contactNameListToLink)
                .toString();
    }
}
