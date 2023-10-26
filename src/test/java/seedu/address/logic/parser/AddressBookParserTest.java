package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ViewContactsCommand;
import seedu.address.logic.commands.contact.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.LinkCommand;
import seedu.address.logic.commands.event.SelectEventCommand;
import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.logic.commands.tag.DeleteTagCommand;
import seedu.address.logic.commands.tag.FilterCommand;
import seedu.address.logic.commands.tag.ViewTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.PersonIsTaggedPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.name.Name;
import seedu.address.model.name.NameContainsKeywordsPredicate;
import seedu.address.model.contact.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.contact.EditPersonDescriptorBuilder;
import seedu.address.testutil.contact.ContactBuilder;
import seedu.address.testutil.contact.PersonUtil;
import seedu.address.testutil.event.EventBuilder;
import seedu.address.testutil.event.EventUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddContactCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new ContactBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        Tag tag = new Tag("friends");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        PersonIsTaggedPredicate predicate = new PersonIsTaggedPredicate(tagList);

        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " " + "friends");
        assertEquals(new FilterCommand(tagList, predicate), command);
    }

    @Test
    public void parseCommand_link() throws Exception {
        Name name = new Name("NUS Career Fair");
        Name contactName = new Name("Li Mei");
        Set<Name> contactNameList = new HashSet<>();
        contactNameList.add(contactName);

        LinkCommand command = (LinkCommand) parser.parseCommand(EventUtil.getLinkCommand(name, contactName));
        assertEquals(new LinkCommand(name, contactNameList), command);
    }

    @Test
    public void parseCommand_selectEvent() throws Exception {
        SelectEventCommand command = (SelectEventCommand) parser.parseCommand(
                SelectEventCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new SelectEventCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_viewevent() throws Exception {
        assertTrue(parser.parseCommand(ViewEventsCommand.COMMAND_WORD) instanceof ViewEventsCommand);
        assertTrue(parser.parseCommand(ViewEventsCommand.COMMAND_WORD + " 3") instanceof ViewEventsCommand);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        Event event = new EventBuilder().withEventContacts().build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(EventUtil.getAddEventCommand(event));
        assertEquals(new AddEventCommand(event), command);
    }

    @Test
    public void parseCommand_home() throws Exception {
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD + " 3") instanceof HomeCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_addTag() throws Exception {
        assertTrue(parser.parseCommand(AddTagCommand.COMMAND_WORD + " t/vendor") instanceof AddTagCommand);
    }

    @Test
    public void parseCommand_viewtag() throws Exception {
        assertTrue(parser.parseCommand(ViewTagsCommand.COMMAND_WORD) instanceof ViewTagsCommand);
        assertTrue(parser.parseCommand(ViewTagsCommand.COMMAND_WORD + " 3") instanceof ViewTagsCommand);
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        Tag tag = new Tag("vendor");
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(DeleteTagCommand.COMMAND_WORD
                + " " + "t/vendor");
        assertEquals(new DeleteTagCommand(tag), command);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ViewContactsCommand.COMMAND_WORD) instanceof ViewContactsCommand);
        assertTrue(parser.parseCommand(ViewContactsCommand.COMMAND_WORD + " 3") instanceof ViewContactsCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
