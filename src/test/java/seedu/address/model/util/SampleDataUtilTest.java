package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyJobFestGo;

public class SampleDataUtilTest {
    @Test
    public void getSampleJobFestGo_hasContactList_success() {
        ReadOnlyJobFestGo ab = SampleDataUtil.getSampleJobFestGo();
        assertTrue(!ab.getContactList().isEmpty());
    }

    @Test
    public void getSampleJobFestGo_hasTagList_success() {
        ReadOnlyJobFestGo ab = SampleDataUtil.getSampleJobFestGo();
        assertTrue(!ab.getTagList().isEmpty());
    }
}
