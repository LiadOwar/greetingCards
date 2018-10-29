package greetingApp.services.DataStorageConnection;

import greetingApp.greetingCardData.AbstractGreetingCardData;

import java.util.List;

/**
 * Created by liad on 28/10/2018.
 */
public interface DataStorageConnection {
    Boolean postGreetingCardToDataStorage(AbstractGreetingCardData greetingCardData);

    Boolean clearDataStorage();

    List<AbstractGreetingCardData> getSavedCards();
}
