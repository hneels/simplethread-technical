import java.time.LocalDate;

/* Create new project test cases here. Results printed to command line */
public class Main {

    public static void main (String[] args) {
        Project project1 = new Project(LocalDate.of(2015, 9, 1),
                LocalDate.of(2015,9,3), Cost.LOW);

        int project1Cost = Reimburser.reimburse(project1);

        System.out.println("Cost for Project 1 is " + project1Cost);

        Project project2 = new Project(LocalDate.of(2015, 9, 1),
                LocalDate.of(2015,9,3), Cost.HIGH);

        int project2Cost = Reimburser.reimburse(project2);

        System.out.println("Cost for Project 2 is " + project2Cost);
    }
}
