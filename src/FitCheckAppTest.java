import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FitCheckAppTest {
    private java.io.InputStream originalInput;
    private PrintStream originalOutput;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        originalInput = System.in;
        originalOutput = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    private FitCheckApp createApp(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new FitCheckApp();
    }

    @Test
    public void testValidMenuChoice() {
        FitCheckApp app = createApp("2\n");
        assertEquals(2, app.readMenuChoice());
    }

    @Test
    public void testInvalidMenuChoice() {
        FitCheckApp app = createApp("hello\n9\n3\n");
        assertEquals(3, app.readMenuChoice());
        assertTrue(output.toString().contains("Please enter a number from 1 to 5."));
    }

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

    @Test
    public void testBuildWeeklyPlan() {
        FitCheckApp app = createApp("1\n1\n1\n");
        app.buildOrChangePlan();
        assertTrue(output.toString().contains("Saved Monday's fit."));
    }

    @Test
    public void testBuildPlanWithInvalidSelection() {
        FitCheckApp app = createApp("1\n9\n1\n1\n");
        app.buildOrChangePlan();
        String result = output.toString();
        assertTrue(result.contains("Please enter a number from 1 to 2."));
        assertTrue(result.contains("Saved Monday's fit."));
    }

    @Test
    public void testRunAndExit() {
        FitCheckApp app = createApp("5\n");
        app.run();
        assertTrue(output.toString().contains("Goodbye!"));
    }

    @Test
    public void testRunWithInvalidMenuInput() {
        FitCheckApp app = createApp("abc\n0\n5\n");
        app.run();
        String result = output.toString();
        assertTrue(result.contains("Please enter a number from 1 to 5."));
        assertTrue(result.contains("Goodbye!"));
    }

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

    @Test
    public void testClearDayThroughMenu() {
        FitCheckApp app = createApp("2\n1\n1\n1\n4\n2\n1\n3\n5\n");
        app.run();
        String result = output.toString();
        assertTrue(result.contains("Cleared Monday."));
        assertTrue(result.contains("MONDAY: No fit planned"));
    }

    @Test
    public void testMainMethod() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        FitCheckApp.main(new String[0]);
        assertTrue(output.toString().contains("Goodbye!"));
    }
}
