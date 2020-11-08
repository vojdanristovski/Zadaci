//package excercises.firstMidterm;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
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
//class TimeTable{
//    List<Times> times;
//    TimeTable(){
//        this.times = new ArrayList<>();
//    }
//    void readTimes(InputStream inputStream) throws UnsupportedFormatException,InvalidTimeException{
//        new BufferedReader(new InputStreamReader(inputStream))
//                .lines()
//                .forEach(line->{
//                    Times t = null;
//                    try {
//                        t = Times.makeTimes(line);
//                    } catch (UnsupportedFormatException e) {
//                        e.printStackTrace();
//                    } catch (InvalidTimeException e) {
//                        e.printStackTrace();
//                    }
//                    this.times.add(t);
//                });
//    }
//    void writeTimes(OutputStream outputStream,TimeFormat timeFormat){
//        PrintWriter pw = new PrintWriter(outputStream);
//        if(timeFormat==TimeFormat.FORMAT_24){
//            Comparator<Times> comparator= Comparator.comparing(Times::write24H);
//            this.times.stream().forEach(time->pw.println());
//        }
//        else{
//
//        }
//    }
//
//}
//class Times{
//    List<String> timeList;
//
//    public Times(List<String> timeList) {
//        this.timeList = new ArrayList<>();
//        this.timeList = timeList;
//    }
//
//    public static Times makeTimes(String line) throws UnsupportedFormatException, InvalidTimeException {
//        List<String> stringList = new ArrayList<>();
//        String [] parts = line.split("\\s+");
//        for(int i=0;i<parts.length;i++){
//            parts[i]=parts[i].replace(".",":");
//            if(!parts[i].contains(":")){
//                throw new UnsupportedFormatException(parts[i]);
//            }
//            String [] delovi = parts[i].split(":");
//            int hours = Integer.parseInt(delovi[0]);
//            int minutes = Integer.parseInt(delovi[1]);
//            if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59){
//                throw new InvalidTimeException(parts[i]);
//            }
//            stringList.add(parts[i]);
//        }
//        return new Times(stringList);
//    }
//    public String writeAmPm(){
//        StringBuilder sb = new StringBuilder();
//         this.timeList.forEach(timeList -> sb.append(convertAmPm(timeList)).append("\n"));
//         return sb.toString();
//    }
//    public String write24H(){
//        StringBuilder sb = new StringBuilder();
//        this.timeList.forEach(timeList->sb.append(timeList).append("\n"));
//        return sb.toString();
//    }
//    public  String convertAmPm(String time){
//        String [] parts = time.split(":");
//        int hours = Integer.parseInt(parts[0]);
//        int minutes = Integer.parseInt(parts[1]);
//        String result = "";
//        if(hours == 0){
//            hours=12;
//            result=hours + ":" + minutes + " AM";
//        }
//       else if(hours >= 1 && hours <= 11){
//            result=hours + ":" + minutes + "AM";
//        }
//        else if(hours==12){
//            result=hours + ":" + minutes + "PM";
//        }
//        else if(hours>=13 && hours<=23){
//            hours-=12;
//            result=hours + ":" + minutes + "PM";
//        }
//        return result;
//    }
//}