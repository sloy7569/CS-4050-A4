import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrimMST {

    public static void main(String[] args) {
        String fileName = "./src/graph.txt";
        List<Edge> edges = readGraph(fileName);
        int numVertices = edges.size() + 1;

        // represent the graph as an adjacency list
        List<List<Edge>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            adjacencyList.get(edge.getSource()).add(edge);
            adjacencyList.get(edge.getDestination()).add(new Edge(edge.getDestination(), edge.getSource(), edge.getWeight()));
        }

        // perform Prim's algorithm to find the minimum spanning tree
        List<Edge> mstEdges = primMST(adjacencyList, numVertices);

        // output the input graph and the minimum spanning tree
        System.out.println("Input Graph:");
        for (List<Edge> vertexEdges : adjacencyList) {
            for (Edge edge : vertexEdges) {
                System.out.println(edge.getSource() + " - " + edge.getDestination() + " : " + edge.getWeight());
            }
        }

        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mstEdges) {
            System.out.println(edge.getSource() + " - " + edge.getDestination() + " : " + edge.getWeight());
        }
    }

    public static List<Edge> readGraph(String fileName) {
        List<Edge> edges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int numVertices = Integer.parseInt(br.readLine().trim());
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int source = Integer.parseInt(parts[0]);
                int destination = Integer.parseInt(parts[1]);
                double weight = Double.parseDouble(parts[2]);
                edges.add(new Edge(source, destination, weight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }
    public static List<Edge> primMST(List<List<Edge>> adjacencyList, int numVertices) {
        List<Edge> mstEdges = new ArrayList<>();
        boolean[] inMST = new boolean[numVertices];
//        int[] key = new int[numVertices];
        double[] key = new double[numVertices];
        int[] parent = new int[numVertices];

        // initialize key values and parent array
        for (int i = 0; i < numVertices; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        // create a minHeap instance with the same size as the number of vertices
        minHeap heap = new minHeap(numVertices);

        // start with vertex 0 (root) and set its key to 0
        key[0] = 0;
        heap.insert(new Element(-1, 1, 0)); // Source -1 represents the root

        // loop until all vertices are included in MST
        while (!heap.isEmpty()) {
            // extract the element with the minimum key value from the heap
            Element minElement = heap.deleteMin();
            int dest = minElement.getDestination();
            int source = minElement.getSource();

//            System.out.println("Current heap element: source - " + source + " dest - " + dest);

            // mark vertex dest as part of MST
            inMST[dest] = true;


            // add the edge to MST (excluding the root)
            if (source != -1) {
                mstEdges.add(new Edge(source, dest, key[dest]));
            }

            // update key values and heap with adjacent vertices of dest
            for (Edge edge : adjacencyList.get(dest)) {
                int v = edge.getDestination();
                double weight = edge.getWeight();

//                System.out.println("Edge with source " + edge.getSource() + " and destination " + edge.getDestination() + " is connected to id " + dest);

                // update key value and parent if v is not in MST and weight is less than current key
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = dest;
                    heap.insert(new Element(dest, v, key[v]));

                }
            }
        }

        return mstEdges;
    }


}

class Edge {
    private int source;
    private int destination;
    private double weight;

    public Edge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
