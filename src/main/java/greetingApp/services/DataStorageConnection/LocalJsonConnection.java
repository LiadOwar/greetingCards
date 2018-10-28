package greetingApp.services.DataStorageConnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import greetingApp.GreetingCardObject.AbstractGreetingCard;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by liad on 28/10/2018.
 */
@Component
public class LocalJsonConnection implements DataStorageConnection {


    @Override
    public Boolean postToDataStorage(String quarry) {
        ObjectMapper mapper = new ObjectMapper();

        Iterator it = AbstractGreetingCard.class.getChildren().iterator();
        Class<? extends AbstractGreetingCard> aClass = AbstractGreetingCardData.class.s;

        try {
            mapper.writeValue(new File("localStorage.json"), quarry);

            mapper.registerSubtypes(aClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            AbstractGreetingCardData greetingCardData = mapper.readValue(new File("localStorage.json"), AbstractGreetingCardData.class);
            if (greetingCardData != null){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
