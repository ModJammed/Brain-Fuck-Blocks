package com.dmillerw.brainFuckBlocks.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.dmillerw.brainFuckBlocks.core.CommonProxy;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		MinecraftForgeClient.preloadTexture(ModInfo.BLOCK_TEXTURE_LOCATION);
		MinecraftForgeClient.preloadTexture(ModInfo.ITEM_TEXTURE_LOCATION);
	}
	
}
