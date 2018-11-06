package greetingApp.greetingCardData;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liad on 06/11/2018.
 */
public class NoTemplateCardData extends AbstractGreetingCardData {
    private String freeText;
    private static String templateType = GreetingCardTemplateTypeEnum.NA.name();

    public NoTemplateCardData(String freeText) {
        super(templateType);
        this.freeText = freeText;
    }

    public NoTemplateCardData() {
        super(templateType);
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
