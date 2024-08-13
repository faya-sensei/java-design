package org.faya.sensei.creational.prototype;

import java.util.UUID;

public interface IResource extends Cloneable {

    /**
     * Get the path of resource.
     *
     * @return The unique path of the resource.
     */
    String getPath();

    /**
     * Get the component uuid.
     *
     * @return The uuid of the component.
     */
    UUID getId();

    /**
     * Set the component uuid.
     *
     * @param id The uuid of the component.
     */
    void setId(UUID id);

    /**
     * Make a copy of the resources. {@inheritDoc}
     */
    IResource clone();
}
