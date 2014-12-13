package def;

import java.util.Arrays;

import util.BitConverter;

public class OvercrowdedBlock extends Block {
	private int ocAddress = new Integer(-1);

	public OvercrowdedBlock(Record[] records, int blockFactor) {
		super(records, blockFactor);
		ocAddress = new Integer(-1);
	}

	public int getOcAddress() {
		return ocAddress;
	}

	public void setOcAddress(int ocAddress) {
		this.ocAddress = ocAddress;
	}

	@Override
	public int getByteSize() {
		return super.getByteSize() + Integer.BYTES;
	}

	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[getByteSize()];
		int offset = 0;
		BitConverter.putInt(ocAddress, bytes, offset);
		offset += Integer.BYTES;

		byte[] superBytes = new byte[super.getByteSize()];
		superBytes = Arrays.copyOfRange(super.getBytes(), 0, super.getByteSize());
		BitConverter.putBytes(superBytes, bytes, offset);
		return bytes;
	}

	@Override
	public void fillFromBytes(byte[] bytes) {
		boolean empty = true;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != 0x00) {
				empty = false;
				break;
			}
		}

		int offset = 0;
		if (empty) {
			ocAddress = -1;
		} else {
			ocAddress = BitConverter.getInt(bytes, offset);
		}
		offset += Integer.BYTES;

		super.fillFromBytes(Arrays.copyOfRange(bytes, offset, bytes.length));
	}
}
