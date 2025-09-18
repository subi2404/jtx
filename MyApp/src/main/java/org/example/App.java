package org.example;

import java.io.*;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        if(args.length != 2){
            System.out.println("Usage: java -jar converter.jar <input.json> <output.xml>");
            return;
        }
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        XMLJSONConverterI converter = ConverterFactory.createXMLJSONConverter();
        converter.convertJSONtoXML(inputFile, outputFile);

        System.out.println("Conversion complete! Output written to " + outputFile.getAbsolutePath());
    }
}
