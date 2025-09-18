package org.example;

public class ConverterFactory {
    public static XMLJSONConverterI createXMLJSONConverter() {
        return new JSONToXMLConverter(); // your class implementing XMLJSONConverterI
    }
}
