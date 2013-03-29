package com.dmillerw.brainFuckBlocks.client;

import com.dmillerw.brainFuckBlocks.client.render.RenderBlockWire;
import com.dmillerw.brainFuckBlocks.core.CommonProxy;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		RenderingRegistry.registerBlockHandler(new RenderBlockWire());
	}
	
}
