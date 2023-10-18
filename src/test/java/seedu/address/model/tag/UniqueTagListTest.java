package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.CATERING;
import static seedu.address.testutil.TypicalTags.VENUES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.TagBuilder;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(VENUES));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(VENUES);
        assertTrue(uniqueTagList.contains(VENUES));
    }

    @Test
    public void contains_tagWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTagList.add(VENUES);
        Tag editedVenues = new TagBuilder().withTag("venues")
                .build();
        assertTrue(uniqueTagList.contains(editedVenues));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(VENUES);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(VENUES));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, VENUES));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(VENUES, null));
    }

    @Test
    public void setTag_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTag(VENUES, VENUES));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        uniqueTagList.add(VENUES);
        uniqueTagList.setTag(VENUES, VENUES);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VENUES);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasSameIdentity_success() {
        uniqueTagList.add(VENUES);
        Tag editedTag = new TagBuilder().withTag(VENUES.getTagName())
                .build();
        uniqueTagList.setTag(VENUES, editedTag);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(editedTag);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(VENUES);
        uniqueTagList.setTag(VENUES, CATERING);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CATERING);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }
    //Done

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(VENUES);
        uniqueTagList.add(CATERING);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(VENUES, CATERING));
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(VENUES);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CATERING);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(VENUES);
        List<Tag> tagList = Collections.singletonList(CATERING);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CATERING);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(VENUES, VENUES);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void deleteTag_validTag() {
        uniqueTagList.add(VENUES);
        uniqueTagList.add(CATERING);
        uniqueTagList.delete(CATERING);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VENUES);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void deleteTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.delete(null));
    }

    @Test
    public void deleteTag_invalidTag_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.delete(VENUES));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void iterator_iteration_success() {
        UniqueTagList list = new UniqueTagList();
        list.add(VENUES);
        list.add(CATERING);

        int count = 0;
        for (Tag tag : list) {
            if (tag.equals(VENUES) || tag.equals(CATERING)) {
                count++;
            }
        }
        assertEquals(2, count);
    }
    @Test
    public void equal_sameList_returnsTrue() {
        UniqueTagList uniqueTagList1 = new UniqueTagList();
        uniqueTagList1.add(VENUES);
        uniqueTagList1.add(CATERING);
        assertTrue(uniqueTagList1.equals(uniqueTagList1));
    }

    @Test
    public void equals_differentLists_returnsFalse() {
        UniqueTagList firstList = new UniqueTagList();
        firstList.add(VENUES);
        UniqueTagList secondList = new UniqueTagList();
        secondList.add(CATERING);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void equals_null_returnsFalse() {
        UniqueTagList list = new UniqueTagList();
        list.add(VENUES);
        assertFalse(list.equals(null));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        UniqueTagList list = new UniqueTagList();
        list.add(VENUES);
        assertFalse(list.equals(VENUES));
    }

    @Test
    public void hashCode_sameList_equalHashCodes() {
        UniqueTagList firstList = new UniqueTagList();
        firstList.add(VENUES);
        firstList.add(CATERING);
        UniqueTagList secondList = new UniqueTagList();
        secondList.add(VENUES);
        secondList.add(CATERING);
        assertEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void hashCode_differentLists_differentHashCodes() {
        UniqueTagList firstList = new UniqueTagList();
        firstList.add(VENUES);
        UniqueTagList secondList = new UniqueTagList();
        secondList.add(CATERING);
        assertNotEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTagList.asUnmodifiableObservableList().toString(), uniqueTagList.toString());
    }
}
