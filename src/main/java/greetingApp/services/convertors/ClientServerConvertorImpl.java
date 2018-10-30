package greetingApp.services.convertors;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.greetingCardData.GetWellGreetingCardData;
import greetingApp.services.GreetingCardTemplateTypeEnum;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by liadm on 28/10/2018.
 */
@Component
public class ClientServerConvertorImpl implements ClientServerConvertor {


    @Override
    public AbstractGreetingCardData convertGreetingCardViewModel(GreetingCardViewModel view) {
        AbstractGreetingCardData ret = null;
        String viewUUid = view.getUUid();
        String viewRecipientName = view.getRecipientName();
        String viewTemplateType = view.getTemplateType();
        String viewSenderName = view.getSenderName();

        if(viewUUid == null){
            viewUUid = generateUuid();
        }

        GreetingCardTemplateTypeEnum viewTemplateTypeEnum = GreetingCardTemplateTypeEnum.valueOf(viewTemplateType);
        GreetingCardTemplateTypeEnum birthDAyCardTemplateName = GreetingCardTemplateTypeEnum.BIRTH_DAY_TEMPLATE;

        String templateTypeForAbstactGreetingCard;

        switch (viewTemplateTypeEnum){
            case  BIRTH_DAY_TEMPLATE : {
                Integer viewRecipientAge = view.getRecipientAge();
                if (viewRecipientAge == null){
                    break;
                }
                ret = new BirthdayGreetingCardData(viewSenderName, viewRecipientName , viewRecipientAge);
                break;
            }
            case GET_WELL_SOON_TEMPLATE:
                ret = new GetWellGreetingCardData(viewSenderName, viewRecipientName);
                break;
            default: break;
        }
        ret.setUUid(viewUUid);
        return ret;

    }

    @Override
    public GreetingCardViewModel convertGreetingCardData(AbstractGreetingCardData cardData) {
        GreetingCardViewModel ret = null;
        String dataUuid = cardData.getUUid();
        String dataRecipientName = cardData.getRecipientName();
        String dataSenderName = cardData.getSenderName();
        String dataTemplateType = cardData.getTemplateType();

        GreetingCardTemplateTypeEnum dataTemplateTypeEnum = GreetingCardTemplateTypeEnum.valueOf(dataTemplateType);
        switch (dataTemplateTypeEnum){
            case  BIRTH_DAY_TEMPLATE : {
               BirthdayGreetingCardData birthdayGreetingCardData = (BirthdayGreetingCardData)cardData;
                ret = new GreetingCardViewModel(dataSenderName, dataRecipientName , dataTemplateType);
                ret.setRecipientAge(birthdayGreetingCardData.getRecipientAge());
                break;
            }
            case GET_WELL_SOON_TEMPLATE:
                ret = new GreetingCardViewModel(dataSenderName, dataRecipientName, dataTemplateType);
                break;
            default: break;
        }
        ret.setUUid(dataUuid);
        return ret;
    }

    private String generateUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
