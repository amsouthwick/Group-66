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
 * This class represents the tests for the Sneaker class.
 *
 * @author Asher
 * @version 2026.10.12
 */
class SneakerTest {
    private Sneaker sneaker;

    // ----------------------------------------------------------
    /**
     * Sets up a sneaker.
     */
    @BeforeEach
    void setUp() {
        sneaker = new Sneaker("Nike", "Air Max", "White", "Casual");
    }


    // ----------------------------------------------------------
    /**
     * Tests that the getters return the constructor values.
     */
    @Test
    void gettersReturnConstructorValues() {
        assertEquals("Nike", sneaker.getBrand());
        assertEquals("Air Max", sneaker.getModel());
        assertEquals("White", sneaker.getColor());
        assertEquals("Casual", sneaker.getStyle());
    }


    // ----------------------------------------------------------
    /**
     * Tests that sneakers with the same fields are equal.
     */
    @Test
    void sameFieldsAreEqual() {
        Sneaker copy = new Sneaker("Nike", "Air Max", "White", "Casual");

        assertTrue(sneaker.equals(copy));
        assertEquals(sneaker.hashCode(), copy.hashCode());
    }


    // ----------------------------------------------------------
    /**
     * Tests that a sneaker with any different field is not equal.
     */
    @Test
    void differentFieldsAreNotEqual() {
        assertFalse(sneaker.equals(new Sneaker("Adidas", "Air Max", "White",
            "Casual")));
        assertFalse(sneaker.equals(new Sneaker("Nike", "Samba", "White",
            "Casual")));
        assertFalse(sneaker.equals(new Sneaker("Nike", "Air Max", "Black",
            "Casual")));
        assertFalse(sneaker.equals(new Sneaker("Nike", "Air Max", "White",
            "Athletic")));
    }


    // ----------------------------------------------------------
    /**
     * Tests that equals rejects null and objects of another type.
     */
    @Test
    void equalsRejectsNullAndWrongType() {
        assertFalse(sneaker.equals(null));
        assertFalse(sneaker.equals("Nike Air Max"));
        assertFalse(sneaker.equals(new Outfit("Nike", "Air Max", "White",
            "Casual")));
    }


    // ----------------------------------------------------------
    /**
     * Tests that toString includes the sneaker's information.
     */
    @Test
    void toStringContainsFields() {
        String result = sneaker.toString();

        assertTrue(result.contains("Nike"));
        assertTrue(result.contains("Air Max"));
        assertTrue(result.contains("White"));
        assertTrue(result.contains("Casual"));
    }
}
