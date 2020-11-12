package excercises.firstMidterm;

import java.util.Scanner;

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
class MinMax<T extends Comparable<T>>{
    T min,max;
    int count;
    public MinMax() {
        min=max=null;
    }

    void update(T element){

        if(max==null){
            max=element;
        }
        if(min == null){
            min = element;
        }
        if(!max.equals(element) && !min.equals(element)){
            count++;
        }
        if(max.compareTo(element) < 0){
            max = element;
        }
        if(min.compareTo(element) > 0){
            min = element;
        }

    }
    T max(){
        return max;
    }
    T min(){
        return min;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d\n",min.toString(),max.toString(),count);
    }
}