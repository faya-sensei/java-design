# Factory Pattern

The Factory Pattern is one of the most commonly used design patterns in Java.
It provides a way to create objects, separating the process of object creation
from the process of using the objects.

## Todo

1. Implement the **abstract product** `ILanguage` interface with classes like
   `AmericanEnglish` and `BritishEnglish` as **concrete product**.

2. Implement the **concrete factory** inherit `ILanguageFamily` interface to
   generate Language objects using country codes like `US` or `GB`.

3. Leverage the **abstract factory** `ILanguageFamilyFactory` to select
   languages based on language codes like `en` or `fr`.

4. Implement a provider to streamline and enhance the object creation process.