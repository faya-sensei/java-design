package org.faya.sensei.creational;

import org.faya.sensei.creational.builder.IMaterial;
import org.faya.sensei.creational.builder.IMaterialBuilder;
import org.faya.sensei.creational.builder.IShader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderPatternTest {

    private static Class<? extends IMaterialBuilder> materialBuilderClass;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.creational.builder";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends IMaterialBuilder>> materialBuilderClasses = reflections.getSubTypesOf(IMaterialBuilder.class);

        assertFalse(materialBuilderClasses.isEmpty());

        materialBuilderClass = materialBuilderClasses.iterator().next();
    }

    @Test
    public void testBuild() throws Exception {
        final IMaterialBuilder materialBuilder = materialBuilderClass.getConstructor().newInstance();

        final IMaterial material = materialBuilder
                .setName("Material")
                .setShader(new String[] {
                        "shaders/vert.hlsl",
                        "shaders/frag.hlsl",
                })
                .setBaseColor(0.5f, 0.0f, 0.0f, 1.0f)
                .setAlpha(0.5f)
                .setRenderQueue(500)
                .addKeyword("INSTANCING_ON")
                .addKeyword("LIGHTMAP_SHADOW_MIXING")
                .build();

        assertInstanceOf(IShader.class, material.getShader());
        assertEquals("Material", material.getName());
        assertArrayEquals(
                List.of("INSTANCING_ON", "LIGHTMAP_SHADOW_MIXING").toArray(),
                material.getShaderKeywords()
        );
        assertEquals(500, material.getRenderQueue());
    }
}
