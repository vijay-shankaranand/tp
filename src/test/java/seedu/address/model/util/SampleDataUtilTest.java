package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;

public class SampleDataUtilTest {
    @Test
    public void getSampleAddressBook_hasPersonList_success() {
        ReadOnlyAddressBook ab = SampleDataUtil.getSampleAddressBook();
        assertTrue(!ab.getPersonList().isEmpty());
    }

    @Test
    public void getSampleAddressBook_hasTagList_success() {
        ReadOnlyAddressBook ab = SampleDataUtil.getSampleAddressBook();
        assertTrue(!ab.getTagList().isEmpty());
    }
}
