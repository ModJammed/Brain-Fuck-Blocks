package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRSInterpreter;

public class BlockPeripheralRedstone extends BlockContainer {

	public BlockPeripheralRedstone(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		TileEntityRSInterpreter periph = (TileEntityRSInterpreter) world.getBlockTileEntity(x, y, z);
		return periph.rsOutput;
	}
	
	public static void updateSurroundingBlocks(World world, int x, int y, int z) {
    	world.notifyBlocksOfNeighborChange(x, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x - 1, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x + 1, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y, z - 1, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y, z + 1, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y - 1, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, BlockIDs.bfPeripheralRedstoneID);
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityRSInterpreter();
	}
	
}
