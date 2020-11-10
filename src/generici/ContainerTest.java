package generici;

import java.nio.charset.CoderMalfunctionError;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Weightable{
    double weight();
}

class Container<T extends Weightable>
{
    List<T> elements;

    public Container() {
        this.elements = new ArrayList<>();
    }
    void addElemen(T element){
        this.elements.add(element);
    }
    List<T> lighterThan(T elem){
      return    this.elements.stream()
                .filter(s->s.weight() < elem.weight())
                .collect(Collectors.toList());
    }
    List<T> between(T a,T b){
        return this.elements.stream()
                .filter(s->s.weight()>a.weight() && s.weight() < b.weight())
                .collect(Collectors.toList());
    }
    public double weightSum(){
        return this.elements.stream()
                .mapToDouble(Weightable::weight)
                .sum();
    }
    public int compare(Container<? extends Weightable> tContainer) {
        return Double.compare(weightSum(),tContainer.weightSum());
    }
}
public class ContainerTest {
    public static void main(String[] args) {

    }
}

class WeightableDouble implements Weightable
{
    double weight;

    public WeightableDouble(double weight) {
        this.weight = weight;
    }

    @Override
    public double weight() {
        return this.weight;
    }
}
class WeightableString implements Weightable{
    String word;

    public WeightableString(String word) {
        this.word = word;
    }

    @Override
    public double weight() {
        return word.length();
    }
}

