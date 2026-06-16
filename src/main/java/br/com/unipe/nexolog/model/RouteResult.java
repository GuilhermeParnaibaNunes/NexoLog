package br.com.unipe.nexolog.model;

import java.util.List;

public class RouteResult {
    private final String algorithmName;
    private final List<String> path;
    private final int totalCost;
    private final int visitedVerticesCount;
    private final long executionTimeNs;

    public RouteResult(
            String algorithmName,
            List<String> path,
            int totalCost,
            int visitedVerticesCount,
            long executionTimeNs
    ) {
        this.algorithmName = algorithmName;
        this.path = path;
        this.totalCost = totalCost;
        this.visitedVerticesCount = visitedVerticesCount;
        this.executionTimeNs = executionTimeNs;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getVisitedVerticesCount() {
        return visitedVerticesCount;
    }

    public long getExecutionTimeNs() {
        return executionTimeNs;
    }

    @Override
    public String toString() {
        return """
                {
                Algorítimo: %s
                Caminho: %s
                Custo: %d
                Vértices visitados: %d
                Tempo de execução (ns): %d
                }
                """
                .formatted(
                        algorithmName,
                        path,
                        totalCost,
                        visitedVerticesCount,
                        executionTimeNs
                );
    }
}