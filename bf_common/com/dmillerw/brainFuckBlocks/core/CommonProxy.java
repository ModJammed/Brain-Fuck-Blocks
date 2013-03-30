package com.dmillerw.brainFuckBlocks.core;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCode;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityWire;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenders() {}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCode.class, "bfCode");
		GameRegistry.registerTileEntity(TileEntityCPU.class, "bfCPU");
		GameRegistry.registerTileEntity(TileEntityWire.class, "bfWire");
	}
	
}
