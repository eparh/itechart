package command.util;

import org.stringtemplate.v4.ST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhenya on 14.01.16.
 */
public enum TemplateContainer {
    INSTANCE;
    public static TemplateContainer getInstance() {
        return INSTANCE;
    }
    TemplateContainer() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Map<String, String> templates;

    public Map<String,String> getTemplates() {
        return templates;
    }

    private void init() throws IOException {
        templates = new HashMap<>();
        templates.put("Template1", createTemplate("template1.st"));
        templates.put("Template2", createTemplate("template2.st"));
    }

    private String createTemplate(String resourceFileName) throws IOException {
        String templateString = readFromFile(resourceFileName);
        ST template = new ST(templateString);
        return template.render();
    }

    private String readFromFile(String resourceFileName) throws IOException {
        try (BufferedReader bReader = new BufferedReader(
                new InputStreamReader(TemplateContainer.class.getResourceAsStream("/" + resourceFileName)))){
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bReader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}
