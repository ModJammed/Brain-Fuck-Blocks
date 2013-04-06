package com.dmillerw.brainFuckBlocks.core;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityByteMonitor;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityChatData;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCode;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityInput;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneData;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneInput;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityWire;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenders() {}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCode.class, "bfCode");
		GameRegistry.registerTileEntity(TileEntityCPU.class, "bfCPU");
		GameRegistry.registerTileEntity(TileEntityWire.class, "bfWire");
		GameRegistry.registerTileEntity(TileEntityChatData.class, "bfChatData");
		GameRegistry.registerTileEntity(TileEntityRedstoneData.class, "bfRedstoneData");
		GameRegistry.registerTileEntity(TileEntityRedstoneInput.class, "bfRedstoneInput");
		GameRegistry.registerTileEntity(TileEntityByteMonitor.class, "bfMonitor");
		GameRegistry.registerTileEntity(TileEntityInput.class, "bfInput");
	}
	
}
