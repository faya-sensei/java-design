package org.faya.sensei.creational.builder;

public interface IMaterialBuilder {

    /**
     * Set the name for the material being built.
     *
     * @param name the name to assign to the material.
     * @return the current instance.
     */
    IMaterialBuilder setName(String name);

    /**
     * Assign a shader to the material by providing a file path to the shader.
     *
     * @param filePath The file paths to the shader files.
     * @return the current instance.
     */
    IMaterialBuilder setShader(String[] filePath);

    /**
     * Set the base color for the material being built.
     *
     * @param r the red component of the color (0.0 - 1.0).
     * @param g the green component of the color (0.0 - 1.0).
     * @param b the blue component of the color (0.0 - 1.0).
     * @param a the alpha component of the color (0.0 - 1.0).
     * @return the current instance.
     */
    IMaterialBuilder setBaseColor(float r, float g, float b, float a);

    /**
     * Set the alpha transparency value for the material being built.
     *
     * @param alpha the alpha value (0.0 - 1.0).
     * @return the current instance.
     */
    IMaterialBuilder setAlpha(float alpha);

    /**
     * Set the render queue index for the material being built.
     *
     * @param index the render queue index.
     * @return the current instance.
     */
    IMaterialBuilder setRenderQueue(int index);

    /**
     * Adds a keyword to the shader of the material.
     *
     * @param keyword the keyword to add.
     * @return the current instance.
     */
    IMaterialBuilder addKeyword(String keyword);

    /**
     * Build the material and initialize the shader.
     *
     * @return an instance of IMaterial.
     */
    IMaterial build();
}
