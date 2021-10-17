/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Birthday {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        double fraction = 0.0;
        int x = 1;
        int count = 0;
        while (true) {
            System.out.println(x + "\t" + count + "\t" + fraction);
            if (fraction >= 0.5)
                break;
            int[] birthdayArray = new int[n + 2];
            int[] birthdayArraySum = new int[n + 2];
            for (int i = 0; i < trials; i++) {
                boolean[] birthdaysInRoom = new boolean[n];
                int peopleInRoom = 0;
                while (true) {
                    int nextBirthday = (int) (Math.random() * (n - 1));
                    peopleInRoom++;
                    if (birthdaysInRoom[nextBirthday]) {
                        count++;
                        break;
                    }
                    birthdaysInRoom[nextBirthday] = true;
                }
                birthdayArray[peopleInRoom]++;
            }
            int initialSum = 0;
            for (int k = 0; k < birthdayArray.length; k++) {
                initialSum += birthdayArray[k];
                birthdayArraySum[k] = initialSum;
            }
            fraction = birthdayArraySum[x + 1] / (double) trials;
            count = birthdayArray[x + 1];
            x++;
        }

    }
}
