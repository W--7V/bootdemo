package local;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    static Selector selector = null;

    static Charset charset = Charset.forName("UTF-8");
    static CharsetDecoder decoder = charset.newDecoder();


    public static void main(String[] args) {

        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("192.168.1.103", 88));
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            Charset charset = Charset.forName("UTF-8");
//            CharsetDecoder decoder = charset.newDecoder();
            while (true) {

                while (selector.select(0) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if(key.isAcceptable()){
                            processAccept(key);
                        }else {
                            processRead(key);
                        }

                    }
                }
//                SocketChannel socketChannel = serverSocketChannel.accept();
//
//                if (socketChannel != null) {
//                    System.out.println("socketChannel is " + socketChannel);
//                    int read = socketChannel.read(byteBuffer);
//                    byteBuffer.flip();
//                    CharBuffer charbuffer = decoder.decode(byteBuffer);
//                    byteBuffer.clear();
//                    System.out.print(charbuffer.toString());
//                } else {
//                    Thread.sleep(1000);
//                    System.out.println("socketChannel is null " + System.currentTimeMillis());
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processAccept(SelectionKey key){
        ServerSocketChannel channel = (ServerSocketChannel)key.channel();
        SocketChannel socketChannel = null;
        try {
            socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("socketChannel is " + socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRead(SelectionKey key){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = (SocketChannel)key.channel();

        try {
            int read = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer charbuffer = decoder.decode(byteBuffer);
            byteBuffer.clear();
            System.out.print(charbuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
