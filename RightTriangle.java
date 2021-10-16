/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class RightTriangle {
    public static void main(String[] args) {
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);
        int third = Integer.parseInt(args[2]);

        int hypothenus = Math.max(Math.max(first, second), third);
        int adjacent = Math.min(Math.min(first, second), third);
        int opposite = (first + second + third) - hypothenus - adjacent;

        boolean result = (hypothenus * hypothenus) == (adjacent*adjacent) + (opposite*opposite);
        result = result && hypothenus > 0 && opposite > 0 && adjacent > 0;
        System.out.println(result);
    }
}
