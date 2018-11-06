package greetingApp.services;

import greetingApp.controllers.GreetingCardsController;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.greetingCardData.GetWellGreetingCardData;
import greetingApp.greetingCardData.NoTemplateCardData;
import greetingApp.services.DataStorageConnection.DataStorageConnection;
import greetingApp.services.convertors.ClientServerConvertor;
import greetingApp.viewmodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    private ClientServerConvertor clientServerConvertor;

    @Autowired
    private DataStorageConnection dataStorageConnection;

    @Autowired
    private GreetingCardsController greetingCardsController;



    @Test
    public void getCatalogTest(){
        List<GreetingCardTemplate> templates = greetingCardService.getTemplates();
        assert (templates.get(0).getType() == (GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE));
        assert (templates.get(1).getType() == (GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE));
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
        AbstractGreetingCardViewModel abstractGreetingCardViewModel = createMockBirthDayViewModel();
        BirthDayCardViewModel birthDayCardViewModel = (BirthDayCardViewModel)abstractGreetingCardViewModel;
        AbstractGreetingCardData  abstractGreetingCardData = clientServerConvertor.convertGreetingCardViewModel(abstractGreetingCardViewModel);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData) abstractGreetingCardData;

        assert birthDayCardViewModel.getTemplateType().equals(birthdayGreetingCardData.getTemplateType());
        assert birthDayCardViewModel.getRecipientName().equals(birthdayGreetingCardData.getRecipientName());
        assert birthDayCardViewModel.getSenderName().equals(birthdayGreetingCardData.getSenderName());
        assert birthDayCardViewModel.getRecipientAge() == (birthdayGreetingCardData.getRecipientAge());
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
    public void postNoTemplateCard_Test(){
        Boolean isSuccess = postNoTemplateCard();
        assert isSuccess;
    }

    @Test
    public void get1SavedBirthdayCardFromStorage_Test(){
        assert dataStorageConnection.clearDataStorage();
        assert postBirthDayCard();
        List<AbstractGreetingCardData> savedCards = greetingCardService.getSavedCards();
        assert savedCards.size() == 1;
        AbstractGreetingCardData abstractTemplateGreetingCardData = savedCards.get(0);
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData) abstractTemplateGreetingCardData;
        assert birthdayGreetingCardData.getRecipientAge() == mockRecipientAge;
    }

    @Test
    public void post2BirthDayCards_Test(){
        assert dataStorageConnection.clearDataStorage();
        assert postBirthDayCard();

        BirthDayCardViewModel birthDayCardViewModel = (BirthDayCardViewModel)createMockBirthDayViewModel();
        BirthdayGreetingCardData  birthdayGreetingCardData2 = (BirthdayGreetingCardData)clientServerConvertor.convertGreetingCardViewModel(birthDayCardViewModel);
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
        BirthDayCardViewModel mockBirthDayViewModel = (BirthDayCardViewModel)createMockBirthDayViewModel();
        BirthdayGreetingCardData greetingCardData = (BirthdayGreetingCardData)clientServerConvertor.convertGreetingCardViewModel(mockBirthDayViewModel);
        assert greetingCardData.getuUid() != null;
    }

    @Test
    public void assignGreetingCardViewModelUUidFromData(){
        AbstractGreetingCardViewModel mockBirthDayViewModel = createMockBirthDayViewModel();
        AbstractGreetingCardData greetingCardData = clientServerConvertor.convertGreetingCardViewModel(mockBirthDayViewModel);
        String dataUUid = greetingCardData.getuUid();
        AbstractGreetingCardViewModel convertedAbstractGreetingCardViewModel = clientServerConvertor.convertGreetingCardData(greetingCardData);
        String convertedGreetingCardViewModelUUid = convertedAbstractGreetingCardViewModel.getuUid();
        assert convertedGreetingCardViewModelUUid.equals(dataUUid);
    }

    @Test
    public void differentGreetingCardViewModelUUid_test(){
        BirthDayCardViewModel mockBirthDayViewModel = (BirthDayCardViewModel)createMockBirthDayViewModel();
        AbstractTemplateGreetingCardViewModel mockGetWellViewModel = createMockGetWellViewModel();
        try {
            BirthDayCardViewModel responseCardViewModel1 = (BirthDayCardViewModel)greetingCardsController.createCard(mockBirthDayViewModel);
            GetWellCardViewModel responseCardViewModel2 = (GetWellCardViewModel)greetingCardsController.createCard(mockGetWellViewModel);

            String responseCardViewModel1UUid = responseCardViewModel1.getuUid();
            String responseCardViewModel2UUid = responseCardViewModel2.getuUid();

            assert !responseCardViewModel1UUid.equals(responseCardViewModel2UUid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSavedGreetingCardDataFromViewModel_Test(){
        dataStorageConnection.clearDataStorage();
        BirthDayCardViewModel mockBirthDayViewModel = (BirthDayCardViewModel)createMockBirthDayViewModel();
        AbstractTemplateGreetingCardViewModel mockGetWellViewModel = createMockGetWellViewModel();
        try {
            BirthDayCardViewModel responseCardViewModel1 = (BirthDayCardViewModel)greetingCardsController.createCard(mockBirthDayViewModel);
            BirthDayCardViewModel responseCardViewModel2 = (BirthDayCardViewModel)greetingCardsController.createCard(mockGetWellViewModel);

            String responseCardViewModel1UUid = responseCardViewModel1.getuUid();
            String responseCardViewModel2UUid = responseCardViewModel2.getuUid();

            Boolean gotSameUuidForCard1 = false;
            Boolean gotSameUuidForCard2 = false;

            List<AbstractGreetingCardData> savedCards = dataStorageConnection.getSavedCards();
            for (AbstractGreetingCardData greetingCardData : savedCards){
                if (responseCardViewModel1UUid.equals(greetingCardData.getuUid())){
                    gotSameUuidForCard1 = true;
                }
                if (responseCardViewModel2UUid.equals(greetingCardData.getuUid())){
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

        mockGreetingCardAbstructData1.setuUid("mockUUid");
        mockGreetingCardAbstructData2.setuUid("mockUUid");

        assert mockGreetingCardAbstructData1.equals(mockGreetingCardAbstructData2);
    }

    @Test
    public void compareTwoDifferentGreetingCardData_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstractData1 = createMockGreetingCardAbstructData();
        BirthdayGreetingCardData mockGreetingCardAbstractData2 = createMockGreetingCardAbstructData();

        mockGreetingCardAbstractData1.setuUid("mockUUid");
        mockGreetingCardAbstractData2.setuUid("different_mockUUid");

        assert !mockGreetingCardAbstractData1.equals(mockGreetingCardAbstractData2);
    }

    @Test
    public void getAllSavedGreetingCards_Test(){
        dataStorageConnection.clearDataStorage();
        postGetWellCard();
        postBirthDayCard();
        try {
            List<AbstractGreetingCardViewModel> allGreetingCards = greetingCardsController.getAllGreetingCards();
           assert allGreetingCards.size() == 2;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void convertCardDataToViewModel_Test(){
        BirthdayGreetingCardData mockGreetingCardAbstructData = createMockGreetingCardAbstructData();
        BirthDayCardViewModel abstractTemplateGreetingCardViewModel = (BirthDayCardViewModel)clientServerConvertor.convertGreetingCardData(mockGreetingCardAbstructData);
        assert mockGreetingCardAbstructData.getuUid() != null;
        assert abstractTemplateGreetingCardViewModel.getuUid() == (mockGreetingCardAbstructData.getuUid());
        assert abstractTemplateGreetingCardViewModel.getSenderName().equals(mockGreetingCardAbstructData.getSenderName());
        assert abstractTemplateGreetingCardViewModel.getRecipientName().equals(mockGreetingCardAbstructData.getRecipientName());
        assert abstractTemplateGreetingCardViewModel.getTemplateType().equals(mockGreetingCardAbstructData.getTemplateType());
        assert abstractTemplateGreetingCardViewModel.getRecipientAge() == (mockGreetingCardAbstructData.getRecipientAge());
    }

    @Test
    public void convertNoTemplateCardViewModelToDataCard(){
        NoTemplateCardViewModel mockNoTemplateViewModel = (NoTemplateCardViewModel)createMockNoTemplateViewModel();
        NoTemplateCardData greetingCardData = (NoTemplateCardData)clientServerConvertor.convertGreetingCardViewModel(mockNoTemplateViewModel);
        assert mockNoTemplateViewModel.getuUid() != null;
        assert greetingCardData.getuUid() == (mockNoTemplateViewModel.getuUid());
        assert greetingCardData.getFreeText().equals(mockNoTemplateViewModel.getFreeText());
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
//        List<AbstractTemplateGreetingCardData> savedCards = dataStorageConnection.getSavedCards();
//    }



    private Boolean postBirthDayCard() {
        BirthDayCardViewModel mockBirthDayViewModel = (BirthDayCardViewModel)createMockBirthDayViewModel();
        BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)clientServerConvertor.convertGreetingCardViewModel(mockBirthDayViewModel);
        return greetingCardService.postCard(birthdayGreetingCardData);
    }

    private Boolean postGetWellCard() {
        GetWellCardViewModel mockGetWellViewModel = (GetWellCardViewModel)createMockGetWellViewModel();
        GetWellGreetingCardData getWellGreetingCardData = (GetWellGreetingCardData)clientServerConvertor.convertGreetingCardViewModel(mockGetWellViewModel);
        return greetingCardService.postCard(getWellGreetingCardData);
    }

    private Boolean postNoTemplateCard() {
        NoTemplateCardViewModel mockNoTemplateViewModel = (NoTemplateCardViewModel)createMockNoTemplateViewModel();
        NoTemplateCardData noTemplateCardData = (NoTemplateCardData)clientServerConvertor.convertGreetingCardViewModel(mockNoTemplateViewModel);
        return greetingCardService.postCard(noTemplateCardData);
    }

    private BirthdayGreetingCardData createMockGreetingCardAbstructData(){
        String senderName = "mock name";
        String recipientName = "mock recipient";
        Integer mockAge = 9;
        BirthdayGreetingCardData birthdayGreetingCardData = new BirthdayGreetingCardData(senderName, recipientName, mockAge);
        return birthdayGreetingCardData;
    }

    private AbstractGreetingCardViewModel createMockBirthDayViewModel(){
        BirthDayCardViewModel ret = new BirthDayCardViewModel(mockSenderName, mockRecipientName, mockRecipientAge);
        return ret;
    }

    private AbstractGreetingCardViewModel createMockNoTemplateViewModel(){
        NoTemplateCardViewModel ret = new NoTemplateCardViewModel("mock free text");

        return ret;
    }

    private GetWellCardViewModel createMockGetWellViewModel(){
        GetWellCardViewModel ret = new GetWellCardViewModel(mockSenderName, mockRecipientName);

        return ret;
    }


}
