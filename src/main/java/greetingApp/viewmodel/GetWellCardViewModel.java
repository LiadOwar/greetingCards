package greetingApp.viewmodel;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liad on 06/11/2018.
 */
public class GetWellCardViewModel extends AbstractTemplateGreetingCardViewModel {
    private static GreetingCardTemplateTypeEnum templateType = GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE;

    public GetWellCardViewModel(String senderName, String recipientName) {
        super(senderName, recipientName, templateType.name());
    }

    public GetWellCardViewModel() {
        super(templateType.name());
    }
}
