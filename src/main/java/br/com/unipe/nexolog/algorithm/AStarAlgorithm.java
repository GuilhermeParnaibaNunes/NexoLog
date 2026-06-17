package br.com.unipe.nexolog.algorithm;

import br.com.unipe.nexolog.graph.Edge;
import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.graph.Vertex;
import br.com.unipe.nexolog.model.RouteResult;

import java.util.*;

public class AStarAlgorithm implements PathFindingAlgorithm {
    @Override
    public String getName() {
        return "A*";
    }

    @Override
    public RouteResult findPath(
            Graph graph,
            String sourceVertexName,
            String targetVertexName
    ) {
        long startTime = System.nanoTime();

        Vertex source = graph.findVertex(sourceVertexName).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + sourceVertexName + " não encontrado."));

        Vertex target = graph.findVertex(targetVertexName).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + targetVertexName + " não encontrado."));

        Map<Vertex, Integer> distances = new HashMap<>();

        Map<Vertex, Vertex> previousVertices = new HashMap<>();

        Set<Vertex> visitedVertices = new HashSet<>();

        PriorityQueue<AStarAlgorithm.VertexScore> queue =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                AStarAlgorithm.VertexScore::getScore
                        )
                );

        for(Vertex vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(source, 0);

        int initialHeuristic = Heuristics.manhattan(source, target);

        queue.add(new VertexScore(source, initialHeuristic));

        while(!queue.isEmpty()) {
            AStarAlgorithm.VertexScore currentData = queue.poll();

            Vertex current = currentData.getVertex();

            if(visitedVertices.contains(current)) {
                continue;
            }

            visitedVertices.add(current);

            if(current.equals(target)) {
                break;
            }

            for(Edge edge : graph.getOutgoingEdges(current)) {
                Vertex neighbor =
                        graph.getOppositeVertex(
                                edge,
                                current
                        );

                if(visitedVertices.contains(neighbor)) {
                    continue;
                }

                int newDistance =
                        distances.get(current)
                                + edge.getWeight();

                int heuristic =
                        Heuristics.manhattan(
                                neighbor,
                                target
                        );

                int priority =
                        newDistance
                                + heuristic;

                if(newDistance < distances.get(neighbor)) {

                    distances.put(
                            neighbor,
                            newDistance
                    );

                    previousVertices.put(
                            neighbor,
                            current
                    );

                    queue.add(
                            new VertexScore(
                                    neighbor,
                                    priority
                            )
                    );
                }
            }
        }

        if(distances.get(target) == Integer.MAX_VALUE) {
            throw new IllegalStateException(
                    "No path found between "
                            + sourceVertexName
                            + " and "
                            + targetVertexName
            );
        }

        List<String> path = new ArrayList<>();
        Vertex current = target;

        while(current != null) {
            path.add(current.getName());
            current = previousVertices.get(current);
        }

        Collections.reverse(path);

        long endTime = System.nanoTime();

        return new RouteResult(
                getName(),
                path,
                distances.get(target),
                visitedVertices.size(),
                endTime - startTime
        );
    }

    private static class VertexScore {
        private final Vertex vertex;
        private final int score;

        public VertexScore(Vertex vertex, int score) {
            this.vertex = vertex;
            this.score = score;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public int getScore() {
            return score;
        }
    }
}