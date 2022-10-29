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
                addFirstDay(projectCost, DayType.FULL, startDate);
                addLastDay(projectCost, DayType.FULL, endDate);

            } else {
                addFirstDay(projectCost, DayType.TRAVEL, startDate);
                addLastDay(projectCost, DayType.TRAVEL, endDate);

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

    // add the first day of a project (could be at travel rate OR full rate, depending on project length)
    private void addFirstDay(Cost cost, DayType dayType, LocalDate date) {

        if (calendar.containsKey(date.minusDays(1))){
            ProjectDay prevDay = calendar.get(date.minusDays(1));
            // previous day becomes a FULL day at the previous project's cost rate
            prevDay.setDayType(DayType.FULL);
            // add end day as a FULL day, regardless of parameter dayType
            addFullDay(cost, date);

        } else {
            calendar.put(date, new ProjectDay(dayType, cost));
        }
    }

    private void addLastDay(Cost cost, DayType dayType, LocalDate date) {
        // if day after is already in calendar
        if (calendar.containsKey(date.plusDays(1))) {

            ProjectDay nextDay = calendar.get(date.plusDays(1));
            // next day becomes a FULL day at the next project's cost rate
            nextDay.setDayType(DayType.FULL);
            // add end day as a FULL day, regardless of parameter dayType
            addFullDay(cost, date);

        } else {
            calendar.put(date, new ProjectDay(dayType, cost));
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
