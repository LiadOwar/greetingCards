package greetingApp.greetingCardData;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        @JsonSubTypes.Type(value = BirthdayGreetingCardData.class, name = "birthdayGreetingCardData"),
        @JsonSubTypes.Type(value = GetWellGreetingCardData.class, name = "getWellGreetingCardData")
})
public abstract class AbstractGreetingCardData {
    protected String uUid;
    protected String senderName;
    protected String recipientName;
    protected String templateType;

    public AbstractGreetingCardData(String senderName, String recipientName) {
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public void setUUid(String uUid) {
        this.uUid = uUid;
    }

    @JsonIgnore
    public String getUUid() {

        return uUid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGreetingCardData that = (AbstractGreetingCardData) o;

        if (!uUid.equals(that.uUid)) return false;
        if (!senderName.equals(that.senderName)) return false;
        if (!recipientName.equals(that.recipientName)) return false;
        return templateType.equals(that.templateType);
    }

    @Override
    public int hashCode() {
        int result = uUid.hashCode();
        result = 31 * result + senderName.hashCode();
        result = 31 * result + recipientName.hashCode();
        result = 31 * result + templateType.hashCode();
        return result;
    }
}
