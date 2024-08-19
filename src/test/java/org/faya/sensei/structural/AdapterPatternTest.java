package org.faya.sensei.structural;

import org.faya.sensei.structural.adapter.ICompressor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AdapterPatternTest {

    private static Class<? extends ICompressor> compressorClass;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.structural.adapter";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends ICompressor>> compressorClasses = reflections.getSubTypesOf(ICompressor.class);

        assertFalse(compressorClasses.isEmpty());

        compressorClass = compressorClasses.stream()
                .filter(clazz -> {
                    int counter = 0;

                    for (final Field field : clazz.getDeclaredFields()) {
                        if (ICompressor.class.isAssignableFrom(field.getType())) {
                            counter++;
                        }
                    }

                    return counter == 2;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CompressorAdapter class not found"));
    }

    @Test
    public void testCompress() throws Exception {
        final byte[] originalData = "test aabbcc 12345".getBytes(StandardCharsets.UTF_8);

        final ICompressor compressor = compressorClass.getConstructor().newInstance();

        assertAll(
                () -> {
                    final byte[] compressedData = compressor.compress("gzip", originalData);
                    assertNotNull(compressedData);
                    assertTrue(compressedData.length > 0);

                    final byte[] decompressedData = compressor.decompress("gzip", compressedData);
                    assertNotNull(decompressedData);
                    assertArrayEquals(originalData, decompressedData);
                },
                () -> {
                    final byte[] compressedData = compressor.compress("rl", originalData);
                    assertNotNull(compressedData);
                    assertTrue(compressedData.length > 0);

                    final byte[] decompressedData = compressor.decompress("rl", compressedData);
                    assertNotNull(decompressedData);
                    assertArrayEquals(originalData, decompressedData);
                }
        );
    }
}