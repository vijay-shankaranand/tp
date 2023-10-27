package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class PersonIsTaggedPredicateTest {
    @Test
    public void equals() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> firstTagList = new ArrayList<>();
        List<Tag> secondTagList = new ArrayList<>();

        firstTagList.add(firstTag);
        secondTagList.add(firstTag);
        secondTagList.add(secondTag);

        PersonIsTaggedPredicate firstPredicate =
                new PersonIsTaggedPredicate(firstTagList);
        PersonIsTaggedPredicate secondPredicate =
                new PersonIsTaggedPredicate(secondTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonIsTaggedPredicate firstPredicateCopy = new PersonIsTaggedPredicate(firstTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
