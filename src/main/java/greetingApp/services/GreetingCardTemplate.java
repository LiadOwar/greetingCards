package greetingApp.services;

import org.springframework.stereotype.Component;

/**
 * Created by liad on 27/10/2018.
 */

public class GreetingCardTemplate {
    private GreetingCardTemplateTypeEnum type;

    public GreetingCardTemplate(GreetingCardTemplateTypeEnum type) {
        this.type = type;
    }

    public GreetingCardTemplateTypeEnum getType() {
        return type;
    }
}
