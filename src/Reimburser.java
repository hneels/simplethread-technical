import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;



public class Reimburser {

    // daily reimbursement costs can be changed here
    static final int LOW_COST_TRAVEL = 45;
    static final int LOW_COST_FULL = 75;
    static final int HIGH_COST_TRAVEL = 55;
    static final int HIGH_COST_FULL = 85;

    // "calendar" mapping Dates to reimbursement amounts
    private static HashMap<LocalDate, ProjectDay> calendar = new HashMap<>();

    public static int reimburse(Project project) {

        LocalDate tempDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();

        // add each date from startDate to endDate (inclusive) to the calendar
        while (tempDate.compareTo(endDate) < 1) {
            // TODO
            tempDate = tempDate.plusDays(1);
        }

        return getTotal();

    }

    private static int getTotal() {
        int total = 0;

        // get the total of all days in the calendar
        for (ProjectDay day : calendar.values()) {

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
