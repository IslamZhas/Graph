import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private Map<V, List<Vertex<V>>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        map.put(v, new LinkedList<>());
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).add((Vertex<V>) new Vertex<>(dest, weight));

        if (undirected)
            map.get(dest).add((Vertex<V>) new Vertex<>(source, weight));
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (V v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source))
            return false;
        for (Vertex<V> vertex : map.get(source)) {
            if (vertex.getDest().equals(dest))
                return true;
        }
        return false;
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v))
            return null;
        List<V> vertices = new LinkedList<>();
        for (Vertex<V> vertex : map.get(v)) {
            vertices.add(vertex.getDest());
        }
        return vertices;
    }

    public Iterable<Vertex<V>> getEdges(V v) {
        if (!hasVertex(v))
            return null;
        return map.get(v);
    }

}