import java.time.LocalDate;


/* A Project object represents a start date, end date, and city cost (low-cost or high-cost*/
public class Project {


    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Cost cost;


    public Project(LocalDate startDate, LocalDate endDate, Cost cost) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public Cost getCost() {
        return cost;
    }


}
