package pl.piwniczneparagrafowki.thewagesofvoid.application.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class JsonConverter {

    String json = null;

    private static JsonConverter instance = null;

    //default
    private JsonConverter(){
    }

    public static JsonConverter getInstance(){
        if(instance == null) {
            instance = new JsonConverter();
        }
        return instance;
    }

    public String toJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
