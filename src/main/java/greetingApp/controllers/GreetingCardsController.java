package greetingApp.controllers;

import greetingApp.services.GreetingCardService;
import greetingApp.services.GreetingCardTemplate;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */
@RestController
public class GreetingCardsController {
    @Autowired
    private GreetingCardService greetingCardService;

    @CrossOrigin
    @RequestMapping("/greetingCard/catalog")
    public List<GreetingCardTemplate> getGreetingCardsCatalog() throws Exception{
        System.out.println("get catalog");
        return greetingCardService.getTemplates();
    }

    @CrossOrigin
    @RequestMapping(method= RequestMethod.POST, value ="/greetingCard/createCard")
    public Boolean createCard(@RequestBody GreetingCardViewModel greetingCardViewModel) throws Exception{
        System.out.println(String.format("got post request with body: [%s]", greetingCardViewModel));


        return true;
    }
}
