---
layout: default.md
title: "Vijayanandan Shankar Anand's Project Portfolio Page"
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

* **Enhancements implemented**:
    - Improved the `add_contact` feature
        - What it does: allows the user to add contacts into JobFestGo with optional tag.
        - Justification: Improved the feature to only allow tags that have already been added to JobFestGo for filter by tag feature.
    - Implemented the `add_event` feature
        - What it does: allows the user to add an event.
        - Justification: This feature improves the product as it provides the user a way to add their events to keep track of their upcoming/past events.
        - Highlights: This enhancement adds a new class to JobFestGo. This enhancement was challenging as there was a need to refactor certain parts of contact class to use for event class to minimize code duplication.
    - Implemented the reminder feature
        - What it does: reminds the users on tasks that are due within the next 3 days when they start up JobFestGo.
        - Justification: This feature improves the product as it provides the user a way to keep track of their upcoming tasks.
        - Highlights: Figuring out the logic to implement this feature was challenging as there was a need to check if the task is due within the next 3 days and if the task is not marked as done using a new predicate.
  

<br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=vijay-shankaranand&breakdown=true)

<br>

* **Contributions to the UG**:
    - Did up the `add_contact` feature
    - Did up the `add_event` feature
    - Did up the navigating the UI section of the UG
    - Did up the target audience section of the UG
    - Replaced screenshots to match JobFestGo UI
    - Used boxes to show warnings of destructive commands to warn users
    - Reviewed and proof-read the UG, checking to see if it appeared as it seems on the website

  <br>

* **Contributions to the DG**:
    - Fixed the PlantUML diagrams for the project to align to our product, changing hints of AB3 to JobFestGo.
    - Did up the `add_contact` feature as well as its use-cases
    - Did up the implementation section for the `add_event` feature and its use-cases, detailing the thought process

<br>

* **Contributions to team-based tasks**:
    - Set up issues on Github and assigned to individuals for issues-tracking
    - Set up labels on Github for issues-tracking 
    - Set up milestones v1.2 and v1.2b on Github for deadline-tracking
    - Update project notes on v1.2 post mortem
    - Updating of developer guide's diagrams
    - Updating of user guide's screenshots, navigating UI and target audience section
    - Refactored codebase to modify all AB3 hints to JobFestGo - eg. from AddressBook to JobFestGo
    - Found the source of critical bug during loading of json which crippled the stability of the product
    - Extensive testing of product before release
    - Added automated test-cases to aid in regression testing of our product


<br>

* **Review/mentoring contributions**:
    - I have regularly helped to review PRs of my team members and here are some of the PRs I have reviewed:
        - [Pull Request 1](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/271)
        - [Pull Request 2](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/140)
        - [Pull Request 3](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/96)

  <br>

* **Contributions beyond the project team**:
  I have assisted in finding multiple bugs for the product of one other team.
  
  Here are some bugs I have found while doing extensive testing of their product:
    - [Bug found 1](https://github.com/vijay-shankaranand/ped/issues/9)
    - [Bug found 2](https://github.com/vijay-shankaranand/ped/issues/6)
    - [Bug found 3](https://github.com/vijay-shankaranand/ped/issues/5)
    - [Bug found 4](https://github.com/vijay-shankaranand/ped/issues/2)
