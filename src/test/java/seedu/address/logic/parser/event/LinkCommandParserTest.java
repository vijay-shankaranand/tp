package seedu.address.logic.parser.event;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalEvents.JOBFEST;
import static seedu.address.testutil.person.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.LinkCommand;
import seedu.address.testutil.event.EventUtil;

public class LinkCommandParserTest {
    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        LinkCommand expectedLinkCommand = new LinkCommand(JOBFEST.getName(), CARL.getName());

        System.out.println(EventUtil.getEventName(JOBFEST.getName())
                + EventUtil.getContactName(CARL.getName()));

        assertParseSuccess(parser," " + EventUtil.getEventName(JOBFEST.getName())
                        + EventUtil.getContactName(CARL.getName()), expectedLinkCommand);
    }

//    @Test
//    public void parse_repeatedValue_failure() {
//        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;
//
//        // multiple names
//        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // multiple phones
//        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // multiple emails
//        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // multiple addresses
//        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // multiple fields repeated
//        assertParseFailure(parser,
//                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
//                        + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));
//
//        // invalid value followed by valid value
//
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // valid value followed by invalid value
//
//        // invalid name
//        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//                expectedMessage);
//
//        // missing phone prefix
//        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//                expectedMessage);
//
//        // missing email prefix
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
//                expectedMessage);
//
//        // missing address prefix
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
//                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
//                expectedMessage);
//    }
}
