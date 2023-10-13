package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VENUES;
import static seedu.address.testutil.TypicalTags.EVENT_PLANNER;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagBuilder;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName_nullTag() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName("vendor"));
    }
    @Test
    public void isValidTagName_nonNullTag() {
        String expected = "[vendor]";
        assertEquals(expected, new Tag("vendor").toString());
    }

     @Test
    public void isSameTag() {
        // same object -> returns true
        assertTrue(VENUES.isSameTag(VENUES));

        // null -> returns false
        assertFalse(VENUES.isSameTag(null));

        // Tag name differs in case -> returns false
        Tag editedVenues = new TagBuilder().withTag(VENUES.getTagName().toUpperCase()).build();
        assertFalse(VENUES.isSameTag(editedVenues));

        // Tag name has trailing spaces -> throws illegalArgumentException as " " found
        String tagNameWithTrailingSpaces = VENUES.getTagName() + " ";
        assertThrows(IllegalArgumentException.class, () -> new TagBuilder().withTag(tagNameWithTrailingSpaces).build());

        // Tag name has non-alphanumeric character -> throws illegalArgumentException
         String tagNameWithNonAlphanumericChar = VENUES.getTagName() + "_";
         assertThrows(IllegalArgumentException.class, () -> new TagBuilder().withTag(tagNameWithNonAlphanumericChar).build());
    }

}
