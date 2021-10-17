/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class DiscreteDistribution {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int arraySize = args.length - 1;
        int[] array = new int[arraySize];
        int[] cumSum = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = Integer.parseInt(args[i + 1]);
        }
        int initialSum = 0;
        for (int i = 0; i < arraySize; i++) {
            initialSum += array[i];
            cumSum[i] = initialSum;
        }
        for (int i = 0; i < m; i++) {
            int r = (int) (Math.random() * (cumSum[arraySize - 1] - 1));
            int uniqueIndex = 1;
            for (int j = 0; j < arraySize; j++) {
                if (r < cumSum[j])
                    break;
                uniqueIndex++;
            }
            System.out.print(uniqueIndex + " ");
        }
        System.out.println();
    }
}
