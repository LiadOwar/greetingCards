package greetingApp.GreetingCardObject;

import greetingApp.greetingCardData.AbstractGreetingCardData;

/**
 * Created by liadm on 28/10/2018.
 */
public abstract class AbstractGreetingCard {
    protected AbstractGreetingCardData abstractGreetingCardData;
    protected String templateType;

    public AbstractGreetingCard(AbstractGreetingCardData abstractGreetingCardData) {
        this.abstractGreetingCardData = abstractGreetingCardData;
    }

    public AbstractGreetingCardData getAbstractGreetingCardData() {
        return abstractGreetingCardData;
    }

    public void setAbstractGreetingCardData(AbstractGreetingCardData abstractGreetingCardData) {
        this.abstractGreetingCardData = abstractGreetingCardData;
    }

    public String getTemplateType() {
        return templateType;
    }
}
