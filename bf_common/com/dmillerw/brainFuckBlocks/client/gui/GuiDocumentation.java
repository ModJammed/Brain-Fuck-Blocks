package com.dmillerw.brainFuckBlocks.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import kovu.utils.gui.GuiUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class GuiDocumentation extends GuiScreen {

	public int page = 1;
	public int xSub = (int) (GuiUtils.getScreenWidth() * 0.8);
	public int ySub = (int) (GuiUtils.getScreenHeight() * 0.8);
	
	private FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
	
	public void drawCenteredString(String text, int yPos, int color) {
		font.drawString(text, (GuiUtils.getScreenWidth() / 2) - (font.getStringWidth(text) / 2), yPos, color);
	}
	
	public void render() {
		GuiUtils.disableDefaults();
		GuiUtils.renderRect(xSub, ySub, GuiUtils.getScreenWidth() - xSub, GuiUtils.getScreenHeight() - ySub, 0, 0, 0, 122);
		GL11.glScalef(2F, 2F, 2F);
		drawCenteredString("Hello", 100, 0x000000);
		GL11.glScalef(.5F, .5F, .5F);
		GuiUtils.enableDefaults();
	}
	
}
