package local;

import com.sun.org.apache.regexp.internal.RE;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdsClient {
    public static void main(String[] args) {
        File f = new File("D:\\sock");
        byte[] buffer = new byte[128];
        Scanner in = new Scanner(System.in);
        try (AFUNIXSocket sock = AFUNIXSocket.newInstance()) {
            sock.connect(AFUNIXSocketAddress.of(f));
            System.out.println("connected!");
            InputStream inputStream = sock.getInputStream();
            OutputStream outputStream = sock.getOutputStream();
            while (true) {
                outputStream.write(in.nextLine().getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                int read = inputStream.read(buffer);
                System.out.println("client : " + new String(buffer, 0, read, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
