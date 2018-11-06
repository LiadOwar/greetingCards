package greetingApp.greetingCardData;

import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liadm on 29/10/2018.
 */
public class GetWellGreetingCardData extends AbstractTemplateGreetingCardData {
    private static String templateType = GreetingCardTemplateTypeEnum.GET_WELL_SOON_TEMPLATE.name();
    public GetWellGreetingCardData(String senderName, String recipientName) {
        super(senderName, recipientName,  templateType);
    }

    public GetWellGreetingCardData() {
        super(templateType);
    }
}
