// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan (906801008)

// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.

// -------------------------------------------------------------------------
/**
 * This class represents the Closet in which outfits and sneakers will be
 * managed.
 * 
 * @author Armaan
 * @version 2026.10.11
 */
public class Closet {
    private LinkedChain<Sneaker> sneakers;
    private LinkedChain<Outfit> outfits;

    // ----------------------------------------------------------
    /**
     * Creates a new Closet object.
     */
    public Closet() {
        sneakers = new LinkedChain<>();
        outfits = new LinkedChain<>();
    }


    // ----------------------------------------------------------
    /**
     * Adds a sneaker to the LinkedChain.
     * 
     * @param sneaker
     *            the sneaker to add.
     */
    public void addSneaker(Sneaker sneaker) {
        if (sneaker == null) {
            throw new IllegalArgumentException("Sneaker cannot be null.");
        }

        sneakers.add(sneaker);
    }


    // ----------------------------------------------------------
    /**
     * Adds an outfit to the LinkedChain.
     * 
     * @param outfit
     *            the outfit to add.
     */
    public void addOutfit(Outfit outfit) {
        if (outfit == null) {
            throw new IllegalArgumentException("Outfit cannot be null.");
        }

        outfits.add(outfit);
    }


    // ----------------------------------------------------------
    /**
     * Removes a sneaker from the LinkedChain.
     * 
     * @param sneaker
     *            the sneaker to remove.
     * 
     * @return boolean true if successful, false otherwise.
     */
    public boolean removeSneaker(Sneaker sneaker) {
        return sneakers.remove(sneaker);
    }


    // ----------------------------------------------------------
    /**
     * Removes an outfit from the LinkedChain.
     * 
     * @param outfit
     *            the outfit to remove.
     * 
     * @return boolean true if successful, false otherwise.
     */
    public boolean removeOutfit(Outfit outfit) {
        return outfits.remove(outfit);
    }


    // ----------------------------------------------------------
    /**
     * Gets a sneaker from the linked chain.
     * 
     * @param index
     *            the position of the sneaker in the list.
     * 
     * @return Sneaker The sneaker in the position.
     */
    public Sneaker getSneaker(int index) {
        return sneakers.get(index);
    }


    // ----------------------------------------------------------
    /**
     * Gets an outfit from the LinkedChain.
     * 
     * @param index
     *            the position of the sneaker in the list.
     * 
     * @return Outfit The outfit in the position.
     */
    public Outfit getOutfit(int index) {
        return outfits.get(index);
    }


    // ----------------------------------------------------------
    /**
     * Returns the sneaker linked chain.
     * 
     * @return LinkedChain<Sneaker> The linked chain of sneakers.
     */
    public LinkedChain<Sneaker> getSneakers() {
        return sneakers;
    }


    // ----------------------------------------------------------
    /**
     * Returns the outfit linked chain.
     * 
     * @return LinkedChain<Outfit> The linked chain of outfits.
     */
    public LinkedChain<Outfit> getOutfits() {
        return outfits;
    }
}
