package greetingApp.viewmodel;

/**
 * Created by liadm on 28/10/2018.
 */
public class GreetingCardViewModel {
    private String uUid;
    private String senderName;
    private Integer recipientAge;
    private String recipientName;
    private String templateType;

    public GreetingCardViewModel(String senderName, String recipientName, String templateType) {
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.templateType = templateType;
    }

    public GreetingCardViewModel() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getRecipientAge() {
        return recipientAge;
    }

    public void setRecipientAge(Integer recipientAge) {
        this.recipientAge = recipientAge;
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

    public String getUUid() {
        return uUid;
    }

    public void setUUid(String uUid) {
        this.uUid = uUid;
    }
}
