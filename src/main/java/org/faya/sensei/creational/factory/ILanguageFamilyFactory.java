package org.faya.sensei.creational.factory;

public interface ILanguageFamilyFactory {

    /**
     * Create the language factory based on ISO-639 language code.
     *
     * @param languageCode The language code.
     * @return The language factory instance.
     */
    ILanguageFamily createLanguageFamily(String languageCode);
}
