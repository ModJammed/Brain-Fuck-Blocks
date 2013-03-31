package com.dmillerw.brainFuckBlocks.tileentity;

import com.dmillerw.brainFuckBlocks.interfaces.IPerpherial;

import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneInput extends TileEntity implements IPerpherial {

	@Override
	public byte getPeripheralType() {
		return 1;
	}

	@Override
	public void handleDataInput(byte data) {}

	@Override
	public byte handleDataOutput() {
		return (byte) worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
	}

}
