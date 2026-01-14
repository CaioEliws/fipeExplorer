package com.caio.fipeExplorer.example01.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Vehicles (@JsonAlias("Valor") String valor,
                        @JsonAlias("Marca") String marca,
                        @JsonAlias("Modelo") String modelo,
                        @JsonAlias("AnoModelo") Integer ano,
                        @JsonAlias("Combustivel") String tipoCombustivel) {
    @Override
    public String toString() {
        return String.format("%s %s  ano: %s valor: %s combustível: %s",
                marca, modelo, ano, valor, tipoCombustivel);
    }
}
