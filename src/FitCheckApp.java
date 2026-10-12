import java.util.Scanner;

public class FitCheckApp {
    private Scanner scanner = new Scanner(System.in);
    private Closet closet = new Closet();
    private WeeklyPlan weeklyPlan = new WeeklyPlan();

    public FitCheckApp() {
        closet.addSneaker(new Sneaker("Adidas", "Samba", "Black", "Casual"));
        closet.addSneaker(new Sneaker("Nike", "Air Force 1", "White", "Casual"));
        closet.addOutfit(new Outfit("Class Outfit", "Black", "Class", "Casual"));
        closet.addOutfit(new Outfit("Gym Outfit", "Gray", "Gym", "Athletic"));
    }

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

    private String chooseDay() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }
        return days[readNumber("Choose day (1-5): ", 1, 5) - 1];
    }

    public void buildOrChangePlan() {
        if (closet.getSneakers().isEmpty() || closet.getOutfits().isEmpty()) {
            System.out.println("Add sneakers and outfits before planning.");
            return;
        }
        String day = chooseDay();
        viewCloset();
        int sneakerNumber = readNumber("Sneaker number: ", 1, closet.getSneakers().size());
        int outfitNumber = readNumber("Outfit number: ", 1, closet.getOutfits().size());
        try {
            Sneaker sneaker = closet.getSneaker(sneakerNumber - 1);
            Outfit outfit = closet.getOutfit(outfitNumber - 1);
            weeklyPlan.setFit(day, sneaker, outfit);
            System.out.println("Saved " + day + "'s fit.");
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
            String day = chooseDay();
            weeklyPlan.clearDay(day);
            System.out.println("Cleared " + day + ".");
        }
    }

    public static void main(String[] args) {
        new FitCheckApp().run();
    }
}
