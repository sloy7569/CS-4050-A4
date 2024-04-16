//Creating a minHeap using an array and the Element class (MIGHT NEED TO BE CHANGED)
public class minHeap {
    private Element[] Heap;
    private int size;
    private int userSize;

    //Constructor for minHeap
    public minHeap(int userSize){
        this.userSize = userSize;
        this.size = 0;

        Heap = new Element[userSize + 1];
        Heap[0] = new Element(0,-10000000) ;
    }

    private int leftChildIndex(int index) {
        return index * 2;
    }

    private int rightChildIndex(int index) {
        return (index * 2) + 1;
    }

    //get the index of the parent
    private int parentIndex(int index) {
        return index/2;
    }

    //return the key of the min
    public int min_key() {
        return Heap[1].getKey();
    }

    //return the id of the min
    public int min_id() {
        return Heap[1].getId();
    }

    //swap two indexes
    private void swap(int a, int b){
        Element temp;
        temp = Heap[a];

        Heap[a] = Heap[b];
        Heap[b] = temp;
    }

    //insert a value into minHeap
    public void insert(Element e) {
        if(size > userSize) {
            return;
        }

        size += 1;
        Heap[size] = e;
        int temp = size;

        while(Heap[temp].getKey() < Heap[parentIndex(temp)].getKey()){
            swap(temp, parentIndex(temp));
            temp = parentIndex(temp);
        }
    }

    //check if a value is in the heap, if so, return true, else return false
    public boolean in_heap(int id) {
          for(int i = 1; i < userSize; i++){
              if(Heap[i].getId() == id){
                  return true;
              }
          }

          return false;
    }

    //check if a key is in the heap, and if so, return it
    public int key(int id) {
        for(int i = 1; i < userSize; i++){
            if(Heap[i].getId() == id){
                return Heap[i].getKey();
            }
        }
        //might want to change return value here?
        return 0;
    }

    //keep the heap structure of the data (PULLED FROM SLIDES)
    private void reheap(int rootIndex){
        boolean done = false;
        Element orphan = Heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex < size)){
            int smallerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if ((rightChildIndex <= size) && Heap[rightChildIndex].getKey() < (Heap[smallerChildIndex].getKey())) {
                smallerChildIndex = rightChildIndex;
            } // end if

            if (orphan.getKey() > (Heap[smallerChildIndex].getKey())) {
                Heap[rootIndex] = Heap[smallerChildIndex];
                rootIndex = smallerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        Heap[rootIndex] = orphan;
    }

    //delete the minimum value from the minHeap
    public Element deleteMin(){
        Element removed = Heap[1];
        Heap[1] = Heap[size];
        size -= 1;
        reheap(1);

        return removed;
    }

    public void heap_ini(int[] keys, int n){

    }

    public void decreaseKey(int id, int new_key){

    }

    //print just for testing (from internet)
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {

            // Printing the parent and both childrens
            System.out.print(
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1]);

            // By here new line is required
            System.out.println();
        }
    }

    //main for testing
    public static void main(String[] arg)
    {

        /*
        System.out.println("The Min Heap is ");

        minHeap test = new minHeap(15);

        Element e1 = new Element(1, 30);
        Element e2 = new Element(2, 70);
        Element e3 = new Element(3, 10);
        Element e4 = new Element(4, 20);
        Element e5 = new Element(5, 43);
        Element e6 = new Element(6, 55);
        Element e7 = new Element(7, 17);
        Element e8 = new Element(8, 90);
        Element e9 = new Element(9, 66);
        Element e10 = new Element(10, 21);


        test.insert(e1);
        test.insert(e2);
        test.insert(e3);
        test.insert(e4);
        test.insert(e5);
        test.insert(e6);
        test.insert(e7);
        test.insert(e8);
        test.insert(e9);
        test.insert(e10);

        test.print();

        test.deleteMin();

        test.print();
        */

    }


}