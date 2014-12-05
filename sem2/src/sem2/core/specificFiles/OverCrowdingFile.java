package sem2.core.specificFiles;

import sem2.blocksBased.BlockFile;

public class OverCrowdingFile<T> extends BlockFile<T> {

	public OverCrowdingFile(String path, int blockFactor, int recordByteSize) {
		super(path, blockFactor, recordByteSize);
	}

	/**
	 * Return address of first FREE (or EMPTY) block in file
	 */
	@Override
	public int getValidAddress() {
		return super.getValidAddress();
	}

}
