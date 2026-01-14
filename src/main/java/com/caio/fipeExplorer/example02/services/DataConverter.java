package com.caio.fipeExplorer.example02.services;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConverter implements IDataConverter {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @Override
    public <T> List<T> getDataList(String json, Class<T> clazz) {
        CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);

        return mapper.readValue(json, collectionType);
    }
}
