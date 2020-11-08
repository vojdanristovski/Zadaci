package excercises.firstMidterm;

import org.w3c.dom.ls.LSOutput;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}
class WeatherStation{
    private int days;
    List<Measurment> measurmentList;

    public WeatherStation(int days) {
        this.days = days;
        this.measurmentList = new ArrayList<>();
    }
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date) {

        boolean ignore = this.measurmentList.stream().anyMatch(measurment -> {
            LocalDateTime ldt = LocalDateTime.ofInstant(measurment.getDate().toInstant(), ZoneId.of("GMT"));
            LocalDateTime ldt1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("GMT"));
            //Duration duration=Duration.between(ldt,ldt1);
            return ldt.plusSeconds(150).isAfter(ldt1);
        });
        if (!ignore) {
            this.measurmentList.removeIf(measurment -> {
                LocalDateTime ldt = LocalDateTime.ofInstant(measurment.getDate().toInstant(), ZoneId.of("GMT"));
                LocalDateTime ldt1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("GMT"));
                return ldt.plusDays(days).isBefore(ldt1);
            });
            this.measurmentList.add(new Measurment(temperature, wind, humidity, visibility, date));

        }
    }
    public int total(){
        return this.measurmentList.size();
    }
    public void status(Date from, Date to){
        Comparator<Measurment> comparator = Comparator.comparing(Measurment::getDate);
        List<Measurment> list = this.measurmentList.stream()
                .sorted(comparator)
                .filter(measurment -> (measurment.getDate().after(from) || measurment.getDate().equals(from)) &&
                        (measurment.getDate().before(to) || measurment.getDate().equals(to)))
                .collect(Collectors.toList());
        if(list.isEmpty())
        {
            throw new RuntimeException();
        }
        list.forEach(System.out::println);
        double average = this.measurmentList.stream()
                .filter(measurment -> (measurment.getDate().after(from) || measurment.getDate().equals(from)) &&
                        (measurment.getDate().before(to) || measurment.getDate().equals(to)))
                .mapToDouble(Measurment::getTemperature)
                .average()
                .getAsDouble();
        System.out.println(String.format("Average temperature: %.2f",average));
    }
}
class Measurment{
    private float temperature;
    private float wind;
    private float humidity;
    private float visibility;
    private Date date;

    public Measurment(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWind() {
        return wind;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temperature,wind,humidity,visibility,
                (date).toString());
    }
}