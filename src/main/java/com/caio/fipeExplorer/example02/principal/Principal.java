package com.caio.fipeExplorer.example02.principal;

import com.caio.fipeExplorer.example02.models.Data;
import com.caio.fipeExplorer.example02.models.Models;
import com.caio.fipeExplorer.example02.models.Vehicle;
import com.caio.fipeExplorer.example02.services.ConsumeAPI;
import com.caio.fipeExplorer.example02.services.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConverter converter = new DataConverter();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consulta:
                """;
        System.out.println(menu);
        var option = input.nextLine();
        String address;

        if (option.toLowerCase().contains("carr")) {
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("mot")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = consumeAPI.getData(address);
        var brands = converter.getDataList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var brandCode = input.nextLine();

        address = address + "/" + brandCode + "/modelos";
        json = consumeAPI.getData(address);
        var listModel = converter.getData(json, Models.class);

        System.out.println("\nModelos dessa marca: ");
        listModel.modelos().stream()
                .sorted(Comparator.comparing(Data::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado: ");
        var vehicleName = input.nextLine();

        List<Data> filteredModels = listModel.modelos().stream()
                .filter(v -> v.nome().toLowerCase().contains(vehicleName.toLowerCase()))
                .toList();

        System.out.println("\nModelos filtrados");
        filteredModels.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscar os valores de avaliação: ");
        var codeModel = input.nextLine();

        address = address + "/" + codeModel + "/anos";
        json = consumeAPI.getData(address);
        List<Data> years = converter.getDataList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearAddress = address + "/" + years.get(i).codigo();
            json = consumeAPI.getData(yearAddress);
            Vehicle vehicle = converter.getData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veiculos filtrados com avaliações por ano: ");
        vehicles.forEach(System.out::println);
    }
}
