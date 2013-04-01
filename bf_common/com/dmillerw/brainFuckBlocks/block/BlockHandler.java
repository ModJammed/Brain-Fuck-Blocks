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
		bfCPU = new BlockCPU(BlockIDs.bfCPUID).setUnlocalizedName("bfCPU");
		GameRegistry.registerBlock(bfCPU, "bfCPU");
		LanguageRegistry.addName(bfCPU, "CPU");
		
		bfCode = new BlockCode(BlockIDs.bfCodeID).setUnlocalizedName("bfCode");
		GameRegistry.registerBlock(bfCode, ItemBlockCode.class, "bfCode");
		for (int i=0; i<BlockCode.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfCode.blockID, 1, i), BlockCode.blockNames[i]);
		}
		
		bfWire = new BlockWire(BlockIDs.bfWireID).setUnlocalizedName("bfWire");
		GameRegistry.registerBlock(bfWire, "bfWire");
		LanguageRegistry.addName(bfWire, "Wire");
		
		bfPeripheralRS = new BlockPeripheralRedstone(BlockIDs.bfPeripheralRedstoneID).setUnlocalizedName("bfPeripheral");
		GameRegistry.registerBlock(bfPeripheralRS, ItemBlockRedstone.class, "bfPeripheralRS");
		for (int i=0; i<BlockPeripheralRedstone.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfPeripheralRS.blockID, 1, i), BlockPeripheralRedstone.blockNames[i]);
		}
	}
	
}
