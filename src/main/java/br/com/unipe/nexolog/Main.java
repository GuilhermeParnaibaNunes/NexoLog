package br.com.unipe.nexolog;

import br.com.unipe.nexolog.algorithm.AStarAlgorithm;
import br.com.unipe.nexolog.model.WarehouseMap;
import br.com.unipe.nexolog.factory.WarehouseMapFactory;
import br.com.unipe.nexolog.model.RouteResult;
import br.com.unipe.nexolog.algorithm.PathFindingAlgorithm;
import br.com.unipe.nexolog.algorithm.DijkstraAlgorithm;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WarehouseMap warehouse = WarehouseMapFactory.createLargeWarehouse();

        PathFindingAlgorithm dijkstra = new DijkstraAlgorithm();

        PathFindingAlgorithm aStar = new AStarAlgorithm();

        System.out.println(
                dijkstra.findPath(
                        warehouse.getGraph(),
                        "RECEBIMENTO",
                        "EXPEDICAO"
                )
        );

        System.out.println();

        System.out.println(
                aStar.findPath(
                        warehouse.getGraph(),
                        "RECEBIMENTO",
                        "EXPEDICAO"
                )
        );
    }
}