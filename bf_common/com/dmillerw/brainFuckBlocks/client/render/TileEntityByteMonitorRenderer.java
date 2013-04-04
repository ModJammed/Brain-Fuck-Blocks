package com.dmillerw.brainFuckBlocks.client.render;

import org.lwjgl.opengl.GL11;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityByteMonitor;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityByteMonitorRenderer extends TileEntitySpecialRenderer {

	public void renderMonitorAt(TileEntityByteMonitor tile, double x, double y, double z, float f) {
		String toDisplay = ""+tile.storedData;

		if (tile.type == 1) {
			toDisplay = ""+(char)tile.storedData;
		}
		
		GL11.glPushMatrix();
		GL11.glTranslated(x+.5, y+.5, z+.5);
		FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
		GL11.glTranslatef(0, 1, 0);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslated(x+.5, y+.5, z+.5);
		
		byte rotation = 0;
		if (tile.getRotation().getOpposite() == ForgeDirection.NORTH) {
			rotation = 0;
			GL11.glRotatef(180, 0, 0, 1);
		} else if (tile.getRotation().getOpposite() == ForgeDirection.WEST) {
			rotation = 1;
			GL11.glRotatef(180, 1, 0, 0);
		} else if (tile.getRotation().getOpposite() == ForgeDirection.SOUTH) {
			rotation = 2;
			GL11.glRotatef(180, 0, 0, 1);
		} else if (tile.getRotation().getOpposite() == ForgeDirection.EAST) {
			rotation = 3;
			GL11.glRotatef(180, 1, 0, 0);
		}
		GL11.glRotatef((rotation * 360) / 4, 0F, 1F, 0F);
		GL11.glTranslatef(0, 0, -0.51F);
		for (int i=0; i<5; i++) {
			GL11.glScalef(.5F, .5F, .5F);
		}
		
		GL11.glTranslatef(-font.getStringWidth(toDisplay) / 2, -font.FONT_HEIGHT / 2, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		font.drawString(toDisplay, 0, 0, 0xFFFFFF);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderMonitorAt((TileEntityByteMonitor) var1, var2, var4, var6, var8);
	}

}
