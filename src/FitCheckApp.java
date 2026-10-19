import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

/** Console interface for FitCheck. */
public class FitCheckApp {
    private Scanner scanner = new Scanner(System.in);
    private Closet closet = new Closet();
    private WeeklyPlan weeklyPlan = new WeeklyPlan();
    private final boolean persistenceEnabled;
    private static final Path SAVE_FILE = Paths.get("fitcheck-data.txt");
    
    /**
     * Creates a new FitCheck application with a predefined closet
     *of sneakers and outfits and loads previously saved data.
     */
    public FitCheckApp() { this(true); }

    /** Pass false for isolated unit tests that should not read/write saved data
     * @param persistenceEnabled 
     * */
    public FitCheckApp(boolean persistenceEnabled) {
        this.persistenceEnabled = persistenceEnabled;
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
        if (persistenceEnabled) loadData();
    }
    /**
     * Runs the FitCheck application by displaying the main menu
     * and processing user selections until the user exits.
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
            System.out.println("6. Manage Closet");
            choice = readMenuChoice();
            switch (choice) {
                case 1: viewCloset(); break;
                case 2: buildOrChangePlan(); break;
                case 3: System.out.println(weeklyPlan.getWeeklySummary()); break;
                case 4: changePlan(); break;
                case 5: System.out.println("Goodbye!"); break;
                case 6: manageCloset(); break;
                default: break;
            }
        } while (choice != 5);
    }
    /**
     * Reads and validates the user's main menu selection.
     * Continues prompting until a number from 1 to 6 is entered.
     *
     * @return the user's valid menu selection
     */
    public int readMenuChoice() { return readNumber("Choose (1-6): ", 1, 6); }

