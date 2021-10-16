/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class ThreeSort {
    public static void main(String[] args) {
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);
        int third = Integer.parseInt(args[2]);

        int maximum = Math.max(Math.max(first, second), third);
        int minimum = Math.min(Math.min(first, second), third);
        int sum = first + second + third;
        System.out.printf("%d %d %d\n", minimum, sum - minimum - maximum, maximum);
    }
}
