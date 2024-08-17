package org.faya.sensei.creational.builder;

public interface IMaterial {

    /**
     * Get the material name.
     *
     * @return The name of the material.
     */
    String getName();

    /**
     * Get the shader associated with this material.
     *
     * @return The shader of the material.
     */
    IShader getShader();

    /**
     * Get the material shader keywords associated with the shader.
     *
     * @return The shader keywords of the material.
     */
    String[] getShaderKeywords();

    /**
     * Get the material render queue.
     *
     * @return The render queue of the material.
     */
    int getRenderQueue();
}
