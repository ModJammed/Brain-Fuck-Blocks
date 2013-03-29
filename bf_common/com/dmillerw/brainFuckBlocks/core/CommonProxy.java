package com.dmillerw.brainFuckBlocks.core;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityBrainFuckCode;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenders() {}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBrainFuckCode.class, "brainFuckTE");
		GameRegistry.registerTileEntity(TileEntityCPU.class, "bfCPU");
	}
	
}
