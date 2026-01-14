package com.caio.fipeExplorer.example01.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Models (List<Data>modelos,
                      List<Data> anos) {
}
