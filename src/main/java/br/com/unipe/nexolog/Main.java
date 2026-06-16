package br.com.unipe.nexolog;

import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.model.WarehouseMap;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(false, true);

        graph.addVertices(
                "Entrada",
                "A1",
                "A2",
                "B1",
                "Expedicao"
        );

        graph.addEdge("Entrada", "A1", 2);
        graph.addEdge("A1", "A2", 1);
        graph.addEdge("A2", "B1", 3);
        graph.addEdge("B1", "Expedicao", 2);

        WarehouseMap map = new WarehouseMap("Centro de Distribuição João Pessoa", graph);

        System.out.println(map.getName());
        System.out.println(map.getGraph());
    }
}