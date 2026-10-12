// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// I used Claude to determine some test cases, however, most of the test cases
// I came up with.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * This class represents the tests for the WeeklyPlan class.
 * 
 * @author Armaan
 * @version 2026.10.11
 */
class WeeklyPlanTest {
    private WeeklyPlan plan;
    private Sneaker sneaker1;
    private Sneaker sneaker2;
    private Outfit outfit1;
    private Outfit outfit2;

    // ----------------------------------------------------------
    /**
     * Sets up the sneakers and outfits.
     */
    @BeforeEach
    void setUp() {
        plan = new WeeklyPlan();

        sneaker1 = new Sneaker("Nike", "Air Max", "White", "Casual");
        sneaker2 = new Sneaker("Adidas", "Samba", "Black", "Casual");

        outfit1 = new Outfit("Everyday", "Blue", "Casual", "Streetwear");
        outfit2 = new Outfit("Formal", "Black", "Work", "Formal");
    }


    // ----------------------------------------------------------
    /**
     * No selections.
     */
    @Test
    void newPlanHasNoSelections() {
        for (Day day : Day.values()) {
            assertNull(plan.getSneaker(day));
            assertNull(plan.getOutfit(day));
        }
    }


    // ----------------------------------------------------------
    /**
     * Test assigned plan.
     */
    @Test
    void setFitAssignsSneakerAndOutfit() {
        plan.setFit(Day.MONDAY, sneaker1, outfit1);

        assertEquals(sneaker1, plan.getSneaker(Day.MONDAY));
        assertEquals(outfit1, plan.getOutfit(Day.MONDAY));
    }


    // ----------------------------------------------------------
    /**
     * Tests if changing a fit only replaces the day selected.
     */
    @Test
    void changingFitReplacesOnlySelectedDay() {
        plan.setFit(Day.MONDAY, sneaker1, outfit1);
        plan.setFit(Day.TUESDAY, sneaker1, outfit1);

        plan.setFit(Day.MONDAY, sneaker2, outfit2);

        assertEquals(sneaker2, plan.getSneaker(Day.MONDAY));
        assertEquals(outfit2, plan.getOutfit(Day.MONDAY));

        assertEquals(sneaker1, plan.getSneaker(Day.TUESDAY));
        assertEquals(outfit1, plan.getOutfit(Day.TUESDAY));
    }


    // ----------------------------------------------------------
    /**
     * Tests if clear day only removes the specified selections on that day.
     */
    @Test
    void clearDayRemovesOnlyThatDaysSelections() {
        plan.setFit(Day.MONDAY, sneaker1, outfit1);
        plan.setFit(Day.TUESDAY, sneaker2, outfit2);

        plan.clearDay(Day.MONDAY);

        assertNull(plan.getSneaker(Day.MONDAY));
        assertNull(plan.getOutfit(Day.MONDAY));

        assertEquals(sneaker2, plan.getSneaker(Day.TUESDAY));
        assertEquals(outfit2, plan.getOutfit(Day.TUESDAY));
    }


    // ----------------------------------------------------------
    /**
     * Test clear an empty day.
     */
    @Test
    void clearingAnAlreadyEmptyDayDoesNotFail() {
        assertDoesNotThrow(() -> plan.clearDay(Day.FRIDAY));

        assertNull(plan.getSneaker(Day.FRIDAY));
        assertNull(plan.getOutfit(Day.FRIDAY));
    }


    // ----------------------------------------------------------
    /**
     * Test null day.
     */
    @Test
    void nullDayIsRejectedBySetFit() {
        assertThrows(IllegalArgumentException.class, () -> plan.setFit(null,
            sneaker1, outfit1));
    }


    // ----------------------------------------------------------
    /**
     * Test null sneaker.
     */
    @Test
    void nullSneakerIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> plan.setFit(
            Day.MONDAY, null, outfit1));

        assertNull(plan.getSneaker(Day.MONDAY));
        assertNull(plan.getOutfit(Day.MONDAY));
    }


    // ----------------------------------------------------------
    /**
     * Test null outfit.
     */
    @Test
    void nullOutfitIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> plan.setFit(
            Day.MONDAY, sneaker1, null));

        assertNull(plan.getSneaker(Day.MONDAY));
        assertNull(plan.getOutfit(Day.MONDAY));
    }


    // ----------------------------------------------------------
    /**
     * Test null day.
     */
    @Test
    void nullDayIsRejectedByGettersAndClear() {
        assertThrows(IllegalArgumentException.class, () -> plan.getSneaker(
            null));

        assertThrows(IllegalArgumentException.class, () -> plan.getOutfit(
            null));

        assertThrows(IllegalArgumentException.class, () -> plan.clearDay(null));
    }


    // ----------------------------------------------------------
    /**
     * Test if the summary contains each day regardless.
     */
    @Test
    void summaryIncludesAllFiveDays() {
        String summary = plan.getWeeklySummary();

        assertTrue(summary.contains("MONDAY"));
        assertTrue(summary.contains("TUESDAY"));
        assertTrue(summary.contains("WEDNESDAY"));
        assertTrue(summary.contains("THURSDAY"));
        assertTrue(summary.contains("FRIDAY"));
        assertEquals(5, summary.split("No fit planned", -1).length - 1);
    }


    // ----------------------------------------------------------
    /**
     * Test if summary includes assigned items.
     */
    @Test
    void summaryIncludesAssignedItems() {
        plan.setFit(Day.MONDAY, sneaker1, outfit1);

        String summary = plan.getWeeklySummary();

        assertTrue(summary.contains("MONDAY"));
        assertTrue(summary.contains("Nike"));
        assertTrue(summary.contains("Air Max"));
        assertTrue(summary.contains("Everyday"));
        assertTrue(summary.contains("TUESDAY"));
        assertTrue(summary.contains("No fit planned"));
    }


    // ----------------------------------------------------------
    /**
     * Tests is a plan keeps selections if you remove the items in the closet.
     */
    @Test
    void planKeepsSelectionsWhenClosetItemsAreRemoved() {
        Closet closet = new Closet();
        closet.addSneaker(sneaker1);
        closet.addOutfit(outfit1);

        plan.setFit(Day.MONDAY, sneaker1, outfit1);

        closet.removeSneaker(sneaker1);
        closet.removeOutfit(outfit1);

        assertEquals(sneaker1, plan.getSneaker(Day.MONDAY));
        assertEquals(outfit1, plan.getOutfit(Day.MONDAY));
    }
}
