package greetingApp.services.greetingCardGenerator;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import org.springframework.stereotype.Component;

/**
 * Created by liadm on 28/10/2018.
 */
@Component
public class BirthDayGreetingCardGenerator extends AbstructGreetingCardGenerator {

    @Override
    public Boolean createCard(AbstractGreetingCardData abstractGreetingCardData) {
        return false;

    }
}
