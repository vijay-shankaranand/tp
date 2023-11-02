package seedu.address.logic.parser.event;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.event.UnlinkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;

/**
 * Parses input arguments and creates a new UnlinkCommand object
 */
public class UnlinkCommandParser implements Parser<UnlinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnlinkCommand
     * and returns an UnlinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnlinkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_CONTACT);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT, PREFIX_CONTACT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlinkCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());
        Set<Name> contactNameList = ParserUtil.parseContactNames(argMultimap.getAllValues(PREFIX_CONTACT));

        return new UnlinkCommand(name, contactNameList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
