package greetingApp.services;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */

public class GreetingCardServiceImpl implements GreetingCardService {

    private List<GreetingCardTemplate> templatesList;

    public GreetingCardServiceImpl(List<GreetingCardTemplate> templatesList) {
        this.templatesList = templatesList;
    }

    public GreetingCardServiceImpl() {
    }

    public List<GreetingCardTemplate> getTemplates(){
        return this.templatesList;
    }
}
