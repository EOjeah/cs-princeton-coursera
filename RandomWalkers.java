/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class RandomWalkers {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        double totalSteps = 0;
        for (int i = 0; i < trials; i++) {
            int x = 0, y = 0;
            int distance = 0, steps = 0;
            while (distance != r) {
                double probability = Math.random();
                if (probability < 0.25) x++;
                else if (probability < 0.5) y++;
                else if (probability < 0.75) x--;
                else y--;
                if (x < 0) x = x * -1;
                if (y < 0) y *= -1;
                distance = x + y;
                steps++;
            }
            totalSteps += steps;
        }
        double averageSteps = totalSteps / trials;
        System.out.println("average number of steps = " + averageSteps);

    }
}
