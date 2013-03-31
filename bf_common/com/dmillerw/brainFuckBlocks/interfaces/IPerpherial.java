package com.dmillerw.brainFuckBlocks.interfaces;

public interface IPerpherial {

	/**
	 * @return How this peripheral should be handled.
	 * 0 indicates the peripheral interprets data
	 * 1 indicates the peripheral sends data
	 */
	public byte getPeripheralType();
	
	public void handleDataInput(byte data);
	
	public byte handleDataOutput();
	
}
