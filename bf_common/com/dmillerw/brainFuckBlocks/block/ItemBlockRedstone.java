package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockRedstone extends ItemBlock {

	private static String[] subNames = new String[] {"redstoneData", "redstoneInput"};
	
	public ItemBlockRedstone(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + subNames[stack.getItemDamage()];
	}
	
}
