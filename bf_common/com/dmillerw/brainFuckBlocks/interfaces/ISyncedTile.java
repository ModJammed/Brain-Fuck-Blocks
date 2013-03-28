package com.dmillerw.brainFuckBlocks.interfaces;

public interface ISyncedTile {

	public int[] getPayload();
	
	public void handlePayload(int[] payload);
	
}
