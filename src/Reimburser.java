import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class Reimburser {

    public static int reimburse(Project project) {

        // "calendar" mapping Dates to reimbursement amounts
        HashMap<LocalDate, Integer> calendar = new HashMap<>();

        LocalDate tempDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();

        // add each date from startDate to endDate (inclusive) to the calendar
        while (tempDate.compareTo(endDate) < 1) {
            // TODO - temporary logic
            int dayPrice = project.getType() == Project.Type.HIGH ? 2 : 1;
            calendar.put(tempDate, dayPrice);
            tempDate = tempDate.plusDays(1);
        }

        int total = 0;

        // get the total of all days in the calendar
        for (int dayPrice : calendar.values()) {
            total += dayPrice;
        }

        return total;
    }
}
