package greetingApp.GreetingCardObject;

import greetingApp.greetingCardData.AbstractTemplateGreetingCardData;

/**
 * Created by liadm on 28/10/2018.
 */
public abstract class AbstractGreetingCard {
    protected AbstractTemplateGreetingCardData abstractTemplateGreetingCardData;
    protected String templateType;

    public AbstractGreetingCard(AbstractTemplateGreetingCardData abstractTemplateGreetingCardData) {
        this.abstractTemplateGreetingCardData = abstractTemplateGreetingCardData;
    }

    public AbstractTemplateGreetingCardData getAbstractTemplateGreetingCardData() {
        return abstractTemplateGreetingCardData;
    }

    public void setAbstractTemplateGreetingCardData(AbstractTemplateGreetingCardData abstractTemplateGreetingCardData) {
        this.abstractTemplateGreetingCardData = abstractTemplateGreetingCardData;
    }

    public String getTemplateType() {
        return templateType;
    }
}
