// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Asher (906883790)

// LLM Statement:
// During the preparation of this assignment, Asher Exstrom used Claude Sonnet 5.5 
// in order to suggest missing methods and to help with debugging. After using this tool, 
// I reviewed and edited the content as needed to ensure its accuracy and take full responsibility for the 
// content in relation to grading. I understand that I am responsible for being able to complete this work 
// without the use of assistance.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * This class represents the tests for the Outfit class.
 *
 * @author Asher
 * @version 2026.10.12
 */
class OutfitTest {
    private Outfit outfit;

    // ----------------------------------------------------------
    /**
     * Sets up an outfit.
     */
    @BeforeEach
    void setUp() {
        outfit = new Outfit("Everyday", "Blue", "Casual", "Streetwear");
    }


    // ----------------------------------------------------------
    /**
     * Tests that the getters return the constructor values.
     */
    @Test
    void gettersReturnConstructorValues() {
        assertEquals("Everyday", outfit.getName());
        assertEquals("Blue", outfit.getColor());
        assertEquals("Casual", outfit.getOccasion());
        assertEquals("Streetwear", outfit.getStyle());
    }


    // ----------------------------------------------------------
    /**
     * Tests that outfits with the same fields are equal.
     */
    @Test
    void sameFieldsAreEqual() {
        Outfit copy = new Outfit("Everyday", "Blue", "Casual", "Streetwear");

        assertTrue(outfit.equals(copy));
        assertEquals(outfit.hashCode(), copy.hashCode());
    }


    // ----------------------------------------------------------
    /**
     * Tests that an outfit with any different field is not equal.
     */
    @Test
    void differentFieldsAreNotEqual() {
        assertFalse(outfit.equals(new Outfit("Formal", "Blue", "Casual",
            "Streetwear")));
        assertFalse(outfit.equals(new Outfit("Everyday", "Black", "Casual",
            "Streetwear")));
        assertFalse(outfit.equals(new Outfit("Everyday", "Blue", "Work",
            "Streetwear")));
        assertFalse(outfit.equals(new Outfit("Everyday", "Blue", "Casual",
            "Formal")));
    }


    // ----------------------------------------------------------
    /**
     * Tests that equals rejects null and objects of another type.
     */
    @Test
    void equalsRejectsNullAndWrongType() {
        assertFalse(outfit.equals(null));
        assertFalse(outfit.equals("Everyday"));
        assertFalse(outfit.equals(new Sneaker("Everyday", "Blue", "Casual",
            "Streetwear")));
    }


    // ----------------------------------------------------------
    /**
     * Tests that toString includes the outfit's information.
     */
    @Test
    void toStringContainsFields() {
        String result = outfit.toString();

        assertTrue(result.contains("Everyday"));
        assertTrue(result.contains("Blue"));
        assertTrue(result.contains("Casual"));
        assertTrue(result.contains("Streetwear"));
    }
}
