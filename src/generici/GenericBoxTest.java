package generici;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
interface Drawable{
    void draw();
}
class Box<T extends Drawable>{
    List<T> elements;
    static Random RANDOM = new Random();
    public Box() {
        this.elements = new ArrayList<>();
    }
    public void addElement(T element){
        this.elements.add(element);
    }
    public boolean isEmpty(){
        return this.elements.isEmpty();
    }
    public void drawElement(){
        if(isEmpty()){
            return ;
        }
        T element = this.elements.remove(RANDOM.nextInt(elements.size()));
        element.draw();
    }
}
public class GenericBoxTest {
    public static void main(String[] args) {
        Box<Drawable> box = new Box<>();
        for(int i=0;i<100;i++){
            box.addElement(()-> System.out.println(Box.RANDOM.nextInt(50)));
        }
        for(int i=0;i<50;i++){
            box.drawElement();
        }
    }
}
