package greetingApp.viewmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import greetingApp.services.GreetingCardTemplateTypeEnum;

/**
 * Created by liad on 06/11/2018.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "templateType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BirthDayCardViewModel.class, name = "BIRTH_DAY_TEMPLATE"),
        @JsonSubTypes.Type(value = GetWellCardViewModel.class, name = "GET_WELL_SOON_TEMPLATE"),
        @JsonSubTypes.Type(value = NoTemplateCardViewModel.class, name = "NA")
})
public abstract class AbstractGreetingCardViewModel {
    protected String uUid;
    protected String templateType;

    public AbstractGreetingCardViewModel(String templateType) {
        this.templateType = templateType;
    }

    public AbstractGreetingCardViewModel() {
    }

    public String getuUid() {
        return uUid;
    }

    public void setuUid(String uUid) {
        this.uUid = uUid;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
