# TaskTracker 

## Overview

**TaskTracker** is a command-line tool built in Java that allows you to manage and track your tasks efficiently. This application helps you keep track of what needs to be done, what you're currently working on, and what you've already completed. Tasks are stored in a JSON file for persistence, allowing you to manage them even after closing the application. This project was done as per the guidelines suggest by roadmap.sh
Link to the project: [https://roadmap.sh/projects/task-tracker](https://roadmap.sh/projects/task-tracker)

## Features

- **Add a Task**: Easily add new tasks to your to-do list.
- **Update a Task**: Modify the description of an existing task.
- **Delete a Task**: Remove a task from your list when it's no longer needed.
- **Mark Task as In Progress**: Indicate that a task is currently being worked on.
- **Mark Task as Done**: Mark tasks as completed.
- **List Tasks**: View all tasks or filter them by their status (`todo`, `in-progress`, `done`).

## Task Properties

Each task in the application is represented by the following properties:

- **id**: A unique identifier for the task.
- **description**: A brief description of the task.
- **status**: The current status of the task (`todo`, `in-progress`, `done`).
- **createdAt**: The date and time when the task was created.
- **updatedAt**: The date and time when the task was last updated.

## Installation

### Prerequisites

- **Java Development Kit (JDK)**: Make sure you have JDK 8 or later installed.
- **Maven**: The project uses Maven for build management.

### Clone the Repository

```bash
git clone https://github.com/Deeperr0/TaskTracker.git
cd TaskTracker
