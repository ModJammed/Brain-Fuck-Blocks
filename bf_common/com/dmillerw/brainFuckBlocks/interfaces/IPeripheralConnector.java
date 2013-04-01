package com.dmillerw.brainFuckBlocks.interfaces;

public interface IPeripheralConnector {

	public boolean acceptsPeripherals(Class<? extends IPeripheral> type);
	
	public IPeripheral[] getConnectedPeripherals(Class<? extends IPeripheral> type);
	
}
