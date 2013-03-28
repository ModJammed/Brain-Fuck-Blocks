package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;

public class BlockHandler {

	public static Block brainFuckCodeBlock;
	
	public static void init() {
		brainFuckCodeBlock = new BlockBrainFuckCode(BlockIDs.brainFuckCodeBlockID);
	}
	
}
