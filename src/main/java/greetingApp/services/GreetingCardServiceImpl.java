package greetingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */

public class GreetingCardServiceImpl implements GreetingCardService {

    private List<GreetingcardTemplate> templatesList;

    public GreetingCardServiceImpl(List<GreetingcardTemplate> templatesList) {
        this.templatesList = templatesList;
    }

    public GreetingCardServiceImpl() {
    }

    public List<GreetingcardTemplate> getTemplates(){
        return this.templatesList;
    }
}
