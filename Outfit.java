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

import java.util.Objects;

// -------------------------------------------------------------------------
/**
 * This class represents one outfit in the closet.
 *
 * @author Asher
 * @version 2026.10.12
 */
public class Outfit {
    private final String name;
    private final String color;
    private final String occasion;
    private final String style;

    // ----------------------------------------------------------
    /**
     * Creates a new Outfit object.
     *
     * @param name
     *            the name of the outfit.
     * @param color
     *            the color of the outfit.
     * @param occasion
     *            the occasion the outfit is for.
     * @param style
     *            the style of the outfit.
     */
    public Outfit(String name, String color, String occasion, String style) {
        this.name = name;
        this.color = color;
        this.occasion = occasion;
        this.style = style;
    }


    // ----------------------------------------------------------
    /**
     * Gets the name.
     *
     * @return String the name.
     */
    public String getName() {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Gets the color.
     *
     * @return String the color.
     */
    public String getColor() {
        return color;
    }


    // ----------------------------------------------------------
    /**
     * Gets the occasion.
     *
     * @return String the occasion.
     */
    public String getOccasion() {
        return occasion;
    }


    // ----------------------------------------------------------
    /**
     * Gets the style.
     *
     * @return String the style.
     */
    public String getStyle() {
        return style;
    }


    // ----------------------------------------------------------
    /**
     * Two outfits are equal when all four fields match.
     *
     * @param other
     *            the object to compare to.
     * @return boolean true if equal, false if different, null, or not an
     *         Outfit.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Outfit that = (Outfit)other;
        return Objects.equals(name, that.name)
            && Objects.equals(color, that.color)
            && Objects.equals(occasion, that.occasion)
            && Objects.equals(style, that.style);
    }


    // ----------------------------------------------------------
    /**
     * Returns a hash code consistent with equals.
     *
     * @return int the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, color, occasion, style);
    }


    // ----------------------------------------------------------
    /**
     * Returns a readable description, e.g.
     * "Everyday (Blue, Casual, Streetwear)".
     *
     * @return String the description.
     */
    @Override
    public String toString() {
        return name + " (" + color + ", " + occasion + ", " + style + ")";
    }
}
