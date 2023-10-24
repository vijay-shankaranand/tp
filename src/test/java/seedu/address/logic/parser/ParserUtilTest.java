package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.address.model.event.EventAddress;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EVENT_NAME = "NUS&%Career Fair";
    private static final String INVALID_EVENT_DATE = "2020-22-15";
    private static final String INVALID_EVENT_ADDRESS = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_EVENT_NAME = "NUS Career Fair";
    private static final String VALID_EVENT_DATE = "2023-12-15";
    private static final String VALID_EVENT_ADDRESS = "123 Main Street #0505";

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
    public void parseEventName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName((String) null));
    }

    @Test
    public void parseEventName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName(INVALID_EVENT_NAME));
    }

    @Test
    public void parseEventName_validValueWithoutWhitespace_returnsEventName() throws Exception {
        EventName expectedEventName = new EventName(VALID_EVENT_NAME);
        assertEquals(expectedEventName, ParserUtil.parseEventName(VALID_EVENT_NAME));
    }

    @Test
    public void parseEventName_validValueWithWhitespace_returnsTrimmedEventName() throws Exception {
        String eventNameWithWhitespace = WHITESPACE + VALID_EVENT_NAME + WHITESPACE;
        EventName expectedEventName = new EventName(VALID_EVENT_NAME);;
        assertEquals(expectedEventName, ParserUtil.parseEventName(eventNameWithWhitespace));
    }

    @Test
    public void parseEventDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDate((String) null));
    }

    @Test
    public void parseEventDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(INVALID_EVENT_DATE));
    }

    @Test
    public void parseEventDate_validValueWithoutWhitespace_returnsEventDate() throws Exception {
        EventDate expectedEventDate = new EventDate(VALID_EVENT_DATE);
        assertEquals(expectedEventDate, ParserUtil.parseEventDate(VALID_EVENT_DATE));
    }

    @Test
    public void parseEventDate_validValueWithWhitespace_returnsTrimmedEventDate() throws Exception {
        String eventDateWithWhitespace = WHITESPACE + VALID_EVENT_DATE + WHITESPACE;
        EventDate expectedEventDate = new EventDate(VALID_EVENT_DATE);
        assertEquals(expectedEventDate, ParserUtil.parseEventDate(eventDateWithWhitespace));
    }

    @Test
    public void parseEventAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventAddress((String) null));
    }

    @Test
    public void parseEventAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventAddress(INVALID_EVENT_ADDRESS));
    }

    @Test
    public void parseEventAddress_validValueWithoutWhitespace_returnsEventAddress() throws Exception {
        EventAddress expectedEventAddress = new EventAddress(VALID_EVENT_ADDRESS);
        assertEquals(expectedEventAddress, ParserUtil.parseEventAddress(VALID_EVENT_ADDRESS));
    }

    @Test
    public void parseEventAddress_validValueWithWhitespace_returnsTrimmedEventAddress() throws Exception {
        String eventAddressWithWhitespace = WHITESPACE + VALID_EVENT_ADDRESS + WHITESPACE;
        EventAddress expectedEventAddress = new EventAddress(VALID_EVENT_ADDRESS);
        assertEquals(expectedEventAddress, ParserUtil.parseEventAddress(eventAddressWithWhitespace));
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


}
