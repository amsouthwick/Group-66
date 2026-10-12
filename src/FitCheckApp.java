import java.util.Scanner;
/**
 *  Virginia Tech Honor Code Pledge:
 *  //
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ari Gallo (arig21)

// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
 */

/**
 *  FitCheck app that utilizes a scanner for user input
 * 
 *  @author amria
 *  @version Oct 12, 2026
 */
public class FitCheckApp {
    private Scanner scanner = new Scanner(System.in);
    private Closet closet = new Closet();
    private WeeklyPlan weeklyPlan = new WeeklyPlan();
    /**
     * 
     */
    public FitCheckApp() {
        closet.addSneaker(new Sneaker("Adidas", "Samba", "Black", "Casual"));
        closet.addSneaker(new Sneaker("Nike", "Air Force 1", "White", "Casual"));
        closet.addSneaker(new Sneaker("Jordan", "Air Jordan 4", "Military Black", "Streetwear"));
        closet.addSneaker(new Sneaker("New Balance", "9060", "Sea Salt", "Casual"));
        closet.addSneaker(new Sneaker("Adidas", "Ultraboost", "Black", "Athletic"));
        closet.addSneaker(new Sneaker("Nike", "Air Max 95", "Triple Black", "Going Out"));
        closet.addSneaker(new Sneaker("Timberland", "6-Inch Premium Boots", "Wheat", "Streetwear"));
        closet.addOutfit(new Outfit("Streetwear Fit", "Black", "Going Out", "Streetwear"));
        closet.addOutfit(new Outfit("Running Fit", "Blue", "Gym", "Athletic"));
        closet.addOutfit(new Outfit("Business Casual", "Beige", "Presentation", "Formal"));
        closet.addOutfit(new Outfit("Weekend Fit", "White", "Weekend", "Casual"));
        closet.addOutfit(new Outfit("Night Out Fit", "Black", "Party", "Going Out"));
        closet.addOutfit(new Outfit("Class Outfit", "Black", "Class", "Casual"));
        closet.addOutfit(new Outfit("Gym Outfit", "Gray", "Gym", "Athletic"));
    }
    /**
     * Runs the FitCheck application and displays the main menu.
     */
    public void run() {
        int choice;
        do {
            System.out.println("\n=== FITCHECK ===");
            System.out.println("1. View Closet");
            System.out.println("2. Build Weekly Plan");
            System.out.println("3. View Weekly Plan");
            System.out.println("4. Change Weekly Plan");
            System.out.println("5. Exit");
            choice = readMenuChoice();
            switch (choice) {
                case 1: viewCloset(); break;
                case 2: buildOrChangePlan(); break;
                case 3: System.out.println(weeklyPlan.getWeeklySummary()); break;
                case 4: changePlan(); break;
                case 5: System.out.println("Goodbye!"); break;
            }
        } while (choice != 5);
    }
    /**
     * Reads the user's menu selection and ensures it is
     * a valid number between 1 and 5.
     *
     * @return the user's valid menu choice
     */
    public int readMenuChoice() {
        return readNumber("Choose (1-5): ", 1, 5);
    }

    private int readNumber(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input.trim());
                if (number >= min && number <= max) {
                    return number;
                }
            }
            catch (NumberFormatException e) {
                // Invalid input is handled below.
            }
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }
    /**
     * Displays all sneakers and outfits currently stored
     * in the closet, along with their corresponding numbers.
     */
    public void viewCloset() {
        System.out.println("\nSneakers:");
        int sneakerCount = closet.getSneakers().size();
        if (sneakerCount == 0) System.out.println("None available.");
        for (int i = 0; i < sneakerCount; i++) {
            System.out.println((i + 1) + ". " + closet.getSneaker(i));
        }

        System.out.println("\nOutfits:");
        int outfitCount = closet.getOutfits().size();
        if (outfitCount == 0) System.out.println("None available.");
        for (int i = 0; i < outfitCount; i++) {
            System.out.println((i + 1) + ". " + closet.getOutfit(i));
        }
    }

    private Day chooseDay() {
        Day[] days = Day.values();
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + formatDay(days[i]));
        }
        return days[readNumber("Choose day (1-5): ", 1, 5) - 1];
    }

    private String formatDay(Day day) {
        String name = day.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    /**
     * Allows the user to create or update a fit for a
     * selected day of the week by choosing a sneaker
     * and outfit from the closet.
     */
    public void buildOrChangePlan() {
        if (closet.getSneakers().isEmpty() || closet.getOutfits().isEmpty()) {
            System.out.println("Add sneakers and outfits before planning.");
            return;
        }
        Day day = chooseDay();
        viewCloset();
        int sneakerNumber = readNumber("Sneaker number: ", 1, closet.getSneakers().size());
        int outfitNumber = readNumber("Outfit number: ", 1, closet.getOutfits().size());
        try {
            Sneaker sneaker = closet.getSneaker(sneakerNumber - 1);
            Outfit outfit = closet.getOutfit(outfitNumber - 1);
            weeklyPlan.setFit(day, sneaker, outfit);
            System.out.println("Saved " + formatDay(day) + "'s fit.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid closet selection. Please try again.");
        }
    }

    private void changePlan() {
        System.out.println("1. Replace a fit");
        System.out.println("2. Clear a day");
        int choice = readNumber("Choose (1-2): ", 1, 2);
        if (choice == 1) {
            buildOrChangePlan();
        }
        else {
            Day day = chooseDay();
            weeklyPlan.clearDay(day);
            System.out.println("Cleared " + formatDay(day) + ".");
        }
    }
    /**
     * Starts the FitCheck application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new FitCheckApp().run();
    }
}
