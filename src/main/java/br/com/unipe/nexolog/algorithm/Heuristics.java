package br.com.unipe.nexolog.algorithm;

import br.com.unipe.nexolog.graph.Vertex;

public final class Heuristics {
    private Heuristics() {}

    public static int manhattan(Vertex current, Vertex target) {
        int manhattan = Math.abs(current.getX() - target.getX()) +
                Math.abs(current.getY() - target.getY());

        return manhattan/3; //Uma heurística mal calibrada torna o A* mais rápido, porém pode produzir soluções subótimas.
    }
}