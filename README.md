# NexoLog

NexoLog is an academic project developed for the **Graph Theory** course.

The project simulates warehouse logistics environments through graph modeling and applies shortest-path algorithms to optimize product movement inside distribution centers.

---

# Problem Context

Modern logistics companies such as Amazon, Mercado Livre, Correios and Ambev operate large distribution centers where products constantly move between different operational sectors.

Typical warehouse operations include:

* Receiving
* Storage
* Internal Transportation
* Picking
* Packaging
* Shipping

Choosing efficient routes directly impacts:

* Processing time
* Operational efficiency
* Resource utilization
* Logistics costs

NexoLog models these environments as weighted graphs and evaluates routing algorithms capable of finding optimal paths between warehouse sectors.

---

# Objectives

The main objective of NexoLog is to demonstrate practical applications of Graph Theory concepts in logistics optimization.

The project explores:

* Graph modeling
* Route optimization
* Shortest-path algorithms
* Algorithm performance analysis
* Comparative evaluation of graph search strategies

---

# Current Features

## Graph Module

Implemented graph functionalities:

* Vertices
* Edges
* Weighted Graphs
* Directed Graphs
* Undirected Graphs
* Adjacency Matrix
* Incidence Matrix
* Degree Analysis
* DFS (Iterative)
* DFS (Recursive)
* Connectivity Verification

---

## Logistics Module

### WarehouseMap

Represents a logistics environment modeled as a graph.

### WarehouseMapFactory

Provides predefined warehouse scenarios:

* Small Distribution Center
* Medium Distribution Center
* Large Distribution Center
* Mega Distribution Center
* Ultra Grid Warehouse

Each scenario increases in complexity and is used for algorithm benchmarking.

---

## Pathfinding Algorithms

### Dijkstra

Computes the optimal route between two warehouse sectors considering edge weights.

Collected metrics:

* Path found
* Total cost
* Visited vertices
* Execution time

### A*

Computes optimal routes using informed search based on Manhattan-distance heuristics.

Benefits observed during experiments:

* Fewer visited vertices
* Faster execution in larger scenarios
* Same optimal route quality as Dijkstra

---

# Experimental Results

Benchmark tests were executed multiple times on different warehouse scenarios.

Observed tendencies:

| Scenario   | Route Cost       | A* Vertex Reduction |
| ---------- | ---------------- | ------------------- |
| Small      | Same as Dijkstra | 0%                  |
| Medium     | Same as Dijkstra | 12.5%               |
| Large      | Same as Dijkstra | 25.0%               |
| Mega       | Same as Dijkstra | 8.0%                |
| Ultra Grid | Same as Dijkstra | 61.1%               |

Results indicate that A* becomes increasingly advantageous as warehouse complexity grows.

---

# Future Work

The next phase of the project aims to move beyond shortest-path problems and address route optimization involving multiple destinations.

Planned algorithms include:

## Nearest Neighbor

A heuristic approach for the Traveling Salesman Problem (TSP).

Possible application:

* Determining an efficient picking sequence for multiple products inside a warehouse.

## Held-Karp

An exact dynamic programming solution for the Traveling Salesman Problem.

Possible application:

* Computing optimal multi-stop routes for warehouse operations.
* Comparing heuristic and exact optimization strategies.

These additions will allow NexoLog to evolve from point-to-point routing into complete logistics route planning.

---

# Project Structure

```text
src/main/java/br/com/unipe/nexolog

├── algorithm
│   ├── PathFindingAlgorithm.java
│   ├── DijkstraAlgorithm.java
│   ├── AStarAlgorithm.java
│   └── Heuristics.java
│
├── factory
│   └── WarehouseMapFactory.java
│
├── graph
│   ├── Graph.java
│   ├── Vertex.java
│   └── Edge.java
│
├── model
│   ├── WarehouseMap.java
│   └── RouteResult.java
│
└── Main.java
```

---

# Technologies

* Java 22
* Gradle
* IntelliJ IDEA

---

# Authors

* Guilherme Parnaíba Nunes
* Sebastião
* Ikharo
* Vinícius

---

# Academic Context

This project was developed as part of the Graph Theory course.

The graph foundation classes were adapted from educational materials provided by Professor Douglas Meneses and further extended to support logistics modeling and route optimization experiments.

---

# License

This project is distributed under the MIT License.
