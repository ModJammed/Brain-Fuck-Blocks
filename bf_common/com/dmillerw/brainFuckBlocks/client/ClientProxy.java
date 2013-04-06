package com.dmillerw.brainFuckBlocks.client;

import com.dmillerw.brainFuckBlocks.client.render.TileEntityByteMonitorRenderer;
import com.dmillerw.brainFuckBlocks.client.render.TileEntityInputRenderer;
import com.dmillerw.brainFuckBlocks.core.CommonProxy;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityByteMonitor;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityInput;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityByteMonitor.class, new TileEntityByteMonitorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInput.class, new TileEntityInputRenderer());
	}
	
}
