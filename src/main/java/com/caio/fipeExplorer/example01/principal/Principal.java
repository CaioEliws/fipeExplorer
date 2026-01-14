package com.caio.fipeExplorer.example01.principal;

import com.caio.fipeExplorer.example01.models.*;
import com.caio.fipeExplorer.example01.services.ConsumeAPI;
import com.caio.fipeExplorer.example01.services.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final ConsumeAPI api = new ConsumeAPI();
    private final DataConverter converter = new DataConverter();
    private final Scanner input = new Scanner(System.in);

    private static final String BASE_URL =
            "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {

        System.out.println("""
                *** OPÇÕES ***
                Carros
                Motos
                Caminhoes
                """);

        System.out.print("Digite a opção: ");
        String vehicle = input.nextLine().toLowerCase();

        String jsonBrands = api.getData(BASE_URL + vehicle + "/marcas");
        List<Data> brands = converter.getDataList(jsonBrands, Data.class);
        brands.forEach(System.out::println);

        System.out.print("\nDigite o código da marca: ");
        String brandCode = input.nextLine();

        String jsonModels = api.getData(
                BASE_URL + vehicle + "/marcas/" + brandCode + "/modelos"
        );

        Models models = converter.getData(jsonModels, Models.class);

        models.modelos().forEach(System.out::println);

        System.out.print("\nDigite um trecho do nome do modelo: ");
        String filter = input.nextLine().toLowerCase();

        List<Data> filteredModels = models.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(filter))
                .collect(Collectors.toList());

        filteredModels.forEach(System.out::println);

        System.out.print("\nDigite o código do modelo: ");
        String modelCode = input.nextLine();

        // 🔹 ANOS
        String jsonYears = api.getData(
                BASE_URL + vehicle + "/marcas/" + brandCode
                        + "/modelos/" + modelCode
                        + "/anos"
        );

        List<Data> years = converter.getDataList(jsonYears, Data.class);

        List<Vehicles> vehicles = new ArrayList<>();

        for (Data year : years) {
            String url = BASE_URL + vehicle
                    + "/marcas/" + brandCode
                    + "/modelos/" + modelCode
                    + "/anos/" + year.codigo();

            String json = api.getData(url);
            Vehicles v = converter.getData(json, Vehicles.class);
            vehicles.add(v);
        }

        System.out.println("\nValores FIPE:");
        vehicles.forEach(System.out::println);

    }
}
