/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class GeneralizedHarmonic {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            double sub = i;
            int total = 0;
            if (r > 0) {
                total = r;
            } else if (r < 0) {
                total = r * -1;
            }
            for (int j = 1; j < total; j++) {
                sub = sub * i;
            }
            if (r > 0) {
                sum += 1.0 / sub;
            } else if (r < 0) {
                sum += sub;
            }
            else sum += 1;
            // System.out.println("sub " + sub);
        }
        System.out.println(sum);
    }
}
