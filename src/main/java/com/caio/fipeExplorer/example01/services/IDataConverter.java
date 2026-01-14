package com.caio.fipeExplorer.example01.services;

import java.util.List;

public interface IDataConverter {
    <T> T getData(String json, Class<T> classe);

    <T> List<T> getDataList(String json, Class<T> classe);
}
