package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.event.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(JOBFEST));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(JOBFEST);
        assertTrue(uniqueEventList.contains(JOBFEST));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.add(JOBFEST);
        Event editedMeeting = new EventBuilder(JOBFEST).withEventAddress("Yishun").build();
        assertTrue(uniqueEventList.contains(editedMeeting));
    }

    @Test
    public void contains_eventWithSameNameInList_returnsTrue() {
        uniqueEventList.add(JOBFEST);
        Event editedMeeting = new EventBuilder(JOBFEST).withEventDate("2023-12-23").build();
        assertTrue(uniqueEventList.contains(editedMeeting));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(JOBFEST);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(JOBFEST));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, JOBFEST));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(JOBFEST, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(JOBFEST, JOBFEST));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        uniqueEventList.add(JOBFEST);
        uniqueEventList.setEvent(JOBFEST, JOBFEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(JOBFEST);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        uniqueEventList.add(JOBFEST);
        Event editedMeeting = new EventBuilder(JOBFEST).withEventAddress("Yishun").build();
        uniqueEventList.setEvent(JOBFEST, editedMeeting);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(editedMeeting);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        uniqueEventList.add(JOBFEST);
        uniqueEventList.setEvent(JOBFEST, NTU);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(NTU);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.add(JOBFEST);
        uniqueEventList.add(NTU);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(JOBFEST, NTU));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(JOBFEST));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(JOBFEST);
        uniqueEventList.remove(JOBFEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(JOBFEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(NTU);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(JOBFEST);
        List<Event> eventList = Collections.singletonList(NTU);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(NTU);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(JOBFEST, JOBFEST);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_iteration_success() {
        UniqueEventList list = new UniqueEventList();
        list.add(JOBFEST);
        list.add(NTU);

        int count = 0;
        for (Event event : list) {
            if (event.equals(JOBFEST) || event.equals(NTU)) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void equals_sameList_returnsTrue() {
        UniqueEventList list = new UniqueEventList();
        list.add(JOBFEST);
        list.add(NTU);
        assertTrue(list.equals(list));
    }



}
