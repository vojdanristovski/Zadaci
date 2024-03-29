package excercises.firstMidterm;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
class Triple<T extends Number>{
    List<T> list;

    public Triple(T a, T b, T c) {
        list = new ArrayList<>(3);
        list.add(a);
        list.add(b);
        list.add(c);
    }
    public double max(){
        return this.list.stream().mapToDouble(Number::doubleValue).max().getAsDouble();
    }
    public double avarage(){
        return this.list.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
    }
    public void sort(){
        list.sort(Comparator.comparingDouble(Number::doubleValue));
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f",list.get(0).doubleValue(),list.get(1).doubleValue(),list.get(2).doubleValue());
    }


}