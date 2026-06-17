# NexoLog

NexoLog is an academic project developed for the Graph Theory course.

The project simulates warehouse logistics using graph-based models and pathfinding algorithms to optimize routes inside distribution centers.

## Scenario

Imagine a large distribution center operated by a logistics company such as Amazon, Correios, Mercado Livre, or Ambev.

Products move continuously between:

- Receiving
- Storage Areas
- Picking Stations
- Shipping Areas

Finding efficient routes directly impacts:

- Processing time
- Resource consumption
- Operational costs

NexoLog models this environment as a graph and applies graph algorithms to analyze and optimize movement through the warehouse.

---

## Current Features

### Graph Structure

- Vertices
- Edges
- Weighted graphs
- Directed and undirected graphs
- Adjacency Matrix
- Incidence Matrix
- DFS (Iterative)
- DFS (Recursive)

### Logistics Modeling

- WarehouseMap
- WarehouseMapFactory
- Predefined warehouse scenarios

### Pathfinding

- Dijkstra Shortest Path Algorithm

### Metrics

- Total route cost
- Number of visited vertices
- Execution time (nanoseconds)

---

## Planned Features

### Phase 2

- CLI Route Game
- User vs Algorithm

### Phase 3

- Nearest Neighbor Algorithm

### Phase 4

- Route Ordering Game

### Phase 5

- Held-Karp Algorithm (TSP)

### Phase 6

- Advanced Route Optimization

### Phase 7

- Front-End Interface
- Multiple Maps
- Rankings
- Custom Scenarios

---

## Project Structure

```text
src/main/java/br/com/unipe/nexolog

├── algorithm
│   ├── PathFindingAlgorithm
│   └── DijkstraAlgorithm
│
├── factory
│   └── WarehouseMapFactory
│
├── graph
│   ├── Graph
│   ├── Vertex
│   └── Edge
│
├── model
│   ├── WarehouseMap
│   ├── RouteResult
│   └── AlgorithmMetrics
│
└── Main
```

---

## Technologies

- Java 22
- Gradle
- IntelliJ IDEA

---

## Authors

- Guilherme Parnaíba
- Sebastião
- Ikharo
- Vinícius

---

## Acknowledgements

The graph foundation classes used in this project were adapted from the Graph Theory course materials provided by Professor Douglas Meneses.
