package pl.training.shop.common;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TextSource {

    private static final String LANGUAGE_SEPARATOR = "_";

    private final TemplateEngine templateEngine;
    private final List<String> availableLanguages;

    public String buildTemplate(String baseTemplateName, Map<String, Object> variables, String language) {
        Context context = createContext(variables);
        String templateName = getTemplateName(baseTemplateName, language);
        return templateEngine.process(templateName, context);
    }

    private Context createContext(Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return context;
    }

    private String getTemplateName(String baseName, String language) {
        return availableLanguages.contains(language) ? baseName + LANGUAGE_SEPARATOR + language : baseName;
    }

}
