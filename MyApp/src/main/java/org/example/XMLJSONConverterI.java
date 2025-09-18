package org.example;

import java.io.File;

public interface XMLJSONConverterI {
    void convertJSONtoXML(File inputJSON, File outputXML) throws Exception;
}
