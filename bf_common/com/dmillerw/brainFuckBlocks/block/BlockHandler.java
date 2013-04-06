package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block bfCode;
	public static Block bfCPU;
	public static Block bfWire;
	public static Block bfPeripheral;
	public static Block bfMonitor;
	public static Block bfInput;
	
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
		
		bfPeripheral = new BlockPeripheral(BlockIDs.bfPeripheralID).setUnlocalizedName("bfPeripheral");
		GameRegistry.registerBlock(bfPeripheral, ItemBlockPeripheral.class, "bfPeripheralRS");
		for (int i=0; i<BlockPeripheral.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfPeripheral.blockID, 1, i), BlockPeripheral.blockNames[i]);
		}
		
		bfMonitor = new BlockMonitor(BlockIDs.bfMonitorID).setUnlocalizedName("bfMonitor");
		GameRegistry.registerBlock(bfMonitor, ItemBlockMonitor.class, "bfMonitor");
		for (int i=0; i<BlockMonitor.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfMonitor.blockID, 1, i), BlockMonitor.blockNames[i]);
		}
		
		bfInput = new BlockInput(BlockIDs.bfInput).setUnlocalizedName("bfInput");
		GameRegistry.registerBlock(bfInput, ItemBlockInput.class, "bfInput");
		LanguageRegistry.addName(new ItemStack(bfInput, 1, 0), "Input Peripheral");
	}
	
}
