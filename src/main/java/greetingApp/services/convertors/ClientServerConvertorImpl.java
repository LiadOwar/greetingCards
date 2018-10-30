package greetingApp.services.convertors;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.greetingCardData.GetWellGreetingCardData;
import greetingApp.services.GreetingCardTemplateTypeEnum;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.springframework.stereotype.Component;

/**
 * Created by liadm on 28/10/2018.
 */
@Component
public class ClientServerConvertorImpl implements ClientServerConvertor {
    @Override
    public AbstractGreetingCardData convertGreetingCardViewModel(GreetingCardViewModel view) {
        AbstractGreetingCardData ret = null;
        String viewRecipientName = view.getRecipientName();
        String viewTemplateType = view.getTemplateType();
        String viewSenderName = view.getSenderName();

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
        return ret;

    }

    @Override
    public GreetingCardViewModel convertGreetingCardData(AbstractGreetingCardData cardData) {
        GreetingCardViewModel ret = null;
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
        return ret;
    }
}
