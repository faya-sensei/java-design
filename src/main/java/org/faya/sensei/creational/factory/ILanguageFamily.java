package org.faya.sensei.creational.factory;

public interface ILanguageFamily {

    /**
     * Create the language prefab based on region name.
     *
     * @param region The language region.
     * @return The language instance.
     */
    ILanguage createDialect(String region);
}
