# InterviewLog

A simple Java console application to track job interviews, companies, dates, and outcomes.

## Features
- Add an interview
- View all interviews
- Search interviews by company
- Update an interview outcome
- Delete an interview
- Store interviews in a local text file (`interviews.txt`)

## Java concepts used
Variables, classes, objects, `ArrayList`, loops, conditionals, methods, `Scanner`, and basic file handling.

## Requirements
- Java 8 or newer

## Run the application

### Option 1: Terminal
From the project folder, run:

```bash
javac -d out src/com/interviewlog/*.java
java -cp out com.interviewlog.Main
```

### Option 2: IntelliJ IDEA or Eclipse
1. Open/import this folder as a Java project.
2. Mark `src` as the source folder if your IDE asks.
3. Run `Main.java`.

Interview data is saved in `interviews.txt` in the folder where the program is launched.

## Example
Add an interview with:
- Company: Example Corp
- Role: Java Developer
- Date: 2026-11-10
- Outcome: Scheduled

This project is intentionally beginner-friendly and uses no external libraries.
