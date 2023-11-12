---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# JobFestGo Developer Guide

## **Table of Content**
 - [Acknowledgements](#acknowledgements)
 - [Setting up, Getting started](#setting-up-getting-started)
 - [Design](#design)
    - [Architecture](#architecture)
    - [UI component](#ui-component)
    - [Logic component](#logic-component)
    - [Model component](#model-component)
    - [Storage component](#storage-component)
    - [Common classes](#common-classes)
 - [Implementation](#implementation)
    - [Return to Home feature](#return-to-home-feature)
    - [Select Event feature](#select-event-feature)
    - [Add Tag feature](#add-tag-feature)
    - [Link/Unlink feature](#link-unlink-feature)
 - [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
 - [Appendix: Requirements](#appendix-requirements)
    - [Product scope](#product-scope)
    - [User stories](#user-stories)
    - [Use cases](#use-cases)
    - [Non-Functional requirements](#non-functional-requirements)
    - [Glossary](#glossary)
 - [Appendix: Effort](#appendix-effort)
 - [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)
    - [Launch and Shutdown](#launch-and-shutdown)
    - [Returning to home page](#returning-to-home-page)
    - [Adding a contact](#adding-a-contact)
    - [Listing all contacts](#listing-all-contacts)
    - [Deleting a contact](#deleting-a-contact)
    - [Editing a contact](#editing-a-contact)
    - [Locating contacts by name](#locating-contacts-by-name)
    - [Adding a tag](#adding-a-tag)
    - [Viewing all tags](#viewing-all-tags)
    - [Deleting a tag](#deleting-a-tag)
    - [Deleting an event](#deleting-an-event)
    - [Viewing all events](#viewing-all-events)
    - [Selecting an event](#selecting-an-event)
    - [Adding a task](#adding-a-task)
    - [Saving data](#saving-data)
 - [Appendix: Planned Enhancements](#appendix-planned-enhancements)
--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the [_AddressBook-Level3_](https://se-education.org/addressbook-level3/) project.


--------------------------------------------------------------------------------------------------------------------

## **Setting up, Getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_contact 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `EventContactDisplay`, `ContactListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Contact` and `Event` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_contact 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete_contact 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteContactCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an  `JobFestGoParser` object which in turn creates a parser that matches the command (e.g., `DeleteContactCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteContactCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a contact).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `JobFestGoParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddContactCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddContactCommand`) which the `JobFestGoParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddContactCommandParser`, `DeleteContactCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="1000" />


The `Model` component,

* stores the JobFestGo data i.e., all `Contact`, `Event`, `Tag`, and `Task` objects which are contained in their respective `UniqueContactList`, `UniqueEventList`, `UniqueTagList`, `UniqueTaskList` objects.
* stores the currently 'selected' `Contact`/`Event`/`Tag`/`Task` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Contact>`/`ObservableList<Event>`/`ObservableList<Tag>`/`ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="650" />

The `Storage` component,
* can save both JobFestGo data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `JobFestGoStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Return to Home feature

The home mechanism is facilitated by `JobFestGo` as well as its observable lists for `Contact`, `Event`, as well as `Task`.

The mechanism interacts with both the UI and the lists stored within `JobFestGo`, particularly `unfilteredContacts`, `unfilteredEvents`, and `filteredTasks`.

Given below is an example usage scenario and how the home mechanism behaves at each step.

**Step 1.** The user has input any other command that is not `home`.

**Step 2.** The user executes `home` command to return to the home page.
The `home` command calls upon the creation of `TaskInReminderPredicate` while using `PREDICATE_SHOW_ALL_CONTACTS` and `PREDICATE_SHOW_ALL_EVENTS` to update the respective filtered lists.

The following sequence diagram shows how the select event operation works:

<puml src="diagrams/HomeSequenceDiagram.puml" alt="HomeSequenceDiagram" />

The following activity diagram summarizes what happens when a user executes the home command:

<puml src="diagrams/HomeActivityDiagram.puml" width="325" />

#### Design considerations:

**Aspect: How home is designed**

* **Current choice:** A home command that users have to type in.
    * Pros: Easy to remember and type since it is only 4 letters. Easier to implement with JobFestGo being CLI based.
    * Cons: Not as intuitive to use as Alternative 1.
    <br></br>
* **Alternative 1:** A home button in the accessibility bar right beside `File` and `Help`.
    * Pros: Even easier to use as only one mouse click is required. No typing is needed.
    * Cons: Harder to implement.

### Add Tag feature

#### Implementation

The add tag mechanism is facilitated by `JobFestGo` as well as its observable lists for `Tag`.

The mechanism interacts with both the UI and the `filteredTags` list stored within `JobFestGo`.

It includes the following operations in ModelManager which is implemented by Model:

* `Model#addTag(Tag)` — Adds a tag to JobFestGo.
* `Model#updateFilteredTagList(Predicate)` — Updates the filtered tag list according to the given predicate.

Given below is an example usage scenario and how the add tag mechanism behaves at each step:

**Step 1.** The user launches the application for the first time. The `JobFestGo` will be initialized with the initial address book state.

**Step 2.** The user executes `add_tag t/vendor` command to add a new tag `Vendor`in JobFestGo.
The parser will parse the command and create a new `AddTagCommand` object, if the entered tag is valid.
<box type="info" seamless>

**Note:** If the tag entered contains non-alphanumeric characters, `AddTagCommandParser` will throw an error when creating the `AddTagCommand` object.

</box>

**Step 3.** The `AddTagCommand` object, created in step 2, will call `Model#addTag(Tag)` to add the tag to JobFestGo.
The `AddTagCommand` object will then call `Model#updateFilteredTagList(Predicate)` to update the filtered tag list.

The following sequence diagram shows how the add tag operation works:

<puml src="diagrams/AddTagSequenceDiagram.puml" alt="AddTagSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `AddTagCommandParser` and `AddTagCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How the add tag command accepts tag inputs**

* **Current choice:** Users can input the tags comprising only of alphanumeric characters.
    * Pros: Easy to implement due to lesser parsing involved.
    * Cons: Users cannot input tags comprising non-alphanumeric characters, such as spaces and special characters.

* **Alternative:** Users can input the tags comprising alphanumeric characters and non-alphanumeric characters.
    * Pros: Users can input tags comprising non-alphanumeric characters, such as spaces and special characters.
    * Cons: Lesser uniqueness of tags as users can input tags with the same name but different non-alphanumeric characters.

### Add Event feature

#### Implementation

The add event mechanism is facilitated by `JobFestGo` as well as its observable lists for `Event`.

The mechanism interacts with both the UI and the `filteredEvents` list stored within `JobFestGo`. It holds a reference to the `Model` component, because the `AddEventCommand` relies on the `Model` to add the event.

It includes the following operations in ModelManager which is implemented by Model:

* `Model#addEvent(Event)` — Adds an event to JobFestGo.
* `Model#updateFilteredEventList(Predicate)` — Updates the filtered event list according to the given predicate.

Given below is an example usage scenario and how the add event mechanism behaves at each step:

**Step 1.** The user launches the application for the first time. The `JobFestGo` will be initialized with the initial address book state.

**Step 2.** The user executes `add_event n/NUS Career Fest d/2024-02-15 a/NUS` command to add a new event `NUS Career Fest` on `2024-02-15` in JobFestGo. The parser will parse the command and create a new `AddEventCommand` object, if the entered event is valid.

<box type="info" seamless>

**Note:** If the event name entered contains non-alphanumeric characters or the date of event is before current date, `AddEventCommandParser` will throw an error when creating the `AddEventCommand` object.

</box>

**Step 3.** The `AddEventCommand` object, created in step 2, will call `Model#addEvent(Event)` to add the event to JobFestGo. The `AddEventCommand` object will then call `Model#updateFilteredEventList(Predicate)` to update the filtered event list.

The following sequence diagram shows how the add event operation works:

<puml src="diagrams/AddEventSequenceDiagram.puml" alt="AddEventSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `AddEventCommandParser` and `AddEventCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes the add event command:

<puml src="diagrams/AddEventActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How the add event command accepts event name inputs**

* **Current choice:** Users can input the event name comprising only of alphanumeric characters.
  * Pros: Easy to implement due to lesser parsing methods and classes involved.
  * Cons: Users cannot input event names comprising non-alphanumeric characters, such as spaces and special characters.
<br></br>
* **Alternative 1:** Users can input the event name comprising alphanumeric characters and non-alphanumeric characters.
  * Pros: Users can input event names comprising non-alphanumeric characters, such as spaces and special characters.
  * Cons: Harder to implement due to more parsing methods and classes involved. There is a need to create an entire new class for eventName whereas the current implementation uses the existing `Name` class.

**Aspect: How the add event command accepts event date inputs**

* **Current choice:** Users can input the event date in the format of `YYYY-MM-DD`.
  * Pros: Easy to implement due to lesser parsing methods and classes involved.
  * Cons: Users cannot input event dates in other formats.
<br></br>
* **Alternative 1:** Users can input the event date in other formats.
  * Pros: Users can input event dates in other formats.
  * Cons: Harder to implement as `LocalDate#parse(String)` accepts only String of a specific format.

### Select Event feature

#### Implementation

The select event mechanism is facilitated by `JobFestGo` as well as its observable lists for `Contact`, `Event`, as well as `Task`.

The mechanism interacts with both the UI and the lists stored within `JobFestGo`, particularly `filteredContacts`, `filteredEvents`, and `filteredTasks`.

Given below is an example usage scenario and how the select event mechanism behaves at each step.

**Step 1.** The user launches the application for the first time. The `JobFestGo` will be initialized with the initial address book state.

**Step 2.** The user executes `select_event 1` command to select the 1st event in JobFestGo. The `select_event` command calls upon the creation of `ContactIsInEventPredicate` and `TaskIsInEventPredicate` to update the respective filtered lists.

The following sequence diagram shows how the select event operation works:

<puml src="diagrams/SelectEventSequenceDiagram.puml" alt="SelectEventSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifelines for `SelectEventCommand` and `SelectEventCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes the select event command:

<puml src="diagrams/SelectEventActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How select event executes**

* **Current choice:** Highlights the event selected.
  * Pros: Visually appealing.
  * Cons: Slightly harder to implement.
    <br></br>
* **Alternative 1:** Event list gets updated to only show the selected event.
  * Pros: Easy to implement.
  * Cons: Users will have to consistently execute home command to view the other events.

### Link/Unlink feature

#### Implementation

The link/unlink mechanism is facilitated by `JobFestGo` as well as its internally stored `UniqueEventList`.
It implements the following operations:

* `JobFestGo#isContactLinkedToEvent()` — Checks whether a `Contact` is linked to an `Event`.
* `JobFestGo#linkContactToEvent()` — Links a `Contact` to an `Event`.
* `JobFestGo#unlinkContactFromEvent()` — Unlinks a `Contact` from an `Event`.

These operations are exposed in the `Model` interface as `Model#isContactLinkedToEvent()`, `Model#linkContactToEvent()` and `Model#unlinkContactFromEvent()` respectively.

Given below is an example usage scenario and how the link/unlink mechanism behaves at each step.

**Step 1.** The user executes `link c/Alice Black c/Bob White ev/NUS Career Fest` command to link the contacts specified by the names "Alice Black" and "Bob White" to the event specified by the name "NUS Career Fest" in JobFestGo. The `link` command initiates a loop to iterate through each input `Contact`.

<box type="info" seamless>

**Note:** If the `Contact` or the `Event` does not exist in JobFestGo, it is not possible to perform the link operation. `Model#getContact()` or `Model#getEvent()` will throw a `ContactNotFoundException` or an `EventNotFoundException` respectively and an error message will be shown to the user.

</box>

**Step 2.** For each `Contact` in the loop, `Model#linkContactToEvent()` is called, adding the `Contact` to the internally stored associated contact list of the `Event`.

<box type="info" seamless>

**Note:** If the `Contact` is already inside the associated contact list of the `Event`, there is no need to perform the link operation again. The `link` command uses `Model#isContactLinkedToEvent()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.

</box>

The following sequence diagram shows how the link operation works:

<puml src="diagrams/LinkSequenceDiagram.puml" alt="LinkSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `LinkCommand` and `LinkCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `unlink` command does the opposite — it calls `Model#unlinkContactFromEvent()`, which removes the specified `Contact`(s) from the internally stored associated contact list of the specified `Event`.

#### Design considerations:

**Aspect: How link/unlink command gets contacts and event:**

* **Current choice:** Gets contacts and event by names.
    * Pros: Clearer for users to execute the command.
    * Cons: We must devise a new way of getting contacts and event by names since the default implementation is to get by index.
    <br></br>
* **Alternative 1:** Gets contacts and event by index.
    * Pros: Easy to implement.
    * Cons: Users may be confused by multiple indices.

**Aspect: How a mix of valid and invalid input should be handled:**

* **Current choice:** Does not perform any link/unlink operations once there is one invalid input encountered.
    * Pros: Easy to implement.
    * Cons: Users need to reenter all other input once there is one invalid input.
    <br></br>
* **Alternative 1:** Links/Unlinks all valid input contacts while throwing an error for all invalid contacts.
    * Pros: Users only need to correct the invalid input.
    * Cons: The exceptions are hard to handle.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
* is a job festival event planner in Singapore
* has a need to manage a significant number of contacts of different types (e.g. vendors, customers) and tasks for different events
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Each event planner has many events to plan, each of which can have a large number of contacts and tasks associated and searching for contact would be a hassle. Our product provides a centralised system that would help job event planners organise their contact information and tasks for quick and easy access.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                    | So that I can…​                                                                             |
|----------|--------------------------------------------|---------------------------------|---------------------------------------------------------------------------------------------|
| `* * *`  | job fest event planner                     | see usage instructions          | refer to instructions when I forget how to use JobFestGo                                    |
| `* * *`  | job fest event planner                     | add a new contact               |                                                                                             |
| `* * *`  | job fest event planner                                       | delete a contact                | remove entries that I no longer need                                                        |
| `* * *`  | job fest event planner                                       | find a contact by name          | locate details of contacts without having to go through the entire list                     |
| `* * *`  | job fest event planner                     | view the entire contact list    |                                                                                             |
| `* *`    | job fest event planner                     | add tags                        | add to the pool of use categories already available                                         |
| `* *`    | job fest event planner                     | view all tags                   | remember contacts of a certain category to contact them for events                          |
| `* *`     | job fest event planner                     | be able to delete tags          | can easily identify who I should be cold calling among my contacts without unnecessary tags |
| `* *`     | job fest event planner                     | filter contacts by tags         | conveniently view all the contacts tagged by specific tags                                  |
| `* *`    | job fest event planner                        | add a new event                 | keep track of the events I have to plan                                                     |
| `* *`     | job fest event planner                     | view all events                 | remember all the events I am involved in so far                                             |
| `* *`    | job fest event planner                        | be able to delete events        | remove events I no longer need                                                              |
| `* *`     | job fest event planner                     | be able to select events        | easily view the contacts and tasks to do for each particular event                          |
| `* *`     | job fest event planner                     | link contacts to events         | remember which event the specific contacts are involved in                                  |
| `* *`     | job fest event planner                     | unlink contacts from events     | remove the association between contacts and events when they are no longer related          |
| `* *`    | job fest event planner                        | add a new task for an event     | remember the tasks I need to do for the event                                               |
| `* *`    | job fest event planner                        | delete a task from an event     | remove tasks that I no longer need                                                          |
| `* *`    | job fest event planner                        | mark a task as completed or not | easily check the progress of my tasks                                                       |
| `* *`    | job fest event planner                        | clear all the data                     | start a new event planning process with new data                                            |
| `* *`      | job fest event planner | return to the home page         |                                                                                             |
| `*`      | job fest event planner | sort contacts by name           | locate a contact easily                                                                     |


### Use cases

(For all use cases below, the **System** is the `JobFestGo` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Return to the home page**

**MSS**
1. User is on any page
2. User requests to return to the home page
3. JobFestGo returns user to the home page

   Use case ends.

**Extensions**

* 2a. The user is already on the home page

  Use case ends.

**Use case: Add a contact**

**MSS**

1.  User requests to add contact and specifies details of contact
2.  JobFestGo adds the contact to list of contacts
3.  JobFestGo shows updated list of contacts

    Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified

  * 1a1. JobFestGo informs user that mandatory fields not specified

    Use case ends.

* 1b. Phone number already exists.

    * 1b1. JobFestGo informs user that phone number already exists.

      Use case ends.

* 1c. Email is in incorrect format.

    * 1c1. JobFestGo informs user that email is in wrong format.

      Use case ends.

* 1d. Phone number is in incorrect format.

    * 1d1. JobFestGo informs user that phone number is in wrong format.

      Use case ends.

* 1e. Tag is not found in list.

    * 1e1. JobFestGo informs user that tag input is not in tag List.

      Use case ends.

**Use case: Delete a contact**

**MSS**

1.  User requests to list contacts
2.  JobFestGo shows a list of contacts
3.  User requests to delete a specific contact in the list
4.  JobFestGo deletes the contact

   Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. JobFestGo shows an error message.

      Use case resumes at step 2.

* 3b. Missing index.

    * 3b1. JobFestGo shows an error message.

      Use case resumes at step 2.

**Use case: List all contacts**

**MSS**

1.  User requests to list all contacts
2.  JobFestGo shows a list of all contacts

    Use case ends.


**Use case: Add a tag**

**MSS**

1.  User requests to add tag
2.  User keys in the tag to add to the collection of tags
3.  JobFestGo adds the tag

   Use case ends.

**Extensions**
* 2a. Missing tag name.

    * 2a1. JobFestGo shows an error message.

      Use case resumes at step 2.

* 2b. The given tag name contains non-alphanumeric characters.

    * 2b1. JobFestGo shows an error message.

      Use case resumes at step 2.

* 3a. The given tag name is already in the tag list.

    * 3a1. JobFestGo shows an error message.

      Use case resumes at step 2.

**Use case: View all tags**

**MSS**
1. User requests to list tags
2. JobFestGo shows a list of all tags

   Use case ends.

**Use case: Delete a tag**

**MSS**

1. User requests to list the tags
2. JobFestGo shows a list of tags
3. User requests to delete a specified tag in the list
4. JobFestGo deletes the tag

      Use case ends.

**Extensions**

* 2a. The list is empty.

   Use case ends.

* 3a. The given tag name is invalid.

    * 3a1. JobFestGo shows an error message.

      Use case resumes at step 2.

* 3b. Missing tag name.

    * 3b1. JobFestGo shows an error message.

      Use case resumes at step 2.

**Use case: Filter contacts by tag**

**MSS**
1. User requests to filter contacts by tags
2. JobFestGo shows a list of contacts tagged by specified tags

   Use case ends.

**Extensions**
* 1a. The given tag name is invalid.

    * 1a1. JobFestGo shows an error message.

      Use case resumes at step 1.

**Use case: Add an event**

**MSS**

1. User requests to add event
2. User specifies the details of the event
3. JobFestGo adds the event to collection of events
4. JobFestGo shows updated list of events

   Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified

    * 1a1. JobFestGo informs user that mandatory fields not specified

      Use case ends.

* 1b. Event name already exists.

    * 1b1. JobFestGo informs user that event name already exists.

      Use case ends.

* 1c. Date is in incorrect format.

    * 1c1. JobFestGo informs user that date is in wrong format.

      Use case ends.

* 1d. Date is before current date.

    * 1d1. JobFestGo informs user that date is before current date.

      Use case ends.

**Use case: View all events**

**MSS**
1. User requests to list events
2. JobFestGo shows a list of all events

   Use case ends.

**Use case: Delete an event**

**MSS**

1. JobFestGo shows a list of events
2. User requests to delete a specified event in the list
3. JobFestGo deletes the event
4. JobFestGo displays the updated events

      Use case ends.

**Extensions**

* 2a. The event list is empty.

   Use case ends.

* 3a. The given index is invalid.

    * 3a1. JobFestGo shows an error message.

      Use case resumes at step 2.

* 3b. Missing index in the command.

    * 3b1. JobFestGo shows an error message.

      Use case resumes at step 2.

**Use case: Select an event**

**MSS**

1. User requests to select an event.
2. JobFestGo displays the contacts and tasks related to the event.
3. User <u>adds a task</u> or <u>deletes a task</u> or <u>marks a task</u> or <u>unmarks a task</u>.

      Use case ends.


**Extensions**

* 1a. The given index is invalid.

    * 1a1. JobFestGo shows an error message.

      Use case resumes at step 1.

* 1b. Missing index.

    * 1b1. JobFestGo shows an error message.

      Use case resumes at step 1.

**Use case: Link contacts to an event**

**MSS**
1. User requests to link specified contacts to a specified event.
2. JobFestGo links the contacts to the event.

   Use case ends.

**Extensions**
* 1a. The given event does not exist.

    * 1a1. JobFestGo shows an error message.

      Use case ends.

* 1b. One of the given contacts does not exist.

    * 1b1. JobFestGo shows an error message.

      Use case ends.

* 1a. One of the given contacts is already linked to the given event.

    * 1a1. JobFestGo shows an error message.

      Use case ends.

**Use case: Unlink contacts from an event**

**MSS**
1. User requests to unlink specified contacts from a specified event.
2. JobFestGo unlinks the contacts from the event.

   Use case ends.

**Extensions**
* 1a. The given event does not exist.

    * 1a1. JobFestGo shows an error message.

      Use case ends.

* 1b. One of the given contacts does not exist.

    * 1b1. JobFestGo shows an error message.

      Use case ends.

* 1a. One of the given contacts is not linked to the given event.

    * 1a1. JobFestGo shows an error message.

      Use case ends.

**Use case: Add a task**

**MSS**

1.  User requests to add task and specifies details of task with the event to be added in.
2.  JobFestGo adds the task to the event specified.
3.  JobFestGo shows updated list of tasks.

    Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified.

    * 1a1. JobFestGo informs user that mandatory fields not specified.

      Use case ends.

* 1b. Task description contains non-alphanumeric characters except `-,./() `.

    * 1b1. JobFestGo informs user that task description contains non-alphanumeric characters.

      Use case ends.
  
* 1c. Date is invalid.

    * 1c1. JobFestGo informs user that date is invalid.

      Use case ends.

* 1c. Event does not exist.

    * 1d1. JobFestGo informs user that event does not already exist.

      Use case ends.

**Use case: Delete a task**

**MSS**

1.  User requests to delete a specified task.
2.  JobFestGo deletes the task.
3.  JobFestGo shows updated list of tasks.

    Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified.

    * 1a1. JobFestGo informs user that mandatory fields not specified.

      Use case ends.

* 1b. Task does not exist.

    * 1c1. JobFestGo informs user that task does not already exist.

      Use case ends.

**Use case: Marking a task**

**MSS**

1.  User requests to mark a specified task as completed.
2.  JobFestGo marks the task as completed.
3.  JobFestGo shows updated status of the task.

    Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified.

    * 1a1. JobFestGo informs user that mandatory fields not specified.

      Use case ends.

* 1b. Task does not exist.

    * 1c1. JobFestGo informs user that task does not already exist.

      Use case ends.

* 1c. Task is already marked as completed.

    * 1c1. JobFestGo informs user that task is already marked as completed.

      Use case ends.

**Use case: Unmarking a task**

**MSS**

1.  User requests to unmark a specified completed task as not completed.
2.  JobFestGo unmarks the task as not completed.
3.  JobFestGo shows updated status of the task.

    Use case ends.

**Extensions**

* 1a. Any of the mandatory fields not specified.

    * 1a1. JobFestGo informs user that mandatory fields not specified.

      Use case ends.

* 1b. Task does not exist.

    * 1c1. JobFestGo informs user that task does not exist.

      Use case ends.

* 1c. Task is not marked as completed.

    * 1c1. JobFestGo informs user that task is not marked as completed.

      Use case ends.

**Use case: Return to the home page**

**MSS**
1. User is on any page
2. User requests to return to the home page
3. JobFestGo returns user to the home page

   Use case ends.

**Extensions**

* 2a. The user is already on the home page

   Use case ends.


---
### Non-Functional Requirements
1. Environment requirement:
* Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Scalability:
* Should be able to hold up to 1000 events, contacts and tasks without a noticeable sluggishness in performance for typical usage.
3. Usability:
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
* The user interface should be intuitive for event planners to use, for non-tech savy job event planners
4. Performance:
* The system should respond to all inputs within a reasonable time frame (within 2 seconds)
5. Size of application:
* The size of the application's `jar` file should not exceed 100MB
6. Memory usage:
* The system should not use more than 2GB of memory when running.

### Glossary

| Term                 | Definition                                                           |
|----------------------|----------------------------------------------------------------------|
| Activity Diagram     | A diagram that shows activities performed by the system after a command. |
| App                  | An application.                                                      |
| API                  | An application programming interface.                                |
| Architecture Diagram | A diagram that explains the high-level design of the App.            |
| Class Diagram        | A diagram that shows the classes of the App and the relationships between them. |
| CLI                  | Command Line Interface, in which users type commands to perform actions. |
| CommandBox           | A UI component that allows users to enter commands.                  |
| Component            | A part of the App that serves a particular function.                 |
| EventContactDisplay  | A UI component that displays the events, contacts and tasks related to the events. |
| GUI                  | Graphical User Interface, which is a visual way for users to interact with a software program. |
| JavaFX               | The UI framework used in the project.                                |
| Logic                | The component responsible for executing commands entered by users.   |
| MainWindow           | The main window of the UI that houses all the different UI components. |
| Mainstream OS        | Windows, Linux, Unix, Unix, OS-X.                                    |
| Model                | The data model that is used by the App.                              |
| PlantUML             | A tool used to create diagrams seen in this guide.                   |
| ResultDisplay        | A UI component that displays the result after executing a command.   |
| Sequence Diagram     | A diagram that shows how the different components interact with each other for a scenario presented. |
| UI                   | User Interface.                                                      |

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

| Component           | AB3           | JobFestGo                |
|---------------------|---------------|--------------------------|
| Effort              | 10            | 25                       |
| Line of Code        | 6k            | +16k                     |

The JobFestGo project required a significant amount of effort to adapt the AddressBook-Level3 (AB3) application to our specific target user profile. One of the major changes was the addition of three new entity types, being `Event`, `Task`, and `Tag`, requiring some modification to the existing model as well as the associated logic and UI components. Another major change that was done was the redesign of the user interface (UI) using JavaFX. This took a significant amount of time and effort to pick up and implement in our project.

Although code was reused from AB3, such code had to be refactored to better fit our application. This can be seen in the `Tag` class, in which now there are some modifications done to ensure that such tags have to be unique, and has to go through the creation and deletion process for validity checks. Furthermore, with the additional entity types `Event` and `Task`, more effort had to be put in for input verification as well as writing high quality test cases for each entity. This is a time-consuming process.

With the addition of the new entity types, we had to map relationships between `Event` and `Task`, as well as `Event` and `Contact`. To tackle this, the team created a list of tasks and a list of contacts inside each event. This brought on multiple changes that had to be made to other components, such that it would be displayed accurately on the UI upon each command. This can be seen in the `LinkCommand` and `UnlinkCommand` for linking contacts to events, and `AddTaskCommand` for adding tasks to events, as well as their relevant parsers and model methods.

Another challenge was the interface redesign, to display the necessary information we wanted. In the case of JobFestGo, this involved adding multiple new UI components, as well as having to carefully construct the flow of events we would like upon each different command being executed. Through multiple testing, we have reached the current stage in which we think best suits the target user profile.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and Shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts, events and tasks. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. Exiting the app

   1. After keying in a few commands, the application can be exited with the `exit` command.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The application loads with the updated json file.

### Returning to home page

1. Returning to home page

    1. Test case: `home`<br>
       Expected: User returns to the home page. Command success status message shown.

    1. Test case: `home 1`<br>
       Expected: User returns to the home page. Recognised as a `home` command.

### Adding a contact

1. A contact can be added from any page.

   1. Test case: `add_contact n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/vendors t/clients`<br>
      Expected: Contact named John Doe with the respective details created, provided that there is no contact named John Doe or has the phone number 98765432. Returns back to home page.

   1. Test case: `add_contact n/John &nbsp;&nbsp; Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/vendors t/clients`<br>
      Expected: No contact is added. Error details shown in the status message.

   1. Other incorrect add contact commands to try: `add_contact`, `add_contact` with existing phone number, `...`
      Expected: Similar to previous.

### Listing all contacts

1. Listing all contacts

   1. Prerequisite: At least one contact has been added to JobFestGo.

   1. Test case: `view_contacts`<br>
      Expected: All contacts are listed. Command success status message shown.

   1. Test case: `view_contacts 1`<br>
      Expected: All contacts will still be listed. Recognised as a `view_contacts` command.

### Deleting a contact

1. Deleting a contact while all contacts are being shown

   1. Prerequisites: List all contacts using the `view_contacts` command. Multiple contacts in the list.

   1. Test case: `delete_contact 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Returns back to home page.

   1. Test case: `delete_contact 0`<br>
      Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same. Returns back to home page.

   1. Other incorrect delete commands to try: `delete_contact`, `delete_contact x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. Deleting a contact while on the home page

   1. Prerequisites: Be on the home screen using the `home` command. Contact list should be displayed in the middle.

   1. Test cases and expected outcomes are similar to the previous case.

1. Deleting a contact after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected. There must be contacts linked to the event.

   1. Test cases and expected outcomes are similar to the previous case except for that the index is based on the currently displayed contact list associated to the selected event.

<box type="info" seamless>

**Note:** Deleting a contact on other pages causes an error since the contact list is not being displayed.

</box>

### Editing a contact

1. Editing a contact while all contacts are being shown

   1. Prerequisites: List all contacts using the `view_contacts` command. Multiple contacts in the list.

   1. Test case: `edit_contact 1 n/John Doe`<br>
      Expected: First contact in the list is edited to have the name John Doe, provided that there is no John Doe in the contact list. Details of the new contact shown in the status message. Returns back to home page.

   1. Test case: `edit_contact 0 n/John Doe`<br>
      Expected: No contact is edited. Error details shown in the status message.

   1. Other incorrect edit commands to try: `edit_contact` `edit_contact x n/`, `edit_contact 1 p/999`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. Editing a contact while on the home page

   1. Prerequisites: On the home page using the `home` command. Multiple contacts in the list.

   1. Test cases and expected outcomes are similar to the previous case.

1. Editing a contact after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected. There must be contacts linked to the event.

   1. Test cases and expected outcomes are similar to the previous case except for that the index is based on the currently displayed contact list associated to the selected event.

<box type="info" seamless>

**Note:** Editing a contact on other pages causes an error since the contact list is not being displayed.

</box>

### Locating contacts by name

1. Finding contacts while all contacts are being shown

   1. Prerequisites: List all contacts using the `view_contacts` command. Multiple contacts in the list.

   1. Test case: `find_contact alex`<br>
      Expected: All contacts with the name containing `alex` are filtered and shown. Command success status message shown with the number of contacts listed (can be 0 if no valid contacts found).

   1. Test case: `find_contact`<br>
      Expected: Error details shown in the status message.

   1. Other incorrect find contact commands to try: `find_contact @`, `...`<br>
      Expected: Similar to previous.

1. Finding contacts while on the home page

   1. Prerequisites: Be on the home screen using the `home` command. Contact list should be displayed in the middle.

   1. Test cases and expected outcomes are similar to the previous case.

1. Finding contacts after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected. There must be contacts linked to the event.

   1. Test cases and expected outcomes are similar to the previous case except for that only contacts in the currently displayed contact list linked to the selected event will be filtered.

<box type="info" seamless>

**Note:** Finding contacts on other pages causes an error since the contact list is not being displayed.

</box>

### Adding a tag

1. A tag can be added from any page.

   1. Test case: `add_tag t/clients`<br>
      Expected: `clients` tag is added. Details of the added tag shown in the status message. Returns back to home page.

   1. Test case: `add_tag t/`<br>
      Expected: No tag is added. Error details shown in the status message. Returns back to home page.

   1. Other incorrect add tag commands to try: `add_tag`, `add_tag x`, `...`<br>
      Expected: Similar to previous.

### Viewing all tags

1. Viewing all tags

    1. Prerequisites: At least one tag has been added to or is already existing in JobFestGo.

    1. Test case: `view_tags`<br>
       Expected: All tags are listed. Command success status message shown.

    1. Test case: `view_tags 1`<br>
       Expected: All tags will still be listed. Recognised as a `view_tags` command.

### Deleting a tag

1. A tag can be deleted from any page.

   1. Test case: `delete_tag t/clients`<br>
      Expected: `clients` tag is deleted. Details of the deleted tag shown in the status message. Returns back to home page.

   1. Test case: `delete_tag t/`<br>
      Expected: No tag is deleted. Error details shown in the status message. Returns back to home page.

   1. Other incorrect delete tag commands to try: `delete_tag`, `delete_tag x`, `...`<br>
      Expected: Similar to previous.

### Filtering contacts by tag
1. Filtering contacts while all contacts are being shown

   1. Prerequisites: List all contacts using the `view_contacts` command. Multiple contacts in the list.

   2. Test case: `filter_by_tag vendors`<br>
      Expected: All contacts with the tag `vendors` are shown. Command success status message shown with the number of contacts listed (can be 0 if no valid contacts found).

   3. Test case: `filter_by_tag vendors customers`<br>
      Expected: All contacts with the tag `vendors` and all contacts with the tag `customers` are shown. Command success status message shown with the number of contacts listed (can be 0 if no valid contacts found).

   4. Test case: `filter_by_tag`<br>
      Expected: Error details shown in the status message.

   5. Other incorrect filter contact commands to try: `filter_by_tag @`, `...`
      Expected: Similar to previous.

2. Filtering contacts while on the home page

   1. Prerequisites: Be on the home screen using the `home` command. Contact list should be displayed in the middle.

   1. Test cases and expected outcomes are similar to the previous case.

3. Filtering contacts after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected. There must be contacts linked to the event.

   1. Test cases and expected outcomes are similar to the previous case except for that only contacts in the currently displayed contact list linked to the selected event will be filtered.

<box type="info" seamless>

**Note:** Filtering contacts on other pages causes an error since the contact list is not being displayed.

</box>

### Adding an event

1. An event can be added from any page

    1. Test case: `add_event n/NUS Career Fair 2023 d/2024-02-15 a/NUS`<br>
        Expected: Event named NUS Career Fair 2023 with the respective details created, provided that there is no event named NUS Career Fair 2023. If not already at home page, returns to home page.

    1. Test case: `add_event n/NUS Career Fair 2023 d/2024-02-15`<br>
        Expected: No event is added. Error details shown in the status message.

    1. Other incorrect add task commands to try: `add_event`, `add_event n/NUS Career Fair 2023 d/2024-02-15 a/`<br>
        Expected: Similar to previous.

### Deleting an event

1. Deleting an event while all events are being shown

   1. Prerequisites: List all events using the `view_events` command. Multiple events in the list.

   2. Test case: `delete_event 1`<br>
      Expected: First event is deleted from the list. Details of the deleted event shown in the status message. Returns back to home page.

   3. Test case: `delete_event 0`<br>
      Expected: No event is deleted. Error details shown in the status message. Returns back to home page.

   4. Other incorrect delete event commands to try: `delete_event`, `delete_event x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. Deleting an event while on the home page

   1. Prerequisites: Be on the home screen using the `home` command. Event list should be displayed on the left.

   1. Test cases and expected outcomes are similar to the previous case.

3. Deleting an event after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected.

   2. Test cases and expected outcomes are similar to the previous case except for that the index is based on the currently displayed event list.

<box type="info" seamless>

**Note:** Deleting an event on other pages causes an error since the event list is not being displayed.

</box>

### Linking contacts to an event

1. Linking contacts to an event

   1. Prerequisites: At least one event and one contact are already existing in JobFestGo.

   2. Test case: `link ev/NUS Career Fest c/Alice Black`<br>
      Expected: Contact `Alice Black` is linked to the event `NUS Career Fest`. Command success status message shown.

   3. Test case: `link ev/NUS Career Fest c/Alice Black c/David Li`<br>
      Expected: Contact `Alice Black` and contact `David Li` are linked to the event `NUS Career Fest`. Command success status message shown.

   4. Test case: `link`<br>
     Expected: Error details shown in the status message with no operation performed.

### Unlinking contacts from an event

Similar to `link` command except that the command word is `unlink` and the expected result is opposite.

### Viewing all events

1. Viewing all events

    1. Prerequisites: At least one event has been added to or is already existing in JobFestGo.

    1. Test case: `view_events`<br>
       Expected: All events are listed. Command success status message shown.

    1. Test case: `view_events 1`<br>
       Expected: All events will still be listed. Recognised as a `view_events` command.

### Selecting an event

1. Selecting an event when all events are shown

   1. Prerequisites: List all events using the `view_events` command. Multiple events in the list.

   1. Test case: `select_event 1`<br>
      Expected: Selects the first event in the list. Displays the event page, displaying the contacts linked to the event and the tasks of the event. Details of the selected event shown in the status message.

   1. Test case: `select_event 0`<br>
      Expected: No event is selected. Error details shown in the status message.

   1. Other incorrect select event commands to try: `select_event`, `select_event x`, `...` (where x is an integer larger than the list size)<br>
      Expected: Similar to previous.

1. Selecting an event at the home page

   1. Prerequisites: At the home page using the `home` command. Multiple events in the list.

   1. Test cases and expected outcomes are similar to the previous case.

1. Selecting an event after selecting an event

   1. Prerequisites: Be on the selected event page using the `select_event x` command in which x represents the index of the event to be selected.

   1. Test cases and expected outcomes are similar to the previous case.

<box type="info" seamless>

**Note:** Selecting an event on other pages causes an error since the event list is not being displayed.

</box>

### Adding a task

1. A task can be added from any page.

   1. Test case: `add_task td/Book Venue d/2024-10-10 ev/NUS Career Fair 2024`<br>
       Expected: Book Venue task is added to NUS Career Fair 2024 event. Details of the added task shown in the status message. Goes to the respective event page and displays the tasks of the event on the right.

   2. Test case: `add_task td/Book Venue d/2024-10-10`<br>
       Expected: No task is added. Error details shown in the status message.

   3. Other incorrect add task commands to try: `add_task`, `add_task td/Book Venue d/2024-10-10 ev/`<br>
       Expected: Similar to previous.

### Deleting a task

1. A task can be deleted from any page.

   1. Test case: `delete_task td/Book Venue ev/NUS Career Fair 2024`<br>
       Expected: Book Venue task is deleted to NUS Career Fair 2024 event. Command success status message shown. Goes to the respective event page and displays the tasks of the event on the right.

   2. Test case: `delete_task td/Book Venue`<br>
       Expected: No task is deleted. Error details shown in the status message.

   3. Other incorrect add task commands to try: `delete_task`, `delete_task ev/NUS Career Fair 2024`<br>
       Expected: Similar to previous.

<box type="info" seamless>

**Note:** If the task has a due date in the next 3 days or is overdue, then the task will appear under the 'Task Due' section of the home page.

</box>

### Marking a task

1. A task can be marked from any page.

   1. Test case: `mark_task td/Book Venue ev/NUS Career Fair 2024`<br>
      Expected: Book Venue task of NUS Career Fair 2024 event is marked as completed. Command success status message shown. Goes to the respective event page and displays the tasks of the event on the right.

   2. Test case: `mark_task td/Book Venue`<br>
      Expected: No task is marked. Error details shown in the status message.

   3. Other incorrect add task commands to try: `mark_task`, `mark_task ev/NUS Career Fair 2024`<br>
      Expected: Similar to previous.

### Unmarking a task

Similar to `mark_task` command except that the command word is `unmark_task` and the expected result is opposite.

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisite: Have opened and closed the application and confirmed that the files `data/jobfestgo.json` have been created in the same directory that JobFestGo was run from.

   1. Manually edit the file `data/jobfestgo.json` with any text-editing software to break the JSON format or edit the keys in the JSON file. Alternatively, you can delete the file.

   1. Relaunch the application.

   1. Test case: `data/jobfestgo.json` was deleted.
      Expected: JobFestGo starts with pre-loaded event, contact and task lists.

   1. Test case: `data/jobfestgo.json` was corrupted.
      Expected: JobFestGo starts with an empty event, contact, and task lists. All events, contacts and tasks are cleared.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

Given below are the planned enhancements for the app for future releases.

1. **Make error messages more useful**

   Currently, the error messages for certain commands are not very useful.
   For example, if the user enters an invalid date such as `2024-04-31` `add_task`,
   the error message is `Date should be valid and in format YYYY-MM-DD not before today's date`.
   This is not very useful as the user does not know why the date is wrong.
   The error message should be more specific, such as `Entered date is invalid. April 2024 should have DD between 1 and 30 inclusive`.

2. **Allow emails to have top-level domains (TLDs) other than `.com`**

   The email field only allows `.com` TLD in the current implementation.
   This should be changed to allow other TLDs such as `.org`, `.edu`, `.net`, etc.

3. **Allow special characters to be entered in `EventName` and `ContactName`**

    Currently, special characters other than spaces are not allowed in `EventName` and `ContactName`.
    This should be changed to accept these characters to allow for a wider range of valid names.

4. **Upon deletion of a tag, the GUI should remain on `view_tags` screen**

    With the current implementation, when a tag is deleted, the GUI returns to the home page.
    This should be changed to remain on the `view_tags` screen for ease of use and verification of the delete transaction.

5. **Raise error when the same details of a contact is entered in `edit_contact` command**
    
    Currently, when the same details of a contact is entered in `edit_contact` command, the contact is still edited.
    This should be changed to raise an error to inform the user that the edited contact details is the same as the original contact details, so that we can prevent redundant edits.

6. **Use index number instead of event name for commands such as `link` and `add_task`**

    The current implementation of JobFestGo requires the user has to enter the event name for commands such as `link` and `add_task`.
    This should be changed to allow the user to enter the index number of the event instead, to allow for faster input.
    Currently, the user has to enter `link n/John Doe ev/NUS Career Fair 2024` to link John Doe to the event NUS Career Fair 2024.
    With the enhancement, the user can enter `link n/John Doe ev/1` to link John Doe to the first event in the event list.

7. **Enforce stricter deadline date for `add_task` command**

   The current implementation of JobFestGo enables users to have tasks with deadline past the event date for AAR purposes.
   For future enhancements, we plan to provide the user the liberty of selecting a period where the event and the tasks for the event will remain in JobFestGo.
   As an example, for `add_event`, there will be another parameter say `days_past_event` and let the user input the number of days past the event deadline for which tasks can be added.
   If the date for the task exceeds this parameter, it will flag an error. Example usage : `add_task td/Book Venue d/2024-10-10 ev/NUS Career Fair 2024 days_past_event/7`.

8. **Remove events and tasks past a particular period**
    
    The current implementation of JobFestGo enables users to have events and tasks with deadline past the event date for AAR purposes.
    Users have to manually delete the events and tasks once completed.
    For future enhancements, continuing from the previous enhancement, we plan to remove the events and tasks past a particular period for better storage management.
    This can be done by checking if the current date is past the event date by the number of days specified in the `days_past_event` parameter.
