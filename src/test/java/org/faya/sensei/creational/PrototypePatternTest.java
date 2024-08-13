package org.faya.sensei.creational;

import org.faya.sensei.creational.prototype.IResource;
import org.faya.sensei.creational.prototype.IStandardResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PrototypePatternTest {

    private static Class<? extends IStandardResource> standardResourceClass;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.creational.prototype";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends IResource>> resourceClasses = reflections.getSubTypesOf(IResource.class);
        final Set<Class<? extends IStandardResource>> standardResourceClasses = reflections.getSubTypesOf(IStandardResource.class);

        assertFalse(resourceClasses.isEmpty());
        assertFalse(standardResourceClasses.isEmpty());

        standardResourceClass = standardResourceClasses.iterator().next();
    }

    @Test
    public void testResourceClone() throws Exception {
        final IStandardResource resource = standardResourceClass.getConstructor().newInstance();
        resource.loadStandardResource();

        final IResource meshResourcePrefab = resource.get("mesh");
        final IResource textureResourcePrefab = resource.get("texture");

        final IResource meshResourcePrefabCloned = meshResourcePrefab.clone();
        final IResource textureResourcePrefabCloned = textureResourcePrefab.clone();

        assertNotSame(meshResourcePrefab, meshResourcePrefabCloned);
        assertNotSame(textureResourcePrefab, textureResourcePrefabCloned);

        assertEquals(meshResourcePrefab.getId(), meshResourcePrefabCloned.getId());
        assertEquals(textureResourcePrefab.getId(), textureResourcePrefabCloned.getId());
    }
}
