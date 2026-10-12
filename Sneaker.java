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
 * This class represents one sneaker in the closet.
 *
 * @author Asher
 * @version 2026.10.12
 */
public class Sneaker {
    private final String brand;
    private final String model;
    private final String color;
    private final String style;

    // ----------------------------------------------------------
    /**
     * Creates a new Sneaker object.
     *
     * @param brand
     *            the brand of the sneaker.
     * @param model
     *            the model of the sneaker.
     * @param color
     *            the color of the sneaker.
     * @param style
     *            the style of the sneaker.
     */
    public Sneaker(String brand, String model, String color, String style) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.style = style;
    }


    // ----------------------------------------------------------
    /**
     * Gets the brand.
     *
     * @return String the brand.
     */
    public String getBrand() {
        return brand;
    }


    // ----------------------------------------------------------
    /**
     * Gets the model.
     *
     * @return String the model.
     */
    public String getModel() {
        return model;
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
     * Gets the style.
     *
     * @return String the style.
     */
    public String getStyle() {
        return style;
    }


    // ----------------------------------------------------------
    /**
     * Two sneakers are equal when all four fields match.
     *
     * @param other
     *            the object to compare to.
     * @return boolean true if equal, false if different, null, or not a
     *         Sneaker.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Sneaker that = (Sneaker)other;
        return Objects.equals(brand, that.brand)
            && Objects.equals(model, that.model)
            && Objects.equals(color, that.color)
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
        return Objects.hash(brand, model, color, style);
    }


    // ----------------------------------------------------------
    /**
     * Returns a readable description, e.g. "Nike Air Max (White, Casual)".
     *
     * @return String the description.
     */
    @Override
    public String toString() {
        return brand + " " + model + " (" + color + ", " + style + ")";
    }
}
