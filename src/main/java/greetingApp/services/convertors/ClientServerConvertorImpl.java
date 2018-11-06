package greetingApp.services.convertors;

import com.fasterxml.jackson.databind.ObjectMapper;
import greetingApp.greetingCardData.*;
import greetingApp.services.GreetingCardTemplateTypeEnum;
import greetingApp.viewmodel.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by liadm on 28/10/2018.
 */
@Component
public class ClientServerConvertorImpl implements ClientServerConvertor {


    @Override
    public AbstractGreetingCardData convertGreetingCardViewModel(AbstractGreetingCardViewModel view) {
        AbstractGreetingCardData ret = null;
        String viewUUid = view.getuUid();
        String viewTemplateType = view.getTemplateType();

        if(viewUUid == null){
            viewUUid = generateUuid();
            view.setuUid(viewUUid);
        }
        GreetingCardTemplateTypeEnum viewTemplateTypeEnum = GreetingCardTemplateTypeEnum.valueOf(viewTemplateType);

        switch (viewTemplateTypeEnum){
            case  BIRTH_DAY_TEMPLATE : {
                BirthDayCardViewModel birthDayCardViewModel = (BirthDayCardViewModel)view;
                String viewRecipientName = birthDayCardViewModel.getRecipientName();
                String viewSenderName = birthDayCardViewModel.getSenderName();
                Integer viewRecipientAge = birthDayCardViewModel.getRecipientAge();
                if (viewRecipientAge == null){
                    break;
                }

                ret = new BirthdayGreetingCardData(viewSenderName, viewRecipientName , viewRecipientAge);
                break;
            }
            case GET_WELL_SOON_TEMPLATE:
                GetWellCardViewModel getWellCardViewModel = (GetWellCardViewModel)view;
                String viewRecipientName = getWellCardViewModel.getRecipientName();
                String viewSenderName = getWellCardViewModel.getSenderName();
                ret = new GetWellGreetingCardData(viewSenderName, viewRecipientName);
                break;
            case NA:
                NoTemplateCardViewModel noTemplateCardViewModel = (NoTemplateCardViewModel)view;
                String viewFreeText = ((NoTemplateCardViewModel) view).getFreeText();
                ret = new NoTemplateCardData(viewFreeText);
                break;
            default: break;
        }
        ret.setuUid(viewUUid);
        return ret;

    }

    @Override
    public AbstractGreetingCardViewModel convertGreetingCardData(AbstractGreetingCardData cardData) {
        AbstractGreetingCardViewModel ret = null;
        String dataUuid = cardData.getuUid();

        String dataTemplateType = cardData.getTemplateType();

        if(dataUuid == null){
            dataUuid = generateUuid();
            cardData.setuUid(dataUuid);
        }

        GreetingCardTemplateTypeEnum dataTemplateTypeEnum = GreetingCardTemplateTypeEnum.valueOf(dataTemplateType);
        switch (dataTemplateTypeEnum){
            case  BIRTH_DAY_TEMPLATE : {
               BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)cardData;
                String dataRecipientName = birthdayGreetingCardData.getRecipientName();
                String dataSenderName = birthdayGreetingCardData.getSenderName();
                Integer dataRecipientAge = birthdayGreetingCardData.getRecipientAge();
                ret = new BirthDayCardViewModel(dataSenderName, dataRecipientName , dataRecipientAge);
                break;
            }
            case GET_WELL_SOON_TEMPLATE:
                GetWellGreetingCardData getWellGreetingCardData = (GetWellGreetingCardData)cardData;
                String dataRecipientName = getWellGreetingCardData.getRecipientName();
                String dataSenderName = getWellGreetingCardData.getSenderName();
                ret = new GetWellCardViewModel(dataSenderName, dataRecipientName);
                break;
            case NA:
                NoTemplateCardData noTemplateCardData = (NoTemplateCardData)cardData;
                String freeText = ((NoTemplateCardData) cardData).getFreeText();
                ret = new NoTemplateCardViewModel(freeText);
                break;
            default: break;
        }
        ret.setuUid(dataUuid);
        return ret;
    }

    private String generateUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
