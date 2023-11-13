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
import seedu.address.model.event.exceptions.EventIsNotLinkedToContactException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskIsInEventPredicate;

/**
 * Links a contact to a specific event.
 */
public class UnlinkCommand extends Command {
    public static final String COMMAND_WORD = "unlink";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unlinks a contact from a specific event. "
            + "Parameters: "
            + PREFIX_EVENT + "EVENT_NAME "
            + PREFIX_CONTACT + "CONTACT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT + "NUS Career Fair 2023 "
            + PREFIX_CONTACT + "John Doe";

    public static final String MESSAGE_SUCCESS = "Unlinked contact(s): %1$s from event: %2$s";
    public static final String MESSAGE_NO_SUCH_EVENT = "The event: %1$s does not exist in JobFestGo.";
    public static final String MESSAGE_NO_SUCH_CONTACT = "The contact: %1$s does not exist in JobFestGo.";
    public static final String MESSAGE_UNLINKED_CONTACT = "The contact: %1$s is not linked to the event: %2$s";

    private final Name eventNameToUnlink;
    private final Set<Name> contactNameListToUnlink;

    /**
     * Creates a UnlinkCommand to unlink the contact specified by the name from the event specified by the name.
     */
    public UnlinkCommand(Name eventNameToUnlink, Set<Name> contactNameListToUnlink) {
        this.eventNameToUnlink = eventNameToUnlink;
        this.contactNameListToUnlink = contactNameListToUnlink;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Event eventToUnlink = model.getEvent(eventNameToUnlink);
            Set<Contact> contactsToUnlink = new HashSet<>();

            for (Name contactName : contactNameListToUnlink) {
                Contact contactToUnlink = model.getContact(contactName);
                if (!model.isContactLinkedToEvent(contactToUnlink, eventToUnlink)) {
                    throw new CommandException(
                            String.format(MESSAGE_UNLINKED_CONTACT, contactName, eventNameToUnlink));
                }
                contactsToUnlink.add(contactToUnlink);
            }

            for (Contact contact : contactsToUnlink) {
                eventToUnlink = model.getEvent(eventNameToUnlink);
                model.unlinkContactFromEvent(contact, eventToUnlink);
            }

            // Update model to depict which screen it is on currently.
            model.switchToContactsScreen(false);
            model.switchToEventsScreen(false);
            model.switchToTagsScreen(false);

            eventToUnlink = model.getEvent(eventNameToUnlink);
            ContactIsInEventPredicate contactListPredicate = new ContactIsInEventPredicate(eventToUnlink);
            TaskIsInEventPredicate taskListPredicate = new TaskIsInEventPredicate(eventToUnlink);
            model.updateFilteredContactList(contactListPredicate);
            model.updateFilteredTaskList(taskListPredicate);

            return new CommandResult(String.format(MESSAGE_SUCCESS, contactNameListToUnlink, eventNameToUnlink),
                    model.getEvent(eventNameToUnlink), false);
        } catch (EventNotFoundException enfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_EVENT, eventNameToUnlink));
        } catch (ContactNotFoundException cnfe) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_CONTACT, cnfe.getName()));
        } catch (EventIsNotLinkedToContactException einltce) {
            throw new CommandException(
                    String.format(MESSAGE_UNLINKED_CONTACT, einltce.getContact().getName(), eventNameToUnlink));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnlinkCommand)) {
            return false;
        }

        UnlinkCommand otherUnlinkCommand = (UnlinkCommand) other;
        return eventNameToUnlink.equals(otherUnlinkCommand.eventNameToUnlink)
                && contactNameListToUnlink.equals(otherUnlinkCommand.contactNameListToUnlink);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventToUnlink", eventNameToUnlink)
                .add("contactToUnlink", contactNameListToUnlink)
                .toString();
    }
}
