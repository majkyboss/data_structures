package core.data;

public class IdCounter {
	private static int i = -1;

	public static int getLastId() {
		return i;
	}

	public static int getNextId() {
		return ++i;
	}

	public static int getFutureId() {
		return i + 1;
	}
}
