---
layout: default.md
title: "Khaleelur Rahman's Project Portfolio Page"
---

### Project: JobFestGo

JobFestGo is a **desktop application** built for job event planners to manage contacts and tasks for their events.

Here’s an **overview** of how JobFestGo can help you with your event planning:
- Store information about your events and their associated contacts
- Track status of your event tasks
- Keep track of upcoming task deadlines
- Search for contacts by name or tag

Optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**, it is a task management tool meant to assist you as a job festival event planner in tracking event-specific tasks and contacts. This removes the hassle of having to shuffle through your contact list based on names that you might not remember and organise your tasks according to events while obtaining your information **efficiently**.

Given below are my contributions to the project.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=khaleelur-rahman&breakdown=true)

<br>

* **Enhancements implemented**:
  - Improved the `add_tag` feature
    - **What it does**: Allows the user to add tags (which are roles of the contact) into JobFestGo.
    - **Justification**: This feature improves the product as it provides the user a way to associate the contacts with their roles in the event.
    - **Highlights**: This enhancement adds a new command along with its classes to JobFestGo. This enhancement was challenging as there was a need to refactor certain parts of the JobFestGo model to enable association of contacts with only pre-existing tags.
  - Implemented the `delete_event` feature
      - **What it does**: Allows the user to delete an event.
      - **Justification**: This feature improves the product as it provides the user a way to delete their events once completed or no longer needed.
  - Implemented the `add_task` feature
      - **What it does**: Allows the user to create task for an event with a deadline.
      - **Justification**: This feature improves the product as it provides the user a way to keep track of the tasks for an event with ease.
      - **Highlights**: This enhancement adds a new command along with its classes to JobFestGo. This enhancement was extremely challenging as there was a need to implement the associated classes for both `tasks` and `events`. Multiple bugs arose due to the complexity of the implementation. However, after multiple debugging sessions, the feature was successfully implemented.
  - In charge of creating the bulk of the backend processes, along with changing hints of the model provided in AB3 to streamline to the product.

<br>

* **Contributions to the UG**:
  - Did up the `add_tag` feature
  - Did up the `delete_event` feature
  - Did up the `add_task` feature
  - Did up the table of contents section
  - Fixed multiple typos and grammatical errors
  - Reviewed and proof-read the UG, checking to see if it appeared as it seems on the website

<br>

* **Contributions to the DG**:
    - Did up the implementation of `add_tag` feature, detailing the thought process and added use cases
    - Did up the implementation of `delete_event` feature, detailing the thought process and added use cases
    - Did up the implementation of `add_task` feature, detailing the thought process and added use cases

<br>

* **Contributions to team-based tasks**:
  - Added test cases for code that is beyond those that are authored by me
  - Added sample data for all classes to ease testing
  - Updated the overall flow of UG
  - Setting task-based deadlines and meetings for various milestones

<br>

* **Review/mentoring contributions**:
  - Reviewed and merged several pull requests for other team members. Some of the PRs reviewed include:
    - [#PR-1](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/226)
    - [#PR-2](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/103)
    - [#PR-3](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/59)

<br>

* **Contributions beyond the project team**:
  - Reviewed a different group's project during PE-D: [PE-D Bugs](https://github.com/Khaleelur-Rahman/ped/issues)
