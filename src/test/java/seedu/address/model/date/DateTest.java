package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    @Test
    public void equals() {
        LocalDate nullDate = null;
        LocalDate date = new Date("2023-11-04");
        assertEquals(date.equals(nullDate), false);
    }
}
