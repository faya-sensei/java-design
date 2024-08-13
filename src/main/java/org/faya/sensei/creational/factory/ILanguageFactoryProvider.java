package org.faya.sensei.creational.factory;

public interface ILanguageFactoryProvider {

    /**
     * Get the language prefab based on the combine of language code and region
     * name.
     *
     * @param locale The full language code
     * @return The language instance.
     */
    ILanguage getLanguage(String locale);
}
