package greetingApp.viewmodel;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liad on 06/11/2018.
 */
public class NoTemplateCardViewModel extends AbstractGreetingCardViewModel {

    private static GreetingCardTemplateTypeEnum templateType = GreetingCardTemplateTypeEnum.NA;
    private String freeText;

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public NoTemplateCardViewModel(String freeText) {
        super(templateType.name());
        this.freeText = freeText;
    }

    public NoTemplateCardViewModel() {
        super(templateType.name());
    }
}
