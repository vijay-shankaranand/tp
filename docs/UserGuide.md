---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# User Guide

### Welcome to JobFestGo

**Your one-stop solution for job event planning!**

JobFestGo is a **desktop application** built for job event planners in Singapore to manage contacts and tasks for their events.

Here’s an **overview** of how JobFestGo can help you with your event planning:
- Store information about your events and their associated contacts
- Track status of your event tasks
- Keep track of upcoming task deadlines
- Search for contacts by name or tag

Optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**, it is a task management tool meant to assist you as a job festival event planner in tracking event-specific tasks and contacts. This removes the hassle of having to shuffle through your contact list based on names that you might not remember and organise your tasks according to events while obtaining your information **efficiently**.

**If you are new here**, visit our [quick start](#quick-start) guide to get started with JobFestGo!

--------------------------------------------------------------------------------------------------------------------

### Table of Contents
  - [Quick start](#quick-start)
  - [Navigating the GUI](#navigating-the-gui)
  - [Features](#features)
    - [Adding a contact : `add_contact`](#adding-a-contact-add-contact)
    - [Listing all contacts : `view_contacts`](#listing-all-contacts-view-contacts)
    - [Deleting a contact : `delete_contact`](#deleting-a-contact-delete-contact)
    - [Editing a contact : `edit_contact`](#editing-a-contact-edit-contact)
    - [Locating contacts by name : `find_contact`](#locating-contacts-by-name-find-contact)
    - [Adding a tag : `add_tag`](#adding-a-tag-add-tag)
    - [Viewing all tags : `view_tags`](#viewing-all-tags-view-tags)
    - [Deleting a tag : `delete_tag`](#deleting-a-tag-delete-tag)
    - [Filtering contacts by tag : `filter_by_tag`](#filtering-contacts-by-tag-filter-by-tag)
    - [Adding an event : `add_event`](#adding-an-event-add-event)
    - [Viewing all events : `view_events`](#viewing-all-events-view-events)
    - [Deleting an event : `delete_event`](#deleting-an-event-delete-event)
    - [Linking contacts to an event : `link`](#linking-contacts-to-an-event-link)
    - [Unlinking contacts from an event: `unlink`](#unlinking-contacts-from-an-event-unlink)
    - [Selecting an event: `select_event`](#selecting-an-event-select-event)
    - [Adding a task : `add_task`](#adding-a-task-add-task)
    - [Viewing help : `help`](#viewing-help-help)
    - [Deleting a task: `delete_task`](#deleting-a-task-delete-task)
    - [Marking a task: `mark_task`](#marking-a-task-mark-task)
    - [Unmarking a task: `unmark_task`](#unmarking-a-task-unmark-task)
    - [Clearing all entries : `clear`](#clearing-all-entries-clear)
    - [Returning to home page : `home`](#returning-to-home-page-home)
    - [Exiting the program : `exit`](#exiting-the-program-exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
    - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
    - [Progress bar for completed tasks of an event `[coming in v2.0]`](#progress-bar-for-completed-tasks-of-an-event-coming-in-v20)
  - [FAQ](#faq)
  - [Known issues](#known-issues)
  - [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
- [How do I check my Java version?](#faq)

2. Download the latest `jobfestgo.jar` from [here](https://github.com/AY2324S1-CS2103T-T09-1/tp/releases).

    ![download jar](images/downloadJar.png)

3. Copy the file to the folder you want to use as the _home folder_ for your JobFestGo.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jobfestgo.jar` command to run the application.
   <br>

   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.
   <br>

   Some example commands you can try:

   * `view_contacts` : Lists all contacts.

   * `add_contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to JobFestGo.

   * `delete_contact 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all entries from JobFestGo.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Navigating the GUI

JobFestGo comes with a GUI (Graphical User Interface) that is designed to be intuitive and easy to use for a pleasant visual experience for our users. Here's a quick rundown of what each section is for:

![GUI](images/GUI.png)

<box type="warning">

**PLEASE NOTE:** If there is an ellipsis (...) at the end of a text field in any section of JobFestGo, please extend the window fully so the full text can be seen.
</box>

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items not in square brackets are compulsory.<br>
  e.g. `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` requires the `NAME`, `PHONE_NUMBER`, `EMAIL` and `ADDRESS` together with their prefixes (if any) to be supplied by the user.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `view_contacts`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Returning to home page : `home`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Returns to the home page of JobFestGo regardless of what screen the user is currently on.
</box>

Format: `home`

### Adding a contact : `add_contact`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Adds a contact to JobFestGo.
</box>

Format: `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Contact name can only take alphanumeric values (i.e should consist only of alphabets and numbers).
* Contact name and address should not have more than one whitespace in-between each word.
* Phone number should be in appropriate (8-digit numeric) format.
* Phone number does not provide support for special characters, such as `+` and `-`, as it is more targeted for job fest event planners in **Singapore**.
* Email should be in the appropriate (@xxx.com) format.
* A contact can have any number of tags.
* Only tags from the tags list can be used for tagging a contact.
* A contact cannot be added if their phone number already exists.
* A contact cannot be added if their name already exists.

Examples:
* `add_contact n/John Doe p/98765432 e/johndtr@example.com a/John street, block 123, #01-01`
* `add_contact n/Johnny t/suppliers e/johnd@example.com a/311, Clementi Ave 2, #02-25 p/98765435`


![result for 'add_contact'](images/addContactResult.png)

<div style="page-break-after: always;"></div>

### Listing all contacts : `view_contacts`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Displays a list of all contacts in JobFestGo.
</box>

Format: `view_contacts`

  ![result for 'view_contacts'](images/viewContactsResults.png)

<div style="page-break-after: always;"></div>

### Deleting a contact : `delete_contact`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Deletes the contact at the specified index from JobFestGo.
</box>

<box type="warning" style="background-color: #FFAA33; color: white;">

**WARNING:** This command is destructive. Once a contact is deleted, it cannot be recovered.
</box>

Format: `delete_contact INDEX`

* Deletes the contact at the specified `INDEX`.
* After the contact is deleted, JobFestGo will automatically return to the home page.
  This is regardless of whether the contact is linked to an event or not.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `view_contact` followed by `delete_contact 2` deletes the 2nd contact in JobFestGo.
* `find_contact Betsy` followed by `delete_contact 1` deletes the 1st contact in the results of the `find_contact` command.

### Editing a contact : `edit_contact`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Edits an existing contact at the specified index in JobFestGo.
</box>

Format: `edit_contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed (i.e adding of tags is not cumulative).
* You can remove all the contact’s tags by typing `t/` without specifying any tags after it.
* This command will follow all the notes specified under [`add_contact`](#adding-a-contact-add-contact) feature.

Examples:
*  `edit_contact 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit_contact 2 n/Betsy Crower t/` edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Locating contacts by name : `find_contact`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Finds contacts whose names contain any of the given keywords.
</box>

<box type="warning" style="background-color: #FFAA33; color: white">

**WARNING:**
This command is cumulative. Repeatedly using this command will result in the list being successively filtered based on the current displayed list. The examples below illustrate this.
</box>

Format: `find_contact KEYWORD [MORE_KEYWORDS]`

* Only the name of a contact can be searched.
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find_contact John` returns `john` and `John Doe`.
* `find_contact doe` after `find_contact john` will return `John Doe` and not `john` who was originally in the result.
* `find_contact alex david` returns `Alex Yeoh`, `David Li`
* Suppose we have a contact `Hans` in JobFestGo.
  `select_event 1` followed by `find_contact Hans` will not include `Hans`, if `Hans` is not linked to the first event.

![result for 'find_contact alex david'](images/findAlexDavidResult.png)

### Adding a tag : `add_tag`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Adds a tag with the specified name to JobFestGo.
</box>

Format: `add_tag t/TAG_NAME`

* The tag name **must not already exist** in JobFestGo.
* The tag name must be strictly alphanumeric. No other characters (including whitespace) are allowed.

Examples:
* `add_tag t/vendors`

<div style="page-break-after: always;"></div>

### Viewing all tags : `view_tags`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Displays a list of all existing tags.
</box>

Format: `view_tags`

  ![result for 'view_tags'](images/viewTagsResult.png)

<div style="page-break-after: always;"></div>

### Deleting a tag : `delete_tag`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Deletes the specified tag name from JobFestGo.
</box>

<box type="warning" style="background-color: #FFAA33; color: white;">

**WARNING:** This command is destructive. Once a tag is deleted, no contacts can be associated with the tag name.
</box>

Format: `delete_tag t/TAG_NAME`

* The tag name **must be an existing tag**. e.g. vendor, personal, customer, …​

Examples:
* `delete_tag t/vendors` deletes the tag `vendors` in JobFestGo.

### Filtering contacts by tag : `filter_by_tag`

<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Displays contacts tagged by any of the specified tags.
</box>

<box type="warning" style="background-color: #FFAA33; color: white">

**WARNING:**
This command is cumulative. Repeatedly using this command will result in the list being successively filtered based on the current displayed list. The examples below illustrate this.
</box>

Format: `filter_by_tag TAG_NAME [MORE_TAG_NAMES]`

* Tag names are case-insensitive. e.g. `Vendors` will match `vendors`.
* Only full words will be matched e.g. `ven` will not match `vendors`.
* Contacts tagged by at least one of the given tags will be returned (i.e. `OR` search).
  e.g. `vendors customers` will return all contacts tagged by `vendors` and all contacts tagged by `customers`.

Examples:
* `filter_by_tag vendors` returns all contacts tagged by the tag: vendor in JobFestGo.
* `filter_by_tag vendors customers` returns all contacts tagged by the tag: `vendors` and
  all contacts tagged by the tag: `customers` in JobFestGo. The image below illustrates the result of this command.
* Suppose we have a contact `Hans` in JobFestGo who is tagged by `vendor`.
  `select_event 1` followed by `filter_by_tag vendor` will not include `Hans`, if `Hans` is not linked to the first event.

![result for 'filter_by_tag vendors customers'](images/filterByRoleResult.png)

### Adding an event : `add_event`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Adds an event to JobFestGo.
</box>

Format: `add_event n/NAME d/DATE a/ADDRESS`

* Event name can only take alphanumeric values.
* The event name **must not already exist** in JobFestGo.
* Event name and address should not have more than one whitespace in-between each word.
* If the event name is very long and cannot be viewed fully, enter [`view_events`](#viewing-all-events-view-events) to see the full text.
* Date must be valid and should be in the appropriate (YYYY-MM-DD) format.
* Date should **not** be before the current date.
* Past the date of the event, the event will be labelled as completed next to its name in event list.

Examples:
* `add_event n/CS2103T Presentation d/2023-11-10 a/311, Clementi Ave 2, #02-25` adds an event named `CS2103T Presentation` to JobFestGo. 

    ![result for 'add_event'](images/addEventResult.png)

### Viewing all events : `view_events`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Displays a list of all existing events.
</box>

Format: `view_events`

![result for 'view_events'](images/viewEventsResult.png)

<div style="page-break-after: always;"></div>

### Deleting an event : `delete_event`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Deletes the event specified at the index from JobFestGo.
</box>

<box type="warning" style="background-color: #FFAA33; color: white;">

**WARNING:** This command is destructive. Once an event is deleted, all tasks associated with the event will be deleted.
</box>

Format: `delete_event INDEX`

* Deletes the event specified at the `INDEX` from the list of events of JobFestGo.
* The index refers to the index number shown in the displayed event list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete_event 1` deletes the 1st event in the displayed event list.

### Linking contacts to an event : `link`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Links specified contacts to the specified event.
</box>

Format: `link ev/EVENT_NAME c/CONTACT_NAME [c/MORE_CONTACT_NAMES]`

* Event name and contact name should not have more than one whitespace in-between each word.
* Only existing contacts and events in JobFestGo can be linked.
* Contacts that are already linked to the specified event cannot be linked again.
* Only full names will be matched e.g. `NUS Career Fair` will not match `NUS Career Fair 2023`,
  `David` will not match `David Li`
* The order of the input does not matter. e.g. `link ev/NUS Career Fair 2023 c/David Li`
 and `link c/David Li ev/NUS Career Fair 2023` are both valid commands and will perform
 the same task.
* The command will only succeed if all contacts and the event keyed in are valid.
 i.e. They are existing contacts and event in JobFestGo and the contacts are not linked to the event.

Examples:
* `link ev/NUS Career Fair 2023 c/David Li` links `David Li` to the event `NUS Career Fair 2023` if `David Li` is not linked to `NUS Career Fair 2023`.

![result for 'link'](images/linkResult.png)

<div style="page-break-after: always;"></div>

<box type="warning" style="background-color: #C73852; color: white">
<span slot="icon" style="color: white;"><md>:fas-close:</md></span>

**Errors:**
If any of the contacts or the event keyed in are not valid and do not exist in JobFestGo, an error will be thrown.
</box>

### Unlinking contacts from an event : `unlink`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Unlinks specified contacts from the specified event.
</box>

Format: `unlink ev/EVENT_NAME c/CONTACT_NAME [c/MORE_CONTACT_NAMES]`

* Event name and contact name should not have more than one whitespace in-between each word.

Examples:
* `unlink ev/NUS Career Fest c/Alice Black` unlinks `Alice Black` from the event `NUS Career Fest` if `Alice Black` is linked to `NUS Career Fest`.

<box type="warning" style="background-color: #C73852; color: white">
<span slot="icon" style="color: white;"><md>:fas-close:</md></span>

**Errors:**
Similar to [`link`](#linking-contacts-to-an-event-link), if any of the contacts or the event keyed in are not valid and do not exist in JobFestGo, an error will be thrown.

If the input contacts and event for `unlink` are not currently linked, an error will also be thrown.
</box>

### Selecting an event : `select_event`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Selects an event at the specified index from JobFestGo. The relevant contacts and tasks will be displayed.
</box>

Format: `select_event INDEX`

* Selects the event at the specified `INDEX`.
* The index refers to the index number shown in the displayed events list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `select_event 3` selects the third event in the displayed events list.

    ![result for 'select_event 3'](images/selectEventResult.png)

### Adding a task : `add_task`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Adds a task to an event in JobFestGo.
</box>

Format: `add_task td/TASK_DESCRIPTION d/DEADLINE ev/EVENT_NAME`

* Task description can take any alphanumeric value, spaces and any of the following characters: `-`, `,`, `.`, `/`, `(`, `)`
* Task description and event name should not have more than one whitespace in-between each word.
* Deadline is a date in the format YYYY-MM-DD.
* Deadline should not be before today's date.
* Event name should be the **name of an already existing event**.
* Tasks with a deadline after the event's date are allowed to be created to accommodate post-event related tasks such as After-Action Review.
* Reminders will be shown on the home page of JobFestGo for tasks within 3 days of the deadline.

Examples:
* `add_task td/Book Venue d/2023-12-23 ev/NUS Career Fair 2023` adds a `Book Venue` by `2023-12-23` task to the event `NUS Career Fair 2023`.

### Deleting a task : `delete_task`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Deletes the task specified by the task description from its associated event in JobFestGo.
</box>

<box type="warning" style="background-color: #FFAA33; color: white">

**WARNING:** This command is destructive. Once a task is deleted, it cannot be recovered.
</box>

Format: `delete_task td/TASK_DESCRIPTION ev/EVENT_NAME`

* Deletes the task specified by the `TASK_DESCRIPTION` from the event specified by the `EVENT_NAME` of JobFestGo.
* Task description and event name should not have more than one whitespace in-between each word.
* Both the `TASK_DESCRIPTION` and the `EVENT_NAME` are case-insensitive.
 e.g. `delete_task td/Book Venue ev/NUS Career Fair 2023` and `delete_task td/book venue ev/nus Career FAIR 2023`
 will perform the same operation.

Examples:
* `delete_task td/Book Venue ev/NUS Career Fair 2023` deletes task `Book Venue` from the task list of the event `NUS Career Fair 2023`.

<box type="warning" style="background-color: #C73852; color: white">
<span slot="icon" style="color: white;"><md>:fas-close:</md></span>

**Errors:**
If the specified event does not exist or the event does not have the specified task, an error will be thrown.
If such a situation happens, you may double-check the task description and the event name and re-enter valid inputs.
</box>

### Marking a task : `mark_task`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Marks the task specified by the task description and its associated event name in JobFestGo as completed.
</box>

Format: `mark_task td/TASK_DESCRIPTION ev/EVENT_NAME`

* Marks the task specified by the `TASK_DESCRIPTION` from the event specified by the `EVENT_NAME` of JobFestGo as completed.
* Task description and event name should not have more than one whitespace in-between each word.
* A label indicating the status of the task will be shown next to its description in the task list.
* Both the `TASK_DESCRIPTION` and the `EVENT_NAME` are case-insensitive.
  e.g. `mark_task td/Book Venue ev/NUS Career Fair 2023` and `mark_task td/book venue ev/nus Career FAIR 2023`
  will perform the same operation.

Examples:
* `mark_task td/Book Venue ev/NUS Career Fair 2023` marks the task `Book Venue` from the task list of the event `NUS Career Fair 2023` as completed.

<box type="warning" style="background-color: #C73852; color: white">
<span slot="icon" style="color: white;"><md>:fas-close:</md></span>

**Errors:**
If the specified event does not exist or the event does not have the specified task, an error will be thrown.
If such a situation happens, you may double-check the task description and the event name and re-enter valid inputs.


If the specified task has already been marked as completed, an error will also be thrown.
</box>

   ![result for 'mark_task'](images/markTaskResult.png)

### Unmarking a task : `unmark_task`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Marks the task specified by the task description and its associated event name in JobFestGo as not completed.
</box>

Format: `unmark_task td/TASK_DESCRIPTION ev/EVENT_NAME`

* Task description and event name should not have more than one whitespace in-between each word.
* It works exactly the same way as [`mark_task`](#marking-a-task-mark-task) except for the fact that `unmark_task` marks a completed task as not completed.
* You may `unmark_task` a task when you realize that you have not completed the task but have wrongly marked it as completed.

Examples:
* `unmark_task td/Book Venue ev/NUS Career Fair 2023` marks the task `Book Venue` from the task list of the event `NUS Career Fair 2023` as not completed.

<box type="warning" style="background-color: #C73852; color: white">
<span slot="icon" style="color: white;"><md>:fas-close:</md></span>

**Errors:**
If the specified event does not exist or the event does not have the specified task, an error will be thrown.
If such a situation happens, you may double-check the task description and the event name and re-enter valid inputs.

If the specified task has already been unmarked, an error will be thrown.
</box>

### Viewing help : `help`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Shows a link to the user guide which will contain all the details required to use the app appropriately.
You can click on the 'Copy URL' button and paste the URL into your browser's address bar.
</box>

![help message](images/helpMessage.png)

Format: `help`

### Clearing all entries : `clear`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Clears all entries from JobFestGo.
</box>

<box type="warning" style="background-color: #FFAA33; color: white">

**WARNING:**
This command is destructive. Once executed, it will delete all contacts, events and tasks from JobFestGo.
This command cannot be undone. Proceed with caution.
</box>

Format: `clear`

### Exiting the program : `exit`
<box type="info" style="background-color:#1e90ff; color: white;">
<span slot="icon" style="color: white;"><md>:fas-address-book:</md></span>
Exits the program.
</box>

Format: `exit`

### Saving the data

JobFestGo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

JobFestGo data are saved automatically as a JSON file `[JAR file location]/data/jobfestgo.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning">

**Caution:**
If your changes to the data file makes its format invalid, JobFestGo will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.

</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Progress bar for completed tasks of an event `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JobFestGo home folder.

**Q**: What do I do if the clicking of the JobFestGo JAR file does not work? <br>
**A**: Given below are the steps to launch JobFestGo using CLI:
<br>
1. Open the command prompt
1. Navigate to the directory where the JAR file is located using `cd [JAR file location]`
1. Type `java -jar jobfestgo.jar` and press enter
1. JobFestGo should launch

**Q**: How can I check my Java version?<br>
**A**: Open a command prompt and type `java -version` . If you do not have Java installed, you
can download it [here](https://www.oracle.com/java/technologies/downloads/#java11).

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. 
<br><br>
**Solution**: Delete the `preferences.json` file created by the application before running the application again.
2. **Help pop-up window shifts to the bottom left corner of the screen before re-centering on `help` command**. Steps to reproduce the issue:
    1. Type `help` in the command box.
    2. Shift the pop-up window to anywhere on the screen.
    3. Close the pop-up window.
    4. Type `help` in the command box again.
    5. The pop-up window will shift to the bottom left corner before re-centering itself.
<br><br>
   
   **Solution:** Run the jar file with GTK 2 using the command `java -Djdk.gtk.version=2 -jar jobfestgo.jar`. This is a known **Mac-only issue** with JavaFX 11 which uses GTK 3 by default. Refer to this [post](https://github.com/javafxports/openjdk-jfx/issues/217) for more details.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Home**   | `home`
**Add Contact**    | `add_contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add_contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 12346658 t/client t/vendor`
**Delete Contact** | `delete_contact INDEX`<br> e.g. `delete_contact 3`
**Edit Contact**   | `edit_contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g. `edit_contact 2 n/James Lee e/jameslee@example.com`
**Find Contact**   | `find_contact KEYWORD [MORE_KEYWORDS]`<br> e.g. `find_contact James Jake`
**View Contacts**   | `view_contacts`
**Add Tag** | `add_tag t/TAG_NAME` <br> e.g. `add_tag t/vendor`
**Delete Tag** | `delete_tag t/TAG_NAME` <br> e.g. `delete_tag t/vendor`
**Filter by tag** | `filter_by_tag TAG_NAME [MORE_TAGNAMES]` <br> e.g. `filter_by_tag vendor`
**View Tags** | `view_tags`
**Add Event** | `add_event n/NAME d/DATE a/ADDRESS` <br> e.g. `add_event n/NUS Career Fest 2023 d/2023-12-23 a/NUS`
**Delete Event** | `delete_event INDEX` <br> e.g. `delete_event 1`
**Link** | `link ev/EVENT_NAME C/CONTACT_NAME [C/MORE_CONTACT_NAMES]` <br> e.g. `link ev/NUS Career Fest c/Alice Black`
**Unlink** | `unlink ev/EVENT_NAME C/CONTACT_NAME [C/MORE_CONTACT_NAMES]` <br> e.g. `unlink ev/NUS Career Fest c/Alice Black`
**Select Event** | `select_event INDEX` <br> e.g. `select_event 1`
**View Events** | `view_events`
**Add Task** | `add_task td/TASK_DESCRIPTION d/DEADLINE ev/EVENT_NAME` <br> e.g. `add_task td/Book Venue d/2023-12-23 ev/NUS Career Fair 2023`
**Delete Task** | `delete_task td/TASK_DESCRIPTION ev/EVENT_NAME` <br> e.g. `delete_task td/Book Venue ev/NUS Career Fair 2023`
**Mark Task** | `mark_task td/TASK_DESCRIPTION ev/EVENT_NAME` <br> e.g. `mark_task td/Book Venue ev/NUS Career Fair 2023`
**Unmark Task** | `unmark_task td/TASK_DESCRIPTION ev/EVENT_NAME` <br> e.g. `unmark_task td/Book Venue ev/NUS Career Fair 2023`
**Help**   | `help`
**Clear**  | `clear`
**Exit**   | `exit`
