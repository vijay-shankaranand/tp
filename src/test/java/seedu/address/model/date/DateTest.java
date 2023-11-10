package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void isDateTodayOrAfter() {
        Date pastDate = new Date("2020-01-01");
        assertEquals(Date.isDateTodayOrAfter(pastDate), false);

        Date futureDate = new Date("2100-01-01");
        assertEquals(Date.isDateTodayOrAfter(futureDate), true);

        Date todayDate = new Date(LocalDate.now().toString());
        assertEquals(Date.isDateTodayOrAfter(todayDate), true);
    }

    @Test
    public void isWithinThreeDays() {
        Date twoDaysFromNow = new Date(LocalDate.now().plusDays(2).toString());
        assertEquals(twoDaysFromNow.isWithinThreeDays(), true);

        Date fourDaysFromNow = new Date(LocalDate.now().plusDays(4).toString());
        assertEquals(fourDaysFromNow.isWithinThreeDays(), false);

        Date pastDate = new Date(LocalDate.now().minusDays(2).toString());
        assertEquals(pastDate.isWithinThreeDays(), false);

        Date todayDate = new Date(LocalDate.now().toString());
        assertEquals(todayDate.isWithinThreeDays(), true);
    }

    @Test
    public void equals() {
        Date date = new Date("2023-11-04");

        // same object -> returns true
        assertEquals(date.equals(date), true);

        // same values -> returns true
        assertEquals(date.equals(new Date("2023-11-04")), true);

        // null -> returns false
        assertEquals(date.equals(null), false);
    }
}
