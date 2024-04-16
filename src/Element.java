public class Element {
    private int id;
    private int key;

    public Element(int id, int key){
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Element{id=" + id + ", key=" + key + "}";
    }

}