package seedu.address.testutil.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import seedu.address.logic.commands.event.LinkCommand;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Name;

/**
 * A utility class for Event.
 */
public class EventUtil {
    /**
     * Returns a link command string for linking the {@code person} to the {@code Event}.
     */
    public static String getLinkCommand(EventName eventName, Name contactName) {
        return LinkCommand.COMMAND_WORD + " "
                + getEventName(eventName)
                + getContactName(contactName);
    }

    /**
     * Returns the part of command string for the given {@code Event}'s name.
     */
    public static String getEventName(EventName eventName) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EVENT + eventName.eventName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code Person}'s name.
     */
    public static String getContactName(Name contactName) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CONTACT + contactName.fullName + " ");
        return sb.toString();
    }
}
