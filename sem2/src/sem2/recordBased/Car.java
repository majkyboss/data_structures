package sem2.recordBased;

public class Car {
	public static final int carNumber_maxLength = 7*2;
	public static final int vinNumber_maxLength = 17*2;

	private String carNumber;
	private String vinNumber;
	private int weight;

	public Car(String carNumber, String vinNumber, int weight) {
		super();
		this.carNumber = carNumber;
		this.vinNumber = vinNumber;
		this.weight = weight;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public int getWeight() {
		return weight;
	}

}
