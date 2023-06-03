import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Set<V> unsettledNodes;
    private Map<V, Double> distances;
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (V target : graph.adjacencyList(node)) {
                double distanceThroughNode = getShortestDistance(node) + getDistance(node, target);
                if (distanceThroughNode < getShortestDistance(target)) {
                    distances.put(target, distanceThroughNode);
                    edgeTo.put(target, node);
                    unsettledNodes.add(target);
                }
            }
        }
    }

    private double getDistance(V node, V target) {
        for (WeightedGraph.Vertex<V> vertex : graph.getEdges(node)) {
            if (vertex.getDest().equals(target))
                return vertex.getWeight();
        }

        throw new RuntimeException("Not found!");
    }
    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null)
                minimum = vertex;
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
