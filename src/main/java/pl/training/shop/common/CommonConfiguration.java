package pl.training.shop.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.TemplateEngine;
import pl.training.shop.common.profiler.Profiler;
import pl.training.shop.common.retry.MethodExecutor;
import pl.training.shop.common.validator.ModelValidator;
import pl.training.shop.common.validator.ValidatorService;

import javax.validation.Validator;
import java.util.List;

@Configuration
public class CommonConfiguration {

    @Bean
    public Profiler profiler() {
        return new Profiler();
    }

    @Bean
    public MethodExecutor methodExecutor() {
        return new MethodExecutor();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatorService validatorService(Validator validator) {
        return new ValidatorService(validator);
    }

    @Bean
    public ModelValidator modelValidator(ValidatorService validatorService) {
        return new ModelValidator(validatorService);
    }

    @Bean
    public TextSource textSource(TemplateEngine templateEngine, @Value("availableLanguages") List<String> availableLanguages) {
        return new TextSource(templateEngine, availableLanguages);
    }

}
