package greetingApp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.services.DataStorageConnection.DataStorageConnection;
import greetingApp.services.greetingCardGenerator.BirthDayGreetingCardGenerator;
import greetingApp.services.greetingCardGenerator.GreetingCardGeneratorService;
import greetingApp.services.greetingCardGenerator.GreetingCardsGeneratorConstants;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by liad on 27/10/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GreetingCardServiceImplTest {
    private String mockSenderName = "mock name";
    private String mockRecipientName = "mock recipient";
    private Integer mockRecipientAge = 9;
    private String mockTemplateType = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE.name();

    @Autowired
    private GreetingCardService greetingCardService;

    @Autowired
    private Map<String, GreetingCardGeneratorService> greetingCardGeneratorMap;

    @Autowired
    private ViewToModelConvertor viewToModelConvertor;

    @Autowired
    private DataStorageConnection dataStorageConnection;

    private GreetingCardGeneratorService getBirthDayCardGenerator() {
        String birthDayCardGeneratorClassSimpleName = GreetingCardsGeneratorConstants.BIRTH_DAY_GENERATOR_NAME;
        return greetingCardGeneratorMap.get(birthDayCardGeneratorClassSimpleName);
    }

    @Test
    public void getCatalogTest(){
        List<GreetingCardTemplate> templates = greetingCardService.getTemplates();
        assert (templates.get(0).getType() == (GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE));
        assert (templates.get(1).getType() == (GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE));
    }
    @Test
    public void getGreetingCardGeneratorService(){
        assert greetingCardGeneratorMap != null;
    }
    @Test
    public void getBirthdayCardsGeneratorService_Test(){
        GreetingCardGeneratorService greetingCardGeneratorService = getBirthDayCardGenerator();
        Class<? extends GreetingCardGeneratorService> generatorClass = greetingCardGeneratorService.getClass();

        String generatorClassSimpleName = generatorClass.getSimpleName();
        String birthDayCardGeneratorClassName = BirthDayGreetingCardGenerator.class.getSimpleName();

        assert generatorClassSimpleName.equals(birthDayCardGeneratorClassName);
        assert greetingCardGeneratorService != null;
    }

    @Test
    public void addSenderNameToBirthdayCard_Test(){
        GreetingCardGeneratorService greetingCardGeneratorService = getBirthDayCardGenerator();
        String senderName = "mock name";
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, "mock recipient", 9);
        String senderNameFromData = birthdayGreetingCardData.getSenderName();
        assert senderNameFromData.equals(senderName);
    }

    @Test
    public void addRecipientNameToBirthdayCard_Test(){

        String senderName = "mock name";
        String recipientName = "mock recipient";
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, recipientName, 9);
        String recipientNameFromData = birthdayGreetingCardData.getRecipientName();
        assert recipientNameFromData.equals(recipientName);
    }

    @Test
    public void setBirthDayCardDataRecipientAge_Test(){
        String senderName = "mock name";
        String recipientName = "mock recipient";
        Integer mockAge = 9;
        Integer differentMockAge = 3;
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, recipientName, mockAge);

        assert birthdayGreetingCardData.getRecipientAge() == mockAge;

        birthdayGreetingCardData.setRecipientAge(differentMockAge);
        assert birthdayGreetingCardData.getRecipientAge() == differentMockAge;
    }

    @Test
    public void convertGreetingCardViewModel(){
        GreetingCardViewModel greetingCardViewModel = createMockViewModel();
        AbstractGreetingCardData abstractGreetingCardData = viewToModelConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)abstractGreetingCardData;

        assert greetingCardViewModel.getTemplateType().equals(birthdayGreetingCardData.getTemplateType());
        assert greetingCardViewModel.getRecipientName().equals(birthdayGreetingCardData.getRecipientName());
        assert greetingCardViewModel.getSenderName().equals(birthdayGreetingCardData.getSenderName());
        assert greetingCardViewModel.getRecipientAge() == (birthdayGreetingCardData.getRecipientAge());
    }

    @Test
    public void connectionToDataStorage_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstructData = createMockGreetingCardAbstructData();
        ObjectMapper mapper = new ObjectMapper();
        String dataStorageConnectionQuarry = null;
        try {
            dataStorageConnectionQuarry = mapper.writeValueAsString(mockGreetingCardAbstructData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Boolean isSuccess = dataStorageConnection.postToDataStorage(dataStorageConnectionQuarry);
        assert isSuccess;
    }

    @Test
    public void postBirthDayCard_Test(){
        GreetingCardViewModel greetingCardViewModel = createMockViewModel();
        AbstractGreetingCardData abstractGreetingCardData = viewToModelConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)abstractGreetingCardData;
        Boolean isSuccess = greetingCardService.postCard(birthdayGreetingCardData);
        assert isSuccess;
    }







    private BirthdayGreetingCardData createMockGreetingCardAbstructData(){
        String senderName = "mock name";
        String recipientName = "mock recipient";
        Integer mockAge = 9;
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, recipientName, mockAge);
        return birthdayGreetingCardData;
    }

    private GreetingCardViewModel createMockViewModel(){
        GreetingCardViewModel ret = new GreetingCardViewModel();
        ret.setSenderName(mockSenderName);
        ret.setRecipientName(mockRecipientName);
        ret.setRecipientAge(mockRecipientAge);
        ret.setTemplateType(mockTemplateType);

        return ret;
    }


}
