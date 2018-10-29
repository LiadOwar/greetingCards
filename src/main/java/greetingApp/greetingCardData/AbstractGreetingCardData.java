package greetingApp.greetingCardData;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by liadm on 28/10/2018.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BirthdayGreetingCardData.class, name = "birthdayGreetingCardData")
})
public abstract class AbstractGreetingCardData {
    protected String senderName;
    protected String recipientName;
    protected String templateType;

    public AbstractGreetingCardData(String senderName, String recipientName) {
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public AbstractGreetingCardData() {
    }

    public String getSenderName(){
        return senderName;
    }

    public String getRecipientName(){
        return recipientName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTemplateType() {
        return templateType;
    }
}
