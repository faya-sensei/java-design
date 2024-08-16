package org.faya.sensei.structural;

import org.faya.sensei.structural.composite.IShape;
import org.faya.sensei.structural.composite.RayMarcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompositePatternTest {

    private static Class<? extends IShape> sceneClass;
    private static Class<? extends IShape> circleClass;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.structural.composite";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends IShape>> shapeClasses = reflections.getSubTypesOf(IShape.class);

        assertFalse(shapeClasses.isEmpty());

        sceneClass = shapeClasses.stream()
                .filter(cls -> {
                    for (final Field field : cls.getDeclaredFields()) {
                        if (List.class.isAssignableFrom(field.getType())) {
                            return true;
                        }
                    }

                    return false;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Scene class not found"));
        circleClass = shapeClasses.stream()
                .filter(cls -> {
                    try {
                        cls.getConstructor(float.class, float.class, float.class);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Circle class not found"));
    }

    @Test
    public void testDraw() throws Exception {
        final RayMarcher rayMarcher = new RayMarcher();

        final IShape scene = sceneClass.getConstructor().newInstance();
        final IShape circle = circleClass.getConstructor(float.class, float.class, float.class).newInstance(5, 5, 3);

        scene.addChild(circle);

        final boolean[][] actual = rayMarcher.render(scene, 10, 10, new float[] { 0, 0 }, 1.0f);

        final int[][] expected = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j] ? 1 : 0);
            }
        }
    }
}
