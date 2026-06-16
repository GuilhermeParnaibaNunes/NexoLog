package br.com.unipe.nexolog;

import br.com.unipe.nexolog.model.WarehouseMap;
import br.com.unipe.nexolog.factory.WarehouseMapFactory;
import br.com.unipe.nexolog.model.RouteResult;
import br.com.unipe.nexolog.algorithm.PathFindingAlgorithm;
import br.com.unipe.nexolog.algorithm.DijkstraAlgorithm;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WarehouseMap warehouse = WarehouseMapFactory.createSmallWarehouse();

        PathFindingAlgorithm algorithm = new DijkstraAlgorithm();

        RouteResult result = algorithm.findPath(
                warehouse.getGraph(),
                "RECEBIMENTO",
                "EXPEDICAO");

        System.out.println(result);
    }
}