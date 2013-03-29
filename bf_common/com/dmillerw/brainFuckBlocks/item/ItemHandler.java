package com.dmillerw.brainFuckBlocks.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemHandler {

	public static Item bfWrench;

	public static void init() {
		bfWrench = new ItemWrench(ItemIDs.bfWrenchID).setUnlocalizedName("bfWrench");
		GameRegistry.registerItem(bfWrench, "bfWrench");
		LanguageRegistry.addName(bfWrench, "Wrench");
	}
	
}
