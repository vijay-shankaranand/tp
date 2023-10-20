package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event EVENT1 = new Event(new EventName(VALID_EVENT_NAME),
        new EventDate(VALID_EVENT_DATE), new EventAddress(VALID_EVENT_ADDRESS),
        new HashSet<Person>());

    private TypicalEvents() {};

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1));
    }
}
