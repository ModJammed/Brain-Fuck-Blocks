package com.dmillerw.brainFuckBlocks.core;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityBrainFuckCode;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBrainFuckCode.class, "brainFuckTE");
	}
	
}
