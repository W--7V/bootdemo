package local;


import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdsServer {
    public static void main(String[] args) {
        File f = new File("D:\\sock");
        byte[] buffer = new byte[128];
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try (AFUNIXServerSocket serverSocket = AFUNIXServerSocket.newInstance()) {
            serverSocket.bind(AFUNIXSocketAddress.of(f));
            System.out.println(serverSocket);
            while (true) {
                AFUNIXSocket socket = serverSocket.accept();
                executorService.execute(() -> {
                    System.out.println("client is " + socket);
                    try (InputStream inputStream = socket.getInputStream();
                         OutputStream outputStream = socket.getOutputStream();) {
                        while (true) {
                            outputStream.write("Hello client!".getBytes(StandardCharsets.UTF_8));
                            outputStream.flush();
                            int read = inputStream.read(buffer);
                            System.out.println("client say : " + new String(buffer, 0, read, StandardCharsets.UTF_8));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
