import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReimburserTest {

    // dates, for readability
    LocalDate sept1 = LocalDate.of(2015, 9, 1);
    LocalDate sept2 = LocalDate.of(2015, 9, 2);
    LocalDate sept3 = LocalDate.of(2015, 9, 3);
    LocalDate sept4 = LocalDate.of(2015, 9, 4);
    LocalDate sept5 = LocalDate.of(2015, 9, 5);
    LocalDate sept6 = LocalDate.of(2015, 9, 6);
    LocalDate sept7 = LocalDate.of(2015, 9, 7);
    LocalDate sept8 = LocalDate.of(2015, 9, 8);
    LocalDate sept9 = LocalDate.of(2015, 9, 9);


    // requirements set 1 should match hand-calculated result
    @Test
    void set1ShouldEqual165() {
        int set1Result = new Reimburser().reimburse(new Project[]{
                new Project(sept1, sept3, Cost.LOW)
        });
        assertEquals(165, set1Result);
    }

    // requirements set 2 should match hand-calculated result
    @Test
    void set2ShouldEqual620() {
        int set2Result = new Reimburser().reimburse(new Project[]{
                new Project(sept1, sept1, Cost.LOW),
                new Project(sept2, sept6, Cost.HIGH),
                new Project(sept6, sept8, Cost.LOW)
        });
        assertEquals(620, set2Result);
    }

    // requirements set 3 should match hand-calculated result
    @Test
    void set3ShouldEqual475() {
        int set3Result = new Reimburser().reimburse(new Project[] {
                new Project(sept1, sept3, Cost.LOW),
                new Project(sept5, sept7, Cost.HIGH),
                new Project(sept8, sept8, Cost.HIGH)
        });
        assertEquals(475, set3Result);
    }

    // requirements set 4 should match hand-calculated result
    @Test
    void set4ShouldEqual245() {
        int set4Result = new Reimburser().reimburse(new Project[] {
                new Project(sept1, sept1, Cost.LOW),
                new Project(sept1, sept1, Cost.LOW),
                new Project(sept2, sept2, Cost.HIGH),
                new Project(sept2, sept3, Cost.HIGH)
        });
        assertEquals(245, set4Result);
    }

    // order of overlapping LOW & HIGH projects in the set should not matter for calculating the result
    @Test
    void orderShouldNotMatter() {
        int order1Result = new Reimburser().reimburse(new Project[] {
                new Project(sept1, sept3, Cost.LOW),
                new Project(sept3, sept5, Cost.HIGH)
        });
        int order2Result = new Reimburser().reimburse(new Project[] {
                new Project(sept3, sept5, Cost.HIGH),
                new Project(sept1, sept3, Cost.LOW)
        });
        assertEquals(order1Result, order2Result);
        assertEquals(345, order2Result);
    }

    // any time a LOW and HIGH project overlap on a travel day, that day should become a FULL HIGH day
    @Test
    void highCostTakesPrecedence() {
        int result = new Reimburser().reimburse(new Project[] {
                new Project(sept1, sept3, Cost.HIGH),
                new Project(sept3, sept5, Cost.LOW),
                new Project(sept5, sept7, Cost.HIGH)
        });
        assertEquals(525, result);
    }

    /* when projects do not overlap, but exist on adjacent days, the travel days should become full days
    at the same cost as the rest of their project */
    @Test
    void adjacentTravelDaysBecomeFull() {
        int result = new Reimburser().reimburse(new Project[] {
                new Project(sept1, sept3, Cost.LOW),
                new Project(sept4, sept6, Cost.HIGH),
                new Project(sept7, sept9, Cost.LOW)
        });
        assertEquals(645, result);
    }

    // reimburser should work even if the project array is empty
    @Test
    void zeroProjectsAreValid() {
        int result = new Reimburser().reimburse(new Project[]{});
        assertEquals(0, result);
    }

    /* reimburser should work even if project dates span end of month or year,
    e.g. Dec 31st 2021 to Jan 3rd 2022
     */
    @Test
    void monthYearEndIsValid() {
        int result = new Reimburser().reimburse(new Project[] {
                new Project(
                        LocalDate.of(2021, 12, 31),
                        LocalDate.of(2022, 1, 2),
                        Cost.HIGH)
        });
        assertEquals(195, result);
    }
}