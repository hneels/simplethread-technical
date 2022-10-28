# Simple Thread Technical Exercise

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

<hr>

## Assumptions & Interpretations

- when adding a project, if it is < 3 days, add all days at full cost

- when adding a new project, if the day immediately BEFORE the first day is already in the calendar, that before day becomes a full day if it had been a travel day (at the OTHER project's rate) and the first day of this project becomes a full day

- if the day immediately after the last day is already in the calendar, that day becomes a full day if it had been a travel day (at the OTHER project's rate) and the last day of this project becomes a full day

- if a low cost project and high cost project overlap, the high cost wins out

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