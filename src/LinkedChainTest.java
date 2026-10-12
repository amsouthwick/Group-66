import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the LinkedChain class.
 */
public class LinkedChainTest {
    private LinkedChain<String> chain;

    /**
     * Initializes an empty LinkedChain before each test.
     */
    @Before
    public void setUp() {
        chain = new LinkedChain<>();
    }

    /**
     * Tests appending items to the chain and verifies size updates.
     */
    @Test
    public void testAddAndSize() {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        
        chain.add("Air Force 1");
        assertFalse(chain.isEmpty());
        assertEquals(1, chain.size());
        assertTrue(chain.contains("Air Force 1"));
        chain.add(null);
        assertEquals(1, chain.size());
    }

    /**
     * Tests inserting items at specific indices within the chain.
     */
    @Test
    public void testAddAtIndex() {
        chain.add("A");
        chain.add("C");
        chain.add(1, "B"); 
        
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
        assertEquals(3, chain.size());
        chain.add(3, "D");
        assertEquals("D", chain.get(3));
    }

    /**
     * Verifies that adding at a negative index throws an exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtInvalidIndexNegative() {
        chain.add(-1, "A");
    }

    /**
     * Verifies that adding at an out-of-bounds index throws an exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtInvalidIndexTooLarge() {
        chain.add(1, "A");
    }

    /**
     * Tests retrieving elements from a valid index.
     */
    @Test
    public void testGet() {
        chain.add("Outfit 1");
        assertEquals("Outfit 1", chain.get(0));
    }

    /**
     * Verifies that retrieving from an invalid index throws an exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        chain.get(0);
    }

    /**
     * Tests removing elements by their index position.
     */
    @Test
    public void testRemoveIndex() {
        chain.add("A");
        chain.add("B");
        chain.add("C"); 
        String removed = chain.remove(1);
        assertEquals("B", removed);
        assertEquals(2, chain.size());
        assertEquals("C", chain.get(1));
        chain.remove(1);
        chain.add("D");
        assertEquals("D", chain.get(1)); 
        chain.remove(0);
        assertEquals(1, chain.size());
        assertEquals("D", chain.get(0));
    }

    /**
     * Verifies that removing from an invalid index throws an exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        chain.remove(0);
    }

    /**
     * Tests removing a specific element object from the chain.
     */
    @Test
    public void testRemoveItem() {
        chain.add("Jordan 4");
        chain.add("Yeezy");
        chain.add("Jordan 4");
        assertTrue(chain.remove("Jordan 4"));
        assertEquals(2, chain.size());
        assertEquals("Yeezy", chain.get(0));
        assertEquals("Jordan 4", chain.get(1));
        assertFalse(chain.remove("Converse"));
        assertFalse(chain.remove(null));
    }

    /**
     * Tests counting occurrences of duplicate elements.
     */
    @Test
    public void testCount() {
        assertEquals(0, chain.count("Vans"));
        chain.add("Vans");
        chain.add("Vans");
        chain.add("Crocs");
        assertEquals(2, chain.count("Vans"));
        assertEquals(1, chain.count("Crocs"));
        assertEquals(0, chain.count(null));
    }

    /**
     * Tests clearing all elements from the chain and resetting state.
     */
    @Test
    public void testClear() {
        chain.add("A");
        chain.add("B");
        chain.clear();
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        chain.add("C");
        assertEquals(1, chain.size());
        assertEquals("C", chain.get(0));
    }
}