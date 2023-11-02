package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.JobFestGo;
import seedu.address.model.ReadOnlyJobFestGo;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * An Immutable JobFestGo that is serializable to JSON format.
 */
@JsonRootName(value = "JobFestGo")
class JsonSerializableJobFestGo {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";

    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";

    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate tasks(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    private final List<JsonAdaptedTag> tagList = new ArrayList<>();

    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    private final List<JsonAdaptedTask> taskList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJobFestGo} with the given Contacts, tags, events, tasks.
     */
    @JsonCreator
    public JsonSerializableJobFestGo(@JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                       @JsonProperty("tagList") List<JsonAdaptedTag> tagList,
                                       @JsonProperty("events") List<JsonAdaptedEvent> events,
                                       @JsonProperty("taskList") List<JsonAdaptedTask> taskList
    ) {
        this.contacts.addAll(contacts);
        this.tagList.addAll(tagList);
        this.events.addAll(events);
        this.taskList.addAll(taskList);
    }


    /**
     * Converts a given {@code ReadOnlyJobFestGo} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJobFestGo}.
     */
    public JsonSerializableJobFestGo(ReadOnlyJobFestGo source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        tagList.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        taskList.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this jobfestgo into the model's {@code JobFestGo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JobFestGo toModelType() throws IllegalValueException {
        JobFestGo jobFestGo = new JobFestGo();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (jobFestGo.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            jobFestGo.addContact(contact);
        }
        for (JsonAdaptedTag jsonAdaptedTag : tagList) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (jobFestGo.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            jobFestGo.addTag(tag);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (jobFestGo.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            jobFestGo.addEvent(event);
        }
        for (JsonAdaptedTask jsonAdaptedTask : taskList) {
            Task task = jsonAdaptedTask.toModelType();
            task = new Task(task.getDescription(), task.getDate(),
                    jobFestGo.getEvent(task.getAssociatedEventName()), task.isCompleted());
            if (jobFestGo.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            jobFestGo.addTask(task);
        }
        return jobFestGo;
    }

}
