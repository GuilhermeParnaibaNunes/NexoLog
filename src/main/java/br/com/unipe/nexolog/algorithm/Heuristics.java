package br.com.unipe.nexolog.algorithm;

import br.com.unipe.nexolog.graph.Vertex;

public final class Heuristics {
    private Heuristics() {}

    public static int manhattan(Vertex current, Vertex target) {
        return Math.abs(current.getX() - target.getX()) +
                Math.abs(current.getY() - target.getY());
    }
}