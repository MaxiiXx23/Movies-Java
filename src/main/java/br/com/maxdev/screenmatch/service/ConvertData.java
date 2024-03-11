package br.com.maxdev.screenmatch.service;

import br.com.maxdev.screenmatch.interfaces.IConvertDataJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertDataJson {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> data) {
        try {
            return mapper.readValue(json, data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
