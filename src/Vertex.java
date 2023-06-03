import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vertex<V> {
    private final V data;
    private final V dest;
    private final Map<Vertex<V>, Double> adjacentVertices;
    public Vertex(V dest,V data){
        this.dest = dest;
        this.data = data;
        adjacentVertices = new HashMap<>();
    }
    public V getData(){
        return data;
    }
    public V getDest() {
        return dest;
    }
    
    public void addAdjacentVertex(Vertex<V> dest, double weight){
        adjacentVertices.put(dest, weight);
    }
    public void addEdge(Vertex<V> nextVertice, double weight){
        this.addAdjacentVertex(nextVertice, weight);
        nextVertice.addAdjacentVertex(this, weight);
    }
    public void addVertex(V v) {
        Vertex<V> vertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(data,vertex.data);
    }


    @Override
    public int hashCode(){
        return Objects.hash(data);
    }
    public boolean contains(Vertex<V> key){
        return adjacentVertices.containsKey(key);
    }
    public Double getWeight(Vertex<V> vVertex){
        if (contains(vVertex)) {
            return adjacentVertices.get(vVertex);
        } else {
            return null;
        }
    }
}
