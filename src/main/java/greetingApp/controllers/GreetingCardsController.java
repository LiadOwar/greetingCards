package greetingApp.controllers;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.AbstractTemplateGreetingCardData;
import greetingApp.services.GreetingCardService;
import greetingApp.services.GreetingCardTemplate;
import greetingApp.services.convertors.ClientServerConvertor;
import greetingApp.viewmodel.AbstractTemplateGreetingCardViewModel;
import greetingApp.viewmodel.AbstractGreetingCardViewModel;
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
    public AbstractGreetingCardViewModel createCard(@RequestBody AbstractGreetingCardViewModel greetingCardViewModel) throws Exception{
        System.out.println(String.format("got post request with body: [%s]", greetingCardViewModel));
        AbstractGreetingCardData cardData = convertor.convertGreetingCardViewModel(greetingCardViewModel);
        AbstractGreetingCardViewModel response = null;
        if (greetingCardService.postCard(cardData)){
            response = convertor.convertGreetingCardData(cardData);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/greetingCard/getAllCards")
    public List<AbstractGreetingCardViewModel> getAllGreetingCards() throws Exception{
        System.out.println("get all cards");
        List<AbstractGreetingCardViewModel> ret = new ArrayList<>();
        List<AbstractGreetingCardData> greetingCardDataList = greetingCardService.getSavedCards();
        for (AbstractGreetingCardData greetingCard : greetingCardDataList){
            AbstractGreetingCardViewModel abstractTemplateGreetingCardViewModel = convertor.convertGreetingCardData(greetingCard);
            ret.add(abstractTemplateGreetingCardViewModel);
        }
        return ret;
    }
}
