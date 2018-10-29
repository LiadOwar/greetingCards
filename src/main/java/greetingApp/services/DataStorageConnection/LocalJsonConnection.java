package greetingApp.services.DataStorageConnection;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import greetingApp.greetingCardData.AbstractGreetingCardData;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liad on 28/10/2018.
 */
@Component
public class LocalJsonConnection implements DataStorageConnection {

    private static final String dataStorageURI  = "localStorage.json";

    @Override
    public Boolean postGreetingCardToDataStorage(AbstractGreetingCardData abstractGreetingCardData) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        List<AbstractGreetingCardData> savedCards = new ArrayList<>();

        try {
            File file = new File(dataStorageURI);
            FileWriter fileWriter = new FileWriter(file, true);
            SequenceWriter seqWriter = mapper.writer().writeValuesAsArray(fileWriter);
            savedCards = getCardDataListFromStorage(mapper, file);
            clearDataStorage();
            savedCards.add(abstractGreetingCardData);
            for (AbstractGreetingCardData cardData : savedCards){
                seqWriter.write(cardData);
            }
            seqWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Object o = mapper.readValue(new File(dataStorageURI), savedCards.getClass());
            if (o != null){
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean clearDataStorage() {
        Boolean ret = false;
        File localStorageDataFile = new File(dataStorageURI);
        try {
            PrintWriter writer = new PrintWriter(localStorageDataFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            List<String> allLines = Files.readAllLines(Paths.get(dataStorageURI));
            if (allLines.isEmpty()){
                ret = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<AbstractGreetingCardData> getSavedCards() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(dataStorageURI);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        List<AbstractGreetingCardData> ret = getCardDataListFromStorage(mapper, file);
        return ret;
    }

    private List<AbstractGreetingCardData> getCardDataListFromStorage(ObjectMapper mapper, File file){

        List<AbstractGreetingCardData> savedCards = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(dataStorageURI));
            if (!allLines.isEmpty()){
                JsonNode jsonNode = mapper.readTree(file);
                for (int i =0; i < jsonNode.size(); i ++){
                    JsonNode node = jsonNode.get(i);
                    AbstractGreetingCardData cardData = mapper.treeToValue(node, AbstractGreetingCardData.class);
                    savedCards.add(cardData);
                    System.out.println(node.toString());
                }
            }
    } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savedCards;
    }
}
