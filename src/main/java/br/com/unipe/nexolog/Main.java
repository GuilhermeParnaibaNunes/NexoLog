package br.com.unipe.nexolog;

import br.com.unipe.nexolog.graph.Graph;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(false, true);

        // Vértices
        graph.addVertices(
                "A",
                "B",
                "C",
                "D",
                "E",
                "F"
        );

        // Arestas
        graph.addEdge("AB", "A", "B", 4);
        graph.addEdge("AC", "A", "C", 2);
        graph.addEdge("BC", "B", "C", 1);
        graph.addEdge("BD", "B", "D", 5);
        graph.addEdge("CD", "C", "D", 8);
        graph.addEdge("CE", "C", "E", 10);
        graph.addEdge("DE", "D", "E", 2);
        graph.addEdge("DF", "D", "F", 6);
        graph.addEdge("EF", "E", "F", 3);

        System.out.println("\n====================");
        System.out.println("GRAFO");
        System.out.println("====================");
        System.out.println(graph);

        System.out.println("\n====================");
        System.out.println("MATRIZ DE ADJACÊNCIAS");
        System.out.println("====================");
        graph.displayAdjacencyMatrix();

        System.out.println("\n====================");
        System.out.println("MATRIZ DE INCIDÊNCIA");
        System.out.println("====================");
        graph.displayIncidenceMatrix();

        System.out.println("\n====================");
        System.out.println("DFS ITERATIVO");
        System.out.println("====================");
        System.out.println(
                graph.iterativeDFS("A", "F")
        );

        System.out.println("\n====================");
        System.out.println("DFS RECURSIVO");
        System.out.println("====================");
        System.out.println(
                graph.recursiveDFS(
                        "A",
                        "F",
                        new ArrayList<>()
                )
        );

        System.out.println("\n====================");
        System.out.println("CONEXO?");
        System.out.println("====================");
        System.out.println(
                graph.isConnected()
        );

        System.out.println("\n====================");
        System.out.println("CONEXO SIMPLIFICADO?");
        System.out.println("====================");
        System.out.println(
                graph.isConnectedOptimized()
        );

        System.out.println("\n====================");
        System.out.println("COMPRIMENTO DO CAMINHO");
        System.out.println("====================");

        System.out.println(
                graph.getPathSize(
                        "A",
                        "B",
                        "D",
                        "F"
                )
        );
    }
}