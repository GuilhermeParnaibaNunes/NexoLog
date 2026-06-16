package br.com.unipe.nexolog.graph;

import java.util.Objects;

public class Edge {
    private final String name;
    private final Vertex sourceVertex;
    private final Vertex targetVertex;
    private final Integer weight;

    public Edge(String name, Vertex sourceVertex, Vertex targetVertex, Integer weight) {
        this.name = name;
        this.sourceVertex = Objects.requireNonNull(sourceVertex);
        this.targetVertex = Objects.requireNonNull(targetVertex);
        this.weight = weight;
    }

    public Edge(String name, Vertex sourceVertex, Vertex targetVertex) {
        this(name, sourceVertex, targetVertex, null);
    }

    public Edge(Vertex sourceVertex, Vertex targetVertex, Integer weight) {
        this(null, sourceVertex, targetVertex, weight);
    }

    public Edge(Vertex sourceVertex, Vertex targetVertex) {
        this(null, sourceVertex, targetVertex, null);
    }

    /**GETTERS:*/

    public String getName() {
        return name;
    }

    public Vertex getSourceVertex() { return sourceVertex; }

    public Vertex getTargetVertex() { return targetVertex; }

    public Integer getWeight() { return weight; }

    @Override
    public String toString() {
        return "[%s] %s -> %s (%s)"
                .formatted(
                        getName() != null ? getName() : "",
                        sourceVertex.getName(),
                        targetVertex.getName(),
                        weight != null ? weight : "-"
                );
    }
}