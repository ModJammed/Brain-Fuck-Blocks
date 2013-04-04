package com.dmillerw.brainFuckBlocks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmillerw.brainFuckBlocks.lib.UserPreferences;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item bfWrench;
	public static Item bfCodeWriter;
	public static Item bfCraftingComponent;

	public static void init() {
		bfWrench = new ItemWrench(ItemIDs.bfWrenchID).setItemName("bfWrench");
		GameRegistry.registerItem(bfWrench, "bfWrench");
		LanguageRegistry.addName(bfWrench, "Wrench");
		
		if (!UserPreferences.codeBlockCraftingEnable) {
			bfCodeWriter = new ItemCodeWriter(ItemIDs.bfCodeWriterID).setItemName("bfCodeWriter");
			GameRegistry.registerItem(bfCodeWriter, "bfCodeWriter");
			LanguageRegistry.addName(bfCodeWriter, "Code Writer");
		}
		
		bfCraftingComponent = new ItemCraftingComponent(ItemIDs.bfCraftingComponentID).setItemName("bfCraftingComponent");
		GameRegistry.registerItem(bfCraftingComponent, "bfCraftingComponent");
		for (int i=0; i<ItemCraftingComponent.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(bfCraftingComponent, 1, i), ItemCraftingComponent.itemNames[i]);
		}
	}
	
}
