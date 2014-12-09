package def;

public class OvercrowdedBlock extends Block {
	private Integer ocAddress = null;

	public OvercrowdedBlock(Record[] records, int blockFactor) {
		super(records, blockFactor);
	}

	public Integer getOcAddress() {
		return ocAddress;
	}

	public void setOcAddress(Integer ocAddress) {
		this.ocAddress = ocAddress;
	}

}
