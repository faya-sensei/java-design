package org.faya.sensei.structural.composite;

public interface IShape {

    /**
     * Add child shape under the shape.
     *
     * @param child The child shape.
     */
    default void addChild(IShape child) { }

    /**
     * Calculates the Signed Distance Function (SDF) for a given point in 2D
     * space.
     *
     * @param x The x-coordinate of the point in 2D space.
     * @param y The y-coordinate of the point in 2D space.
     * @return The signed distance.
     */
    float getSDF(float x, float y);
}
