package sem2.core;

import sem2.blocksBased.Block;
import util.BitConverter;

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
public class ContinueIntBlock extends Block<Integer> {
	// TODO change to abstract class and make infinityIntBlock (continue block
	// with address to another blocks as value)
	private int overCrowdingBlockAddress = -1;

	public ContinueIntBlock(short blockFactor) {
		super(Integer.BYTES, blockFactor);
	}

	public ContinueIntBlock(byte[] bytes) {
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

	public void setOverCrowdingBlockAddress(int overCrowdingBlockAddress) {
		this.overCrowdingBlockAddress = overCrowdingBlockAddress;
	}

	@Override
	protected int getHeaderSize() {
		int retVal = super.getHeaderSize();
		retVal += Integer.BYTES;
		return retVal;
	}

	@Override
	protected byte[] getHeader() {
		byte[] bytes = new byte[getHeaderSize()];
		int offset = 0;
		BitConverter.putBytes(super.getHeader(), bytes, offset);
		offset += super.getHeaderSize();

		return bytes;
	}

	@Override
	protected boolean addToFullBlock(Integer value) {
		// TODO Auto-generated method stub
		return false;
	}

}
