package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ContactIsTaggedPredicateTest {
    @Test
    public void equals() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> firstTagList = new ArrayList<>();
        List<Tag> secondTagList = new ArrayList<>();

        firstTagList.add(firstTag);
        secondTagList.add(firstTag);
        secondTagList.add(secondTag);

        ContactIsTaggedPredicate firstPredicate =
                new ContactIsTaggedPredicate(firstTagList);
        ContactIsTaggedPredicate secondPredicate =
                new ContactIsTaggedPredicate(secondTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactIsTaggedPredicate firstPredicateCopy = new ContactIsTaggedPredicate(firstTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
