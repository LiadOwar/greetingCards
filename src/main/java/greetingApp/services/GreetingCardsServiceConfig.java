package greetingApp.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */
@Configuration
public class GreetingCardsServiceConfig {
    @Bean
    public GreetingCardService getGreetingCardService(){
        List<GreetingCardTemplate> greetingcardTemplates = new ArrayList<>();
        greetingcardTemplates.add(new GreetingCardTemplate(GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE));
        greetingcardTemplates.add(new GreetingCardTemplate(GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE));
        return new GreetingCardServiceImpl(greetingcardTemplates);
    }
}
