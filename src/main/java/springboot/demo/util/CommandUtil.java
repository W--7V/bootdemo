package springboot.demo.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommandUtil {

    public static String run(String command) throws IOException {
        String result = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            try {
                //等待命令执行完成
                process.waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }
}
