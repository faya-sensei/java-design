package org.faya.sensei.creational;

import org.faya.sensei.creational.factory.ILanguage;
import org.faya.sensei.creational.factory.ILanguageFactoryProvider;
import org.faya.sensei.creational.factory.ILanguageFamily;
import org.faya.sensei.creational.factory.ILanguageFamilyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryPatternTest {

    private static Class<? extends ILanguageFamily> languageFactoryClass;
    private static Class<? extends ILanguageFamilyFactory> languageFamilyFactoryClass;
    private static Class<? extends ILanguageFactoryProvider> languageFactoryProviderClass;

    @BeforeAll
    public static void prepare() {
        final String packageName = "org.faya.sensei.creational.factory";

        final Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(Scanners.SubTypes));

        final Set<Class<? extends ILanguage>> languageClasses = reflections.getSubTypesOf(ILanguage.class);
        final Set<Class<? extends ILanguageFamily>> languageFamilyClasses = reflections.getSubTypesOf(ILanguageFamily.class);
        final Set<Class<? extends ILanguageFamilyFactory>> languageFamilyFactoryClasses = reflections.getSubTypesOf(ILanguageFamilyFactory.class);
        final Set<Class<? extends ILanguageFactoryProvider>> languageFactoryProviderClasses = reflections.getSubTypesOf(ILanguageFactoryProvider.class);

        assertFalse(languageClasses.isEmpty());
        assertFalse(languageFamilyClasses.isEmpty());
        assertFalse(languageFamilyFactoryClasses.isEmpty());
        assertFalse(languageFactoryProviderClasses.isEmpty());

        languageFactoryClass = languageFamilyClasses.iterator().next();
        languageFamilyFactoryClass = languageFamilyFactoryClasses.iterator().next();
        languageFactoryProviderClass = languageFactoryProviderClasses.iterator().next();
    }

    @Test
    public void testLanguageFactory() throws Exception {
        final ILanguageFamily languageFactory = languageFactoryClass.getConstructor().newInstance();

        assertAll(
                () -> {
                    final ILanguage language = languageFactory.createDialect("US");

                    assertNotNull(language);
                    assertEquals("American English", language.getName());
                },
                () -> {
                    final ILanguage language = languageFactory.createDialect("GB");

                    assertNotNull(language);
                    assertEquals("British English", language.getName());
                }
        );
    }

    @Test
    public void testLanguageFamilyFactory() throws Exception {
        final ILanguageFamilyFactory languageFamilyFactory = languageFamilyFactoryClass.getConstructor().newInstance();

        final ILanguageFamily englishFamily = languageFamilyFactory.createLanguageFamily("en");

        assertNotNull(englishFamily);
    }

    @Test
    public void testLanguageFactoryProvider() throws Exception {
        final ILanguageFactoryProvider languageFactoryProvider = languageFactoryProviderClass.getConstructor().newInstance();

        assertAll(
                () -> {
                    final ILanguage language = languageFactoryProvider.getLanguage("en-US");

                    assertNotNull(language);
                    assertEquals("American English", language.getName());
                },
                () -> {
                    final ILanguage language = languageFactoryProvider.getLanguage("en-GB");

                    assertNotNull(language);
                    assertEquals("British English", language.getName());
                }
        );
    }
}
