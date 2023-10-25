package seedu.address.testutil.event;

import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;


/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event JOBFEST = new EventBuilder().withName("JobFest 2023")
            .withEventDate("2023-12-12")
            .withEventAddress("3 Temasek Blvd, Singapore 038983")
            .withEventContacts(ALICE, BOB)
            .build();

    public static final Event NTU = new EventBuilder().withName("NTU Job In Fair 2023")
            .withEventDate("2023-12-10")
            .withEventAddress("50 Nanyang Ave, #32 Block N4 #02a, Singapore 639798")
            .withEventContacts(ALICE)
            .build();

    private TypicalEvents() {}

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(JOBFEST, NTU));
    }
}
