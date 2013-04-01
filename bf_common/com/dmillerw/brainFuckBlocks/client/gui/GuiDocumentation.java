package com.dmillerw.brainFuckBlocks.client.gui;

import org.lwjgl.opengl.GL11;

import com.dmillerw.brainFuckBlocks.lib.ModInfo;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.gui.GuiScreen;

public class GuiDocumentation extends GuiScreen {

    private int bookImageWidth = 280;
    private int bookImageHeight = 251;
	
	public void drawScreen(int par1, int par2, float par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/"+ModInfo.MOD_ID.toLowerCase()+"/textures/gui/gui_doc.png");
        int k = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
        this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);

        super.drawScreen(par1, par2, par3);
    }
	
}
