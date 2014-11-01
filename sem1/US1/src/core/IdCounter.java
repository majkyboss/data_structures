package core;

public class IdCounter {
	private static int i = -1;
	
	public static int getLastId(){
		return i;
	}
	
	public static int getNextId(){
		return ++i;
	}
}
