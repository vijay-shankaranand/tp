package seedu.address.testutil.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.LinkCommand;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;



/**
 * A utility class for Event.
 */
public class EventUtil {
    /**
     * Returns a link command string for linking the {@code contact} to the {@code Event}.
     */
    public static String getLinkCommand(Name name, Name contactName) {
        return LinkCommand.COMMAND_WORD + " "
                + getName(name)
                + getContactName(contactName);
    }

    /**
     * Returns the part of command string for the given {@code Event}'s name.
     */
    public static String getName(Name name) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EVENT + name.fullName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code Contact}'s name.
     */
    public static String getContactName(Name contactName) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CONTACT + contactName.fullName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().fullName + " ");
        sb.append(PREFIX_DATE + event.getDate().date + " ");
        sb.append(PREFIX_ADDRESS + event.getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns an add event command string for adding the {@code event}.
     */
    public static String getAddEventCommand(Event event) {
        return AddEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }
}
