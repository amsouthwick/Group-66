// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// I used Claude to determine some test cases, however, most of the test cases
// I came up with. I also asked Claude the syntax for testing exceptions.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * This class represents the tests for the Closest class.
 * 
 * @author Armaan
 * @version 2026.10.11
 */
class ClosetTest {
    private Closet closet;

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
        closet = new Closet();

        sneaker1 = new Sneaker("Nike", "Air Max", "White", "Casual");
        sneaker2 = new Sneaker("Adidas", "Samba", "Black", "Casual");

        outfit1 = new Outfit("Everyday", "Blue", "Casual", "Streetwear");
        outfit2 = new Outfit("Formal", "Black", "Work", "Formal");
    }


    // ----------------------------------------------------------
    /**
     * Tests if a closet is empty.
     */
    @Test
    void newClosetIsEmpty() {
        assertTrue(closet.getSneakers().isEmpty());
        assertTrue(closet.getOutfits().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests adding and getting a sneaker.
     */
    @Test
    void addSneakerAndRetrieveIt() {
        closet.addSneaker(sneaker1);

        assertEquals(1, closet.getSneakers().size());
        assertEquals(sneaker1, closet.getSneaker(0));
    }


    // ----------------------------------------------------------
    /**
     * Tests adding and getting an outfit.
     */
    @Test
    void addOutfitAndRetrieveIt() {
        closet.addOutfit(outfit1);

        assertEquals(1, closet.getOutfits().size());
        assertEquals(outfit1, closet.getOutfit(0));
    }


    // ----------------------------------------------------------
    /**
     * Tests adding multiple of either to see if the order is preserved.
     */
    @Test
    void addMultipleItemsPreservesOrder() {
        closet.addSneaker(sneaker1);
        closet.addSneaker(sneaker2);
        closet.addOutfit(outfit1);
        closet.addOutfit(outfit2);

        assertEquals(sneaker1, closet.getSneaker(0));
        assertEquals(sneaker2, closet.getSneaker(1));
        assertEquals(outfit1, closet.getOutfit(0));
        assertEquals(outfit2, closet.getOutfit(1));
    }


    // ----------------------------------------------------------
    /**
     * Tests adding a null sneaker.
     */
    @Test
    void addingNullSneakerIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> closet.addSneaker(
            null));

        assertTrue(closet.getSneakers().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests adding a null outfit.
     */
    @Test
    void addingNullOutfitIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> closet.addOutfit(
            null));

        assertTrue(closet.getOutfits().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing an existing sneaker.
     */
    @Test
    void removeExistingSneakerReturnsTrue() {
        closet.addSneaker(sneaker1);

        assertTrue(closet.removeSneaker(sneaker1));
        assertTrue(closet.getSneakers().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing a missing sneaker.
     */
    @Test
    void removeMissingSneakerReturnsFalse() {
        assertFalse(closet.removeSneaker(sneaker1));
        assertTrue(closet.getSneakers().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing an existing outfit.
     */
    @Test
    void removeExistingOutfitReturnsTrue() {
        closet.addOutfit(outfit1);

        assertTrue(closet.removeOutfit(outfit1));
        assertTrue(closet.getOutfits().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing a missing outfit.
     */
    @Test
    void removeMissingOutfitReturnsFalse() {
        assertFalse(closet.removeOutfit(outfit1));
        assertTrue(closet.getOutfits().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests removing the first occurance of a sneaker in a linked list.
     */
    @Test
    void removeOnlyFirstEqualSneaker() {
        closet.addSneaker(sneaker1);
        closet.addSneaker(new Sneaker("Nike", "Air Max", "White", "Casual"));

        assertTrue(closet.removeSneaker(sneaker1));
        assertEquals(1, closet.getSneakers().size());
    }


    // ----------------------------------------------------------
    /**
     * Tests invalids indices.
     */
    @Test
    void getSneakerRejectsInvalidIndices() {
        assertThrows(IndexOutOfBoundsException.class, () -> closet.getSneaker(
            0));

        closet.addSneaker(sneaker1);

        assertThrows(IndexOutOfBoundsException.class, () -> closet.getSneaker(
            -1));

        assertThrows(IndexOutOfBoundsException.class, () -> closet.getSneaker(
            1));
    }


    // ----------------------------------------------------------
    /**
     * Tests invalids indices.
     */
    @Test
    void getOutfitRejectsInvalidIndices() {
        assertThrows(IndexOutOfBoundsException.class, () -> closet.getOutfit(
            0));

        closet.addOutfit(outfit1);

        assertThrows(IndexOutOfBoundsException.class, () -> closet.getOutfit(
            -1));

        assertThrows(IndexOutOfBoundsException.class, () -> closet.getOutfit(
            1));
    }
}
