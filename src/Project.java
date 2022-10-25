import java.time.LocalDate;


/* A Project object representing a start date, end date, and type of city (low-cost or high-cost*/
public class Project {

    public enum Type {
        LOW,
        HIGH
    }

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Type type;

    public Project(LocalDate startDate, LocalDate endDate, Type type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public Type getType() {
        return type;
    }


}
