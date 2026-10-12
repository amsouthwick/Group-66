// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// I used Claude to determine how to implement the Enum-based days.

// -------------------------------------------------------------------------
/**
 * This class represents the weekly plan of outfits and sneakers.
 * 
 * @author Armaan
 * @version 2026.10.11
 */
public class WeeklyPlan {
    private final Sneaker[] dailySneakers;
    private final Outfit[] dailyOutfits;

    // ----------------------------------------------------------
    /**
     * Create a new Closet object.
     */
    public WeeklyPlan() {
        dailySneakers = new Sneaker[Day.values().length];
        dailyOutfits = new Outfit[Day.values().length];
    }


    // ----------------------------------------------------------
    /**
     * Assigns a sneaker and outfit to the specified day.
     *
     * @param day
     *            the day to assign the fit to.
     * @param sneaker
     *            the sneaker selected for the day.
     * @param outfit
     *            the outfit selected for the day.
     * @throws IllegalArgumentException
     *             if the day, sneaker, or outfit is null.
     */
    public void setFit(Day day, Sneaker sneaker, Outfit outfit) {
        validateDay(day);

        if (sneaker == null || outfit == null) {
            throw new IllegalArgumentException(
                "Sneaker and outfit cannot be null.");
        }

        int index = day.ordinal();
        dailySneakers[index] = sneaker;
        dailyOutfits[index] = outfit;
    }


    // ----------------------------------------------------------
    /**
     * Gets the sneaker assigned to the specified day.
     *
     * @param day
     *            the day to get the sneaker for.
     *
     * @return Sneaker The sneaker assigned to the day, or null if
     *         no sneaker has been assigned.
     * @throws IllegalArgumentException
     *             if the day is null.
     */
    public Sneaker getSneaker(Day day) {
        validateDay(day);
        return dailySneakers[day.ordinal()];
    }


    // ----------------------------------------------------------
    /**
     * Gets the outfit assigned to the specified day.
     *
     * @param day
     *            the day to get the outfit for.
     *
     * @return Outfit The outfit assigned to the day, or null if
     *         no outfit has been assigned.
     * @throws IllegalArgumentException
     *             if the day is null.
     */
    public Outfit getOutfit(Day day) {
        validateDay(day);
        return dailyOutfits[day.ordinal()];
    }


    // ----------------------------------------------------------
    /**
     * Clears the sneaker and outfit assigned to the specified day.
     *
     * @param day
     *            the day to clear the selections for.
     * @throws IllegalArgumentException
     *             if the day is null.
     */
    public void clearDay(Day day) {
        validateDay(day);

        int index = day.ordinal();
        dailySneakers[index] = null;
        dailyOutfits[index] = null;
    }


    // ----------------------------------------------------------
    /**
     * Gets a summary of the planned fits for Monday through Friday.
     *
     * @return String A summary containing each day and its assigned
     *         sneaker and outfit, or a message if no fit is planned.
     */
    public String getWeeklySummary() {
        StringBuilder summary = new StringBuilder();

        for (Day day : Day.values()) {
            int index = day.ordinal();

            summary.append(day).append(": ");

            if (dailySneakers[index] == null && dailyOutfits[index] == null) {
                summary.append("No fit planned");
            }
            else {
                summary.append("Sneaker: ").append(dailySneakers[index]).append(
                    " | Outfit: ").append(dailyOutfits[index]);
            }

            summary.append(System.lineSeparator());
        }

        return summary.toString();
    }


    // ----------------------------------------------------------
    /**
     * Validates that the specified day is not null.
     *
     * @param day
     *            the day to validate.
     * @throws IllegalArgumentException
     *             if the day is null.
     */
    private void validateDay(Day day) {
        if (day == null) {
            throw new IllegalArgumentException("Day cannot be null.");
        }
    }

}
