package br.com.unipe.nexolog.graph;

import javax.swing.*;
import java.util.*;

public class Graph {
    private final List<Edge> edges;
    private final List<Vertex> vertices;
    private boolean isDirected;
    private int order;
    private int size;
    private final boolean isWeighted;

    public Graph() {
        this(false, false);
    }

    public Graph(boolean isDirected, boolean isWeighted) {
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public void addVertices(String... names) {
        for(String name : names) {
            vertices.add(new Vertex(name));
            order++;
        }
    }

    public void addEdge(String v1, String v2) {
        edges.add(createEdge("", v1, v2, null));
    }

    public void addEdge(String v1, String v2, int weight) {
        edges.add(createEdge("", v1, v2, weight));
    }

    public void addEdge(String name, String v1, String v2) {
        edges.add(createEdge(name, v1, v2, null));
    }

    public void addEdge(String name, String v1, String v2, int weight) {
        edges.add(createEdge(name, v1, v2, weight));
    }

    private Edge createEdge(String edgeName, String vertex1Name, String vertex2Name, Integer weight) {
        Vertex v1 = findVertex(vertex1Name).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + vertex1Name + " não encontrado."));
        Vertex v2 = findVertex(vertex2Name).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + vertex2Name + " não encontrado."));
        if(!isDirected) {
            inferDirectedGraph(v1, v2);
        }
        increaseVerticesDegree(v1, v2);
        updateAdjacencies(v1, v2);
        size++;
        return new Edge(edgeName, v1, v2, weight);
    }

    private void updateAdjacencies(Vertex v1, Vertex v2) {
        v1.addOutgoingAdjacency(v2); // // v1 -> v2
        v2.addIncomingAdjacency(v1); // // v2 <- v1
        if(!isDirected) {
            v1.addIncomingAdjacency(v2);
            v2.addOutgoingAdjacency(v1);
        }
    }

    private void increaseVerticesDegree(Vertex v1, Vertex v2) {
        if(isDirected) {
            v1.incrementOutDegree();
            v2.incrementInDegree();
        } else {
            v1.incrementDegree();
            v2.incrementDegree();
        }
    }

    private void inferDirectedGraph(Vertex v1, Vertex v2) {
        if(isSelfLoop(v1, v2)) {
            reprocessAsDigraph();
        }else {
            for(Edge edge : edges) {
                if(isReverseEdge(v1, v2, edge) || isDuplicateEdge(v2, v1, edge)) {
                    reprocessAsDigraph();
                    break;
                }
            }
        }
    }

    private static boolean isDuplicateEdge(Vertex v1, Vertex v2, Edge edge) {
        return edge.getSourceVertex().equals(v1) && edge.getTargetVertex().equals(v2);
    }

    private static boolean isReverseEdge(Vertex v1, Vertex v2, Edge edge) {
        return edge.getSourceVertex().equals(v2) && edge.getTargetVertex().equals(v1);
    }

    private static boolean isSelfLoop(Vertex v1, Vertex v2) {
        return v1.getName().equals(v2.getName());
    }

    public Optional<Vertex> findVertex(String name) {
        for(Vertex vertex : vertices) {
            if(vertex.getName().equalsIgnoreCase(name)) {
                return Optional.of(vertex);
            }
        }
        return Optional.empty();
    }

    private void reprocessAsDigraph() {
        isDirected = true;
        System.out.println("Reprocessamento para dígrafo necessário. O grafo agora é direcionado.");
        clearDegreesAndAdjacencies();
        recomputeDegreesAndAdjacencies();
    }

    private void recomputeDegreesAndAdjacencies() {
        edges.forEach(edge -> {
            Vertex source = edge.getSourceVertex();
            Vertex target = edge.getTargetVertex();
            increaseVerticesDegree(source, target);
            updateAdjacencies(source, target);
        });
    }

    private void clearDegreesAndAdjacencies() {
        vertices.forEach(vertex -> {
            vertex.degreeReset();
            vertex.adjacencyReset();
        });
    }

    public String formatVerticesDegreeInfo() {
        StringBuilder degrees = new StringBuilder();
        for(Vertex vertex : vertices) {
            degrees.append(vertex.formatDegreeInfo(isDirected));
        }
        return degrees.toString();
    }

    public String formatOutgoingAdjacencyInfo() {
        StringBuilder outgoingAdjacency = new StringBuilder();
        for(Vertex vertex : vertices) {
            outgoingAdjacency.append("\n").append(vertex.getName()).append(": ").append(vertex.getOutAdjacency());
        }
        return outgoingAdjacency.toString();
    }

    public String formatIncomingAdjacencyInfo() {
        StringBuilder outgoingAdjacency = new StringBuilder();
        for(Vertex vertex : vertices) {
            outgoingAdjacency.append("\n").append(vertex.getName()).append(": ").append(vertex.getInAdjacency());
        }
        return outgoingAdjacency.toString();
    }

    public void displayAdjacencyMatrix() {
        List<Vertex> orderedVertices = vertices.stream().sorted(Comparator.comparing(Vertex::getName)).toList();

        StringBuilder matrix = new StringBuilder("\nMatriz de Adjacência\n");
        matrix.append("\t");
        orderedVertices.forEach(v -> matrix.append(v.getName()).append("\t"));
        matrix.append("\n");

        for(Vertex vertex : orderedVertices) { // read-only
            matrix.append(vertex.getName()).append("\t");
            List<Vertex> outgoingAdjacency = vertex.getOutAdjacency();
            for(Vertex anotherVertex : orderedVertices) {
                matrix.append(outgoingAdjacency.contains(anotherVertex) ? "1" : "0").append("\t");
            }
            matrix.append("\n");
        }

        System.out.println(matrix);
    }

    public void displayIncidenceMatrix() {
        List<Vertex> orderedVertices = vertices.stream().sorted(Comparator.comparing(Vertex::getName)).toList();
        StringBuilder matrix = new StringBuilder("\nMatriz de Incidência\n\t");
        edges.forEach(a -> matrix.append(a.getName()).append("\t"));
        matrix.append("\n");
        for(Vertex vertex : orderedVertices) {
            matrix.append(vertex.getName()).append("\t");
            for(Edge edge : edges) {
                Vertex source = edge.getSourceVertex();
                Vertex target = edge.getTargetVertex();
                String value;
                if(source.equals(vertex) && target.equals(vertex)) {
                    value = " 2";
                } else if(source.equals(vertex)) {
                    value = isDirected ? "-1" : "1";
                } else if(target.equals(vertex)) {
                    value = " 1";
                } else { // caso contrário
                    value = " 0";
                }
                matrix.append(value).append("\t");
            }
            matrix.append("\n");
        }
        System.out.println(matrix);
    }

    public List<String> iterativeDFS(String source, String target) {
        Vertex sourceVertex = findVertex(source).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + source + " não encontrado."));
        Vertex targetVertex = target == null ? null
                : findVertex(target).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + target + " não encontrado."));

        Stack<Vertex> stack = new Stack<>();
        List<Vertex> visited = new ArrayList<>();
        StringBuilder path = new StringBuilder("Percurso = ");

        visited.add(sourceVertex);
        stack.push(sourceVertex);

        path.append(sourceVertex.getName()).append(", ");

        while(!stack.isEmpty()) {
            Vertex current = stack.peek();

            if(current.equals(targetVertex)) {
                break;
            }

            List<Vertex> outgoingAdjacency = current.getOutAdjacency();
            List<Vertex> orderedOutgoingAdjacency = outgoingAdjacency.stream().sorted(Comparator.comparing(Vertex::getName))
                    .toList();

            // Pegue a primeira adjacência não visitada
            Optional<Vertex> nextVertex = orderedOutgoingAdjacency.stream().filter(a -> !visited.contains(a)).findFirst();

            if(nextVertex.isPresent()) {
                Vertex adjacency = nextVertex.get();
                visited.add(adjacency);
                path.append(adjacency.getName()).append(", ");
                stack.push(adjacency); // avança para o primeiro neighbor não visitado
            } else {
                stack.pop(); // vértice esgotado: remove da stack
            }
        }

        System.out.println(path);
        return visited.stream().map(Vertex::getName).toList();
    }

    public List<String> recursiveDFS(String source, String target, List<Vertex> visited) {
        final List<Vertex> currentVisited = visited != null ? visited : new ArrayList<>();

        Vertex v = findVertex(source).orElseThrow(
                () -> new IllegalArgumentException("Vertex " + source + " não encontrado."));
        currentVisited.add(v);

        if(source.equals(target)) {
            return currentVisited.stream().map(Vertex::getName).toList();
        }

        // itera os neighbors um a um — após backtrack, os já visited são pulados pelo
        // contains()
        // espelhando o peek() + findFirst() do iterativo
        for(Vertex adj : v.getOutAdjacency()) {
            if(currentVisited.contains(adj)) {
                continue;
            }

            recursiveDFS(adj.getName(), target, currentVisited);

            // se target foi encontrado em algum ramo, propaga o resultado
            if(target != null && currentVisited.stream().anyMatch(x -> x.getName().equals(target))) {
                return currentVisited.stream().map(Vertex::getName).toList();
            }
        }

        // vértice esgotado (sem neighbors não visited): retorna o path até aqui
        return currentVisited.stream().map(Vertex::getName).toList();
    }

    public int getPathSize(String... path) {
        if(!isWeighted) {
            return path.length - 1; // qtd de edges percorridas
        }
        int length = 0;
        List<Edge> edgesPercorridas = new ArrayList<>();

        for(int i = 0; i < path.length - 1; i++) {
            int currentIndex = i;
            Vertex source = findVertex(path[currentIndex]).orElseThrow(
                    () -> new IllegalArgumentException("Vertex " + path[currentIndex] + " não encontrado."));
            Vertex target = findVertex(path[currentIndex + 1]).orElseThrow(
                    () -> new IllegalArgumentException("Vertex " + path[currentIndex + 1] + " não encontrado."));
            Optional<Edge> edge = edges.stream()
                    .filter(a -> a.getSourceVertex().equals(source) && a.getTargetVertex().equals(target))
                    .findFirst();
            if(edge.isPresent()) {
                if(edgesPercorridas.contains(edge.get())) {
                    throw new IllegalArgumentException("Edge repetida!");
                }
                edgesPercorridas.add(edge.get());
                length += edge.get().getWeight();
            }
        }
        return length;
    }

    public boolean isConnected() {
        for(Vertex vertex : vertices) {

            if(vertex.getDegree() == 0) {
                return false;
            }
        }

        for(Vertex vertex : vertices) {

            List<String> path =
                    iterativeDFS(vertex.getName(), null);

            if(path.size() < vertices.size()) {
                return false;
            }
        }

        return true;
    }

    public boolean isConnectedOptimized() {
        if(vertices.stream().anyMatch(v -> v.getDegree() == 0)) {
            return false;
        }

        return vertices.stream().noneMatch(v ->
                        iterativeDFS(v.getName(), null).size() < vertices.size());
    }

    public List<String> greedySearch(String sourceVertexName, String targetVertexName) {
        List<Vertex> visitedVertices = new ArrayList<>();
        int pathSize = 0;

        Vertex sourceVertex = findVertex(sourceVertexName).orElseThrow();
        Vertex targetVertex = findVertex(targetVertexName).orElseThrow();

        visitedVertices.add(sourceVertex);
        Vertex current = sourceVertex;

        while(!current.equals(targetVertex)) {
            Vertex nextVertex = current;

            // Otimização: Pegamos direto os neighbors sem iterar sobre edges do grafo
            // inteiro
            List<Vertex> outgoingAdjacency = nextVertex.getOutAdjacency();
            if(outgoingAdjacency == null || outgoingAdjacency.isEmpty()) {
                System.out.println("Caminho não encontrado. Busca falhou em: " + current.getName());
                return null;
            }

            // Busca a edge não percorrida com o menor weight baseada nos neighbors do
            // vértice current
            List<Edge> neighborEdges = new ArrayList<>();
            for(Vertex neighbor : outgoingAdjacency) {
                if(!visitedVertices.contains(neighbor)) {
                    neighborEdges.addAll(getEdgesToNeighbor(nextVertex, neighbor));
                }
            }

            // Se não houver edges vizinhas, significa que não há path para o target
            if(neighborEdges.isEmpty()) {
                System.out.println("Caminho não encontrado. Busca falhou em: " + current.getName());
                return null;
            }

            // Pega a edge com o menor weight
            Edge bestEdge = neighborEdges.stream()
                    .min(Comparator.comparing(Edge::getWeight))
                    .orElseThrow();

            pathSize += bestEdge.getWeight() != null ? bestEdge.getWeight() : 0;
            current = getOppositeVertex(bestEdge, nextVertex);
            visitedVertices.add(current);

            System.out.println("Percorrendo edge " + bestEdge.getName() +
                    " (weight " + bestEdge.getWeight() +
                    ") para o vértice " + current.getName());
        }

        List<String> visitedVerticesNames = visitedVertices.stream().map(Vertex::getName).toList();

        System.out.println("Destino " + targetVertex.getName() + " encontrado! Busca concluída com sucesso.");
        System.out.println("Caminho: " + String.join(" -> ", visitedVerticesNames));
        System.out.println("Comprimento do path: " + pathSize);

        return visitedVerticesNames;
    }


    private List<Edge> getEdgesToNeighbor(Vertex current, Vertex neighbor) {
        return edges.stream()
                .filter(a -> (a.getSourceVertex().equals(current) && a.getTargetVertex().equals(neighbor)) ||
                        (!isDirected && a.getTargetVertex().equals(current) && a.getSourceVertex().equals(neighbor)))
                .toList();
    }

    private Vertex getOppositeVertex(Edge edge, Vertex vertex) {
        return edge.getSourceVertex().equals(vertex) ? edge.getTargetVertex() : edge.getSourceVertex();
    }

    public List<Edge> getOutgoingEdges(Vertex vertex){
        return edges.stream()
                .filter(edge -> edge.getSourceVertex().equals(vertex))
                .toList();
    }

    public List<Edge> getIncidentEdges(Vertex vertex) {
        return edges.stream()
                .filter(edge -> edge.getSourceVertex().equals(vertex)
                                    || edge.getTargetVertex().equals(vertex))
                .toList();
    }

    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    @Override
    public String toString() {
        return """
                {
                direcionado = %s,
                ordem = %d,
                tamanho = %d,
                vertices = %s,
                arestas = %s,
                graus = %s,
                adjacências = %s,
                adjacentes = %s
                }
                """.formatted(isDirected ? "sim" : "não", order, size, vertices, edges, formatVerticesDegreeInfo(),
                formatOutgoingAdjacencyInfo(), formatIncomingAdjacencyInfo());
    }
}