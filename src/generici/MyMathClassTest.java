package generici;

import java.util.ArrayList;
import java.util.Comparator;

public class MyMathClassTest {
    public static double standardDeviation(ArrayList<? extends Number> array){
        double avg = array.stream().mapToDouble(s->((Number) s).doubleValue()).average().getAsDouble();
        double sum = array.stream().mapToDouble(s->(avg-((Number) s).doubleValue()) * (avg-((Number) s).doubleValue()))
                .sum();
        return Math.sqrt(sum/array.size());
    }
    public static double min(ArrayList<? extends Number> array) {
        return array.stream().mapToDouble(s->((Number) s).doubleValue())
                .min().getAsDouble();
    }
    public static double max(ArrayList<? extends Number> array){
        return array.stream().mapToDouble(s->((Number) s).doubleValue())
                .max().getAsDouble();
    }
    public static int count(ArrayList<? extends Number> arrayList){
        return arrayList.size();
    }
    public static double sum(ArrayList<? extends Number> arrayList){
        return arrayList.stream().mapToDouble(s->((Number) s).doubleValue())
                .sum();
    }
    public static double avg(ArrayList<? extends Number> arrayList){
        return arrayList.stream().mapToDouble(s->((Number) s).doubleValue())
                .average()
                .getAsDouble();
    }

    public static void main(String[] args) {

    }
}
