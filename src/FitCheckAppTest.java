
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class FitCheckAppTest {
    private java.io.InputStream originalInput;
    private PrintStream originalOutput;
    private ByteArrayOutputStream output;

    /**
     * setup environment before each method runs
     */
    @BeforeEach
    public void setUp() {
        originalInput = System.in;
        originalOutput = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }
    /**
     * Restores the original input and output streams
     * after each test is completed.
     */
    @AfterEach
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }
    /**
     * Creates a FitCheck application using simulated
     * user input for testing.
     *
     * @param input the simulated console input
     * @return a new FitCheckApp instance
     */
    private FitCheckApp createApp(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new FitCheckApp();
    }
    /**
     * Tests that the application correctly reads
     * a valid menu selection from the user.
     */
    @Test
    public void testValidMenuChoice() {
        FitCheckApp app = createApp("2\n");
        assertEquals(2, app.readMenuChoice());
    }
    /**
     * Tests that invalid menu inputs are rejected
     * until the user enters a valid selection.
     */
    @Test
    public void testInvalidMenuChoice() {
        FitCheckApp app = createApp("hello\n9\n3\n");
        assertEquals(3, app.readMenuChoice());
        assertTrue(output.toString().contains("Please enter a number from 1 to 6."));
    }
    /**
     * Tests that the closet displays the available
     * sneakers and outfits correctly.
     */
    @Test
    public void testViewCloset() {
        FitCheckApp app = createApp("");
        app.viewCloset();
        String result = output.toString();
        assertTrue(result.contains("Sneakers:"));
        assertTrue(result.contains("Outfits:"));
        assertTrue(result.contains("Samba"));
        assertTrue(result.contains("Class Outfit"));
    }
    /**
     * Tests that the user can successfully create
     * and save a fit for a selected day.
     */
    @Test
    public void testBuildWeeklyPlan() {
        FitCheckApp app = createApp("1\n1\n1\n");
        app.buildOrChangePlan();
        assertTrue(output.toString().contains("Saved Monday's fit."));
    }
    /**
     * Tests that invalid sneaker selections are handled
     * before allowing the user to save a valid fit.
     */
    @Test
    public void testBuildPlanWithInvalidSelection() {
        FitCheckApp app = createApp("1\n9\n1\n1\n");
        app.buildOrChangePlan();
        String result = output.toString();
        assertTrue(result.contains("Please enter a number from 1 to 7."));
        assertTrue(result.contains("Saved Monday's fit."));
    }
    /**
     * Tests that the application exits correctly
     * when the user selects the exit option.
     */
    @Test
    public void testRunAndExit() {
        FitCheckApp app = createApp("5\n");
        app.run();
        assertTrue(output.toString().contains("Goodbye!"));
    }
    /**
     * Tests that the application handles invalid menu
     * inputs and continues running until the user exits.
     */
    @Test
    public void testRunWithInvalidMenuInput() {
        FitCheckApp app = createApp("abc\n0\n5\n");
        app.run();
        String result = output.toString();
        assertTrue(result.contains("Please enter a number from 1 to 6."));
        assertTrue(result.contains("Goodbye!"));
    }
    /**
     * Tests that a saved fit can be viewed through
     * the main menu with the correct sneaker and outfit.
     */
    @Test
    public void testViewWeeklyPlanThroughMenu() {
        FitCheckApp app = createApp("2\n1\n1\n1\n3\n5\n");
        app.run();
        String result = output.toString();
        assertTrue(result.contains("Saved Monday's fit."));
        assertTrue(result.contains("MONDAY: Sneaker:"));
        assertTrue(result.contains("Samba"));
        assertTrue(result.contains("Class Outfit"));
    }
    /**
     * Tests that a planned fit can be cleared
     * and that the day is displayed as unplanned.
     */
    @Test
    public void testClearDayThroughMenu() {
        FitCheckApp app = createApp("2\n1\n1\n1\n4\n2\n1\n3\n5\n");
        app.run();
        String result = output.toString();
        assertTrue(result.contains("Cleared Monday."));
        assertTrue(result.contains("MONDAY: No fit planned"));
    }
    /**
     * Tests that the main method starts the application
     * and allows the user to exit successfully.
     */
    @Test
    public void testMainMethod() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        FitCheckApp.main(new String[0]);
        assertTrue(output.toString().contains("Goodbye!"));
    }

/**
 * Tests adding a new sneaker through the menu.
 */
@Test
public void testAddSneaker() {
    FitCheckApp app = createApp(
        "6\n1\nPuma\nSuede\nRed\nCasual\n1\n5\n");

    app.run();

    String result = output.toString();
    assertTrue(result.contains("Sneaker added successfully!"));
    assertTrue(result.contains("Puma Suede"));
}

/**
 * Tests adding a new outfit through the menu.
 */
@Test
public void testAddOutfit() {
    FitCheckApp app = createApp(
        "6\n2\nStudy Fit\nBlue\nClass\nCasual\n1\n5\n");

    app.run();

    String result = output.toString();
    assertTrue(result.contains("Outfit added successfully!"));
    assertTrue(result.contains("Study Fit"));
}

/**
 * Tests removing an existing sneaker.
 */
@Test
public void testRemoveSneaker() {
    FitCheckApp app = createApp(
        "6\n3\n1\n1\n5\n");

    app.run();

    String result = output.toString();
    assertTrue(result.contains("Sneaker removed successfully!"));

    int lastCloset = result.lastIndexOf("Sneakers:");
    String finalCloset = result.substring(lastCloset);

    assertFalse(finalCloset.contains("Samba"));
}

/**
 * Tests removing an existing outfit.
 */
@Test
public void testRemoveOutfit() {
    FitCheckApp app = createApp(
        "6\n4\n1\n1\n5\n");

    app.run();

    String result = output.toString();
    assertTrue(result.contains("Outfit removed successfully!"));

    int lastCloset = result.lastIndexOf("Outfits:");
    String finalCloset = result.substring(lastCloset);

    assertFalse(finalCloset.contains("Streetwear Fit"));
}

/**
 * Tests that the new menu option is accepted.
 */
@Test
public void testManageClosetMenu() {
    FitCheckApp app = createApp("6\n5\n5\n");

    app.run();

    String result = output.toString();
    assertTrue(result.contains("MANAGE CLOSET"));
    assertTrue(result.contains("Goodbye!"));
}

}
