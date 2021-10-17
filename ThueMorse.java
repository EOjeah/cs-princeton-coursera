/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class ThueMorse {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean[] initial = new boolean[n * n];
        if (n > 1)
            initial[1] = !initial[0];
        int index = 2;
        while (index < n * n) {
            for (int i = 0; i < index; i++) {
                if (i + index >= initial.length)
                    break;
                initial[i + index] = !initial[i];
            }
            index *= 2;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initial[i] == initial[j])
                    System.out.print("+  ");
                else
                    System.out.print("-  ");
            }
            System.out.println();
        }
    }
}
