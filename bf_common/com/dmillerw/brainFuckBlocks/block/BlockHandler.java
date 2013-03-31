package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block bfCode;
	public static Block bfCPU;
	public static Block bfWire;
	public static Block bfPeripheralRS;
	
	public static void init() {
		bfCode = new BlockCode(BlockIDs.bfCodeID).setUnlocalizedName("bfCode");
		GameRegistry.registerBlock(bfCode, ItemBlockCode.class, "bfCode");
		for (int i=0; i<BlockCode.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfCode.blockID, 1, i), BlockCode.blockNames[i]);
		}
		
		bfCPU = new BlockCPU(BlockIDs.bfCPUID).setUnlocalizedName("bfCPU");
		GameRegistry.registerBlock(bfCPU, "bfCPU");
		LanguageRegistry.addName(bfCPU, "CPU");
		
		bfWire = new BlockWire(BlockIDs.bfWireID).setUnlocalizedName("bfWire");
		GameRegistry.registerBlock(bfWire, "bfWire");
		LanguageRegistry.addName(bfWire, "Wire");
		
		bfPeripheralRS = new BlockPeripheralRedstone(BlockIDs.bfPeripheralRedstoneID).setUnlocalizedName("bfPeripheral");
		GameRegistry.registerBlock(bfPeripheralRS, "bfPeripheralRS");
		LanguageRegistry.addName(bfPeripheralRS, "Redstone Interpreter");
	}
	
}
