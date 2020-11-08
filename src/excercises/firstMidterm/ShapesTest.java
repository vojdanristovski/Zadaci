    package excercises.firstMidterm;

    import java.util.*;

    enum Color {
        RED, GREEN, BLUE
    }
    public class ShapesTest {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Canvas canvas = new Canvas();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int type = Integer.parseInt(parts[0]);
                String id = parts[1];
                if (type == 1) {
                    Color color = Color.valueOf(parts[2]);
                    float radius = Float.parseFloat(parts[3]);
                    canvas.add(id, color, radius);
                } else if (type == 2) {
                    Color color = Color.valueOf(parts[2]);
                    float width = Float.parseFloat(parts[3]);
                    float height = Float.parseFloat(parts[4]);
                    canvas.add(id, color, width, height);
                } else if (type == 3) {
                    float scaleFactor = Float.parseFloat(parts[2]);
                    System.out.println("ORIGNAL:");
                    System.out.print(canvas);
                    canvas.scale(id, scaleFactor);
                    System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                    System.out.print(canvas);
                }

            }
        }
    }
    interface Scalable{
        void scale(float scaleFactor);
    }
    interface Stackable{
        float weight();
    }
    class Canvas {
        Set<Forma> formaList;

        public Canvas() {
            this.formaList = new TreeSet<>();
        }
        void add(String id,Color color,float radius){
            this.formaList.add(new Krug(id,color,radius));
        }
        void add(String id,Color color,float width,float height){
            this.formaList.add(new Pravoagolnik(id,color,width,height));
        }
        void scale(String id,float scaleFactor){
            this.formaList.stream().filter(forma -> forma.id.equals(id))
                    .findAny()
                    .get()
                    .scale(scaleFactor);

        }
        @Override
        public String toString() {
            Comparator<Forma> comparator = Comparator.comparing(Forma::weight).reversed();
            StringBuilder sb = new StringBuilder();
             this.formaList.stream().sorted(comparator).forEach(forma -> sb.append(forma.toString()));
             return sb.toString();
        }
    }


    abstract class Forma implements Scalable,Stackable, Comparable<Forma>{
        String id;
        Color color;

        public Forma(String id, Color color) {
            this.id = id;
            this.color = color;
        }

        @Override
        abstract public String toString();
        abstract String getForma();

    }
    class Pravoagolnik extends Forma{
        float width;
        float height;

        public Pravoagolnik(String id, Color color, float width, float height) {
            super(id, color);
            this.width = width;
            this.height = height;
        }

        @Override
        public void scale(float scaleFactor) {
            this.height *= scaleFactor;
            this.width *=scaleFactor;
        }

        @Override
        public float weight() {
            return width*height;
        }

        @Override
        public String toString() {
            return String.format("R: %-5s%-10s%10.2f\n",this.id,this.color.toString(),this.weight());
        }

        @Override
        String getForma() {
            return "Pravoagolnik";
        }

        @Override
        public int compareTo(Forma forma) {
            return Float.compare(forma.weight(),weight());
        }
    }
    class Krug extends Forma{
        float radius;

        public Krug(String id, Color color, float radius) {
            super(id, color);
            this.radius = radius;
        }

        @Override
        public void scale(float scaleFactor) {
            this.radius*=scaleFactor;
        }

        @Override
        public float weight() {
            return (float) (Math.pow(radius,2) * Math.PI);
        }

        @Override
        public String toString() {
            return String.format("C: %-5s%-10s%10.2f\n",this.id,this.color.toString(),this.weight());
        }

        @Override
        String getForma() {
            return "Krug";
        }
        @Override
        public int compareTo(Forma forma) {
            return Float.compare(forma.weight(),weight());
        }
    }