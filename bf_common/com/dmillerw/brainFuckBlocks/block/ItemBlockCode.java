package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCode extends ItemBlock {

	private static String[] subNames = new String[] {"dataInc", "dataDec", "byteInc", "byteDec", "output", "input", "bracketOpen", "bracketClose", "machineCasing"};
	
	public ItemBlockCode(int id) {
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
