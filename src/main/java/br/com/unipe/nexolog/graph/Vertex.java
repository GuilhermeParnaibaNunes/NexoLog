package br.com.unipe.nexolog.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String name;

    private int degree;
    private int inDegree; //Must be 0 for undirected
    private int outDegree;//Must be 0 for undirected

    private final List<Vertex> inAdjacency;
    private final List<Vertex> outAdjacency;

    public Vertex (String name) {
        this.name = name;
        outAdjacency = new ArrayList<>();
        inAdjacency = new ArrayList<>();
    }

    //package-private
    void degreeReset() {
        degree = inDegree = outDegree = 0;
    }

    //package-private
    void adjacencyReset() {
        outAdjacency.clear();
        inAdjacency.clear();
    }

    //package-private
    void incrementDegree() { //Undirected graph
        degree++;
    }

    //package-private
    void incrementInDegree() {
        degree++;
        inDegree++;
    }

    //package-private
    void incrementOutDegree() {
        degree++;
        outDegree++;
    }

    //package-private
    void addIncomingAdjacency(Vertex vertex) {
        inAdjacency.add(vertex);
    }

    //package-private
    void addOutgoingAdjacency(Vertex vertex) {
        outAdjacency.add(vertex);
    }

    public String formatDegreeInfo(boolean directed) {
        if(!directed) {
            return "\n%s: grau %d".formatted(name, degree);
        }

        return "\n%s: grau %d (%d in | %d out)"
                .formatted(
                        name,
                        degree,
                        inDegree,
                        outDegree
                );
    }

    @Override
    public String toString() {
        return name;
    }

    /**GETTERS & SETTERS:*/

    //GETTERS
    public String getName() {
        return name;
    }

    public int getDegree() {
        return degree;
    }

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public List<Vertex> getInAdjacency() {
        return inAdjacency;
    }

    public List<Vertex> getOutAdjacency() {
        return outAdjacency;
    }

    //SETTERS
    // TODO: review visibility of degree setters
    /*public void setName(String name) {
        this.name = name;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public void setInAdjacency(List<Vertex> inAdjacency) {
        this.inAdjacency = inAdjacency;
    }

    public void setOutAdjacency(List<Vertex> outAdjacency) {
        this.outAdjacency = outAdjacency;
    }*/

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) obj;

        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
