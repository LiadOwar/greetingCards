package greetingApp.viewmodel;

/**
 * Created by liadm on 28/10/2018.
 */

public class AbstractTemplateGreetingCardViewModel extends AbstractGreetingCardViewModel {

    protected String senderName;
    protected String recipientName;


    public AbstractTemplateGreetingCardViewModel(String senderName, String recipientName, String templateType) {
        super(templateType);
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public AbstractTemplateGreetingCardViewModel(String templateType) {
        super(templateType);
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

}
