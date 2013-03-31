package com.dmillerw.brainFuckBlocks.core;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	public static boolean shouldRender = false;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.RENDER)) {
			Minecraft mc = FMLClientHandler.instance().getClient();
			
			if (mc.currentScreen == null) {
				onRenderTick();
			}
		}
	}

	public void onRenderTick() {
		if (shouldRender) {
			//Render documentation
		}
	}
	
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Client Tick Handler";
	}

}
