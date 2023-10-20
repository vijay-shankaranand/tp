package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns an array of sample persons.
     * @return an array of sample persons
     */
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    /**
     * Returns an array of sample tags.
     * @return an array of sample tags
     */
    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag("friends"),
            new Tag("colleagues"),
            new Tag("neighbours"),
            new Tag("family"),
            new Tag("classmates")
        };
    }

    /**
     * Returns an array of sample events.
     * @return an array of sample events
     */
    public static Event[] getSampleEvents() {
        Person[] samplePersons = getSamplePersons();
        Set<Person> samplePersonSet1 = new HashSet<>();
        samplePersonSet1.add(samplePersons[0]);
        samplePersonSet1.add(samplePersons[1]);

        Set<Person> samplePersonSet2 = new HashSet<>();
        samplePersonSet2.add(samplePersons[2]);
        samplePersonSet2.add(samplePersons[3]);
        samplePersonSet2.add(samplePersons[4]);

        Set<Person> samplePersonSet3 = new HashSet<>();

        return new Event[] {
            new Event(new EventName("NUS Career Fair 2023"), new EventDate("2023-12-23"),
                    new EventAddress("311, Clementi Ave 2, #02-25"), samplePersonSet1),
            new Event(new EventName("JobFest 2023"), new EventDate("2023-11-22"),
                    new EventAddress("3 Temasek Blvd, Singapore 038983"), samplePersonSet2),
            new Event(new EventName("NTU Job In Fair 2023"), new EventDate("2023-11-10"),
                    new EventAddress("50 Nanyang Ave, #32 Block N4 #02a, Singapore 639798"), samplePersonSet3)
        };
    }

    /**
     * Returns an {@code AddressBook} with sample data.
     * @return
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
    public static Set<Person> getPersonSet(Person ... persons) {
        return Arrays.stream(persons).collect(Collectors.toSet());
    }

}
