package greetingApp.greetingCardData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import greetingApp.GreetingCardObject.AbstractGreetingCard;

/**
 * Created by liadm on 28/10/2018.
 */

public abstract class AbstractTemplateGreetingCardData extends AbstractGreetingCardData {
    protected String senderName;
    protected String recipientName;

    public AbstractTemplateGreetingCardData(String senderName, String recipientName, String templateType) {
        super(templateType);
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public AbstractTemplateGreetingCardData(String templateType) {
        super(templateType);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractTemplateGreetingCardData that = (AbstractTemplateGreetingCardData) o;

        if (senderName != null ? !senderName.equals(that.senderName) : that.senderName != null) return false;
        return recipientName != null ? recipientName.equals(that.recipientName) : that.recipientName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (recipientName != null ? recipientName.hashCode() : 0);
        return result;
    }
}
