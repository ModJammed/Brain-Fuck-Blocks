package com.dmillerw.brainFuckBlocks.tileentity;

import com.dmillerw.brainFuckBlocks.interfaces.IOutputPeripheral;

import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneInput extends TileEntity implements IOutputPeripheral {
	
	@Override
	public byte handleDataOutput() {
		return (byte) worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
	}

}
