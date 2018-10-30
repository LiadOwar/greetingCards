package greetingApp.services;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.greetingCardData.AbstractGreetingCardData;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */
public interface GreetingCardService {
    List<GreetingCardTemplate> getTemplates();

    Boolean postCard(AbstractGreetingCardData greetingCardData);

    List<AbstractGreetingCardData> getSavedCards();

}
