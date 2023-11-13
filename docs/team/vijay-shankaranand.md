---
layout: default.md
title: "Vijayanandan Shankar Anand's Project Portfolio Page"
---

### Project: JobFestGo

JobFestGo is a **desktop application** built for job event planners in Singapore to manage contacts and tasks for their events.

Features of JobFestGo include:
- Adding contacts
- Adding events
- Adding tasks
- Reminders for tasks

It is a management tool meant to assist you in effectively, efficiently managing your tasks and contacts for events.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=vijay-shankaranand&breakdown=true)

<br>

* **Enhancements implemented**:
    - Improved the `add_contact` feature
        - What it does: allows the user to add contacts into JobFestGo with optional tag.
        - Justification: Improved the feature to only allow tags that have already been added to JobFestGo for filter by tag feature.
    - Implemented the `add_event` feature
        - What it does: allows the user to add an event.
        - Justification: This feature improves the product as it provides the user a way to add their events to keep track of their upcoming/past events.
        - Highlights: This enhancement adds a new class to JobFestGo. This enhancement was challenging as there was a need to refactor certain parts of contact class to use for event class to minimize code duplication. There was also a need to refactor out the name class to minimize code duplication
    - Implemented the reminder feature
        - What it does: reminds the users on tasks that are due within the next 3 days when they start up JobFestGo.
        - Justification: This feature improves the product as it provides the user a way to be reminded of their upcoming tasks so as to not forget completing them on time.
        - Highlights: Figuring out the logic to implement this feature was challenging as there was a need to check if the task is due within the next 3 days and if the task is not marked as done using a new predicate.
  

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
    - Fixed the existing PlantUML diagrams such as Model & UI diagrams to make it neater.
    - Did up the `add_contact` feature as well as its use-cases
    - Did up the implementation section for the `add_event` feature and its use-cases, detailing the thought process
    - Dedicated time to pick up the syntax and features of PlantUML to optimize our diagrams for neatness and readability

<br>

* **Contributions to team-based tasks**:
    - Set up issues on Github and assigned to individuals for issues-tracking
    - Set up labels on Github for issues-tracking 
    - Set up milestones v1.2 and v1.2b on Github for deadline-tracking
    - Update project notes on v1.2 post mortem
    - Updating of developer guide's diagrams
    - Updating of user guide's screenshots, navigating UI and target audience section
    - Refactored codebase to modify all AB3 hints to JobFestGo - eg. from AddressBook to JobFestGo
    - Extensive testing of product before release
    - Added automated test-cases to aid in regression testing of our product


<br>

* **Review/mentoring contributions**:
    - I have regularly helped to review PRs of my team members and here are some of the PRs I have reviewed:
        - [Pull Request 1](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/271)
        - [Pull Request 2](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/140)
        - [Pull Request 3](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/96)
    - Found the source of critical bug during loading of json which crippled the stability of the product through extensive debugging and advised assigned developer on changes to make
        - [Bug fix made](https://github.com/AY2324S1-CS2103T-T09-1/tp/pull/239/files)
<br>

* **Contributions beyond the project team**:
  I have assisted in finding multiple bugs for the product of one other team.
  
  Here are some bugs I have found while doing extensive testing of their product:
    - [Bug found 1](https://github.com/vijay-shankaranand/ped/issues/9)
    - [Bug found 2](https://github.com/vijay-shankaranand/ped/issues/6)
    - [Bug found 3](https://github.com/vijay-shankaranand/ped/issues/5)
    - [Bug found 4](https://github.com/vijay-shankaranand/ped/issues/2)
