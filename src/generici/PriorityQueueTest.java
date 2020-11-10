package generici;

import java.util.ArrayList;
import java.util.List;

class Node<T> implements Comparable<Node<T>>{
    T element;
    int priority;

    public Node(T element, int priority) {
        this.element = element;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(Node<T> tNode) {
        return Integer.compare(this.priority,tNode.priority);
    }
}
class PriorityQueue<T>{
    List<Node<T>> nodeList;

    public PriorityQueue() {
        this.nodeList = new ArrayList<>();
    }
    public void add(T element,int priority){
        Node<T> node = new Node<>(element,priority);
        int count=0;
        for(int i=0;i<this.nodeList.size();i++){
            if(node.compareTo(nodeList.get(i)) >= 0) //sortiranje opagacki redosled
            {
                break;
            }
            count++;
        }
        nodeList.add(count,node);
    }
    public T remove(){
        if(nodeList.size()==0){
            return null;
        }

        return nodeList.remove(0).element;
    }
}
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<String> queue=new PriorityQueue<>();
        queue.add("najnizok",10);
        queue.add("sreden",50);
        queue.add("najvisok",100);
        queue.add("sreden1",49);
        String element="";
        while((element = queue.remove())!=null){
            System.out.println(element);
        }
    }
}
