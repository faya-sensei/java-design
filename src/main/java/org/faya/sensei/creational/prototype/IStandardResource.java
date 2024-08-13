package org.faya.sensei.creational.prototype;

public interface IStandardResource {

    /**
     * Get the resource based on the resource path.
     *
     * @param path The resource path.
     * @return The resource instance.
     */
    IResource get(String path);

    /**
     * Get and initialize the build-in resources.
     */
    void loadStandardResource();
}
