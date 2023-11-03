package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void isWithinThreeDays_returnsTrue() {
        Date futureDate = new Date(LocalDate.now().plusDays(2).toString());
        assertEquals(futureDate.isWithinThreeDays(), true);

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
