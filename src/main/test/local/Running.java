package local;

import java.util.Random;
import java.util.UUID;

public class Running {

    public String f1(String a, int b) {
        System.out.println(a + " " + b);
        return a;
    }

    public static void main(String[] args) {
        Running rc = new Running();
        while (true) {
            rc.f1(UUID.randomUUID().toString(), new Random().nextInt());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}
