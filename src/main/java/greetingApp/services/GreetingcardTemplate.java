package greetingApp.services;

import org.springframework.stereotype.Component;

/**
 * Created by liad on 27/10/2018.
 */

public class GreetingcardTemplate {
    private GreetingCardTemplateTypeEnum type;

    public GreetingcardTemplate(GreetingCardTemplateTypeEnum type) {
        this.type = type;
    }

    public GreetingCardTemplateTypeEnum getType() {
        return type;
    }
}
