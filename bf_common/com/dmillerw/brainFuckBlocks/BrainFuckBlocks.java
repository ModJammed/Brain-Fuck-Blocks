package com.dmillerw.brainFuckBlocks;

import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.network.BFPacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION)
@NetworkMod(serverSideRequired=false, clientSideRequired=true, packetHandler=BFPacketHandler.class)
public class BrainFuckBlocks {

	@PreInit
	public void preInit(FMLPreInitializationEvent e) {
		
	}
	
	@Init
	public void init(FMLInitializationEvent e) {
		
	}
	
}