    private int readNumber(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input.trim());
                if (number >= min && number <= max) return number;
            }
            catch (NumberFormatException e) { /* Prompt again. */ }
            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }
    /**
     * Displays all sneakers and outfits currently stored
     * in the closet.
     */
    public void viewCloset() {
        System.out.println("\nSneakers:");
        int sneakerCount = closet.getSneakers().size();
        if (sneakerCount == 0) System.out.println("None available.");
        for (int i = 0; i < sneakerCount; i++)
            System.out.println((i + 1) + ". " + closet.getSneaker(i));
        System.out.println("\nOutfits:");
        int outfitCount = closet.getOutfits().size();
        if (outfitCount == 0) System.out.println("None available.");
        for (int i = 0; i < outfitCount; i++)
            System.out.println((i + 1) + ". " + closet.getOutfit(i));
    }

    private Day chooseDay() {
        Day[] days = Day.values();
        for (int i = 0; i < days.length; i++)
            System.out.println((i + 1) + ". " + formatDay(days[i]));
        return days[readNumber("Choose day (1-5): ", 1, 5) - 1];
    }

    private String formatDay(Day day) {
        String name = day.name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    /**
     * Allows the user to select a weekday, sneaker, and outfit
     * to create or replace a daily fit in the weekly plan.
     * Saves the updated plan when persistence is enabled.
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
        Sneaker sneaker = closet.getSneaker(sneakerNumber - 1);
        Outfit outfit = closet.getOutfit(outfitNumber - 1);
        weeklyPlan.setFit(day, sneaker, outfit);
        saveData();
        System.out.println("Saved " + formatDay(day) + "'s fit.");
    }

    private void changePlan() {
        System.out.println("1. Replace a fit");
        System.out.println("2. Clear a day");
        int choice = readNumber("Choose (1-2): ", 1, 2);
        if (choice == 1) buildOrChangePlan();
        else {
            Day day = chooseDay();
            weeklyPlan.clearDay(day);
            saveData();
            System.out.println("Cleared " + formatDay(day) + ".");
        }
    }

    private void manageCloset() {
        System.out.println("\n=== MANAGE CLOSET ===");
        System.out.println("1. Add Sneaker");
        System.out.println("2. Add Outfit");
        System.out.println("3. Remove Sneaker");
        System.out.println("4. Remove Outfit");
        System.out.println("5. Back");
        int choice = readNumber("Choose (1-5): ", 1, 5);
        switch (choice) {
            case 1: addNewSneaker(); break;
            case 2: addNewOutfit(); break;
            case 3: removeExistingSneaker(); break;
            case 4: removeExistingOutfit(); break;
            default: break;
        }
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private void addNewSneaker() {
        closet.addSneaker(new Sneaker(readText("Brand: "), readText("Model: "),
            readText("Color: "), readText("Style: ")));
        saveData();
        System.out.println("Sneaker added successfully!");
    }

    private void addNewOutfit() {
        closet.addOutfit(new Outfit(readText("Name: "), readText("Color: "),
            readText("Occasion: "), readText("Style: ")));
        saveData();
        System.out.println("Outfit added successfully!");
    }

    private void removeExistingSneaker() {
        int count = closet.getSneakers().size();
        if (count == 0) { System.out.println("No sneakers to remove."); return; }
        viewCloset();
        int number = readNumber("Sneaker to remove: ", 1, count);
        closet.removeSneaker(closet.getSneaker(number - 1));
        saveData();
        System.out.println("Sneaker removed successfully!");
    }

    private void removeExistingOutfit() {
        int count = closet.getOutfits().size();
        if (count == 0) { System.out.println("No outfits to remove."); return; }
        viewCloset();
        int number = readNumber("Outfit to remove: ", 1, count);
        closet.removeOutfit(closet.getOutfit(number - 1));
        saveData();
        System.out.println("Outfit removed successfully!");
    }

    private String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }

    private String sneakerRecord(Sneaker sneaker) {
        return "S|" + encode(sneaker.getBrand()) + "|" + encode(sneaker.getModel())
            + "|" + encode(sneaker.getColor()) + "|" + encode(sneaker.getStyle());
    }

    private String outfitRecord(Outfit outfit) {
        return "O|" + encode(outfit.getName()) + "|" + encode(outfit.getColor())
            + "|" + encode(outfit.getOccasion()) + "|" + encode(outfit.getStyle());
    }

    /** Saves closet and plan in a local text file. */
    private void saveData() {
        if (!persistenceEnabled) return;
        List<String> lines = new ArrayList<>();
        lines.add("FITCHECK1");
        for (int i = 0; i < closet.getSneakers().size(); i++)
            lines.add(sneakerRecord(closet.getSneaker(i)));
        for (int i = 0; i < closet.getOutfits().size(); i++)
            lines.add(outfitRecord(closet.getOutfit(i)));
        for (Day day : Day.values()) {
            Sneaker sneaker = weeklyPlan.getSneaker(day);
            Outfit outfit = weeklyPlan.getOutfit(day);
            if (sneaker != null && outfit != null)
                lines.add("P|" + day.name() + "|" + sneakerRecord(sneaker)
                    + "|" + outfitRecord(outfit));
        }
        try {
            Files.write(SAVE_FILE, lines, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("Could not save FitCheck data: " + e.getMessage());
        }
    }

    /** Restores closet and plan from local file, or uses defaults if unavailable. */
    private void loadData() {
        if (!Files.exists(SAVE_FILE)) return;
        try {
            List<String> lines = Files.readAllLines(SAVE_FILE, StandardCharsets.UTF_8);
            if (lines.isEmpty() || !"FITCHECK1".equals(lines.get(0)))
                throw new IllegalArgumentException("Unknown save format");
            Closet loadedCloset = new Closet();
            WeeklyPlan loadedPlan = new WeeklyPlan();
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\\|", -1);
                if (parts[0].equals("S") && parts.length == 5)
                    loadedCloset.addSneaker(parseSneaker(parts, 1));
                else if (parts[0].equals("O") && parts.length == 5)
                    loadedCloset.addOutfit(parseOutfit(parts, 1));
                else if (parts[0].equals("P") && parts.length == 12
                    && parts[2].equals("S") && parts[7].equals("O")) {
                    Day day = Day.valueOf(parts[1]);
                    loadedPlan.setFit(day, parseSneaker(parts, 3), parseOutfit(parts, 8));
                }
                else throw new IllegalArgumentException("Invalid save record");
            }
            closet = loadedCloset;
            weeklyPlan = loadedPlan;
        }
        catch (IOException | IllegalArgumentException e) {
            System.out.println("Could not load saved data; using default closet.");
        }
    }

    private Sneaker parseSneaker(String[] parts, int offset) {
        return new Sneaker(decode(parts[offset]), decode(parts[offset + 1]),
            decode(parts[offset + 2]), decode(parts[offset + 3]));
    }

    private Outfit parseOutfit(String[] parts, int offset) {
        return new Outfit(decode(parts[offset]), decode(parts[offset + 1]),
            decode(parts[offset + 2]), decode(parts[offset + 3]));
    }
    /**
     *  Starts the FitCheck application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) { new FitCheckApp().run(); }
}
