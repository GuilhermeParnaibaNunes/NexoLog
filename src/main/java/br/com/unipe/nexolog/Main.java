package br.com.unipe.nexolog;

import br.com.unipe.nexolog.model.WarehouseMap;
import br.com.unipe.nexolog.factory.WarehouseMapFactory;

public class Main {

    public static void main(String[] args) {
        WarehouseMap warehouse = WarehouseMapFactory.createSmallWarehouse();

        System.out.println("\n====================");
        System.out.println(warehouse.getName());
        System.out.println("====================");
        System.out.println(warehouse.getGraph());

        warehouse = WarehouseMapFactory.createMediumWarehouse();
        System.out.println("\n====================");
        System.out.println(warehouse.getName());
        System.out.println("====================");
        System.out.println(warehouse.getGraph());

        warehouse = WarehouseMapFactory.createLargeWarehouse();
        System.out.println("\n====================");
        System.out.println(warehouse.getName());
        System.out.println("====================");
        System.out.println(warehouse.getGraph());

    }
}