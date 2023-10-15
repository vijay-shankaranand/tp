package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} is tagged by any of the given tags.
 */
public class PersonIsTaggedPredicate implements Predicate<Person> {
    private final List<Tag> tags;

    public PersonIsTaggedPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {

        return tags.stream().anyMatch(tag -> person.isTaggedBy(tag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonIsTaggedPredicate)) {
            return false;
        }

        PersonIsTaggedPredicate otherPersonIsTaggedPredicate = (PersonIsTaggedPredicate) other;
        return tags.equals(otherPersonIsTaggedPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }
}
