package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void isDateTodayOrAfter_returnsTrue() {
        Date pastDate = new Date("2020-01-01");
        assertEquals(Date.isDateTodayOrAfter(pastDate), false);

        Date futureDate = new Date("2100-01-01");
        assertEquals(Date.isDateTodayOrAfter(futureDate), true);

        Date todayDate = new Date(LocalDate.now().toString());
        assertEquals(Date.isDateTodayOrAfter(todayDate), true);
    }

    @Test
    public void isWithinThreeDays_returnsTrue() {
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
        LocalDate nullDate = null;
        Date date = new Date("2023-11-04");
        assertEquals(date.equals(nullDate), false);
    }
}
