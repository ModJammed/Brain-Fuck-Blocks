package com.dmillerw.brainFuckBlocks.interfaces;

public interface IPayloadReceiver {

	public void handlePayload(int[] payload);
	
	public int[] getPayload();
	
}
