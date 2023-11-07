package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import org.junit.jupiter.api.Test;
public class TaskIsInEventPredicateTest {
    @Test
    public void equals() {
        TaskIsInEventPredicate firstPredicate = new TaskIsInEventPredicate(JOBFEST);
        assertTrue(firstPredicate.equals(firstPredicate));
        assertFalse(firstPredicate.equals(null));

        TaskIsInEventPredicate secondPredicate = new TaskIsInEventPredicate(NTU);
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
