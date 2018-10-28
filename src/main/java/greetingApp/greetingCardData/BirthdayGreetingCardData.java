package greetingApp.greetingCardData;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liadm on 28/10/2018.
 */
public class BirthdayGreetingCardData extends AbstractGreetingCardData{
    private Integer recipientAge;
    public BirthdayGreetingCardData(String senderName, String recipientName, Integer recipientAge) {
        super(senderName, recipientName);
        this.recipientAge = recipientAge;
        this.templateType = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE.name();
    }

    public Integer getRecipientAge() {
        return recipientAge;
    }

    public void setRecipientAge(Integer recipientAge) {
        this.recipientAge = recipientAge;
    }
}
