package com.caio.fipeExplorer.example01.services;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConverter implements IDataConverter {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classe) {
        return mapper.readValue(json, classe);
    }

    @Override
    public <T> List<T> getDataList(String json, Class<T> classe) {
        CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        return mapper.readValue(json, collectionType);
    }
}
