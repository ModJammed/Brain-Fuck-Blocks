package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block brainFuckCodeBlock;
	public static Block bfWire;
	public static Block bfCPU;
	
	public static void init() {
		brainFuckCodeBlock = new BlockBrainFuckCode(BlockIDs.brainFuckCodeBlockID).setUnlocalizedName("bfCode");
		GameRegistry.registerBlock(brainFuckCodeBlock, ItemBlockBrainFuckCode.class, "brainFuckCodeBlock");
		for (int i=0; i<BlockBrainFuckCode.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(BlockHandler.brainFuckCodeBlock.blockID, 1, i), BlockBrainFuckCode.blockNames[i]);
		}
		
		bfWire = new BlockWire(BlockIDs.bfWireID).setUnlocalizedName("bfWire");
		GameRegistry.registerBlock(bfWire, "bfWire");
		LanguageRegistry.addName(bfWire, "Wire");
		
		bfCPU = new BlockCPU(BlockIDs.bfCPUID).setUnlocalizedName("bfCPU");
		GameRegistry.registerBlock(bfCPU, "bfCPU");
		LanguageRegistry.addName(bfCPU, "CPU");
	}
	
}
