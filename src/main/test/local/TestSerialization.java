package local;

import java.io.*;

public class TestSerialization {
    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("aaa.txt"));
            oos.writeObject(new TestSerialization.Ts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Ts implements Serializable {
        int id = 1;
    }
}
