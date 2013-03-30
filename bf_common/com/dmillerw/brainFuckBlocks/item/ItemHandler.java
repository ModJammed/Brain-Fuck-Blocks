package com.dmillerw.brainFuckBlocks.item;

import com.dmillerw.brainFuckBlocks.lib.UserPreferences;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemHandler {

	public static Item bfWrench;
	public static Item bfCodeWriter;

	public static void init() {
		bfWrench = new ItemWrench(ItemIDs.bfWrenchID).setUnlocalizedName("bfWrench");
		GameRegistry.registerItem(bfWrench, "bfWrench");
		LanguageRegistry.addName(bfWrench, "Wrench");
		
		if (!UserPreferences.codeBlockCraftingEnable) {
			bfCodeWriter = new ItemCodeWriter(ItemIDs.bfCodeWriterID).setUnlocalizedName("bfCodeWriter");
			GameRegistry.registerItem(bfCodeWriter, "bfCodeWriter");
			LanguageRegistry.addName(bfCodeWriter, "Code Writer");
		}
	}
	
}
