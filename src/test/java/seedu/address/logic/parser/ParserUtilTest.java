package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.address.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.date.Date;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_NAME_DOUBLE_WHITESPACE = "Rachel  Lim";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EVENT_DATE = "2020-22-15";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String INVALID_ADDRESS_DOUBLE_WHITESPACE = "123  Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_EVENT_DATE = "2023-12-15";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_invalidValueDoubleWhiteSpace_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE, "Name"), ()
                -> ParserUtil.parseName(INVALID_NAME_DOUBLE_WHITESPACE));
    }
    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_invalidValueDoubleWhiteSpace_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE, "Address"), () ->
                ParserUtil.parseAddress(INVALID_ADDRESS_DOUBLE_WHITESPACE));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_EVENT_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedEventDate = new Date(VALID_EVENT_DATE);
        assertEquals(expectedEventDate, ParserUtil.parseDate(VALID_EVENT_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedEventDate() throws Exception {
        String eventDateWithWhitespace = WHITESPACE + VALID_EVENT_DATE + WHITESPACE;
        Date expectedEventDate = new Date(VALID_EVENT_DATE);
        assertEquals(expectedEventDate, ParserUtil.parseDate(eventDateWithWhitespace));
    }

    @Test
    public void parseContactNames_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContactNames((Collection<String>) null));
    }

    @Test
    public void parseContactNames_collectionWithInvalidContactNames_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactNames(Arrays.asList(VALID_NAME, INVALID_NAME)));
    }

    @Test
    public void parseContactNames_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseContactNames(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseContactNames_collectionWithValidContactNames_returnsContactNameSet() throws Exception {
        Set<Name> actualContactNameSet = ParserUtil.parseContactNames(Arrays.asList(VALID_NAME, VALID_NAME));
        Set<Name> expectedContactNameSet = new HashSet<Name>(Arrays.asList(new Name(VALID_NAME), new Name(VALID_NAME)));

        assertEquals(expectedContactNameSet, actualContactNameSet);
    }

    @Test
    public void parseTaskDescriptions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskDescription(null));
    }

    @Test
    public void parseTaskDescriptions_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskDescription(""));
    }

    @Test
    public void parseTaskDescriptions_validValueWithoutWhitespace_returnsTaskDescription() throws Exception {
        TaskDescription expectedTaskDescription = new TaskDescription("test");
        assertEquals(expectedTaskDescription, ParserUtil.parseTaskDescription("test"));
    }

    @Test
    public void parseTaskDescriptions_validValueWithWhitespace_returnsTrimmedTaskDescription() throws Exception {
        String taskDescriptionWithWhitespace = WHITESPACE + "test" + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription("test");
        assertEquals(expectedTaskDescription, ParserUtil.parseTaskDescription(taskDescriptionWithWhitespace));
    }

    @Test
    public void parseTaskDescriptions_validValueWithSpecialCharacter_returnsTrimmedTaskDescription() throws Exception {
        String taskDescriptionWithSpecialCharacter = WHITESPACE + "test@#$%^&*()" + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription("test@#$%^&*()");
        assertEquals(expectedTaskDescription, ParserUtil.parseTaskDescription(taskDescriptionWithSpecialCharacter));
    }

}
