package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {
    public static final Tag EVENT_PLANNER = new TagBuilder().withTag("eventPlanner").build();
    public static final Tag CLIENTS = new TagBuilder().withTag("clients").build();
    public static final Tag VENUES = new TagBuilder().withTag("venues").build();
    public static final Tag SERVICES = new TagBuilder().withTag("services").build();
    public static final Tag WEDDINGS = new TagBuilder().withTag("weddings").build();
    public static final Tag CORPORATE_EVENTS = new TagBuilder().withTag("corporateEvents").build();
    public static final Tag BIRTHDAYS = new TagBuilder().withTag("birthdays").build();
    public static final Tag ANNIVERSARIES = new TagBuilder().withTag("anniversaries").build();
    public static final Tag CATERING = new TagBuilder().withTag("catering").build();
    public static final Tag DECORATIONS = new TagBuilder().withTag("decorations").build();
    public static final Tag ENTERTAINMENT = new TagBuilder().withTag("entertainment").build();
    public static final Tag CLIENT_FEEDBACK = new TagBuilder().withTag("clientFeedback").build();

    private TypicalTags() {} //prevents instantiation

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(
                EVENT_PLANNER, CLIENTS, VENUES, SERVICES,
                WEDDINGS, CORPORATE_EVENTS, BIRTHDAYS, ANNIVERSARIES,
                CATERING, DECORATIONS, ENTERTAINMENT, CLIENT_FEEDBACK
        ));
    }

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

}
