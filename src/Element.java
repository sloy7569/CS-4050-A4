//public class Element {
//    private int id;
//    private int key;
//
//    public Element(int id, int key){
//        this.id = id;
//        this.key = key;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getKey() {
//        return key;
//    }
//
//    @Override
//    public String toString() {
//        return "Element{id=" + id + ", key=" + key + "}";
//    }
//
//}

class Element {
    private int source;
    private int destination;
    private double weight;

    public Element(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getId() {
        return source;
    }

    public double getKey() {
        return weight;
    }

    // Getters for source, destination, and weight
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