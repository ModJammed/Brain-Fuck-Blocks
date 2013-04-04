package com.dmillerw.brainFuckBlocks.tileentity;

import com.dmillerw.brainFuckBlocks.interfaces.IOutputPeripheral;

import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneInput extends TileEntity implements IOutputPeripheral {
	
	@Override
	public byte handleDataOutput() {
		//Because 1.4.7 doesn't have signal strength, it interprets a simple on/off signal instead
		return (byte) (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) ? 15 : 0);
	}

}
