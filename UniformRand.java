/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class UniformRand {
    public static void main(String[] args) {
        double a = Math.random();
        double b = Math.random();
        double c = Math.random();
        double d = Math.random();
        double e = Math.random();
        System.out.printf("%f %f %f %f %f\n", a, b, c, d, e);
        double mininum = Math.min(Math.min(Math.min(a,b), Math.min(c,d)), e);
        double maximum = Math.max(Math.max(Math.max(a,b), Math.max(c,d)), e);
        double average = (a + b + c + d + e ) / 5.0;
        System.out.printf("Average: %f\n", average);
        System.out.printf("Minimum: %f\n", mininum);
        System.out.printf("Maximum: %f\n", maximum);

    }
}
