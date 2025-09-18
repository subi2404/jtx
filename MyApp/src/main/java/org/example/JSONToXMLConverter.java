package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;

public class JSONToXMLConverter implements XMLJSONConverterI {

    @Override
    public void convertJSONtoXML(File inputJSON, File outputXML) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode tree = jsonMapper.readTree(inputJSON);

        String xmlContent = convertNode(tree, null);

        try (FileWriter writer = new FileWriter(outputXML)) {
            writer.write(xmlContent);
        }
    }

    private String convertNode(JsonNode node, String nodeName) {
        if (node.isObject()) {
            StringBuilder sb = new StringBuilder("<object");
            if (nodeName != null) sb.append(" name=\"").append(nodeName).append("\"");
            sb.append(">");
            node.fields().forEachRemaining(entry -> sb.append(convertNode(entry.getValue(), entry.getKey())));
            sb.append("</object>");
            return sb.toString();
        } else if (node.isArray()) {
            StringBuilder sb = new StringBuilder("<array>");
            for (JsonNode item : node) sb.append(convertNode(item, null));
            sb.append("</array>");
            return sb.toString();
        } else if (node.isTextual()) {
            return "<string" + (nodeName != null ? " name=\"" + nodeName + "\"" : "") + ">"
                    + node.asText() + "</string>";
        } else if (node.isNumber()) {
            return "<number" + (nodeName != null ? " name=\"" + nodeName + "\"" : "") + ">"
                    + node.asText() + "</number>";
        } else if (node.isBoolean()) {
            return "<boolean" + (nodeName != null ? " name=\"" + nodeName + "\"" : "") + ">"
                    + node.asText() + "</boolean>";
        } else if (node.isNull()) {
            return "<null" + (nodeName != null ? " name=\"" + nodeName + "\"" : "") + "/>";
        }
        return "";
    }
}
