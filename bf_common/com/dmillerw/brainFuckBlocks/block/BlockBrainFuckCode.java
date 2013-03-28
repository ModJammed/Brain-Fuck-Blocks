package com.dmillerw.brainFuckBlocks.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;

public class BlockBrainFuckCode extends BlockContainer {

	public static String[] blockNames = new String[] {"Increment Pointer", "Decrement Pointer", "Increment Byte", "Decrement Byte", "Output Byte", "Input Byte", "Bracket Open", "Bracket Close"};
	
	protected BlockBrainFuckCode(int id) {
		super(id, Material.rock);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}

	//TODO Textures
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return null;
	}
	
	/* IGNORE */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
