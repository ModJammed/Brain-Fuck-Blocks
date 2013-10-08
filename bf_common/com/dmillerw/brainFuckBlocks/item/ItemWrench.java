package com.dmillerw.brainFuckBlocks.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;

public class ItemWrench extends Item implements IBFWrench {

	private Icon texture;
	
	public ItemWrench(int id) {
		super(id);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setMaxStackSize(1);
		setMaxDamage(500);
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void onWrenched(World world, int x, int y, int z, EntityPlayer player, ItemStack wrench) {
		if (!player.capabilities.isCreativeMode) {
			wrench.damageItem(1, player);
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wrench");
	}
	
}
