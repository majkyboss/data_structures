package sem2.core;

import sem2.blocksBased.Block;

/**
 * Adding the value:
 * If {@link #getFreeRecord()} returns null value then set
 * {@link #overCrowdingBlockAddress} address to continuing block and use this
 * block <br>
 * <br>
 * Getting the value:
 * If {@link #getFreeRecord()} returns null value then take
 * {@link #overCrowdingBlockAddress} address and continue finding in this block
 * 
 * @author Banik
 *
 */
public class ContinuingIntBlock extends Block<Integer> {
	// TODO change to abstract class and make infinityIntBlock (continue block
	// with address to another blocks as value)
	private int overCrowdingBlockAddress;

	public ContinuingIntBlock(int recordByteSize, short blockFactor) {
		super(recordByteSize, blockFactor);
	}

	public ContinuingIntBlock(byte[] bytes) {
		super(bytes);
	}

	@Override
	protected void initRecords() {
		if (records != null) {
			for (int i = 0; i < records.length; i++) {
				records[i] = new IntegerRecord();
			}
		}
	}

	public int getOverCrowdingBlockAddress() {
		return overCrowdingBlockAddress;
	}

}