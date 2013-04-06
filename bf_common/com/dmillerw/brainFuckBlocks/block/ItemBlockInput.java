package com.dmillerw.brainFuckBlocks.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockInput extends ItemBlock {

	private static String[] subNames = new String[] {"inputChar"};
	private static String[] ioTypes = new String[] {"Input"};
	
	public ItemBlockInput(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(ioTypes[stack.getItemDamage()]);
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
