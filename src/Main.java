import java.time.LocalDate;

/* Create new project test cases here. Results printed to command line */
public class Main {

    public static void main (String[] args) {

        // dates, for readability
        LocalDate sept1 = LocalDate.of(2015, 9, 1);
        LocalDate sept2 = LocalDate.of(2015, 9, 2);
        LocalDate sept3 = LocalDate.of(2015, 9, 3);
        LocalDate sept4 = LocalDate.of(2015, 9, 4);
        LocalDate sept5 = LocalDate.of(2015, 9, 5);
        LocalDate sept6 = LocalDate.of(2015, 9, 6);
        LocalDate sept7 = LocalDate.of(2015, 9, 7);
        LocalDate sept8 = LocalDate.of(2015, 9, 8);

        int set1Cost = Reimburser.reimburse(new Project[]{
                new Project(sept1, sept3, Cost.LOW)
        });

        System.out.printf("Cost for Set 1 is %d %n%n", set1Cost);

        int set2Cost = Reimburser.reimburse(new Project[]{
                new Project(sept1, sept1, Cost.LOW),
                new Project(sept2, sept6, Cost.HIGH),
                new Project(sept6, sept8, Cost.LOW)
        });

        System.out.printf("Cost for Set 2 is %d %n%n", set2Cost);

    }
}
