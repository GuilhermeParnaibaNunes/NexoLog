package br.com.unipe.nexolog.algorithm;

import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.model.RouteResult;

public interface PathFindingAlgorithm {
    String getName();

    RouteResult findPath(
            Graph graph,
            String sourceVertexName,
            String targetVertexName
    );
}