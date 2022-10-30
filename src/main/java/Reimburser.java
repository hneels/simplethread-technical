import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;


public class Reimburser {

    // "calendar" mapping Dates to project days (low cost/ high cost, travel/ full)
    private final Map<LocalDate, ProjectDay> calendar = new TreeMap<>();

    public int reimburse(Project[] projects) {

        for (Project project : projects) {

            LocalDate startDate = project.getStartDate();
            LocalDate endDate = project.getEndDate();
            Cost projectCost = project.getCost();

            // if the project is < 3 days, there are no travel days
            if (ChronoUnit.DAYS.between(startDate, endDate) < 2) {
                addEdgeDay(projectCost, DayType.FULL, startDate);
                addEdgeDay(projectCost, DayType.FULL, endDate);

            } else {
                addEdgeDay(projectCost, DayType.TRAVEL, startDate);
                addEdgeDay(projectCost, DayType.TRAVEL, endDate);

                // add middle days
                LocalDate tempDate = startDate.plusDays(1);
                while (endDate.compareTo(tempDate) > 0) {
                    addFullDay(projectCost, tempDate);
                    tempDate = tempDate.plusDays(1);
                }
            }
        }
        return getTotal();
    }

    /* for first day and last day of a project, both adjacent days are checked for 'push-up' against other projects.
    This ensures that projects nested within each other (e.g. Test 9) have consistent behavior */
    private void addEdgeDay(Cost cost, DayType dayType, LocalDate date) {

        // if neither day BEFORE nor day AFTER already in calendar, add this day at original rate and type
        if (!calendar.containsKey(date.minusDays(1)) && !calendar.containsKey(date.plusDays(1))) {
            calendar.put(date, new ProjectDay(dayType, cost));

        } else {
            // previous day, if occupied, becomes a FULL day at the previous project's cost rate
            if (calendar.containsKey(date.minusDays(1))) {
                calendar.get(date.minusDays(1)).setDayType(DayType.FULL);
            }
            // next day, if occupied, becomes a FULL day at the next project's cost rate
            if (calendar.containsKey(date.plusDays(1))) {
                calendar.get(date.plusDays(1)).setDayType(DayType.FULL);
            }
            // add edge day as a FULL day, even if parameter dayType was TRAVEL
            addFullDay(cost, date);
        }
    }

    private void addFullDay(Cost cost, LocalDate date) {
        if (calendar.containsKey(date)) {
            ProjectDay day = calendar.get(date);
            day.setDayType(DayType.FULL);
            // never overwrite a high-cost full day with a low-cost full day
            if (cost == Cost.HIGH) day.setCost(Cost.HIGH);
        } else {
            calendar.put(date, new ProjectDay(DayType.FULL, cost)); // could be low OR high cost
        }
    }


    private int getTotal() {
        System.out.println("------");

        int total = 0;

        // get the total of all days in the calendar
        for (Map.Entry<LocalDate, ProjectDay> entry : calendar.entrySet()) {
            LocalDate date = entry.getKey();
            ProjectDay day = entry.getValue();

            System.out.printf("Date: %s | %s%n", date, day);

            if (day.getDayType() == DayType.TRAVEL) {
                total += 45;
            } else {
                total += 75;
            }
            // high-cost cities are an extra 10 for both travel and full
            if (day.getCost() == Cost.HIGH) {
                total += 10;
            }
        }
        return total;
    }
}
