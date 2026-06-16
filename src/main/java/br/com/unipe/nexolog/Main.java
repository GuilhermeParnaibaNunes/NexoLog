package br.com.unipe.nexolog;

//import br.com.unipe.nexolog.model.WarehouseMap;
//import br.com.unipe.nexolog.factory.WarehouseMapFactory;
import br.com.unipe.nexolog.model.RouteResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RouteResult result = new RouteResult(
                "Algorítimo de teste",
                List.of(
                        "RECEBIMENTO",
                        "CORREDOR_A",
                        "EMBALAGEM",
                        "EXPEDICAO"
                ),
                10,
                7,
                12000
        );

        System.out.println(result);
    }
}