package excercises.firstMidterm;

import javax.print.DocFlavor;
import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}
class GenericFraction<T extends Number,U extends Number>
{
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if(denominator.doubleValue()==0){
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
        double finalDenominator = this.denominator.doubleValue() * gf.denominator.doubleValue();
        double finalNumerator = (this.numerator.doubleValue() * gf.denominator.doubleValue()) + (this.denominator.doubleValue()*gf.numerator.doubleValue());
        while(finalDenominator%2 == 0&&finalNumerator%2==0){
            finalDenominator/=2;
            finalNumerator/=2;
        }
        for(int i = 3; i < Math.sqrt(finalDenominator); i++){
            if(finalNumerator%i == 0 && finalDenominator%i ==0){
                finalNumerator/=i;
                finalDenominator/=i;
                i--;
            }
        }
        return new GenericFraction<>(finalNumerator,finalDenominator);
    }
    double toDouble(){
        return this.numerator.doubleValue()/this.denominator.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f",numerator.doubleValue(),denominator.doubleValue());
    }
}
class ZeroDenominatorException extends Exception{
    public ZeroDenominatorException(String message) {
        super(message);
    }
}