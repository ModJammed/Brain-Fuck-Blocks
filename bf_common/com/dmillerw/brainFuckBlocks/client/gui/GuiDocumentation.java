package com.dmillerw.brainFuckBlocks.client.gui;

import kovu.utils.gui.GuiUtils;
import net.minecraft.client.gui.GuiScreen;

public class GuiDocumentation extends GuiScreen {

	public int page = 1;
	
	public void render() {
		GuiUtils.drawRect(0, 0, 500, 500, 0x000000);
	}
	
}
