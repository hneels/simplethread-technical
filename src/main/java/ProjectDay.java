
/* A Day object can be a low-cost travel day, low-cost full day, high-cost travel day, or high-cost full day*/
public class ProjectDay {

    private DayType type;
    private Cost cost;

    public ProjectDay(DayType type, Cost cost) {
        this.type = type;
        this.cost = cost;
    }

    public DayType getDayType() {
        return type;
    }
    public void setDayType(DayType type) {
        this.type = type;
    }
    public Cost getCost() {
        return cost;
    }
    public void setCost(Cost cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s cost %s day", cost, type);
    }

}
