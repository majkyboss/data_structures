package util;

public class BitConverter {
	public static final int INT_SIZE = Integer.BYTES;
	public static final int LONG_SIZE = Long.BYTES;

	public static void putInt(int value, byte[] array, int offset) {
		array[offset] = (byte) (0xff & (value >> 24));
		array[offset + 1] = (byte) (0xff & (value >> 16));
		array[offset + 2] = (byte) (0xff & (value >> 8));
		array[offset + 3] = (byte) (0xff & value);
	}

	public static int getInt(byte[] array, int offset) {
		//@f:off
		return ((array[offset] & 0xff) << 24) | 
				((array[offset + 1] & 0xff) << 16) | 
				((array[offset + 2] & 0xff) << 8) | 
				(array[offset + 3] & 0xff);
		//@f:on
	}

	public static void putLong(long value, byte[] array, int offset) {
		array[offset] = (byte) (0xff & (value >> 56));
		array[offset + 1] = (byte) (0xff & (value >> 48));
		array[offset + 2] = (byte) (0xff & (value >> 40));
		array[offset + 3] = (byte) (0xff & (value >> 32));
		array[offset + 4] = (byte) (0xff & (value >> 24));
		array[offset + 5] = (byte) (0xff & (value >> 16));
		array[offset + 6] = (byte) (0xff & (value >> 8));
		array[offset + 7] = (byte) (0xff & value);
	}

	public static long getLong(byte[] array, int offset) {
		//@f:off
		return ((long) (array[offset] & 0xff) << 56) | 
				((long) (array[offset + 1] & 0xff) << 48) |
				((long) (array[offset + 2] & 0xff) << 40) | 
				((long) (array[offset + 3] & 0xff) << 32) | 
				((long) (array[offset + 4] & 0xff) << 24) | 
				((long) (array[offset + 5] & 0xff) << 16) | 
				((long) (array[offset + 6] & 0xff) << 8) | 
				((long) (array[offset + 7] & 0xff));
		//@f:on
	}

	/**
	 * Inserts entered String value into byte array. Every character of string
	 * is encoded into pair of bytes. Encoded string starts to be stored into
	 * array at "offset" index.
	 * 
	 * @param value
	 *            string value to store
	 * @param array
	 *            byte array for storing encoded string
	 * @param offset
	 *            number of skipped items in array
	 */
	public static void putString(String value, byte[] array, int offset) {
		// // works
		// char ch = value.charAt(0);
		// // storing
		// byte[] charStored = new byte[Character.BYTES];
		// charStored[0] = (byte) (0xff & (ch >> 8));
		// charStored[0 + 1] = (byte) (0xff & ch);
		// // loading
		// short charLoadedShort = (short) (((charStored[0] & 0xff) << 8) |
		// (charStored[0 + 1] & 0xff));
		// char[] charLoaded = Character.toChars(charLoadedShort);
		// // ---------

		int charPosition = 0;
		for (int i = 0; i < value.toCharArray().length; i++) {
			char charToStore = value.charAt(i);
			array[offset + charPosition] = (byte) (0xff & (charToStore >> 8));
			array[offset + charPosition + 1] = (byte) (0xff & charToStore);
			charPosition += 2;
		}
	}

	/**
	 * Reads "length" bytes from source array and converts them to String.
	 * Supposed that one character is stored in pair of bytes.
	 * 
	 * @param array
	 *            source byte array
	 * @param offset
	 *            number of skipped places in array
	 * @param length
	 *            length of
	 * @return read bytes transformed to String value
	 */
	public static String getString(byte[] array, int offset, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i += 2) {
			short charLoadedShort = (short) (((array[offset + i] & 0xff) << 8) | (array[offset + i + 1] & 0xff));
			char[] charLoaded = Character.toChars(charLoadedShort);
			sb.append(charLoaded);
		}
		return sb.toString();
	}

	/**
	 * Copy source byte array into toArray byte array, starts at offset position
	 * in toArray byte array
	 * 
	 * @param source
	 *            source byte array
	 * @param toArray
	 *            destination byte array
	 * @param offset
	 *            number of skipped places in toArray byte array
	 */
	public static void putBytes(byte[] source, byte[] toArray, int offset) {
		for (int i = 0; i < source.length && i < toArray.length; i++) {
			toArray[offset + i] = source[i];
		}
	}

	public static void putShort(short value, byte[] array, int offset) {
		array[offset] = (byte) (0xff & (value >> 8));
		array[offset + 1] = (byte) (0xff & value);
	}

	public static short getShort(byte[] array, int offset) {

		//@f:off
		return (short) (((array[offset] & 0xff) << 8) | 
						 (array[offset + 1] & 0xff));
		//@f:on
	}
}
