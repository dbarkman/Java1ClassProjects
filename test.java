// test.java
// David Barkman
// david.barkman@cox.net
// Feb 13, 2009
// Class - CIS163AA
// Section - 27602

import java.util.Random;

public class test {

    public static void main(String[] args) {

        int count = 0;
        while (count < 100) {
            Random r = new Random();
            int num = r.nextInt();
            Random r1 = new Random(num);
            int num1 = r.nextInt(9999);
            //System.out.println(num + " - " + num1);
            //double num = Math.random();
            if (num1 % 3 == 0) {
                System.out.println("paper");
            } else if (num1 % 5 == 0) {
                System.out.println("rock");
            } else if (num1 % 7 == 0) {
                System.out.println("scisors");
            }
//            try {
//            Thread.sleep(27);
//            } catch (InterruptedException e) {
//            e.printStackTrace();
//            }

            count++;
        }
    }
}
