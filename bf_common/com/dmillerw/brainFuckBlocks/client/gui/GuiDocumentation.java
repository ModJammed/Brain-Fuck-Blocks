package com.dmillerw.brainFuckBlocks.client.gui;

import cpw.mods.fml.client.FMLClientHandler;
import kovu.utils.gui.GuiUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class GuiDocumentation extends GuiScreen {

	public int page = 1;
	
	public void render() {
		GuiUtils.disableDefaults();
		GuiUtils.renderRect(0, 0, GuiUtils.getScreenWidth(), GuiUtils.getScreenHeight(), 0, 0, 0, 122);
		
		FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
		
		font.drawStringWithShadow("Hello", 0, 0, 0x000000);
		
		GuiUtils.enableDefaults();
	}
	
}
