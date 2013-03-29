package com.dmillerw.brainFuckBlocks.block;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWire extends BlockContainer {

	public BlockWire(int id) {
		super(id, Material.cloth);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public int getRenderType() {
		return BrainFuckBlocks.wireRenderID;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
