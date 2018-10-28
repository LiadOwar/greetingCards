package greetingApp.services;

import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.greetingCardData.BirthdayGreetingCardData;
import greetingApp.viewmodel.GreetingCardViewModel;
import org.springframework.stereotype.Component;

/**
 * Created by liadm on 28/10/2018.
 */
@Component
public class ViewToModelConvertorImpl implements ViewToModelConvertor {
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
            default: break;
        }
        return ret;

    }
}
