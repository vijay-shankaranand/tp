---
layout: default.md
title: "Khaleelur Rahman's Project Portfolio Page"
---
## Khaleelur Rahman's Project Portfolio Page
#### Project: JobFestGo

JobFestGo is a **desktop application** built for job event planners in Singapore to manage contacts and tasks for their events.

JobFestGo can aid your event planning process in the following ways:
- Stores information about your events, and their associated contacts and tasks 
- Keeps track of upcoming task deadlines
- Associate contacts with roles for future reference

With the advantages of a **Graphical User Interface (GUI)**
and optimized for use with a **Command Line Interface (CLI)**,
it's a task management tool designed to help you, as a job festival event planner,
to keep track of tasks and contacts unique to your events. JobFestGo streamlines the process of event planning,
supercharges productivity and helps you get things done on time.

Given below are my contributions to the project:
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=khaleelur-rahman&breakdown=true)

<br>

* **Enhancements implemented**:
  In charge of creating the bulk of the backend processes, along with changing hints of the model provided in AB3 to streamline to the product.
  1) **Implemented the `add_tag` feature**
     - **What it does**: Allows the user to add tags (which are roles of the contact) into JobFestGo.
     - **Justification**: This feature improves the product as it provides the user a way to associate the contacts with their roles for an event.
     - **Highlights**: This enhancement adds a new command along with its associated classes to JobFestGo. This enhancement was challenging as there was a need to refactor certain parts of the JobFestGo model to enable association of contacts with only pre-existing tags.
  2) **Implemented the `delete_event` feature**
     - **What it does**: Allows the user to delete an event.
     - **Justification**: This feature improves the product as it provides the user a way to delete their events once completed or no longer needed.
  3) **Implemented the `add_task` feature**
     - **What it does**: Allows the user to create task for an event with a deadline.
     - **Justification**: This feature improves the product as it provides the user a way to keep track of the tasks for an event with ease.
     - **Highlights**: This enhancement adds a new command along with its classes to JobFestGo. This enhancement was extremely challenging as there was a need to implement the associated classes for both `tasks` and `events`. Multiple bugs arose due to the complexity of the implementation. However, after multiple debugging sessions, the feature was successfully implemented.

<br>

* **Contributions to the UG**:
  - Did up multiple sections which describe the features of JobFestGo. These include:
    - `Adding a tag : add_tag` section
    - `Deleting an event : delete_event` section
    - `Adding a task : add_task` section
  - Added a `Table of Contents` section
  - Added few FAQs under the `FAQ` section
  - Added a `Known Issues` section and added solutions to the issues
  - Added screenshots of the GUI for multiple commands such as `view_events`, `view_contacts` etc.
  - Fixed multiple typos and grammatical errors
  - Reviewed and proof-read the UG, checking to see if it was correctly hosted on the website

<br>

* **Contributions to the DG**:
  - Did up the implementation of `add_tag` feature, detailing the thought process and considerations
  - Added sequence and activity diagrams for `add_tag` feature
  - Added user stories for `add_tag`, `delete_event` and `add_task` commands
  - Amended user stories section to fit the target audience
  - Added use cases for `add_tag`, `delete_event` and `add_task` commands
  - Added instructions for manual testing for `add_tag`, `delete_event` and `add_task` commands
  - Removed instances of AB3 and replaced it with JobFestGo
  - Edited the UML diagrams for Model and UI Components
  - Added a `Planned Enhancements` section for future implementations

<br>

* **Contributions to team-based tasks**:
  - Set task-based deadlines and meetings for various milestones
  - Sent regular to-dos for the team to be on track and be aware of what is to be done
  - Added test cases for code that is beyond those that are authored by me
  - Added sample data for all classes to ease testing
  - Maintained issue tracker of some issues([Issue 1](https://github.com/AY2324S1-CS2103T-T09-1/tp/issues/119), [Issue 2](https://github.com/AY2324S1-CS2103T-T09-1/tp/issues/117))

<br>

* **Review/mentoring contributions**:
  - Reviewed and merged several pull requests for other team members. Some of the PRs reviewed include:
    - [#PR-1](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/226)
    - [#PR-2](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/103)
    - [#PR-3](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/59)

<br>

* **Contributions beyond the project team**:
  - Reviewed a different group's project during PE-D: [PE-D Bugs](https://github.com/Khaleelur-Rahman/ped/issues).
