package greetingApp.services.convertors;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.AbstractTemplateGreetingCardData;
import greetingApp.viewmodel.AbstractTemplateGreetingCardViewModel;
import greetingApp.viewmodel.AbstractGreetingCardViewModel;

/**
 * Created by liadm on 28/10/2018.
 */
public interface ClientServerConvertor {
    AbstractGreetingCardData convertGreetingCardViewModel(AbstractGreetingCardViewModel view);

    AbstractGreetingCardViewModel convertGreetingCardData(AbstractGreetingCardData cardData);
}
