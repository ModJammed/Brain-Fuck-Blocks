package com.dmillerw.brainFuckBlocks.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBFWrench {

	public void onWrenched(World world, int x, int y, int z, EntityPlayer player, ItemStack wrench);
	
}
