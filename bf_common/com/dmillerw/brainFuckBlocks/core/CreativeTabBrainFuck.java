package com.dmillerw.brainFuckBlocks.core;

import com.dmillerw.brainFuckBlocks.block.BlockHandler;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabBrainFuck extends CreativeTabs {

	public CreativeTabBrainFuck(String name) {
		super(name);
	}
	
	public int getTabIconItemIndex() {
		return BlockHandler.brainFuckCodeBlock.blockID;
	}
	
}
