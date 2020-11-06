package excercises.firstMidterm;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}

class F1Race {
    List<Racer> racers;
    F1Race(){
        this.racers = new ArrayList<>();
    }
    void readResults(InputStream inputStream){
        new BufferedReader(new InputStreamReader(inputStream)).lines()
                .forEach(line->{
                    Racer r = Racer.makeRacer(line);
                    this.racers.add(r);
                });
    }
    void printSorted(OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        Comparator<Racer> racerComparator = Comparator.comparing(Racer::getBestLap);
        AtomicInteger count = new AtomicInteger(1);
        this.racers.stream()
                .sorted(racerComparator)
                .forEach(racer -> pw.println(String.format("%d.%s", count.getAndIncrement(),racer)));
        pw.flush();
    }
}
class Racer{
    private String racerName;
    private List<Lap> laps;

    public Racer(String racerName, List<Lap> laps) {
        this.laps = new ArrayList<>();
        this.racerName = racerName;
        this.laps = laps;
    }
    public String getBestLap(){
        Comparator<Lap> comparator=Comparator.comparing(Lap::getLap);
       return this.laps.stream()
                .sorted(comparator)
                .findFirst()
                .get()
               .getLap();
    }
    public static Racer makeRacer(String line){
        List<Lap> laps = new ArrayList<>();
        String [] parts = line.split("\\s+");
        String name = parts[0];
        for(int i=1;i<parts.length;i++){
            laps.add(new Lap(parts[i]));
        }
        return new Racer(name,laps);
    }
    public String toString(){
        return String.format("%-10s%10s",racerName,getBestLap());
    }
}
class Lap{
    private String lap;

    public Lap(String lap) {
        this.lap = lap;
    }

    public String getLap() {
        return lap;
    }

}