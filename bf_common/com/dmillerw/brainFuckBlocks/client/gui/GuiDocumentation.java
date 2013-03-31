package com.dmillerw.brainFuckBlocks.client.gui;

import org.lwjgl.opengl.GL11;

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
		
		GL11.glScalef(2F, 2F, 2F);
		font.drawStringWithShadow("Hello", 0, 0, 0xFFFFFF);
		GL11.glScalef(.5F, .5F, .5F);
		
		GuiUtils.enableDefaults();
	}
	
}
