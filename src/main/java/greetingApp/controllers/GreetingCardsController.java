package greetingApp.controllers;

import greetingApp.services.GreetingCardService;
import greetingApp.services.GreetingcardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<GreetingcardTemplate> getGreetingCardsCatalog() throws Exception{
        System.out.println("get catalog1234");


        return greetingCardService.getTemplates();
    }
}
