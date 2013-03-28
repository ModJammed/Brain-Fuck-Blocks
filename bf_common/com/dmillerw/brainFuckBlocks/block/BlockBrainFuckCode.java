package com.dmillerw.brainFuckBlocks.block;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrainFuckCode extends BlockContainer {

	protected BlockBrainFuckCode(int id) {
		super(id, Material.rock);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}

	//TODO Textures
	
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
