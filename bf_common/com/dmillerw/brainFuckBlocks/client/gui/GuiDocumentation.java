package com.dmillerw.brainFuckBlocks.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.gui.GuiScreen;

public class GuiDocumentation extends GuiScreen {

    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
	
	public void drawScreen(int par1, int par2, float par3) {
		System.out.println("Drawing screen");
		
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/gui/book.png");
        int k = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
        this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);

        super.drawScreen(par1, par2, par3);
    }
	
}
