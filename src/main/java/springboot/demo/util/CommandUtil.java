package springboot.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandUtil {

    public static String run(String command) throws IOException {
        Scanner input = null;
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
            InputStream is = process.getInputStream();
            input = new Scanner(is);
            while (input.hasNextLine()) {
                result += input.nextLine() + "\n";
            }
            result = command + "\n" + result;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }
}
