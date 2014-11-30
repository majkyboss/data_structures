package sem2;

public class LinearHash {
	private final Block[] blocks;
	private final String unsortedFilePath = "dataDir/unsortedFile";

	public LinearHash(int blocksNumber) {
		super();
//		this.blocks = new Block[blocksNumber];
		this.blocks = new Block[4];
	}

}
