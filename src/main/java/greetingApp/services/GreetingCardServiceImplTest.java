package greetingApp.services;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.controllers.GreetingCardsController;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.greetingCardData.GetWellGreetingCardData;
import greetingApp.services.DataStorageConnection.DataStorageConnection;
import greetingApp.services.convertors.ClientServerConvertor;
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
    private ClientServerConvertor clientServerConvertor;

    @Autowired
    private DataStorageConnection dataStorageConnection;

    @Autowired
    private GreetingCardsController greetingCardsController;

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
        GreetingCardViewModel greetingCardViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData abstractGreetingCardData = clientServerConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)abstractGreetingCardData;

        assert greetingCardViewModel.getTemplateType().equals(birthdayGreetingCardData.getTemplateType());
        assert greetingCardViewModel.getRecipientName().equals(birthdayGreetingCardData.getRecipientName());
        assert greetingCardViewModel.getSenderName().equals(birthdayGreetingCardData.getSenderName());
        assert greetingCardViewModel.getRecipientAge() == (birthdayGreetingCardData.getRecipientAge());
    }

    @Test
    public void postBirthDayCard_Test(){
        Boolean isSuccess = postBirthDayCard();
        assert isSuccess;
    }

    @Test
    public void postGetWellCard_Test(){
        Boolean isSuccess = postGetWellCard();
        assert isSuccess;
    }

    @Test
    public void get1SavedBirthdayCardFromStorage_Test(){
        assert dataStorageConnection.clearDataStorage();
        assert postBirthDayCard();
        List<AbstractGreetingCardData> savedCards = greetingCardService.getSavedCards();
        assert savedCards.size() == 1;
        AbstractGreetingCardData abstractGreetingCardData = savedCards.get(0);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData) abstractGreetingCardData;
        assert birthdayGreetingCardData.getRecipientAge() == mockRecipientAge;
    }

    @Test
    public void post2BirthDayCards_Test(){
        assert dataStorageConnection.clearDataStorage();
        assert postBirthDayCard();

        GreetingCardViewModel greetingCardViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData abstractGreetingCardData2 = clientServerConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData2 = (BirthdayGreetingCardData)abstractGreetingCardData2;
        birthdayGreetingCardData2.setRecipientAge(22);
        birthdayGreetingCardData2.setRecipientName("Mock 2nd Name");
        assert greetingCardService.postCard(birthdayGreetingCardData2);
        List<AbstractGreetingCardData> savedCards = greetingCardService.getSavedCards();
        assert savedCards.size() == 2;
    }

    @Test
    public void clearDataStorage_Test(){
        postBirthDayCard();
        dataStorageConnection.clearDataStorage();
    }

    @Test
    public void generateUUidFromNewGreetingCardViewModel(){
        GreetingCardViewModel mockBirthDayViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData greetingCardData = clientServerConvertor.convertGreetingCardViewModel(mockBirthDayViewModel);
        assert greetingCardData.getUUid() != null;
    }

    @Test
    public void assignGreetingCardViewModelUUidFromData(){
        GreetingCardViewModel mockBirthDayViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData greetingCardData = clientServerConvertor.convertGreetingCardViewModel(mockBirthDayViewModel);
        String dataUUid = greetingCardData.getUUid();
        GreetingCardViewModel convertedGreetingCardViewModel = clientServerConvertor.convertGreetingCardData(greetingCardData);
        String convertedGreetingCardViewModelUUid = convertedGreetingCardViewModel.getUUid();
        assert convertedGreetingCardViewModelUUid.equals(dataUUid);
    }

    @Test
    public void differentGreetingCardViewModelUUid_test(){
        GreetingCardViewModel mockBirthDayViewModel = createMockBirthDayViewModel();
        GreetingCardViewModel mockGetWellViewModel = createMockGetWellViewModel();
        try {
            GreetingCardViewModel responseCardViewModel1 = greetingCardsController.createCard(mockBirthDayViewModel);
            GreetingCardViewModel responseCardViewModel2 = greetingCardsController.createCard(mockGetWellViewModel);

            String responseCardViewModel1UUid = responseCardViewModel1.getUUid();
            String responseCardViewModel2UUid = responseCardViewModel2.getUUid();

            assert !responseCardViewModel1UUid.equals(responseCardViewModel2UUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSavedGreetingCardDataFromViewModel_Test(){
        dataStorageConnection.clearDataStorage();
        GreetingCardViewModel mockBirthDayViewModel = createMockBirthDayViewModel();
        GreetingCardViewModel mockGetWellViewModel = createMockGetWellViewModel();
        try {
            GreetingCardViewModel responseCardViewModel1 = greetingCardsController.createCard(mockBirthDayViewModel);
            GreetingCardViewModel responseCardViewModel2 = greetingCardsController.createCard(mockGetWellViewModel);

            String responseCardViewModel1UUid = responseCardViewModel1.getUUid();
            String responseCardViewModel2UUid = responseCardViewModel2.getUUid();

            Boolean gotSameUuidForCard1 = false;
            Boolean gotSameUuidForCard2 = false;

            List<AbstractGreetingCardData> savedCards = dataStorageConnection.getSavedCards();
            for (AbstractGreetingCardData greetingCardData : savedCards){
                if (responseCardViewModel1UUid.equals(greetingCardData.getUUid())){
                    gotSameUuidForCard1 = true;
                }
                if (responseCardViewModel2UUid.equals(greetingCardData.getUUid())){
                    gotSameUuidForCard2 = true;
                }
            }
            assert gotSameUuidForCard1;
            assert gotSameUuidForCard2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compareTwoSameGreetingCardData_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstructData1 = createMockGreetingCardAbstructData();
        BirthdayGreetingCardData mockGreetingCardAbstructData2 = createMockGreetingCardAbstructData();

        mockGreetingCardAbstructData1.setUUid("mockUUid");
        mockGreetingCardAbstructData2.setUUid("mockUUid");

        assert mockGreetingCardAbstructData1.equals(mockGreetingCardAbstructData2);
    }

    @Test
    public void compareTwoDifferentGreetingCardData_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstructData1 = createMockGreetingCardAbstructData();
        BirthdayGreetingCardData mockGreetingCardAbstructData2 = createMockGreetingCardAbstructData();

        mockGreetingCardAbstructData1.setUUid("mockUUid");
        mockGreetingCardAbstructData2.setUUid("different_mockUUid");

        assert !mockGreetingCardAbstructData1.equals(mockGreetingCardAbstructData2);
    }

    @Test
    public void convertCardDataToViewModel_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstructData = createMockGreetingCardAbstructData();
        GreetingCardViewModel greetingCardViewModel = clientServerConvertor.convertGreetingCardData(mockGreetingCardAbstructData);
        assert mockGreetingCardAbstructData.getUUid() != null;
        assert greetingCardViewModel.getUUid() == (mockGreetingCardAbstructData.getUUid());
        assert greetingCardViewModel.getSenderName().equals(mockGreetingCardAbstructData.getSenderName());
        assert greetingCardViewModel.getRecipientName().equals(mockGreetingCardAbstructData.getRecipientName());
        assert greetingCardViewModel.getTemplateType().equals(mockGreetingCardAbstructData.getTemplateType());
        assert greetingCardViewModel.getRecipientAge() == (mockGreetingCardAbstructData.getRecipientAge());
    }


//    @Test
//    public void compareDifferentCardsDataWithSameData(){
//        BirthdayGreetingCardData birthdayGreetingCardData1 = new BirthdayGreetingCardData(mockSenderName, mockRecipientName, mockRecipientAge);
//        BirthdayGreetingCardData birthdayGreetingCardData2 = new BirthdayGreetingCardData(mockSenderName, mockRecipientName, mockRecipientAge);
//        assert !birthdayGreetingCardData1.equals(birthdayGreetingCardData2);
//    }
//
//    @Test
//    public void postBirthDayCardAndGetWellCard(){
//        clearDataStorage_Test();
//        final Boolean aBoolean = postBirthDayCard();
//        postGetWellCard();
//        List<AbstractGreetingCardData> savedCards = dataStorageConnection.getSavedCards();
//    }



    private Boolean postBirthDayCard() {
        GreetingCardViewModel greetingCardViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData abstractGreetingCardData = clientServerConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)abstractGreetingCardData;
        return greetingCardService.postCard(birthdayGreetingCardData);
    }

    private Boolean postGetWellCard() {
        GreetingCardViewModel greetingCardViewModel = createMockGetWellViewModel();
        AbstractGreetingCardData abstractGreetingCardData = clientServerConvertor.convertGreetingCardViewModel(greetingCardViewModel);
        GetWellGreetingCardData getWellGreetingCardData = (GetWellGreetingCardData)abstractGreetingCardData;
        return greetingCardService.postCard(getWellGreetingCardData);
    }

    private BirthdayGreetingCardData createMockGreetingCardAbstructData(){
        String senderName = "mock name";
        String recipientName = "mock recipient";
        Integer mockAge = 9;
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, recipientName, mockAge);
        return birthdayGreetingCardData;
    }

    private GreetingCardViewModel createMockBirthDayViewModel(){
        GreetingCardViewModel ret = new GreetingCardViewModel(mockSenderName, mockRecipientName,mockTemplateType);
        ret.setRecipientAge(mockRecipientAge);

        return ret;
    }

    private GreetingCardViewModel createMockGetWellViewModel(){
        GreetingCardViewModel ret = new GreetingCardViewModel(mockSenderName, mockRecipientName, GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE.name());

        return ret;
    }


}
