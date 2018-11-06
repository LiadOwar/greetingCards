package greetingApp.viewmodel;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liad on 06/11/2018.
 */
public class BirthDayCardViewModel extends AbstractTemplateGreetingCardViewModel {
    private Integer recipientAge;
    private static GreetingCardTemplateTypeEnum templateType = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE;

    public BirthDayCardViewModel(String senderName, String recipientName,Integer recipientAge) {
        super(senderName, recipientName, templateType.name());
        this.recipientAge = recipientAge;
    }

    public BirthDayCardViewModel() {
        super(templateType.name());

    }

    public Integer getRecipientAge() {
        return recipientAge;
    }

    public void setRecipientAge(Integer recipientAge) {
        this.recipientAge = recipientAge;
    }
}
