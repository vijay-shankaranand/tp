package seedu.address.logic.parser.event;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.event.AddEventCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalEvents.NTU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.model.address.Address;
import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.testutil.event.EventBuilder;


public class AddEventCommandParserTest {

    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event correctEvent = new EventBuilder(NTU).withEventContacts().build();
        AddEventCommand correctCommand = new AddEventCommand(correctEvent);
        assertParseSuccess(parser, " " + PREFIX_NAME
                + NTU.getName()
                + " " + PREFIX_DATE + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress(), correctCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing name prefix
        assertParseFailure(parser, " " + NTU.getName() + " "
                        + PREFIX_DATE + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // missing date prefix
        assertParseFailure(parser, " " + PREFIX_NAME + NTU.getName() + " "
                        + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // missing address prefix
        assertParseFailure(parser, " " + PREFIX_NAME + NTU.getName() + " "
                        + PREFIX_DATE + NTU.getDate() + " " + NTU.getAddress(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedEventString = " " + PREFIX_NAME + NTU.getName() + " "
                + PREFIX_DATE + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress();

        // multiple names
        assertParseFailure(parser, " " + PREFIX_NAME + NTU.getName() + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple dates
        assertParseFailure(parser, " " + PREFIX_DATE + NTU.getDate() + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple addresses
        assertParseFailure(parser, " " + PREFIX_ADDRESS + NTU.getAddress() + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedEventString + " " + PREFIX_ADDRESS + NTU.getAddress()
                        + " " + PREFIX_DATE + NTU.getDate() + " " + PREFIX_NAME + NTU.getName()
                        + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_DATE, PREFIX_ADDRESS));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid date
        assertParseFailure(parser, INVALID_DATE_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedEventString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid date
        assertParseFailure(parser, validExpectedEventString + INVALID_DATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // invalid address
        assertParseFailure(parser, validExpectedEventString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, " " + INVALID_NAME_DESC + " "
                + PREFIX_DATE + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress(), Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, " " + PREFIX_NAME + NTU.getName()
                + INVALID_DATE_DESC + " " + PREFIX_ADDRESS + NTU.getAddress(), Date.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, " " + PREFIX_NAME + NTU.getName() + " "
                + PREFIX_DATE + NTU.getDate() + " " + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " " + INVALID_NAME_DESC
                        + INVALID_DATE_DESC + " " + PREFIX_ADDRESS + NTU.getAddress(),
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " " + PREFIX_NAME + NTU.getName() + " "
                        + PREFIX_DATE + NTU.getDate() + " " + PREFIX_ADDRESS + NTU.getAddress(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}
