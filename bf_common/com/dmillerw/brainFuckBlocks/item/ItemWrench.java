package com.dmillerw.brainFuckBlocks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.util.TextureCoordinates;

public class ItemWrench extends Item implements IBFWrench {

	public ItemWrench(int id) {
		super(id);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setMaxStackSize(1);
		setMaxDamage(500);
	}

	@Override
	public int getIconFromDamage(int damage) {
		return new TextureCoordinates(0, 2).getTextureIndex();
	}
	
	@Override
	public void onWrenched(World world, int x, int y, int z, EntityPlayer player, ItemStack wrench) {
		if (!player.capabilities.isCreativeMode) {
			wrench.damageItem(1, player);
		}
	}
	
    @Override
    public String getTextureFile() {
    	return ModInfo.ITEM_TEXTURE_LOCATION;
    }
	
}
