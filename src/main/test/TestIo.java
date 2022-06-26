import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class TestIo {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10 * 1024 * 1024; i++) {
//        for (int i = 0; i <10; i++) {
            stringBuilder.append("i");
        }
        Scanner in = new Scanner(System.in);
//        in.nextLine();

        byte[] bytes = stringBuilder.toString().getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
        byteBuffer.put(bytes);
        long start1 = System.currentTimeMillis();
        long p = 0L;
        for (int i = 0; i < 10; i++) {
            byteBuffer.flip();
            p = testSequentIo(byteBuffer, i*p);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("SequentIo:" + (end1 - start1) / 1000.0);


        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            byteBuffer.flip();
            testRandomIo(bytes);
        }
        long end = System.currentTimeMillis();
        System.out.println("RandomIo:" + (end - start) / 1000.0);
    }

    public static long testSequentIo(ByteBuffer byteBuffer, long index) {
        File file = new File("sio.txt");
        RandomAccessFile randomAccessTargetFile;
//        MappedByteBuffer map;
        try {
            randomAccessTargetFile = new RandomAccessFile(file, "rw");
            randomAccessTargetFile.seek(randomAccessTargetFile.length());
            FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
//            System.out.println(targetFileChannel.position());
//            map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (long) 10 * 1024 * 1024);
            targetFileChannel.write(byteBuffer);
//            map.position(index);
//            map.put(s.getBytes());
//            return map.position();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return 0L;
    }

    public static void testRandomIo(byte[] bytes) {
        File file = new File("rio.txt");
        //创建FileWriter对象
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            //如果文件不存在，创建文件
            if (!file.exists())
                file.createNewFile();
            fos.write(bytes);
            fos.flush();
            fos.close();
//            writer = new BufferedWriter(new FileWriter(file, true), 10 * 1024 * 1024);
//            writer.write(s);//写入内容
//            writer.flush();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
