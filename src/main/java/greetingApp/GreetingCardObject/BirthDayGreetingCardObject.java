package greetingApp.GreetingCardObject;

import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liadm on 28/10/2018.
 */
public class BirthDayGreetingCardObject extends AbstractGreetingCard{

    public BirthDayGreetingCardObject(BirthdayGreetingCardData birthdayGreetingCardData) {
        super(birthdayGreetingCardData);
        this.templateType = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE.name();
    }



}
