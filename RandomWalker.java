/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class RandomWalker {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        int x = 0, y = 0;
        int xModified, yModified;
        int distance = 0, steps = 0;
        System.out.println("("+ x + ", " + y + ")");
        while (distance != r) {
            double probability = Math.random();
            if (probability < 0.25) x++;
            else if (probability < 0.5) y++;
            else if (probability < 0.75) x--;
            else y--;
            System.out.println("("+ x + ", " + y + ")");
            if (x < 0) {
                xModified = x * -1;
            } else xModified = x;
            if (y < 0) {
                yModified = y * -1;
            } else yModified = y;
            distance = xModified + yModified;
            steps++;
        }
        System.out.println("steps = " + steps);
    }
}
