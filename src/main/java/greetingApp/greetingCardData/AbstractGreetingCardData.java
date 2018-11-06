package greetingApp.greetingCardData;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by liad on 06/11/2018.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BirthdayGreetingCardData.class, name = "birthdayGreetingCardData"),
        @JsonSubTypes.Type(value = GetWellGreetingCardData.class, name = "getWellGreetingCardData"),
        @JsonSubTypes.Type(value = NoTemplateCardData.class, name = "noTemplateCardData")
})
public abstract class AbstractGreetingCardData {
    protected String uUid;
    protected String templateType;

    public AbstractGreetingCardData(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getuUid() {
        return uUid;
    }

    public void setuUid(String uUid) {
        this.uUid = uUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGreetingCardData that = (AbstractGreetingCardData) o;

        if (uUid != null ? !uUid.equals(that.uUid) : that.uUid != null) return false;
        return templateType != null ? templateType.equals(that.templateType) : that.templateType == null;
    }

    @Override
    public int hashCode() {
        int result = uUid != null ? uUid.hashCode() : 0;
        result = 31 * result + (templateType != null ? templateType.hashCode() : 0);
        return result;
    }
}
