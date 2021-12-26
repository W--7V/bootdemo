package local;

import com.sun.deploy.net.socket.UnixDomainSocket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class TestNio {
	
	static String srcPath = "";
	static String tarPath = "";
	

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		testNio();
		
		long end = System.currentTimeMillis();
		System.out.println("nio:" + (end - start) / 1000.0);
	}

	public static void testNio() {
		try (FileInputStream inputStream = new FileInputStream("D:/111.mkv");
				FileOutputStream outputStream = new FileOutputStream("D:/1112.mkv", false);) {
			FileChannel sourceChannel = inputStream.getChannel();
			FileChannel targetChannel = outputStream.getChannel();

			for (long count = sourceChannel.size(); count > 0;) {
				long transferTo = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
				sourceChannel.position(sourceChannel.position() + transferTo);
				count -= transferTo;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testNioByMmb() {
		try (RandomAccessFile inputStream = new RandomAccessFile("D:/111.mkv", "rw");
				FileOutputStream outputStream = new FileOutputStream("D:/1112.mkv", false);) {
			FileChannel sourceChannel = inputStream.getChannel();
			FileChannel targetChannel = outputStream.getChannel();

			MappedByteBuffer buffer = sourceChannel.map(MapMode.READ_WRITE, 0, sourceChannel.size());
			sourceChannel.read(buffer);
			buffer.flip();
			targetChannel.write(buffer);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testIo() {
		try (FileInputStream inputStream = new FileInputStream("D:/111.mkv");
				FileOutputStream outputStream = new FileOutputStream("D:/1112.mkv", false);) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
