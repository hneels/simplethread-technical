# SimpleThread Technical Exercise
## Version 2 with Testing

**Table of Contents**
- [How to Run](#how-to-run)
- [Requirements](#requirements)
- [Assumptions & Interpretations](#assumptions---interpretations)
- [Desired Outcomes from Sample Sets](#desired-outcomes-from-sample-sets)
    * [Set 1](#set-1-)
    * [Set 2](#set-2-)
    * [Set 3](#set-3-)
    * [Set 4](#set-4-)



## How to Run

**To run tests:**
In IntelliJ, you can run each test individually (located in src.test.java.ReimburserTest) by clicking the green arrow next to each test. Alternately, you can run command "./gradlew test" to run all the tests at once. The tests evaluate additional project sets and edge cases beyond the four provided in the requirements below.

**To run as application:**
To run the main method (in src.main.java.Main) which evaluates the four project sets and prints output, you can either run "./gradlew run" command from project root, or simply right-click and select "Run Main.main()" in IntelliJ.



## Requirements
You have a set of projects, and you need to calculate a reimbursement amount for the set. Each project has a start date and an end date. The first day of a project and the last day of a project are always "travel" days. Days in the middle of a project are "full" days. There are also two types of cities a project can be in, high cost cities and low cost cities.

A few rules:
- First day and last day of a project, or sequence of projects, is a travel day.
- Any day in the middle of a project, or sequence of projects, is considered a full day.
- If there is a gap between projects, then the days on either side of that gap are travel days.
- If two projects push up against each other, or overlap, then those days are full days as well.
- Any given day is only ever counted once, even if two projects are on the same day.
- A travel day is reimbursed at a rate of 45 dollars per day in a low cost city.
- A travel day is reimbursed at a rate of 55 dollars per day in a high cost city.
- A full day is reimbursed at a rate of 75 dollars per day in a low cost city.
- A full day is reimbursed at a rate of 85 dollars per day in a high cost city.

Given the following sets of projects, provide code that will calculate the reimbursement for each.

Set 1:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/3/15


Set 2:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 2: High Cost City Start Date: 9/2/15 End Date: 9/6/15
- Project 3: Low Cost City Start Date: 9/6/15 End Date: 9/8/15

Set 3:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/3/15
- Project 2: High Cost City Start Date: 9/5/15 End Date: 9/7/15
- Project 3: High Cost City Start Date: 9/8/15 End Date: 9/8/15

Set 4:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 2: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 3: High Cost City Start Date: 9/2/15 End Date: 9/2/15
- Project 4: High Cost City Start Date: 9/2/15 End Date: 9/3/15



## Assumptions & Interpretations

- A one- or two-day project has only full days, no travel days
- When low cost and high cost projects overlap, the high cost takes precedence, regardless of project order in the set
- First and last days of a project become full days if they are adjacent to full OR travel days belonging to another project, and that other project's adjacent day becomes a full day at the other project's rate.
    - see tests in src.test.java.ReimburserTest for examples



## Desired Outcomes from Sample Sets

### Set 1:
Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/3/15

breakdown:
- 9/1 low travel
- 9/2 low full
- 9/3 low travel
- total: $165

### Set 2:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 2: High Cost City Start Date: 9/2/15 End Date: 9/6/15
- Project 3: Low Cost City Start Date: 9/6/15 End Date: 9/8/15

breakdown:
- 9/1 low full - *note that one-day trip has no travel days*
- 9/2 high full
- 9/3 high full
- 9/4 high full
- 9/5 high full
- 9/6 ~~high travel~~ high full
- 9/7 low full
- 9/8 low travel

notes:
- 9/2 would be a travel day for P2 but it pushes against P1 so it switches to full at P2's rate
- 9/6 would be a travel day for either P2 or P3 (overlap) but it pushes up so it switches to full at the HIGHER rate
- total: $620

### Set 3:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/3/15
- Project 2: High Cost City Start Date: 9/5/15 End Date: 9/7/15
- Project 3: High Cost City Start Date: 9/8/15 End Date: 9/8/15

breakdown:
- 9/1 low travel
- 9/2 low full
- 9/3 low travel
- 9/4 NONE
- 9/5 high travel
- 9/6 high full
- 9/7 ~~high travel~~ high full
- 9/8 high full

notes:
- 9/7 would be a travel day for P2 but it pushes up on P3 and becomes a full day at P2's rate
- 9/8 is a one-day trip so there is no travel day
- total: $475

### Set 4:
- Project 1: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 2: Low Cost City Start Date: 9/1/15 End Date: 9/1/15
- Project 3: High Cost City Start Date: 9/2/15 End Date: 9/2/15
- Project 4: High Cost City Start Date: 9/2/15 End Date: 9/3/15

breakdown:
- 9/1 low full
- 9/2 high full
- 9/3 high full

notes:
- one-day trips have no travel days
- total: $245