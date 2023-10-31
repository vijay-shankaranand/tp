package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Contact} is tagged by any of the given tags.
 */
public class ContactIsTaggedPredicate implements Predicate<Contact> {
    private final List<Tag> tags;

    public ContactIsTaggedPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Contact contact) {
        return tags.stream().anyMatch(tag -> contact.isTaggedBy(tag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactIsTaggedPredicate)) {
            return false;
        }

        ContactIsTaggedPredicate otherContactIsTaggedPredicate = (ContactIsTaggedPredicate) other;
        return tags.equals(otherContactIsTaggedPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tags", tags).toString();
    }
}
