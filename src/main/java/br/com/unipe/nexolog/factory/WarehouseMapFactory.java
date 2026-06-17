package br.com.unipe.nexolog.factory;

import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.model.WarehouseMap;

public final class WarehouseMapFactory {
    private WarehouseMapFactory() {
        // Classe utilitária
    }

    public static WarehouseMap createSmallWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertex("RECEBIMENTO", 0, 0);
        graph.addVertex("ARMAZENAMENTO_A", 2, 1);
        graph.addVertex("ARMAZENAMENTO_B", 2, -1);
        graph.addVertex("SEPARACAO", 5, 0);
        graph.addVertex("EXPEDICAO", 8, 0);

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

        graph.addVertex("RECEBIMENTO", 0, 0);
        graph.addVertex("CORREDOR_A", 2, 2);
        graph.addVertex("CORREDOR_B", 2, -2);
        graph.addVertex("CORREDOR_C", 4, 0);
        graph.addVertex("ARMAZENAMENTO_A", 4, 4);
        graph.addVertex("ARMAZENAMENTO_B", 4, -4);
        graph.addVertex("EMBALAGEM", 7, 0);
        graph.addVertex("EXPEDICAO", 10, 0);

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

        graph.addVertex("RECEBIMENTO", 0, 0);

        graph.addVertex("CORREDOR_A", 2, 3);
        graph.addVertex("CORREDOR_B", 2, -3);
        graph.addVertex("CORREDOR_C", 4, 3);
        graph.addVertex("CORREDOR_D", 4, -3);

        graph.addVertex("ARMAZENAMENTO_A", 4, 6);
        graph.addVertex("ARMAZENAMENTO_B", 4, -6);
        graph.addVertex("ARMAZENAMENTO_C", 6, 6);

        graph.addVertex("SEPARACAO_A", 8, 3);
        graph.addVertex("SEPARACAO_B", 8, -3);

        graph.addVertex("EMBALAGEM", 11, 0);
        graph.addVertex("EXPEDICAO", 14, 0);

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