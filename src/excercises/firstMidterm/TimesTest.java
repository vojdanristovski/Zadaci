//package excercises.firstMidterm;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//public class TimesTest {
//
//    public static void main(String[] args) {
//        TimeTable timeTable = new TimeTable();
//        try {
//            timeTable.readTimes(System.in);
//        } catch (UnsupportedFormatException e) {
//            System.out.println("UnsupportedFormatException: " + e.getMessage());
//        } catch (InvalidTimeException e) {
//            System.out.println("InvalidTimeException: " + e.getMessage());
//        }
//        System.out.println("24 HOUR FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
//        System.out.println("AM/PM FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
//    }
//
//}
//
//enum TimeFormat {
//    FORMAT_24, FORMAT_AMPM
//}
//class UnsupportedFormatException extends Exception{
//    public UnsupportedFormatException(String message) {
//        super(message);
//    }
//}
//class InvalidTimeException extends Exception{
//    public InvalidTimeException(String message) {
//        super(message);
//    }
//}
//class Time{
//    private int hours;
//    private int minutes;
//
//    public Time(int hours, int minutes) {
//        this.hours = hours;
//        this.minutes = minutes;
//    }
//
//    public int getHours() {
//        return hours;
//    }
//
//    public int getMinutes() {
//        return minutes;
//    }
//
//    public String getAmPm(){
//        String result="";
//        if(hours == 0){
//            result = String.format("%2d:%2d AM",hours+12,minutes);
//        }
//        else if(hours==12){
//            result=String.format("%2d:%2d PM",hours,minutes);
//        }
//        else if(hours>=13 && hours <=23){
//            result=result=String.format("%2d:%2d PM",hours-12,minutes);
//        }
//        else if(hours>=1 && hours<=11){
//            result=String.format("%2d:%2d AM",hours,minutes);
//        }
//        return result;
//    }
//    public String to24(){
//        return String.format("%2d:%d",hours,minutes);
//    }
//
//}
//class Times{
//    List<Time> timeList;
//
//    public Times(List<Time> timeList) {
//        this.timeList = new ArrayList<>();
//        this.timeList = timeList;
//    }
//    public static Times makeTimes(String line) throws UnsupportedFormatException, InvalidTimeException {
//        List<Time> list = new ArrayList<>();
//        String [] parts = line.split("\\s+");
//        for(int i=0;i<parts.length;i++){
//            parts[i]=parts[i].replace(".",":");
//            if(!parts[i].contains(":")){
//                throw new UnsupportedFormatException(parts[i]);
//            }
//            String [] niza = parts[i].split(":");
//            int hours = Integer.parseInt(niza[0]);
//            int minutes = Integer.parseInt(niza[1]);
//            if(hours<0 || hours >23 || minutes<0 || minutes>59){
//                throw new InvalidTimeException(parts[i]);
//            }
//            list.add(new Time(hours,minutes));
//        }
//        return new Times(list);
//    }
//    public String to24H(){
//        return String.format("%s",this.timeList.stream().map(Time::to24).collect(Collectors.joining("\n")));
//    }
//    public String toAMPM(){
//        return String.format("%s",this.timeList.stream().map(Time::getAmPm).collect(Collectors.joining("\n")));
//    }
//}
//class TimeTable{
//    List<Times> times;
//
//    public TimeTable() {
//        this.times = new ArrayList<>();
//    }
//    public void readTimes(InputStream in) throws UnsupportedFormatException,InvalidTimeException {
//        new BufferedReader(new InputStreamReader(in))
//                .lines()
//                .forEach(line->{
//                    Times t = null;
//                    try {
//                        t = Times.makeTimes(line);
//                    } catch (UnsupportedFormatException | InvalidTimeException e) {
//                        e.getMessage();
//                    }
//                    this.times.add(t);
//                });
//    }
//    public void writeTimes(PrintStream out, TimeFormat format24) {
//        PrintWriter printWriter=new PrintWriter(out);
//        Comparator<Times> comparator= Comparator.comparing()
//        if(format24 == TimeFormat.FORMAT_24)
//        {
//            this.times.forEach(t->printWriter.println(t.to24H()));
//        }
//        else{
//            this.times.forEach(t->printWriter.println(t.toAMPM()));
//        }
//        printWriter.flush();
//    }
//}
