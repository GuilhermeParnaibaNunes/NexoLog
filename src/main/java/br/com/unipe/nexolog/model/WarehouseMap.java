package br.com.unipe.nexolog.model;

import br.com.unipe.nexolog.graph.Graph;

public class WarehouseMap {
    private final String name;
    private final Graph graph;

    public WarehouseMap(String name, Graph graph) {
        this.name = name;
        this.graph = graph;
    }

    public String getName() {
        return name;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return name;
    }
}