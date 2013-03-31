package com.dmillerw.brainFuckBlocks.tileentity;

import net.minecraft.tileentity.TileEntity;

import com.dmillerw.brainFuckBlocks.interfaces.IPerpherial;

public class TileEntityRSInterpreter extends TileEntity implements IPerpherial {

	public byte rsOutput = 0;
	
	@Override
	public byte getPeripheralType() {
		return 0;
	}

	@Override
	public void handleDataInput(byte data) {
		rsOutput = data;
	}

	@Override
	public byte handleDataOutput() {
		return 0;
	}

}
