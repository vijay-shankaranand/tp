---
  layout: default.md
  title: "Jaryl Goh Jun Zhong's Project Portfolio Page"
---

### Project: JobFestGo

JobFestGo is a **desktop application** built for job event planners in Singapore to manage contacts and tasks for their events.

Here’s an **overview** of how JobFestGo can help you with your event planning:
- Store information about your events and their associated contacts
- Track status of your event tasks
- Keep track of upcoming task deadlines
- Search for contacts by name or tag

Optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**, it is a task management tool meant to assist you as a job festival event planner in tracking event-specific tasks and contacts. This removes the hassle of having to shuffle through your contact list based on names that you might not remember and organise your tasks according to events while obtaining your information **efficiently**.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=rionshocker&breakdown=true)

<br>

* **Enhancements implemented**:
  - Implemented the `delete_tag` feature
    - What it does: allows the user to delete tags added to JobFestGo.
    - Justification: This feature improves the product as it gives the user a chance to delete tags that they might have mistakenly keyed in and would like to delete them.
    - Highlights: This enhancement was slightly challenging due to the need to remove the deleted tag from the contacts that have the existing tag.
  - Implemented the `select_event` feature
    - What it does: allows the user to select the event and view its respective contacts and tasks.
    - Justification: This feature improves the product as it provides the user with ease of viewing the tasks that needs to be handled in an event as well as contacts associated to the event.
    - Highlights: This enhancement touches on the UI component, to display that a particular event has been selected. The implementation was slightly challenging in view of the connection between frontend and backend implementation.
  - Handled the UI component of the project
    - Did up the MainWindow display of the application to have 3 columns, and ensuring that the flow of events followed by entering a command is valid.
    - Highlights: It was slightly time-consuming having to learn JavaFX and applying it on the project.
  - In charge of linking backend implementations to frontend

<br>

* **Contributions to the UG**:
  - Did up the `delete_tag` feature
  - Did up the `select_event` feature
  - Reviewed and proof-read the UG, checking to see if it appeared as it seems on the website
  - Helped to ensure line breaks are placed properly for ease of exporting to PDF

<br>

* **Contributions to the DG**:
  - Fixed the PlantUML diagrams for the project, depicting the `UI`, `Model`.
  - Did up the implementation of `select_event`, detailing the thought process
  - Did up the `delete_tag` feature as well as its use-cases
  - Did up the `select_event` feature and its use-cases
  - Added instructions for manual testing for `add_contact`, `edit_contact`, `view_contacts`, `delete_tag`, and `select_event` commands
  - Did up the table of contents section

<br>

* **Contributions to team-based tasks**:
  - Set up the GitHub team repo
  - Setting up of tools e.g., GitHub, Gradle
  - Maintaining the issue tracker
  - Release management for v1.2, v1.3, v1.3.1
  - Updating of developer guide's diagrams
  - Updated sample data that is relevant to our target user profile for initial loading

<br>

* **Review/mentoring contributions**:
  - Reviewed and merged several pull requests for other team members. Some of the PRS I reviewed include:
    - [PR-1](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/256)
    - [PR-2](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/255)
    - [PR-3](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/231)

<br>

* **Contributions beyond the project team**:
  - Reviewed a different group's project during PE-D: [PE-D Bugs](https://github.com/rionshocker/ped/issues)
