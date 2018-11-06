package greetingApp.greetingCardData;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liadm on 28/10/2018.
 */
public class BirthdayGreetingCardData extends AbstractTemplateGreetingCardData {
    private static String templateType = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE.name();
    private Integer recipientAge;
    public BirthdayGreetingCardData(String senderName, String recipientName, Integer recipientAge) {
        super(senderName, recipientName, templateType);
        this.recipientAge = recipientAge;
    }

    public BirthdayGreetingCardData() {
        super(templateType);
    }

    public Integer getRecipientAge() {
        return recipientAge;
    }

    public void setRecipientAge(Integer recipientAge) {
        this.recipientAge = recipientAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BirthdayGreetingCardData that = (BirthdayGreetingCardData) o;

        return recipientAge.equals(that.recipientAge);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + recipientAge.hashCode();
        return result;
    }
}
