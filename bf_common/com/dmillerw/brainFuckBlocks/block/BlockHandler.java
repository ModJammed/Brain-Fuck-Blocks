package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block bfCode;
	public static Block bfCPU;
	
	public static void init() {
		bfCode = new BlockBrainFuckCode(BlockIDs.bfCodeID).setUnlocalizedName("bfCode");
		GameRegistry.registerBlock(bfCode, ItemBlockBrainFuckCode.class, "bfCode");
		for (int i=0; i<BlockBrainFuckCode.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(BlockHandler.bfCode.blockID, 1, i), BlockBrainFuckCode.blockNames[i]);
		}
		
		bfCPU = new BlockCPU(BlockIDs.bfCPUID).setUnlocalizedName("bfCPU");
		GameRegistry.registerBlock(bfCPU, "bfCPU");
		LanguageRegistry.addName(bfCPU, "CPU");
	}
	
}
