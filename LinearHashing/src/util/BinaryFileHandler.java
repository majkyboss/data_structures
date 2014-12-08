package util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import def.Block;

public class BinaryFileHandler {
	public static byte[] loadBinaryFile(FileInputStream fileStream, int start, int length) {
		byte[] buffer = new byte[2];
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		BufferedInputStream in;
		try {
			in = new BufferedInputStream(fileStream);

			int c = 0;

			int bytesRead = 0;
			in.skip(start);
			while ((c = in.read(buffer)) > -1 && bytesRead < length) {
				out.write(buffer, 0, c);
				bytesRead += c;
			}

			return out.toByteArray();

			// writing to file
			// BufferedOutputStream bos = new BufferedOutputStream(new
			// FileOutputStream("/tmp/newFile.mp4"));
			// bos.write(out.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToBinaryFile(byte[] bytes, File file, int start, int length) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

			// move the cursor to the end of the file
			// you can move the cursor to any position inside the file to
			// write at random positions
			randomAccessFile.seek(start);
			randomAccessFile.write(bytes);

			randomAccessFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printBlock(Block block) {
		System.out.println(block.toString());
	}

	public static void trimFile(File file, int fromAddress) {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.setLength(fromAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
