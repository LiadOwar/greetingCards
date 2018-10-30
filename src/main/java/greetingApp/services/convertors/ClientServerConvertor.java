package greetingApp.services.convertors;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.viewmodel.GreetingCardViewModel;

/**
 * Created by liadm on 28/10/2018.
 */
public interface ClientServerConvertor {
    AbstractGreetingCardData convertGreetingCardViewModel(GreetingCardViewModel view);

    GreetingCardViewModel convertGreetingCardData(AbstractGreetingCardData cardData);
}
