package br.com.unipe.nexolog.factory;

import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.model.WarehouseMap;

public final class WarehouseMapFactory {
    private WarehouseMapFactory() {
        // Classe utilitária
    }

    public static WarehouseMap createSmallWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertices(
                "RECEBIMENTO",
                "ARMAZENAMENTO_A",
                "ARMAZENAMENTO_B",
                "SEPARACAO",
                "EXPEDICAO"
        );

        graph.addEdge("R-A", "RECEBIMENTO", "ARMAZENAMENTO_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "ARMAZENAMENTO_B", 4);

        graph.addEdge("A-S", "ARMAZENAMENTO_A", "SEPARACAO", 3);
        graph.addEdge("B-S", "ARMAZENAMENTO_B", "SEPARACAO", 1);

        graph.addEdge("S-E", "SEPARACAO", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Pequeno",
                graph
        );
    }

    public static WarehouseMap createMediumWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertices(
                "RECEBIMENTO",
                "CORREDOR_A",
                "CORREDOR_B",
                "CORREDOR_C",
                "ARMAZENAMENTO_A",
                "ARMAZENAMENTO_B",
                "EMBALAGEM",
                "EXPEDICAO"
        );

        graph.addEdge("R-A", "RECEBIMENTO", "CORREDOR_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "CORREDOR_B", 5);

        graph.addEdge("A-AA", "CORREDOR_A", "ARMAZENAMENTO_A", 3);
        graph.addEdge("A-C", "CORREDOR_A", "CORREDOR_C", 2);

        graph.addEdge("B-AB", "CORREDOR_B", "ARMAZENAMENTO_B", 2);
        graph.addEdge("B-C", "CORREDOR_B", "CORREDOR_C", 1);

        graph.addEdge("AA-EMB", "ARMAZENAMENTO_A", "EMBALAGEM", 4);
        graph.addEdge("AB-EMB", "ARMAZENAMENTO_B", "EMBALAGEM", 2);

        graph.addEdge("C-EMB", "CORREDOR_C", "EMBALAGEM", 3);

        graph.addEdge("EMB-E", "EMBALAGEM", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Médio",
                graph
        );
    }

    public static WarehouseMap createLargeWarehouse() {

        Graph graph = new Graph(false, true);

        graph.addVertices(
                "RECEBIMENTO",

                "CORREDOR_A",
                "CORREDOR_B",
                "CORREDOR_C",
                "CORREDOR_D",

                "ARMAZENAMENTO_A",
                "ARMAZENAMENTO_B",
                "ARMAZENAMENTO_C",

                "SEPARACAO_A",
                "SEPARACAO_B",

                "EMBALAGEM",

                "EXPEDICAO"
        );

        graph.addEdge("R-A", "RECEBIMENTO", "CORREDOR_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "CORREDOR_B", 5);

        graph.addEdge("A-AA", "CORREDOR_A", "ARMAZENAMENTO_A", 2);
        graph.addEdge("A-C", "CORREDOR_A", "CORREDOR_C", 3);

        graph.addEdge("B-AB", "CORREDOR_B", "ARMAZENAMENTO_B", 2);
        graph.addEdge("B-D", "CORREDOR_B", "CORREDOR_D", 4);

        graph.addEdge("C-AC", "CORREDOR_C", "ARMAZENAMENTO_C", 2);
        graph.addEdge("C-SA", "CORREDOR_C", "SEPARACAO_A", 5);

        graph.addEdge("D-SB", "CORREDOR_D", "SEPARACAO_B", 3);

        graph.addEdge("AA-SA", "ARMAZENAMENTO_A", "SEPARACAO_A", 3);
        graph.addEdge("AB-SB", "ARMAZENAMENTO_B", "SEPARACAO_B", 2);
        graph.addEdge("AC-SA", "ARMAZENAMENTO_C", "SEPARACAO_A", 1);

        graph.addEdge("SA-EMB", "SEPARACAO_A", "EMBALAGEM", 2);
        graph.addEdge("SB-EMB", "SEPARACAO_B", "EMBALAGEM", 4);

        graph.addEdge("EMB-E", "EMBALAGEM", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Grande",
                graph
        );
    }
}