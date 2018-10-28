package greetingApp.services;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.services.DataStorageConnection.DataStorageConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */

public class GreetingCardServiceImpl implements GreetingCardService {

    @Autowired
    private DataStorageConnection dataStorageConnection;

    private List<GreetingCardTemplate> templatesList;

    public GreetingCardServiceImpl(List<GreetingCardTemplate> templatesList) {
        this.templatesList = templatesList;
    }

    public GreetingCardServiceImpl() {
    }

    public List<GreetingCardTemplate> getTemplates(){
        return this.templatesList;
    }

    @Override
    public Boolean postCard(AbstractGreetingCardData greetingCardData) {
        return false;
    }
}
