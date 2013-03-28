package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block brainFuckCodeBlock;
	
	public static void init() {
		brainFuckCodeBlock = new BlockBrainFuckCode(BlockIDs.brainFuckCodeBlockID);
		GameRegistry.registerBlock(brainFuckCodeBlock, ItemBlockBrainFuckCode.class, "brainFuckCodeBlock");
		for (int i=0; i<BlockBrainFuckCode.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(BlockHandler.brainFuckCodeBlock.blockID, 1, i), BlockBrainFuckCode.blockNames[i]);
		}
	}
	
}
