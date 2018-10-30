package greetingApp.controllers;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.services.GreetingCardService;
import greetingApp.services.GreetingCardTemplate;
import greetingApp.services.convertors.ClientServerConvertor;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liad on 27/10/2018.
 */
@RestController
public class GreetingCardsController {
    @Autowired
    private GreetingCardService greetingCardService;
    @Autowired
    private ClientServerConvertor convertor;

    @CrossOrigin
    @RequestMapping("/greetingCard/catalog")
    public List<GreetingCardTemplate> getGreetingCardsCatalog() throws Exception{
        System.out.println("get catalog");
        return greetingCardService.getTemplates();
    }

    @CrossOrigin
    @RequestMapping(method= RequestMethod.POST, value ="/greetingCard/createCard")
    public GreetingCardViewModel createCard(@RequestBody GreetingCardViewModel greetingCardViewModel) throws Exception{
        System.out.println(String.format("got post request with body: [%s]", greetingCardViewModel));
        AbstractGreetingCardData cardData = convertor.convertGreetingCardViewModel(greetingCardViewModel);
        GreetingCardViewModel response = null;
        if (greetingCardService.postCard(cardData)){
            response = convertor.convertGreetingCardData(cardData);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/greetingCard/getAllCards")
    public List<GreetingCardViewModel> getAllGreetingCards() throws Exception{
        System.out.println("get all cards");
        List<GreetingCardViewModel> ret = new ArrayList<>();
        List<AbstractGreetingCardData> greetingCardDataList = greetingCardService.getSavedCards();
        for (AbstractGreetingCardData greetingCard : greetingCardDataList){
            GreetingCardViewModel greetingCardViewModel = convertor.convertGreetingCardData(greetingCard);
            ret.add(greetingCardViewModel);
        }
        return ret;
    }
}
