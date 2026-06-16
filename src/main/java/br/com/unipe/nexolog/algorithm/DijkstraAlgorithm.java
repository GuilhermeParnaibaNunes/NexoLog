package br.com.unipe.nexolog.algorithm;

import br.com.unipe.nexolog.graph.Edge;
import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.graph.Vertex;
import br.com.unipe.nexolog.model.RouteResult;

import java.util.*;

public class DijkstraAlgorithm implements PathFindingAlgorithm {
    @Override
    public String getName() {
        return "Dijkstra";
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

        PriorityQueue<DijkstraNode> queue =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                DijkstraNode::getDistance
                        )
                );

        for(Vertex vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(source, 0);

        queue.add(new DijkstraNode(source,0));

        while(!queue.isEmpty()) {
            DijkstraNode currentData = queue.poll();

            Vertex current = currentData.getVertex();

            if(visitedVertices.contains(current)) {
                continue;
            }

            visitedVertices.add(current);

            if(current.equals(target)) {
                break;
            }

            for(Edge edge : graph.getIncidentEdges(current)) {
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
                            new DijkstraNode(
                                    neighbor,
                                    newDistance
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

    private static class DijkstraNode {
        private final Vertex vertex;
        private final int distance;

        public DijkstraNode(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }
}