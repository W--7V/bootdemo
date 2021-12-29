package local;

public class TestThreadLocal {

    public static void main(String[] args) {
        ThreadLocal tl = new ThreadLocal();
        tl.set("123");
        tl.get();
    }
}
