package br.com.unipe.nexolog;

import br.com.unipe.nexolog.algorithm.AStarAlgorithm;
import br.com.unipe.nexolog.algorithm.DijkstraAlgorithm;
import br.com.unipe.nexolog.algorithm.PathFindingAlgorithm;
import br.com.unipe.nexolog.factory.WarehouseMapFactory;
import br.com.unipe.nexolog.model.RouteResult;
import br.com.unipe.nexolog.model.WarehouseMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static final int TEST_RUNS = 1000;

    public static void main(String[] args) {

        executeBenchmark(
                "Centro de Distribuição Pequeno",
                WarehouseMapFactory.createSmallWarehouse()
        );

        executeBenchmark(
                "Centro de Distribuição Médio",
                WarehouseMapFactory.createMediumWarehouse()
        );

        executeBenchmark(
                "Centro de Distribuição Grande",
                WarehouseMapFactory.createLargeWarehouse()
        );

        executeBenchmark(
                "Centro de Distribuição Mega",
                WarehouseMapFactory.createMegaWarehouse()
        );

        executeBenchmark(
                "Armazém Ultra Grid",
                WarehouseMapFactory.createUltraGridWarehouse()
        );
    }

    private static void executeBenchmark(
            String mapName,
            WarehouseMap warehouse
    ) {

        PathFindingAlgorithm dijkstra = new DijkstraAlgorithm();
        PathFindingAlgorithm aStar = new AStarAlgorithm();

        // Warm-up da JVM/JIT
        for(int i = 0; i < 1000; i++) {

            dijkstra.findPath(
                    warehouse.getGraph(),
                    "RECEBIMENTO",
                    "EXPEDICAO"
            );

            aStar.findPath(
                    warehouse.getGraph(),
                    "RECEBIMENTO",
                    "EXPEDICAO"
            );
        }

        long dijkstraTimeSum = 0;
        long aStarTimeSum = 0;

        int dijkstraVisitedSum = 0;
        int aStarVisitedSum = 0;

        RouteResult lastDijkstra = null;
        RouteResult lastAStar = null;

        for(int i = 0; i < TEST_RUNS; i++) {

            lastDijkstra = dijkstra.findPath(
                    warehouse.getGraph(),
                    "RECEBIMENTO",
                    "EXPEDICAO"
            );

            lastAStar = aStar.findPath(
                    warehouse.getGraph(),
                    "RECEBIMENTO",
                    "EXPEDICAO"
            );

            dijkstraTimeSum += lastDijkstra.getExecutionTimeNs();
            aStarTimeSum += lastAStar.getExecutionTimeNs();

            dijkstraVisitedSum += lastDijkstra.getVisitedVerticesCount();
            aStarVisitedSum += lastAStar.getVisitedVerticesCount();
        }

        long dijkstraAverageTime = dijkstraTimeSum / TEST_RUNS;
        long aStarAverageTime = aStarTimeSum / TEST_RUNS;

        double timeSaving =
                ((double)(dijkstraAverageTime - aStarAverageTime)
                        / dijkstraAverageTime) * 100.0;

        double visitedSaving =
                ((double)(dijkstraVisitedSum - aStarVisitedSum)
                        / dijkstraVisitedSum) * 100.0;

        System.out.println("\n==================================================");
        System.out.println(mapName);
        System.out.println("==================================================");

        System.out.println("\nDIJKSTRA");
        System.out.println(lastDijkstra);

        System.out.println("\nA*");
        System.out.println(lastAStar);

        System.out.println("\nMÉDIAS (" + TEST_RUNS + " EXECUÇÕES)");
        System.out.println("------------------------------------");

        System.out.printf(
                "Tempo médio Dijkstra: %,d ns%n",
                dijkstraAverageTime
        );

        System.out.printf(
                "Tempo médio A*: %,d ns%n",
                aStarAverageTime
        );

        System.out.printf(
                "Economia de tempo: %.2f%%%n",
                timeSaving
        );

        System.out.printf(
                "Economia de vértices visitados: %.2f%%%n",
                visitedSaving
        );
    }
}